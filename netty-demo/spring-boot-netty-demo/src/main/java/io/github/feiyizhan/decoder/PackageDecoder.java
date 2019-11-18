package io.github.feiyizhan.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class PackageDecoder extends ByteToMessageDecoder {

    private final static Integer MAX_SIZE = 32;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < MAX_SIZE) {
            return;
        }
        out.add(in.readBytes(MAX_SIZE));
    }
}
