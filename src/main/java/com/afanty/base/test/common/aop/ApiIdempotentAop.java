package com.afanty.base.test.common.aop;

import com.afanty.base.test.common.annotation.ApiIdempotent;
import com.afanty.base.test.common.utils.JsonUtil;
import com.afanty.base.test.common.utils.SpringUtil;
import com.afanty.base.test.common.web.MsgCode;
import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 接口幂等性注解的AOP
 * <p>
 * 执行顺序：@Around→@Before→Method→@Around→@After→@AfterReturning
 *
 * @Author yejx
 * @Date 2022/1/21
 */
@Aspect
@Component
public class ApiIdempotentAop implements Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiIdempotentAop.class);

    /**
     * 定义切面，注解了“@ApiIdempotent”的方法
     */
    @Pointcut(value = "@annotation(com.afanty.base.test.common.annotation.ApiIdempotent)")
    private void cut() {
    }

    /**
     * 执行前的切入操作
     */
    @Before("cut()")
    public void doBefore(JoinPoint joinPoint) {
        LOGGER.info("ApiIdempotentAop starting...");
    }

    /**
     * 执行中的切入操作
     *
     * @param joinPoint
     * @return
     */
    @SuppressWarnings("unchecked")
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        ApiIdempotent apiIdempotent = this.getApiIdempotent(joinPoint);
        if (Objects.nonNull(apiIdempotent)) {
            LOGGER.info("ApiIdempotentAop running：{}", methodSignature.getMethod());
            // 获取幂等注解需要的字段，并从接口参数获取对应的值
            Map<String, Object> paramMap = new HashMap<>();
            String fields = apiIdempotent.fields();
            String[] names = fields.split(",");
            Object[] args = joinPoint.getArgs();
            // 根据字段名获取字段值
            for (String name : names) {

            }
            if (!paramMap.isEmpty()) {
                // 调用对应的service方法
                int count= this.invokeService(apiIdempotent, paramMap);
                if (count > 0) {
                    LOGGER.error("ApiIdempotentAop：请勿重复操作");
                    return new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), "请勿重复操作", null);
                } else {
                    LOGGER.info("ApiIdempotentAop：允许操作");
                }
            }
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("ApiIdempotentAop Exception：{}", throwable.getMessage());
            throw throwable;
        }
    }

    /**
     * 执行完后的切入操作
     *
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "cut()")
    public void doAfterReturning(Object result) {
        LOGGER.info("ApiIdempotentAop over: {}", JSONObject.toJSONString(result));
    }

    /**
     * 获取方法上的注解
     *
     * @param joinPoint
     * @return
     */
    public ApiIdempotent getApiIdempotent(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 1. 先从类上面获取该注解
        Object target = joinPoint.getTarget();
        Class<?> targetClass = target.getClass();
        ApiIdempotent apiIdempotent = targetClass.getAnnotation(ApiIdempotent.class);
        // 2. 如果类上面没有这个注解，再从方法上获取
        if (Objects.isNull(apiIdempotent)) {
//            // 获取方法上的注解，方案一
//            try {
//                Method method = targetClass.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
//                apiIdempotent = method.getAnnotation(ApiIdempotent.class);
//            } catch (NoSuchMethodException e) {
//                LOGGER.error("获取幂等性接口注解异常：{}", e.getMessage());
//            }
            // 获取方法上的注解，方案二
            Method method = methodSignature.getMethod();
            apiIdempotent = method.getAnnotation(ApiIdempotent.class);
        }
        return apiIdempotent;
    }

    /**
     * 调用service方法
     *
     * @param apiIdempotent
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public int invokeService(ApiIdempotent apiIdempotent, Map<String, Object> paramMap) {
        int count = 0;
        try {
            // 要执行的方法所在的类
            Class serviceClass = apiIdempotent.serviceClass();
            // 要执行的方法的名称
            String methodName = apiIdempotent.methodName();
            // 通过类获取指定名称的方法
            Method method = serviceClass.getDeclaredMethod(methodName, Map.class);
            count = (int) method.invoke(SpringUtil.getBean(serviceClass), paramMap);
        } catch (NoSuchMethodException e) {
            LOGGER.error("ApiIdempotentAop invokeService() NoSuchMethodException：{}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("ApiIdempotentAop invokeService() IllegalAccessException：{}", e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error("ApiIdempotentAop invokeService() InvocationTargetException：{}", e.getMessage());
        }
        return count;
    }

    /**
     * aop的顺序要早于spring的事务
     */
    @Override
    public int getOrder() {
        return 1;
    }

}
