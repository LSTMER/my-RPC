package part2.Client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:36
 *@Description:version1
 *@version:1.0
 */

import part2.Client.proxy.ClientProxy;
import part2.common.pojo.User;
import part2.common.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        ClientProxy clientProxy=new ClientProxy("127.0.0.1",8888,0);
        UserService proxy=clientProxy.getProxy(UserService.class);

        User user = proxy.getUserById(1);
        System.out.println("从服务端得到的user="+user.toString());

    }
}