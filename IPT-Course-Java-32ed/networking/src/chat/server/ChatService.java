package chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatService implements Runnable{
	public Logger logger = Logger.getLogger(ChatService.class.getName());

	private TCPChatServer server;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String nickname;
	private SimpleDateFormat sdf = 
		new SimpleDateFormat("dd.MM.yyyy' 'HH:mm:ss");
	
	public ChatService() {
	}

	public ChatService(TCPChatServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	@Override
	public void run() {
		try{
			in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream())), true);
	
			nickname = in.readLine();
			logger.log(Level.INFO, "User " + nickname + " has joined chat.");
			String message = "";
			while(!message.equals("logout()")) {
				message = in.readLine();
				StringBuilder messageBuilder = new StringBuilder(nickname);
				String messageToSend = messageBuilder.append("[")
					.append(sdf.format(new Date()))
					.append("]: ").append(message).toString();
				server.sendAll(messageToSend);
			}	
		} catch (IOException ex) {
			logger.log(Level.SEVERE, 
				"Chat client error [" + nickname + "]: "+ socket,
				ex);
		} finally {
			logger.log(Level.INFO, "User " + nickname + " has left chat.");
			server.removeService(this);
		}
		
	}

	public void sendMessage(String message) throws IOException {
		out.println(message);
	}
	
	
}
