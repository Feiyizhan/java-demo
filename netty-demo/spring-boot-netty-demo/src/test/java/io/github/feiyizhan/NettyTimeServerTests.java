package io.github.feiyizhan;

import io.github.feiyizhan.server.NettyTimeServer;

/**
 * NettyTimeServer 测试类
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class NettyTimeServerTests {

    public static void main(String[] args) {
        NettyTimeServer server = new NettyTimeServer(8888);
        server.start();
    }
}
