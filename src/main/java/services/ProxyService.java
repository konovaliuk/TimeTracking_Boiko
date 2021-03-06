package services;


import services.interfaces.Service;
import utils.TransactionalInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Mirosha
 */
public class ProxyService<T> implements Service {
    private Object proxy;

    public ProxyService(Object object, Class clazz) {
        InvocationHandler invocationHandler = new TransactionalInvocationHandler(object);
        proxy = Proxy.newProxyInstance(Service.class.getClassLoader(), clazz.getInterfaces(), invocationHandler);
    }

    public T getProxy() {
        return (T) proxy;
    }
}
