package com.hitsz.simon.Client.serviceCenter;/*
 *@Author:Simon
 *@Date: 2025-09-27 - 2025 09 27 9:06
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Client.ZKWatcher.Watcher;
import com.hitsz.simon.Client.cache.ServiceCache;
import com.hitsz.simon.common.loadbalance.LoadBalance;
import com.hitsz.simon.common.loadbalance.impl.RoundBalance;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.net.InetSocketAddress;
import java.util.List;

import com.hitsz.simon.common.Constant;



@Slf4j
public class ZKServiceCenter implements ServiceCenter {

    /*zookeeper client*/
    private final CuratorFramework client;

    private final ServiceCache cache;

    @Setter
    private LoadBalance strategy = new RoundBalance();;

    public ZKServiceCenter(){
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000).retryPolicy(policy).namespace(Constant.ROOT_PATH).build();
        this.client.start();
        log.info("zookeeper client connect successfully...");
        cache = new ServiceCache();
        Watcher watcher = new Watcher(cache, client);
        watcher.startWatching();
    }

    public ZKServiceCenter(LoadBalance lb){
        this();
        setStrategy(lb);
    }

    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {
        String host = null;
        try {
            List<String> services = cache.getServices(serviceName);
            if(services==null){
                services = client.getChildren().forPath("/"+serviceName);
            }
            cache.removeAll(serviceName);
            cache.addService(serviceName, services);
            host = strategy.balance(services);
            return parseAddress(host);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getServiceAddressString(InetSocketAddress serverAddress){
        return serverAddress.getHostName() +":"+
                serverAddress.getPort();
    }

    private InetSocketAddress parseAddress(String address){
        String[] a = address.split(":");
        return new InetSocketAddress(a[0], Integer.parseInt(a[1]));
    }
}
