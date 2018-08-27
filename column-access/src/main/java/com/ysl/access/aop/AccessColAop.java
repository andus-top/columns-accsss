package com.ysl.access.aop;

import com.ysl.access.criteria.SysAccessCriteria;
import com.ysl.access.service.SysAccess;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 错误示范，放弃使用
 */

/**
 * 获取权限列的切面
 * @table
 * @autor YSL
 * 2018-08-22 16:15
 */
//@Aspect// 声明为一个切面
@Order(1)// 指定切面的优先级, 值越小优先级越高
@Component// 放入ioc容器
public class AccessColAop {
    @Autowired
    private SysAccess sysAccessMapper;
    //切入点表达式可以通过操作符 &&, ||, ! 结合起来
// 如：@Before("execution(* com.ysl.spring.aop.aspectj_annotation.*.add(int, int)) || execution(* com.ysl.spring.aop.aspectj_annotation.*.div(int, int))")

    /**
     * 定义一个申明切入点表达式的方法
     */
    @Pointcut("execution(* com.ysl.access.dao.*.get*(..))")
    public void declareJoinPoint(){}

    // 前置通知， 目标方法执行前执行该方法
    @Before("declareJoinPoint()")
    // JoinPoint 类型的参数, 从中可以访问到方法的签名和方法的参数.
    public void beforeMethod(JoinPoint joinPoint){

        SysAccessCriteria result = null;

        System.out.println("类名："+joinPoint.getClass());

        String methodName = joinPoint.getSignature().getName();
        System.out.println("切面方法名称： " + methodName);

        Object [] args = joinPoint.getArgs();
        int flag = -1;

        for (int i=0; i<args.length; i++) {
            if(args[i] instanceof SysAccessCriteria){
                SysAccessCriteria sysAccessCriteria = (SysAccessCriteria) args[i];
                result = sysAccessMapper.getUserAceess(sysAccessCriteria);
                flag = i;
            }
        }
        // 由于在Spring源码中存放参数的对象用final修饰符修饰了，所以这里修改参数无效
        if(result != null && flag >= 0){
            args[flag] = result;
        }
        // 由于在Spring源码中存放参数的对象用final修饰符修饰了，所以这里修改参数无效

    }

    // 后置通知，
    // 目标方法执行后执行该方法(无论是否发生异常)
    // 不能访问目标方法返回的结果
    @After("declareJoinPoint()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

    // 返回通知
    // 方法正常执行后执行的方法
    @AfterReturning(value = "declareJoinPoint()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends. Return value " + result);
    }

    // 异常通知 ,在目标方法出异常后执行
    // 如果入参ex的类型是NullPointerException，而实际出的是ArithmeticException ,也不会执行。
    // 也说明该方法可以指定在出特定异常时执行
    @AfterThrowing(value = "declareJoinPoint()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " throw exception" + ex.getMessage());
    }
}


