package com.spring.aop;

/**
 * @author sivan
 * 拦截器
 */
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("before.........");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after...........");
    }

    @Override
    public Object around(com.spring.aop.Invocation invocation) throws Throwable {
        System.out.println("around before ........");
        Object obj = invocation.processed();
        System.out.println("around after ........");
        return obj;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning..........");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing..........");
    }

    @Override
    public boolean userAround() {
        return true;
    }
}
