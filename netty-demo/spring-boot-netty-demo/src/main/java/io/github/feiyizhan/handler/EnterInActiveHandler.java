package io.github.feiyizhan.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Scanner;

/**
 * 连接激活就触发输入的处理器
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class EnterInActiveHandler extends ChannelHandlerAdapter {

    /**
     * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     */
    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("请输入消息:");
            String content = sc.next();

            if("exit".equalsIgnoreCase(content)){
                break;
            }
            ByteBuf resp = Unpooled.copiedBuffer(content.getBytes());
            ctx.writeAndFlush(resp);
        }while(true);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入消息:");
        String content = sc.next();
        if("exit".equalsIgnoreCase(content)){
            ctx.close();
            return;
        }
        ByteBuf resp = Unpooled.copiedBuffer(content.getBytes());
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        //让下一个ChannelHandler处理
        ctx.fireChannelRead(msg);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
