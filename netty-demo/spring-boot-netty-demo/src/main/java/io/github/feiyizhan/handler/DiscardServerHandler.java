package io.github.feiyizhan.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 不做任何响应的处理器
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Discard the received data silently.
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("当前读索引的位置："+buf.readerIndex());
        System.out.println("当前写索引的位置："+buf.writerIndex());
        buf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
