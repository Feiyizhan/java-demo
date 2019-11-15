package io.github.feiyizhan.handler;

/**
 * @author 徐明龙 XuMingLong 2019-11-13
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] req = "I love you".getBytes();
        ByteBuf first = Unpooled.buffer(req.length);
        first.writeBytes(req);
        ctx.writeAndFlush(first).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        if("no".equals(body)){
            //让下一个ChannelHandler处理
            ctx.fireChannelRead(msg);
        }
        else {
            if("yes".equals(body)){
                //等待下一次
                System.out.println("完结！"+body);
                ctx.close();
            }else{
                System.out.println("异常！"+body);
                ctx.close();
            }

        }

    }
}