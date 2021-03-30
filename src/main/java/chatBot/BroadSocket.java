package chatBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// �Ϲ� �������� �������� �� ���� url
@ServerEndpoint("/broadsocket")
public class BroadSocket {
	// searchUser �Լ��� filter ǥ������ ���� �������̽�
	private interface SearchExpression {
		// ���ٽ��� ���� �Լ�
		boolean expression(User user);
	}

	// ������ �������� ������ key�� �����ϱ� ���� �ζ��� Ŭ����
	private class User {
		Session session;
		String key;
	}

	// ������ �������� ���� ����Ʈ
	private static List<User> sessionUsers = Collections.synchronizedList(new ArrayList<>());

	// Session���� ���� ����Ʈ���� User Ŭ������ Ž��
	private static User getUser(Session session) {
		return searchUser(x -> x.session == session);
	}

	// Key�� ���� ����Ʈ���� User Ŭ������ Ž��
	private static User getUser(String key) {
		return searchUser(x -> x.key.equals(key));
	}

	// ���� ����Ʈ Ž�� �Լ�
	private static User searchUser(SearchExpression func) {
		Optional<User> op = sessionUsers.stream().filter(x -> func.expression(x)).findFirst();
		// ����� ������
		if (op.isPresent()) {
			// ����� ����
			return op.get();
		}
		// ������ null ó��
		return null;
	}

	// browser���� �� �������� �����ϸ� ȣ��Ǵ� �Լ�
	@OnOpen
	public void handleOpen(Session userSession) {
		// �ζ��� Ŭ���� User�� ����
		User user = new User();
		// UniqueŰ�� �߱� ('-'�� �����Ѵ�.)
		user.key = UUID.randomUUID().toString().replace("-", "");
		// WebSocket�� ����
		user.session = userSession;
		// ���� ����Ʈ�� ����Ѵ�.
		sessionUsers.add(user);
		// ��� Client�� ������ ������ ���� �˸���.
		Admin.visit(user.key);
	}

	// browser���� �� ������ ���� �޽����� ���� ȣ��Ǵ� �Լ�
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		// Session���� ���� ����Ʈ���� User Ŭ������ Ž��
		User user = getUser(userSession);
		// ���� ����Ʈ�� User�� ������(�翬�� �ִ�. ������ ����..)
		if (user != null) {
			// ��� Client�� ���� key�� �޽����� ������.
			Admin.sendMessage(user.key, message);
		}
	}

	// ��� client�� �������� �޽����� ������ �Լ�
	public static void sendMessage(String key, String message) {
		// key�� ���� ����Ʈ���� User Ŭ������ Ž��
		User user = getUser(key);
		// ���� ����Ʈ�� User�� ������(�翬�� �ִ�. ������ ����..)
		if (user != null) {
			try {
				// ���� Session���� socket�� ����� �� �޽����� �����Ѵ�.
				user.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// WebSocket�� ���ᰡ �Ǹ�, ���� ��ư�� ���� ������ ���� �������� ������ �߻��Ѵ�.
	@OnClose
	public void handleClose(Session userSession) {
		// Session���� ���� ����Ʈ���� User Ŭ������ Ž��
		User user = getUser(userSession);
		// ���� ����Ʈ�� User�� ������(�翬�� �ִ�. ������ ����..)
		if (user != null) {
			// ��� Client�� ���� key�� ���� ���Ḧ �˸���.
			Admin.bye(user.key);
			// �� ���� ���� ����Ʈ���� ������ �����Ѵ�.
			sessionUsers.remove(user);
		}
	}

	// �������� ���� ����Ʈ�� Ű�� ����Ϸ��� �Ҷ�.
	public static String[] getUserKeys() {
		// ��ȯ�� String �迭�� �����Ѵ�.
		String[] ret = new String[sessionUsers.size()];
		// ���� ����Ʈ�� �ݺ����� ������.
		for (int i = 0; i < ret.length; i++) {
			// ������ Ű�� ��ȯ ������ �ִ´�.
			ret[i] = sessionUsers.get(i).key;
		}
		// �� ��ȯ
		return ret;
	}
}
