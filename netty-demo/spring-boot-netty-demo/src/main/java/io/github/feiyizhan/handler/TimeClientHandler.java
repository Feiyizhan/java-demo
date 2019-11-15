package io.github.feiyizhan.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间服务器-客户端处理
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // (1)
        try {
            long seconds = m.readLong() ;
            long nano = m.readLong();
            Instant now = Instant.ofEpochSecond(seconds,nano);
            System.out.println(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS")
                .format(LocalDateTime.ofInstant(now,ZoneId.of("UTC+8")))
            );
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
