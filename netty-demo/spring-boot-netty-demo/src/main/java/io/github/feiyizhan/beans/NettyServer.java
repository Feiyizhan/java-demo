package io.github.feiyizhan.beans;

/**
 * @author 徐明龙 XuMingLong 2019-11-13
 */

import io.github.feiyizhan.handler.CounterHandler;
import io.github.feiyizhan.handler.LogHandler;
import io.github.feiyizhan.handler.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {
    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup work = new NioEventLoopGroup();
//    @PostConstruct
    public void start() throws InterruptedException {
        //创建ServerBootstrap实例来引导绑定和启动服务器
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                public void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(
                        new LogHandler(),
                        new CounterHandler("server"),
                        new TimeServerHandler()
                    )
//                        new ServerUAVHandler())
                    ;
                }
            });
        ChannelFuture cf = bootstrap.bind(8888).sync();
        if (cf.isSuccess()){
            System.out.println("启动成功");
        }
    }
//    @PreDestroy
    private void destory() throws Exception{
        boss.shutdownGracefully();
        work.shutdownGracefully();
        System.out.println("关闭server");
    }
}