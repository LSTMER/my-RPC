package part3.Server.server.impl;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 10:02
 *@Description:version1
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import part3.Server.provider.ServiceProvider;
import part3.Server.server.RpcServer;
import part3.Server.server.worker.WorkerThread;

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
