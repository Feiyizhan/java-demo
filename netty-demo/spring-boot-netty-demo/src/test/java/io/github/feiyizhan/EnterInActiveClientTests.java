package io.github.feiyizhan;

import io.github.feiyizhan.client.EnterInActiveClient;

/**
 * 连接激活就触发输入的客户端测试
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class EnterInActiveClientTests {

    public static void main(String[] args) {
        EnterInActiveClient client = new EnterInActiveClient("127.0.0.1",8888);
        client.start();
    }
}
