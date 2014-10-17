package invoicing.gui;

import invoicing.controller.ProductController;
import invoicing.entity.Product;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

public class InvoicingApp {
	private JFrame frame;
	private ProductController pController = new ProductController();
	private AddProductDialog addProductDialog = 
			new AddProductDialog(this);
	private JTable tProducts;
	private Path dbPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoicingApp window = new InvoicingApp();
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
	public InvoicingApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenDbFile = new JMenuItem("Open DB File ...");
		mntmOpenDbFile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		      JFileChooser c = new JFileChooser(".");
		      int rVal = c.showOpenDialog(frame);
		      if(rVal == JFileChooser.APPROVE_OPTION) {
		        dbPath = c.getCurrentDirectory().toPath()
		        		.resolve(c.getSelectedFile().getName());
		        try {
					pController.readProductsFromFile(dbPath.toString());
					updateProducts();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, 
						"Error reading file " + dbPath.toString() +": " +
						e1.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
		      }
		}
	});

	// Using Java 8 lambdas
//	mntmOpenDbFile.addActionListener( e -> {
//			JOptionPane.showMessageDialog(frame, "Loading Product DB ...", 
//					"DB Message", JOptionPane.INFORMATION_MESSAGE);
//		});

		mntmOpenDbFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		mnFile.add(mntmOpenDbFile);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmSaveDbFile = new JMenuItem("Save DB File");
		mntmSaveDbFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pController.writeProductsToFile(dbPath.toString());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, 
							"Error writing file " + dbPath.toString() +": " +
							e1.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		mntmSaveDbFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSaveDbFile);
		
		JMenuItem mntmSaveDbFileAs = new JMenuItem("Save DB File As ...");
		mntmSaveDbFileAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser(".");
			      int rVal = c.showSaveDialog(frame);
			      if(rVal == JFileChooser.APPROVE_OPTION) {
			    	  Path newPath = c.getCurrentDirectory().toPath()
			        		.resolve(c.getSelectedFile().getName());
			    	  try {
			    		  pController.writeProductsToFile(newPath.toString());
			    		  dbPath = newPath;
			    	  } catch (IOException e) {
			    		  JOptionPane.showMessageDialog(frame, 
							"Error writing file " + dbPath.toString() +": " +
							e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
			    		  e.printStackTrace();
			    	  }
				}
			}
		});
		mnFile.add(mntmSaveDbFileAs);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
		
		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);
		
		JMenuItem mntmAddNewProduct = new JMenuItem("Add New Product");
		mntmAddNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addProductDialog.setVisible(true);
			}
		});
		mntmAddNewProduct.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
		mnProducts.add(mntmAddNewProduct);
		
		JMenuItem mntmListProducts = new JMenuItem("List Products");
		mntmListProducts.addActionListener(e -> {updateProducts();});
		mntmListProducts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnProducts.add(mntmListProducts);
		
		JMenuItem mntmEditProduct = new JMenuItem("Edit Product");
		mnProducts.add(mntmEditProduct);
		
		JSeparator separator_2 = new JSeparator();
		mnProducts.add(separator_2);
		
		JMenuItem mntmDeleteProduct = new JMenuItem("Delete Product");
		mnProducts.add(mntmDeleteProduct);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmContentHelp = new JMenuItem("Content Help ...");
		mntmContentHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmContentHelp);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us ...");
		mnHelp.add(mntmAboutUs);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tProducts = new JTable(new AbstractTableModel(){

			@Override
			public int getColumnCount() {
				return ProductController.PRODUCT_COLUMNS;
			}

			@Override
			public int getRowCount() {
				return pController.getProducts().size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Product p = pController.getProducts().get(rowIndex);
				switch(columnIndex) {
					case 0: return p.getPid(); 
					case 1: return p.getName(); 
					case 2: return p.getPrice(); 
					case 3: return p.getSupplier(); 
					case 4: return p.getMeasure(); 
					case 5: return p.getDescription(); 
				}	
				return null;
			}
			
			@Override
			public String getColumnName(int column) {
				return ProductController.COLUMN_NAMES[column];
			}
			
		});
		scrollPane.setViewportView(tProducts);
	}

	public ProductController getProductController() {
		return pController;
	}

	public void updateProducts(){
		tProducts.revalidate();
	}
}
