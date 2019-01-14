package socket;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.jasper.tagplugins.jstl.core.Set;
@ServerEndpoint("/socket")
public class Socket {
	private static java.util.Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		System.out.println(message);
		synchronized (clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					client.getBasicRemote().sendText("message");
				}
			}
		}
	} // onMessage 끝
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session);
		clients.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
