package chat.client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

import chat.multicasting.ChatGUI;
import chat.utility.DatagramUtilities;

public class MyUDPChatClient extends Thread implements MyChat{
	private DatagramSocket socket;
	private byte[] buf = new byte[1000];
	private DatagramPacket dp = 
	    new DatagramPacket(buf, buf.length);
	private String nickname;
	private ChatGUI gui;
	private volatile boolean stop = false;
	private InetAddress address;
	private int port;


	public MyUDPChatClient(String host, int port, ChatGUI gui) {
		System.out.println("Making client ");
		this.port = port;
		this.gui = gui;
		try {
			address = InetAddress.getByName(host);
			socket = new DatagramSocket();
			socket.connect(address,port);
			socket.send(DatagramUtilities.toDatagram("", address, port));
			System.out.println("Socket created: "
					+socket.getLocalPort() 
					+ "-->" + socket.getPort());
			start();
		} catch (IOException e) {
			e.printStackTrace();
			socket.close();
		}
	}
	
	public void run() {
		String message;
		while (!stop) {
			try {
				socket.receive(dp);
				message = DatagramUtilities.toString(dp);
				if(message!=null) {
					System.out.println(message);
					gui.showMessage(message);
				}
			} catch (SocketException e1) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Chat client closing ...");
	}

	public void requestStop() {
		stop = true;
	}

	@Override
	public void sendMessage(String message) {
		message = "["+nickname+"] " + new Date() +": "+message;
		send(message);
	}

	private void send(String message) {
		DatagramPacket p =
			DatagramUtilities.toDatagram(message, address, port);
		System.out.println("Packet " + p.getAddress() + p.getPort());
		try {
			socket.send(p);
			System.out.println("Package sent" 
					+ DatagramUtilities.toString(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public void stopChat() {
		send("<END>");
		socket.close();
		requestStop();
		System.out.println("Client closed.");
	}

}
