package io.github.feiyizhan.server;

import io.github.feiyizhan.handler.TimeServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 时间服务器
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class NettyTimeServer extends BaseServer {


    /**
     * 构建一个NettyTimeServer
     * @author 徐明龙 XuMingLong 2019-11-14
     * @param port
     * @return
     */
    public NettyTimeServer(int port) {
        super(port);
    }

    /**
     * 设置ChildHandler
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return io.netty.channel.ChannelInitializer<T>
     */
    @Override public void setChildHandler() {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            public void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new TimeServerHandler());
            }

        });

    }
}
