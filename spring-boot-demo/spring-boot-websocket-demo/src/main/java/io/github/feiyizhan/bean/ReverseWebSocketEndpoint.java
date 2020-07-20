package io.github.feiyizhan.bean;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author 徐明龙 XuMingLong 2019-12-03
 */
@ServerEndpoint("/reverse")
public class ReverseWebSocketEndpoint {

    @OnMessage
    public void handleMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText("Reversed: " + new StringBuilder(message).reverse());
    }

}
