package com.ysl.access.proxy;

import com.ysl.access.criteria.SysAccessCriteria;
import com.ysl.access.service.SysAccess;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现MethodInterceptor接口，充当AbstractAutoProxyCreator自动生成的代理对象的拦截器
 * @autor: YSL
 * 2018-08-24 09:43
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Autowired
    private SysAccess sysAccess;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        // 权限封装类
        SysAccessCriteria result = null;
        int flag = -1;

        // 目标方法的参数
        Object[] args = invocation.getArguments();
        for (int i=0; i<args.length; i++) {
            // 只修改权限条件类型的参数
            if(args[i] instanceof SysAccessCriteria){
                SysAccessCriteria sysAccessCriteria = (SysAccessCriteria) args[i];
                result = sysAccess.getUserAceess(sysAccessCriteria);
                flag = i;
            }
        }
        // 修改目标参数
        if(flag >= 0 && result != null){
            args[flag] = result;
        }

        // 执行目标方法
        Object object = invocation.proceed();

        return object;
    }
}
