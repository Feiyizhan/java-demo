package io.github.feiyizhan.handler;

/**
 * @author 徐明龙 XuMingLong 2019-11-13
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.RandomUtils;

public class ServerUAVHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String responseStr = "no";
        if(RandomUtils.nextInt(0,10) == 5){
            responseStr = "yes";
        }
        ByteBuf resp = Unpooled.copiedBuffer(responseStr.getBytes());
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
}
