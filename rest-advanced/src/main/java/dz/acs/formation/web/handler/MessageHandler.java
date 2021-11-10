package dz.acs.formation.web.handler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * MessageHandler
 * @author ataibi
 *
 */

@NoArgsConstructor
public class MessageHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, final TextMessage message) throws Exception {

		sessions.stream().forEach(sess -> {
			try {
				sess.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
