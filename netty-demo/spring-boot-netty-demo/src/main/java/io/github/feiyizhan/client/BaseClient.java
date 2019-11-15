package io.github.feiyizhan.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 基础服务
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public abstract class BaseClient<T extends Channel> {
    protected EventLoopGroup work = new NioEventLoopGroup();
    private int port;
    private String host;
    protected Bootstrap bootstrap ;

    public BaseClient(String host,int port){
        this.host = host;
        this.port = port;
        this.bootstrap = new Bootstrap();
    }

    /**
     * 获取ChannelInitializer
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return io.netty.channel.ChannelInitializer<T>
     */
    public abstract ChannelInitializer<T> getChannelInitializer();

    /**
     * 启动
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return void
     */
    public void start() {
        try{
            bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .handler(getChannelInitializer());
            //绑定端口
            ChannelFuture cf = bootstrap.connect(host,port).sync();
            if(cf.isSuccess()){
                System.out.println("连接服务器成功");
            }
            cf.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            work.shutdownGracefully();
        }

    }

    /**
     * 销毁
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return void
     */
    public void destroy(){
        work.shutdownGracefully();
    }
}
