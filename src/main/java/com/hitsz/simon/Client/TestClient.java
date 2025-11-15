package com.hitsz.simon.Client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:36
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Client.proxy.ClientProxy;
import com.hitsz.simon.common.pojo.User;
import com.hitsz.simon.common.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        ClientProxy clientProxy=new ClientProxy();
        UserService proxy=clientProxy.getProxy(UserService.class);

        User user = proxy.getUserById(1);
        System.out.println("从服务端得到的user="+user.toString());

    }
}