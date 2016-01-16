package chat.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import chat.NetClient;
import chat.client.TCPChatClient;

public class ChatGUI {

	private JFrame frame;
	private SignInDialog signDialog;
	private NetClient netClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatGUI window = new ChatGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatGUI() {
		netClient = new TCPChatClient(); //Choose protocol
		initialize();
	}

	public NetClient getNetClient() {
		return netClient;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize Sign In dialog
		signDialog = new SignInDialog(this);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnChat = new JMenu("Chat");
		menuBar.add(mnChat);
		
		JMenuItem mntmSignin = new JMenuItem("Sign In");
		mntmSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signDialog.setVisible(true);
			}
		});
		mntmSignin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnChat.add(mntmSignin);
		
		JMenuItem mntmSignOut = new JMenuItem("Sign Out");
		mntmSignOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnChat.add(mntmSignOut);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}

}
