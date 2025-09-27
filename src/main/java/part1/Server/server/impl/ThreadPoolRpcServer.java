package part1.Server.server.impl;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 12:21
 *@Description:version1
 *@version:1.0
 */

import part2.Server.provider.ServiceProvider;
import part2.Server.server.RpcServer;
import part2.Server.server.worker.WorkerThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRpcServer implements RpcServer {
    private final ThreadPoolExecutor threadPool;
    private ServiceProvider serviceProvider;
    public ThreadPoolRpcServer(ServiceProvider serviceProvider) {
        threadPool=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
        this.serviceProvider= serviceProvider;
    }

    @Override
    public void start(int port) {
        try(
        ServerSocket serverSocket = new ServerSocket(port);
        ){
            while(true){
                Socket socket = serverSocket.accept();
                threadPool.submit(new WorkerThread(socket, serviceProvider));
            }
        }catch(Exception e){

        }
    }

    @Override
    public void stop() {

    }
}
