package io.github.feiyizhan.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 整包处理器
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class PackageHandler extends ChannelHandlerAdapter {

    private ByteBuf buf;
    private final static Integer MAX_SIZE = 32;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(MAX_SIZE);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m);
        System.out.println("当前读索引的位置："+buf.readerIndex());
        System.out.println("当前写索引的位置："+buf.writerIndex());

        System.out.println("current package size:" + buf.readableBytes());
        if (buf.readableBytes() >= MAX_SIZE) {
            byte[] resp = new byte[MAX_SIZE];
            buf.readBytes(resp);
            System.out.println(new String(resp,"UTF-8"));
            //整理缓存
            buf.discardReadBytes();
            System.out.println("当前读索引的位置："+buf.readerIndex());
            System.out.println("当前写索引的位置："+buf.writerIndex());
            //将整包的内容写回。
            m.writeBytes(resp);
            ChannelFuture f = ctx.writeAndFlush(m);
        }else{
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
