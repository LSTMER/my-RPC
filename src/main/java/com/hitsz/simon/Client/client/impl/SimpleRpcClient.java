package com.hitsz.simon.Client.client.impl;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 14:14
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Client.client.RpcClient;
import com.hitsz.simon.common.message.RpcRequest;
import com.hitsz.simon.common.message.RpcResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRpcClient implements RpcClient {
    private String host;
    private int port;

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try(Socket socket = new Socket(host, port);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ){
            oos.writeObject(request);
            oos.flush();

            return (RpcResponse) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            RpcResponse.fail();
            return null;
        }
    }
}
