package io.github.feiyizhan;

import io.github.feiyizhan.server.DiscardServer;

/**
 * @author 徐明龙 XuMingLong 2019-11-15
 */
public class DiscardServerTests {

    public static void main(String[] args) {
        DiscardServer server = new DiscardServer(8888);
        server.start();
    }
}
