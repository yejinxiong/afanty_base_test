package com.afanty.base.test.common.aop;

import com.afanty.base.test.common.annotation.ApiIdempotent;
import com.afanty.base.test.common.utils.JsonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        ApiIdempotent apiIdempotent = this.getApiIdempotent(joinPoint);
        if (Objects.nonNull(apiIdempotent)) {
            LOGGER.info("ApiIdempotentAop running：{}", ((MethodSignature) signature).getMethod());
            String method = apiIdempotent.method();
            String paramStr = apiIdempotent.param();
            Map<String, Object> paramMap;
            if (StringUtils.isBlank(paramStr)) {
                LOGGER.error("ApiIdempotent param不能为空");
                return false;
            } else if (!JsonUtil.isJsonStr(paramStr)) {
                LOGGER.error("ApiIdempotent param必须为json格式");
                return false;
            } else {
                paramMap = JSONObject.parseObject(paramStr);
            }
            if (StringUtils.isBlank(method)) {
                LOGGER.error("ApiIdempotent method不能为空");
                return false;
            } else {
                List list = this.invokeService(apiIdempotent, paramMap);


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
//            try {
//                Method method = targetClass.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
//                apiIdempotent = method.getAnnotation(ApiIdempotent.class);
//            } catch (NoSuchMethodException e) {
//                LOGGER.error("获取幂等性接口注解异常：{}", e.getMessage());
//            }
            Method method = methodSignature.getMethod();
            apiIdempotent = method.getAnnotation(ApiIdempotent.class);
        }
        return apiIdempotent;
    }

    /**
     * 调用service方法
     * @param apiIdempotent
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List invokeService(ApiIdempotent apiIdempotent, Map<String, Object> paramMap) {
        List list = new ArrayList();
        try {
            Class serviceClass = apiIdempotent.serviceClass();
            // 获取指定的方法
            String method = apiIdempotent.method();
            Method declaredMethod = serviceClass.getDeclaredMethod(method, Map.class);
            // 获取service对象
            Object servicObj = serviceClass.newInstance();
            Object invoke = declaredMethod.invoke(servicObj, paramMap);
            System.out.println(invoke);
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException：{}", e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.error("InstantiationException：{}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException：{}", e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException：{}", e.getMessage());
        }
        return list;
    }

    /**
     * aop的顺序要早于spring的事务
     */
    @Override
    public int getOrder() {
        return 1;
    }

}
