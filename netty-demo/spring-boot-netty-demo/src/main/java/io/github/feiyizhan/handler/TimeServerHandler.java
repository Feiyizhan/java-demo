package io.github.feiyizhan.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.time.Instant;
import java.time.temporal.ChronoField;

/**
 * 时间服务器-服务端处理处理
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        final ByteBuf time = ctx.alloc().buffer(16);
        Instant now = Instant.now();
        long seconds = now.getLong(ChronoField.INSTANT_SECONDS);
        long nano = now.getLong(ChronoField.NANO_OF_SECOND);
        System.out.println(seconds);
        System.out.println(nano);
        time.writeLong(seconds);
        time.writeLong(nano);
        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
