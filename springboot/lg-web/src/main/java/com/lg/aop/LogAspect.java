package com.lg.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author liuga
 * @date 2023/02/09 14:27
 * Description:
 * @Aspect 表明是一个切面类
 * @Component 将当前类注入到Spring容器内
 * @Pointcut 切入点，其中execution用于使用切面的连接点。使用方法：execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选)) ，可以使用通配符匹配字符，*可以匹配任意字符。
 * @Before 在方法前执行
 * @After 在方法后执行
 * @AfterReturning 在方法执行后返回一个结果后执行
 * @AfterThrowing 在方法执行过程中抛出异常的时候执行
 * @Around 环绕通知，就是可以在执行前后都使用，这个方法参数必须为ProceedingJoinPoint，proceed()方法就是被切面的方法，上面四个方法可以使用JoinPoint，JoinPoint包含了类名，被切面的方法名，参数等信息。
 */

@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public * com.lg.controller.*.*(..))")
    public void logAspect(){}

    @Before("logAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("doBefore");
    }

    @After("logAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter");
    }

    @AfterReturning("logAspect()")
    public void doAfterReturning(JoinPoint joinPoint){
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("logAspect()")
    public void deAfterThrowing(JoinPoint joinPoint){
        System.out.println("deAfterThrowing");
    }

    @Around("logAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("deAround");
        return joinPoint.proceed();
    }

}
