package io.github.feiyizhan.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 徐明龙 XuMingLong 2020-07-22
 */
@Aspect
@Component
public class TransactionAop {

    /**
     * 定义切点
     * @author 徐明龙 XuMingLong 2020-07-22
     * @return void
     */
    @Pointcut("execution(* io.github.feiyizhan..service.*.*(..))")
    public void pointcut() {
    }

    /**
     * 方法执行前
     * @author 徐明龙 XuMingLong 2020-07-22
     * @return void
     */
    @Before("pointcut()")
    public void beginTransaction(JoinPoint joinPoint) {
        System.out.println("方法AOP-before beginTransaction");
        System.out.println("方法AOP-before ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法执行完毕
     * @author 徐明龙 XuMingLong 2020-07-22
     * @return void
     */
    @After("pointcut()")
    public void commit(JoinPoint joinPoint) {
        System.out.println("方法AOP-after commit");
    }

    /**
     * 方法执行完毕并正常返回
     * @author 徐明龙 XuMingLong 2020-07-22
     * @param joinPoint
     * @param returnObject
     * @return void
     */
    @AfterReturning(pointcut="pointcut()", returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
        System.out.println("方法AOP-afterReturning");
    }

    /**
     * 方法执行异常
     * @author 徐明龙 XuMingLong 2020-07-22
     * @return void
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("方法AOP-afterThrowing afterThrowing  rollback");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("方法AOP-around");
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("方法AOP-around");
        }
    }
}
