package chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.MessageListener;
import chat.NetClient;
import chat.model.ConnectionSettings;
import timeserver.TimeServer;

public class TCPChatClient implements NetClient, Runnable {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ConnectionSettings settings;
	private InetAddress address;

	public TCPChatClient() {
	}

	public Socket getSocket() {
		return socket;
	}

	public ConnectionSettings getSettings() {
		return settings;
	}

	@Override
	public String login(ConnectionSettings settings) {
		this.settings = settings;
		try {
			address = InetAddress.getByName(settings.getAddress());
			socket = new Socket(address, settings.getPort());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
						true);
			out.println(settings.getNickname());
			System.out.println("User " +settings.getNickname() 
				+ " logged to to server: " + socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "Unknown host: " + settings.getAddress();
		} catch (IOException e) {
			e.printStackTrace();
			return "Error communicating with server.";
		}
		return null;
	}

	@Override
	public boolean sendMessage(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMessageListener(MessageListener ml) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMessageListener(MessageListener ml) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {

		
	}

}
