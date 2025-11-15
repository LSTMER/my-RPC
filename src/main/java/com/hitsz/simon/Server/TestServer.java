package com.hitsz.simon.Server;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:55
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Server.provider.ServiceProvider;
import com.hitsz.simon.Server.server.RpcServer;
import com.hitsz.simon.Server.server.impl.NettyRpcServer;
import com.hitsz.simon.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        ServiceProvider provider = new ServiceProvider("127.0.0.1",8888);
        provider.registerService(new UserServiceImpl());

        RpcServer server = new NettyRpcServer(provider);
        server.start(8888);
    }
}
