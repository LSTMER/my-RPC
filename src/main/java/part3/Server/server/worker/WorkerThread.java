package part3.Server.server.worker;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 10:06
 *@Description:version1
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import part3.Server.provider.ServiceProvider;
import part3.common.message.RpcRequest;
import part3.common.message.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
@AllArgsConstructor
@Slf4j
public class WorkerThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;
    @Override
    public void run() {
        log.info("a working thread start!");
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            //读取客户端传过来的request
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            log.info("a rpcRequest receive...");
            log.info(rpcRequest.toString());
            //反射调用服务方法获取返回值
            RpcResponse rpcResponse=getResponse(rpcRequest);
            //向客户端写入response
            oos.writeObject(rpcResponse);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private RpcResponse getResponse(RpcRequest request){
        Object service = serviceProvider.getService(request.getInterfaceName());
        log.info("get service from serviceProvider...");
        Method method = null;
        try{
            log.info("get info from rpcRequest to invoke matched method...");
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsType());
            Object result = method.invoke(service, request.getParams());
            return RpcResponse.success(result);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("method run exception");
            return RpcResponse.fail();
        }
    }
}
