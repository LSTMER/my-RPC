package com.hitsz.simon.Server.serviceRegister;/*
 *@Author:Simon
 *@Date: 2025-09-27 - 2025 09 27 9:28
 *@Description:version1
 *@version:1.0
 */

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress address);
}
