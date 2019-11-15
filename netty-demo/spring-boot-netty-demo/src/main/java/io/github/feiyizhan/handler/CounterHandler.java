package io.github.feiyizhan.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 徐明龙 XuMingLong 2019-11-13
 */
public class CounterHandler extends ChannelHandlerAdapter {

    private String name;
    private AtomicInteger counter;

    public CounterHandler(String name){
        this.name = name;
        this.counter = new AtomicInteger(0);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(name+":第"+counter.addAndGet(1)+"次消息");
        //让下一个ChannelHandler处理
        ctx.fireChannelRead(msg);
    }

}
