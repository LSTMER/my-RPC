package com.hitsz.simon.Server.provider;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:56
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Server.serviceRegister.ServiceRegister;
import com.hitsz.simon.Server.serviceRegister.ZKServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private String host;
    private int port;
    private Map<String, Object> interfaceProvider;
    private ServiceRegister serviceRegister;
    public ServiceProvider(String host, int port){
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        this.serviceRegister = new ZKServiceRegister();
    }

    public void registerService(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for(Class<?> clazz:interfaces){
            interfaceProvider.put(clazz.getName(), service);
            serviceRegister.register(clazz.getName(), new InetSocketAddress(host, port));
        }
    }

    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
