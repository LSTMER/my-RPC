package part1.client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:36
 *@Description:version1
 *@version:1.0
 */

import part1.client.proxy.ClientProxy;
import part1.common.pojo.User;
import part1.common.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        // 创建ClientProxy，处理远程方法调用
        // 代理工厂
        ClientProxy clientProxy = new ClientProxy("localhost", 8888);

        // 动态生成UserService接口的代理对象
        // 传入要代理的对象，并生成对应的代理对象
        UserService userService = clientProxy.getProxy(UserService.class);

        // 调用方法，触发ClientProxy的invoke方法
        User user = userService.getUserById(1);
        System.out.println(user);
    }
}
