package com.spring.aop;

import org.springframework.jmx.access.InvocationFailureException;

public interface Interceptor {
    /**
     * 事前方法
     * @return
     */
    public boolean before();

    /**
     * 事后方法
     */
    public void after();

    /**
     * 取代原有事件方法
     * @param invocation -- 回调参数, 可以通过它的 processed 方法, 回调原有事件
     * @return 原有事件返回对象
     * @throws InvocationFailureException
     * @throws IllegalAccessException
     */
    public Object around(Invocation invocation) throws Throwable;

    /**
     * 是否有返回方法, 事件没有发生异常执行
     */
    public void afterReturning();

    /**
     * 事后异常方法, 当事件发生异常后执行
     */
    public void afterThrowing();

    /**
     * 是否使用 around 方法取代原有方法
     * @return
     */
    boolean userAround();
}
