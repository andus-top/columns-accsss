package com.ysl.access.aop;


import com.ysl.access.criteria.SysAccessCriteria;
import com.ysl.access.dao.BaseMapper;
import com.ysl.access.service.SysAccess;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 错误示范，放弃使用
 */

/**
 * 权限列代理
 * @table
 * @autor YSL
 * 2018-08-22 17:55
 */
public class MyAccessColLoggingProxy {

    @Autowired
    private SysAccess sysAccessMapper;

    // 要代理的对象
    private BaseMapper target;

    public MyAccessColLoggingProxy(BaseMapper target) {
        super();
        this.target = target;
    }

    //返回代理对象
    public BaseMapper getMyProxy(){
        BaseMapper proxy = null;

        // 代理对象由哪个加载器加载
        ClassLoader loader = target.getClass().getClassLoader();
        // 代理对象的类型
        Class [] interfaces = new Class[]{BaseMapper.class};
        // 当调用代理对象其中的方法时，应该执行的代码
        InvocationHandler h = new InvocationHandler() {
            /**
             * proxy: 代理对象。 一般不使用该对象, 调用该对象的方法的话容易产生死循环
             * method: 正在被调用的方法
             * args: 调用方法传入的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                String methodName = method.getName();

                Object result = null;

                try {
                    //前置通知，//在这里可以修改目标方法参数
                    System.out.println("[before] The method " + methodName + " begins with " + Arrays.asList(args));

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


                    //调用目标方法
                    result = method.invoke(target, args);

                    //返回通知, 可以访问到方法的返回值
                    System.out.println("Return value " + result);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    //异常通知, 可以访问到方法出现的异常
                    System.out.println("[ception] The method " + methodName + " throw exception" + e.getMessage());
                }

                //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
                System.out.println("[after] The method"+ methodName + "ends with " + result);

                return result;
            }
        };

        /**
         * loader: 代理对象使用的类加载器。
         * interfaces: 指定代理对象的类型. 即代理对象中可以有哪些方法.
         * h: 当具体调用代理对象的方法时, 应该如何进行响应, 实际上就是调用 InvocationHandler 的 invoke 方法
         */
        proxy = (BaseMapper) Proxy.newProxyInstance(loader, interfaces, h);

        return proxy;
    }
}
