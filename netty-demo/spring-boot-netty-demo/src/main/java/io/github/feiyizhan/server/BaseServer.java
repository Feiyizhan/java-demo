package io.github.feiyizhan.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 基础服务
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public abstract class BaseServer<T extends Channel> {
    protected EventLoopGroup boss = new NioEventLoopGroup();
    protected EventLoopGroup work = new NioEventLoopGroup();
    private int port;
    protected ServerBootstrap bootstrap ;
    public BaseServer(int port){
        this.port = port;
        this.bootstrap = new ServerBootstrap();
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
        //创建ServerBootstrap实例来引导绑定和启动服务器
        try{
            bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                //定义链接处理器
                .childHandler(getChannelInitializer());
            //异步启动并绑定指定端口
            ChannelFuture cf = bootstrap.bind(port).sync();
            if (cf.isSuccess()){
                System.out.println("启动成功");
            }
            //等待服务被并关闭
            cf.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            destroy();
        }

    }

    /**
     * 销毁
     * @author 徐明龙 XuMingLong 2019-11-14
     * @return void
     */
    public void destroy(){
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }
}
