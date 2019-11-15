package io.github.feiyizhan.server;

import io.github.feiyizhan.handler.DiscardServerHandler;
import io.github.feiyizhan.handler.LogHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

/**
 * 不做任何响应的服务器
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class DiscardServer extends BaseServer {

    public DiscardServer(int port) {
        super(port);
    }

    /**
     * 设置 option
     * @author 徐明龙 XuMingLong 2019-11-15
     * @return void
     */
    @Override public void setOption() {
        super.setOption();
        //设置TCP协议的syns queue的最大值
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
    }

    /**
     * 设置 ChildOption
     * @author 徐明龙 XuMingLong 2019-11-15
     * @return void
     */
    @Override public void setChildOption() {
        super.setChildOption();
        //开启TCP协议的心跳检测
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

    }

    /**
     * 设置ChildHandler
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return io.netty.channel.ChannelInitializer<T>
     */
    @Override public void setChildHandler() {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            public void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(
                    new LogHandler(),
                    new DiscardServerHandler()
                );
            }
        });

    }
}
