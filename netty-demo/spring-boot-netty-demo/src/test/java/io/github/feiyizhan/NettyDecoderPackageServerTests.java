package io.github.feiyizhan;

import io.github.feiyizhan.server.NettyDecoderPackageServer;

/**
 * NettyDecoderPackageServerTests 测试类
 * @author 徐明龙 XuMingLong 2019-11-18
 */
public class NettyDecoderPackageServerTests {

    public static void main(String[] args) {
        NettyDecoderPackageServer server = new NettyDecoderPackageServer(8888);
        server.start();
    }
}
