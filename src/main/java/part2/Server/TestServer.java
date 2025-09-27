package part2.Server;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:55
 *@Description:version1
 *@version:1.0
 */

import part2.Server.provider.ServiceProvider;
import part2.Server.server.RpcServer;
import part2.Server.server.impl.NettyRpcServer;
import part2.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        ServiceProvider provider = new ServiceProvider();
        provider.registerService(new UserServiceImpl());

        RpcServer server = new NettyRpcServer(provider);
        server.start(8888);
    }
}
