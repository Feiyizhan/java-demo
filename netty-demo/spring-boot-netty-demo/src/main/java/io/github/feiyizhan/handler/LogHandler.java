package io.github.feiyizhan.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class LogHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("当前容量："+buf.maxCapacity());
        System.out.println("当前读索引的位置："+buf.readerIndex());
        System.out.println("当前写索引的位置："+buf.writerIndex());
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("收到消息:"+body);
        //将读过的内容还原，供下一个处理器使用
        //有这几种办法：
        //1.将内容重新写回去, 缺点是 ByteBuf有多份数据，之前读过的数据未清理。
//        buf.writeBytes(req);
        //2.回收已读取的数据的空间，再写回去。缺点是有额外的内存复制开销，如果分段读取，容易产生碎片。
        //buf.discardReadBytes(); //先清理已读取的内容，并重置读的索引为0，将未读的内容复制到起始位置
        //buf.writeBytes(req);
        //3.重置读取的索引为0
        buf.resetReaderIndex();
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
