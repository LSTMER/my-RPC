package part1.Server;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:55
 *@Description:version1
 *@version:1.0
 */

import part1.Server.provider.ServiceProvider;
import part1.Server.server.impl.SimpleRpcServer;
import part1.common.service.impl.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        ServiceProvider provider = new ServiceProvider();
        provider.registerService(new UserServiceImpl());

        SimpleRpcServer server = new SimpleRpcServer(provider);
        server.start(8888);
    }
}
