package part3.Client.cache;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:41
 *@Description:my-RPC
 *@version:1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCache {
    private final Map<String, List<String>> servicesCache = new HashMap<>();

    public void addService(String serviceName, List<String> address){
        List<String> oldAddress = servicesCache.computeIfAbsent(serviceName, k->new ArrayList<>());
        oldAddress.addAll(address);
    }

    public void replaceService(String serviceName, String oldAddress, String newAddress){
        if(servicesCache.containsKey(serviceName)){
            List<String> addresses = servicesCache.get(serviceName);
            addresses.remove(oldAddress);
            addresses.add(newAddress);
        }
    }

    public List<String> getServices(String serviceName){
        return servicesCache.get(serviceName);
    }

    public void delete(String serviceName, String address){
        servicesCache.get(serviceName).remove(address);
    }

    public void removeAll(String serviceName) {
        servicesCache.get(serviceName).clear();
    }
}
