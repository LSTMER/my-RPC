package part1.Server.provider;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:56
 *@Description:version1
 *@version:1.0
 */

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    Map<String, Object> interfaceProvider;
    public ServiceProvider(){
        this.interfaceProvider = new HashMap<>();
    }

    public void registerService(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for(Class<?> clazz:interfaces){
            interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
