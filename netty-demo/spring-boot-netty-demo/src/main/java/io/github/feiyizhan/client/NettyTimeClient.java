package io.github.feiyizhan.client;

import io.github.feiyizhan.handler.TimeClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * NettyTimeClient
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class NettyTimeClient extends BaseClient<SocketChannel> {
    /**
     * 构建一个 NettyTimeClient
     * @author 徐明龙 XuMingLong 2019-11-14
     * @param host
     * @param port
     * @return
     */
    public NettyTimeClient(String host, int port) {
        super(host, port);
    }

    /**
     * 获取ChannelInitializer
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return io.netty.channel.ChannelInitializer<T>
     */
    @Override public ChannelInitializer<SocketChannel> getChannelInitializer() {
        return new ChannelInitializer<SocketChannel>() {

            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast( new TimeClientHandler()
                );
            }
        };
    }
}
