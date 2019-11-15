package io.github.feiyizhan.beans;

/**
 * @author 徐明龙 XuMingLong 2019-11-13
 */

import io.github.feiyizhan.handler.CounterHandler;
import io.github.feiyizhan.handler.LogHandler;
import io.github.feiyizhan.handler.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class NettyClient {
    private SocketChannel socketChannel;
    EventLoopGroup group = new NioEventLoopGroup();
//    @PostConstruct
    public void start() throws Exception {
        //boss 线程组用于处理连接工作
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                 .channel(NioSocketChannel.class)
                //关闭 TCP_IP Nagle算法
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(
                            new LogHandler(),
                            new CounterHandler("client"),
                            new TimeClientHandler()
//                            new ClientHandler(),
//                            new EnterHandler()

                        )

//                            .addLast(new CounterHandler("client"),new ClientHandler())
                        ;
                    }
                });
            //绑定端口
            ChannelFuture cf = b.connect("127.0.0.1",8888).sync();
            if(cf.isSuccess()){
                System.out.println("连接服务器成功");
            }
            cf.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully();
        }
    }
}
