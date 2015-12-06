package invoicing.view;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import invoicing.controller.ItemController;
import invoicing.entity.Item;

public class MainWindow extends JApplet {
	private ItemController<Item> productController = new ItemController<>();
	
	private JFrame mainFrame;
	private AddProductDialog dlgAddProduct;
	
	public MainWindow() {
	}
	
	public MainWindow(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public ItemController<Item> getProductController() {
		return productController;
	}

	public void init() {
		JLabel label = new JLabel("");
		getContentPane().add(label, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
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
		mnProducts.add(mntmAddProduct);
		
		mntmAddProduct.addActionListener( ev -> {
			if(dlgAddProduct == null) {
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

}
