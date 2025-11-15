package com.hitsz.simon.Client.proxy;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:26
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Client.client.RpcClient;
import com.hitsz.simon.Client.client.impl.NettyRpcClient;
import com.hitsz.simon.Client.client.impl.SimpleRpcClient;
import com.hitsz.simon.common.message.RpcRequest;
import com.hitsz.simon.common.message.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@AllArgsConstructor
@Slf4j
public class ClientProxy implements InvocationHandler {
    //传入参数service接口的class对象，反射封装成一个request

    private RpcClient rpcClient;
    public ClientProxy(String host,int port,int choose){
        switch (choose){
            case 0:
                rpcClient=new NettyRpcClient();
                break;
            case 1:
                rpcClient=new SimpleRpcClient(host,port);
        }
    }
    public ClientProxy(){
        rpcClient=new NettyRpcClient();
    }
    //jdk动态代理，每一次代理对象调用方法，都会经过此方法增强（反射获取request对象，socket发送到服务端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //构建request
        RpcRequest request= RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramsType(method.getParameterTypes()).build();
        //数据传输
        log.info("proxy method is invoking...");
        RpcResponse response= rpcClient.sendRequest(request);
        return response.getData();
    }
    public <T>T getProxy(Class<T> clazz){
        log.info("proxy got...");
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}

