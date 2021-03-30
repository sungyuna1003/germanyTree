package chatBot;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// ��� �������� �������� �� ���� url
@ServerEndpoint("/admin")
public class Admin {
	// ��� ������ �ϳ���� �����ϰ� ���� �� �̻��� ���ǿ��� ������ �ϸ� ������ ���Ǹ� �۵��Ѵ�.
	private static Session admin = null;

	// ��� ������ ������ �ϸ� �߻��ϴ� �̺�Ʈ �Լ�
	@OnOpen
	public void handleOpen(Session userSession) {
		// ������ ��� ������ ������ �������̶��
		if (admin != null) {
			try {
				// ������ ���´�.
				admin.close();
			} catch (IOException e) {
			}
		}
		// ��� ������ ������ �ٲ۴�.
		admin = userSession;
		// ������ ������ �ִ� ������ ������ ��� client�� ������.
		for (String key : BroadSocket.getUserKeys()) {
			// ����.. ����
			visit(key);
		}
	}

	// ��� ������ �޽����� ������ �߻��ϴ� �̺�Ʈ
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		// key�� �޽��� ����Ű�� #####�� �־���. (json���� �ص� �Ǵµ� Gson ������� �ϸ� ����������...)
		String[] split = message.split("#####", 2);
		// ���� key ������
		String key = split[0];
		// �� ������ �޽���
		String msg = split[1];
		// �Ϲ� ������ key�� Ž���� �޽��� ����
		BroadSocket.sendMessage(key, msg);
	}

	// ������ ����� �� ��� ������ null ó���Ѵ�.
	@OnClose
	public void handleClose(Session userSession) {
		admin = null;
	}

	// ��� ������ �޽����� ������ �Լ�
	private static void send(String message) {
		if (admin != null) {
			try {
				admin.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// �Ϲ� ������ �������� ��, ��� �������� �˸��� �Լ�
	public static void visit(String key) {
		// json ������ status�� visit�̰� key�� ���� Ű �����̴�.(javascript�� ���ߴ� ��������)
		send("{\"status\":\"visit\", \"key\":\"" + key + "\"}");
	}

	// �Ϲ� ������ �޽����� ���� ��, ��� �������� �˸��� �Լ�
	public static void sendMessage(String key, String message) {
		// json ������ status�� message�̰� key�� ���� Ű �����̴�.(javascript�� ���ߴ� ��������) message�� ������ �޽����̴�.
		send("{\"status\":\"message\", \"key\":\"" + key + "\", \"message\":\"" + message + "\"}");
	}

	// �Ϲ� ������ ������ ���� ��, ��� �������� �˸��� �Լ�
	public static void bye(String key) {
		// json ������ status�� bye�̰� key�� ���� Ű �����̴�.(javascript�� ���ߴ� ��������)
		send("{\"status\":\"bye\", \"key\":\"" + key + "\"}");
	}
}
