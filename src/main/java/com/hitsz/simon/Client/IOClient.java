package com.hitsz.simon.Client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:19
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.common.message.RpcRequest;
import com.hitsz.simon.common.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
    public static RpcResponse sendRequest(String host, int port, RpcRequest request){
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
