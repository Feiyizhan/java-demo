package io.github.feiyizhan;

import io.github.feiyizhan.client.EnterClient;

/**
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class EnterClientTests {

    public static void main(String[] args) {
        EnterClient client = new EnterClient("127.0.0.1",8888);
        client.start();
    }
}
