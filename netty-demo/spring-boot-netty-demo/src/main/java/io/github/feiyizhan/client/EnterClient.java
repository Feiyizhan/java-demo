package io.github.feiyizhan.client;

import io.github.feiyizhan.handler.EnterHandler;
import io.github.feiyizhan.handler.LogHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class EnterClient  extends BaseClient<SocketChannel> {

    public EnterClient(String host, int port) {
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
                ch.pipeline().addLast(
                    new LogHandler(),
                    new EnterHandler()
                );
            }
        };
    }
}
