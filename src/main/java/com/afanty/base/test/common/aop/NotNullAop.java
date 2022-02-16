package com.afanty.base.test.common.aop;

import com.afanty.base.test.common.annotation.NotNull;
import com.afanty.base.test.common.web.domain.MsgCode;
import com.afanty.base.test.common.web.domain.ResponseResult;
import com.afanty.base.test.common.web.domain.StatusCode;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 判断接口入参是否为空注解@NotNull的AOP
 *
 * @Author yejx
 * @Date 2022/1/24
 */
@Aspect
@Component
public class NotNullAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotNullAop.class);

    /**
     * 定义切面，注解了“@NotNull”的方法
     */
    @Pointcut(value = "@annotation(com.afanty.base.test.common.annotation.NotNull)")
    private void cut() {
    }

    /**
     * 执行前的切入操作
     */
    @Before("cut()")
    public void doBefore(JoinPoint joinPoint) {
        LOGGER.info("NotNullAop starting...");
    }

    /**
     * 执行中的切入操作
     * <p>
     * 通过@NotNull注解指定要进行非空判断的字段，
     * 需要解决如何将注解所标注的方法的参数赋值给指定的字段
     *
     * @param joinPoint 入参
     * @return
     */
    @SuppressWarnings("unchecked")
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        LOGGER.info("NotNullAop running...");
        // 1. 判断注解是否标注在方法上
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        LOGGER.info("NotNullAop api：{}", ((MethodSignature) signature).getMethod());
        // 2. 获取方法上的注解
        NotNull notNull = this.getNotNull(joinPoint);
        if (Objects.nonNull(notNull)) {
            // 3. 获取幂等注解需要的字段
            String fields = notNull.fields();
            String[] names = fields.split(",");
            if (names.length > 0 && StringUtils.isNotBlank(names[0])) {
                // 4. 获取注解所标注的方法的参数
                Object[] args = joinPoint.getArgs();
                // 5. 获取指定参数类型
                Class clazz = notNull.entityClass();
                // 6. 筛选参数中类型为指定类型的参数
                Object object = Arrays.stream(args).filter(arg -> arg.getClass() == clazz).findFirst().orElse(Object.class);
                String jsonString = JSONObject.toJSONString(object);
                if (StringUtils.isNotBlank(jsonString) && !"{}".equals(jsonString)) {
                    Class<?> aClass = object.getClass();
                    List<String> errorList = new ArrayList<>();
                    // 7. 根据字段名获取字段值
                    for (String name : names) {
                        Field declaredField = aClass.getDeclaredField(name);
                        // 8. 设置对象的访问权限，保证对private的属性的访问
                        declaredField.setAccessible(true);
                        Object fieldValue = declaredField.get(object);
                        // 9. 收集空值字段
                        if (Objects.isNull(fieldValue)) {
                            String meaning = declaredField.getAnnotation(ApiModelProperty.class) == null ? name : declaredField.getAnnotation(ApiModelProperty.class).value();
                            errorList.add(meaning);
                        }
                    }
                    // 10. 如果有空值，则直接返回
                    if (errorList.size() > 0) {
                        String errorStr = Joiner.on("，").join(errorList);
                        LOGGER.error("NotNullAop Exception：{}", "字段【" + errorStr + "】不能为空");
                        return new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_1000.getKey(), "字段【" + errorStr + "】不能为空", null);
                    }
                } else {
                    LOGGER.error("NotNullAop Exception：{}", StatusCode.CODE_1001.getDesc());
                    return new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_1001.getKey(), StatusCode.CODE_1001.getDesc(), null);
                }
            }
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("NotNullAop Exception：{}", throwable.getMessage());
            throw throwable;
        }
    }

    /**
     * 执行完后的切入操作
     *
     * @param result 接口响应体
     */
    @AfterReturning(returning = "result", pointcut = "cut()")
    public void doAfterReturning(Object result) {
        LOGGER.info("NotNullAop result: {}", JSONObject.toJSONString(result));
    }

    /**
     * 获取方法上的注解
     *
     * @param joinPoint 参数
     * @return
     */
    public NotNull getNotNull(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        NotNull notNull = null;
        try {
            // 获取方法上的注解，方案一
            Object target = joinPoint.getTarget();
            Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
            notNull = method.getAnnotation(NotNull.class);
        } catch (NoSuchMethodException e) {
            LOGGER.error("NotNullAop 获取注解异常：{}", e.getMessage());
        }
//        // 获取方法上的注解，方案二
//        Method method = methodSignature.getMethod();
//        notNull = method.getAnnotation(NotNull.class);
        return notNull;
    }
}
