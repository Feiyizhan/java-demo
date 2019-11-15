package io.github.feiyizhan;

import io.github.feiyizhan.client.NettyTimeClient;

/**
 * NettyTimeClient 测试
 * @author 徐明龙 XuMingLong 2019-11-14
 */
public class NettyTimeClientTests {
     public static void main(String[] args) {
         NettyTimeClient client = new NettyTimeClient("127.0.0.1",8888);
         client.start();
     }
}
