package part3.Client.client.impl;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 14:16
 *@Description:version1
 *@version:1.0
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import part3.Client.client.RpcClient;
import part3.Client.netty.nettyInitializer.NettyClientInitializer;
import part3.Client.serviceCenter.ServiceCenter;
import part3.Client.serviceCenter.ZKServiceCenter;
import part3.common.loadbalance.impl.ConsistencyHashBalance;
import part3.common.message.RpcRequest;
import part3.common.message.RpcResponse;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Slf4j
public class NettyRpcClient implements RpcClient {
    private ServiceCenter serviceCenter;
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    public NettyRpcClient(){
        serviceCenter = new ZKServiceCenter(new ConsistencyHashBalance());
    }

    //netty客户端初始化
    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                //NettyClientInitializer这里 配置netty对消息的处理机制
                .handler(new NettyClientInitializer());
    }
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            String serviceName = request.getInterfaceName();
            //创建一个channelFuture对象，代表这一个操作事件，sync方法表示堵塞直到connect完成
            InetSocketAddress address = serviceCenter.serviceDiscovery(serviceName);
            ChannelFuture channelFuture  = bootstrap.connect(address).sync();
            //channel表示一个连接的单位，类似socket
            Channel channel = channelFuture.channel();
            // 发送数据
            channel.writeAndFlush(request);
            //sync()堵塞获取结果
            channel.closeFuture().sync();
            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在hanlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 当前场景下选择堵塞获取结果
            // 其它场景也可以选择添加监听器的方式来异步获取结果 channelFuture.addListener...
            log.info("channel is closed...");
            AttributeKey<RpcResponse> key = AttributeKey.valueOf("RpcResponse");
            RpcResponse response = channel.attr(key).get();
            log.info("the response is received...");
            log.info(response.toString());
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}