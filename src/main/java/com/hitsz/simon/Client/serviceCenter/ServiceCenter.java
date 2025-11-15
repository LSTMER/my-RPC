package com.hitsz.simon.Client.serviceCenter;/*
 *@Author:Simon
 *@Date: 2025-09-27 - 2025 09 27 9:04
 *@Description:version1
 *@version:1.0
 */

import java.net.InetSocketAddress;

public interface ServiceCenter {
    InetSocketAddress serviceDiscovery(String serviceName);
}
