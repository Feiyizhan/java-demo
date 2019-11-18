package io.github.feiyizhan;

import io.github.feiyizhan.server.NettyPackageServer;

/**
 * NettyTimeServer 测试类
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class NettyPackageServerTests {

    public static void main(String[] args) {
        NettyPackageServer server = new NettyPackageServer(8888);
        server.start();
    }
}
