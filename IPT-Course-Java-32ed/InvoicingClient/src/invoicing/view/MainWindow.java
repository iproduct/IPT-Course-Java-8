package invoicing.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import invoicing.rmi.Invoicing;
import invoicing.rmi.client.InvoicingRMIClient;

public class MainWindow extends JApplet {
	private Invoicing server;

	private JFrame mainFrame;
	private AddProductDialog dlgAddProduct;
	private BrowseProductsDialog dlgBrowseProducts;
	private final Action opendb = new SwingAction();

	public MainWindow() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainWindow(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}

	public Invoicing getServer() {
		return server;
	}

	public void init() {
		JLabel label = new JLabel("");
		getContentPane().add(label, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAction(opendb);
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);

		JMenu mnInvoices = new JMenu("Invoices");
		menuBar.add(mnInvoices);

		JMenu mnContragents = new JMenu("Contragents");
		menuBar.add(mnContragents);

		JMenuItem mntmAdd = new JMenuItem("Add Contragent");
		mnContragents.add(mntmAdd);

		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);

		JMenuItem mntmAddProduct = new JMenuItem("Add Product");
		mntmAddProduct
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnProducts.add(mntmAddProduct);

		JMenuItem mntmBrowseProducts = new JMenuItem("Browse Products");
		mntmBrowseProducts.addActionListener(ev -> {
			if (dlgBrowseProducts == null) {
				dlgBrowseProducts = new BrowseProductsDialog(mainFrame, MainWindow.this);
			}
			dlgBrowseProducts.refresh();
			dlgBrowseProducts.setVisible(true);
		});
		mntmBrowseProducts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnProducts.add(mntmBrowseProducts);

		JSeparator separator_1 = new JSeparator();
		mnProducts.add(separator_1);

		JMenuItem mntmEditProduct = new JMenuItem("Edit Product");
		mnProducts.add(mntmEditProduct);

		JMenuItem mntmDeleteProduct = new JMenuItem("Delete Product");
		mnProducts.add(mntmDeleteProduct);

		mntmAddProduct.addActionListener(ev -> {
			if (dlgAddProduct == null) {
				dlgAddProduct = new AddProductDialog(mainFrame, MainWindow.this);
			}
			dlgAddProduct.setVisible(true);
		});

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelp);

		JMenuItem mntmAboutProduct = new JMenuItem("About");
		mnHelp.add(mntmAboutProduct);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame mainFrame = new JFrame("Product Demo");
				JApplet applet = new MainWindow(mainFrame);
				mainFrame.getContentPane().add(applet);
				applet.init();
				mainFrame.setSize(800, 600);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Connect to Server");
			putValue(SHORT_DESCRIPTION, "Connect to server");
		}

		public void actionPerformed(ActionEvent e) {
			String serviceUrl = JOptionPane.showInputDialog(
				"Please, enter Invocing server host and port (ex. //localhost:2016/Invoicing):",
				"//localhost:2016/Invoicing");
			Pattern p = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?(//)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");  
		    Matcher m = p.matcher(serviceUrl); 
			if (true) {
				try {
					server = InvoicingRMIClient.connectToServer(serviceUrl);
					System.out.println(server.getAllProducts());
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					JOptionPane.showMessageDialog(mainFrame, e1, "Server Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(mainFrame, 
						"Invalid Server URL Error: " + serviceUrl, 
						"Invalid Server URL Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
