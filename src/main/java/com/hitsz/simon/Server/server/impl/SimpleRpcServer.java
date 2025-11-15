package com.hitsz.simon.Server.server.impl;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 10:02
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.Server.provider.ServiceProvider;
import com.hitsz.simon.Server.server.RpcServer;
import com.hitsz.simon.Server.server.worker.WorkerThread;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
@Slf4j
@AllArgsConstructor
public class SimpleRpcServer implements RpcServer {
    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        try(
        ServerSocket serverSocket = new ServerSocket(port);
        ){
            System.out.println("server starting");
            while(true){
                Socket socket = serverSocket.accept();
                log.info("a new connect accept!");
                new Thread(new WorkerThread(socket, serviceProvider)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
