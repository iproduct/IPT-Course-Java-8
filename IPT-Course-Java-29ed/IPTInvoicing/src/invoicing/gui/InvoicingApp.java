/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoicing project is a simple demo using Swing GUI and local file for DB
* 
* Copyright (c) 2012 - 2014 IPT - Intellectual Products & Technologies Ltd.
* All rights reserved.
*
* E-mail: office@iproduct.org
* Web: http://iproduct.org/
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License (the "License")
* as published by the Free Software Foundation version 2 of the License.
* You may not use this file except in compliance with the License. You can
* obtain a copy of the License at root directory of this project in file
* LICENSE.txt.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* When distributing the software, include this COPYRIGHT & LICENSE HEADER
* in each file, and include the License file LICENSE.txt in the root directory
* of your distributable.
*
* GPL Classpath Exception:
* IPT - Intellectual Products & Technologies (IPT) designates this particular
* file as subject to the "Classpath" exception as provided by IPT in
* the GPL Version 2 License file that accompanies this code.
*
* In case you modify this file,
* please add the appropriate notice below the existing Copyright notices,
* with the fields enclosed in brackets {} replaced by your own identification:
* "Portions Copyright (c) {year} {name of copyright owner}"
*/

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
import java.util.Comparator;

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
import javax.swing.table.TableRowSorter;

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
		
		//Create products table model
		AbstractTableModel tProductsModel = new AbstractTableModel(){

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
			
		};
		
		// Create products table
		tProducts = new JTable(tProductsModel);
		scrollPane.setViewportView(tProducts);
		
		//Set sorter for columns
//		tProducts.setAutoCreateRowSorter(true);
		TableRowSorter<AbstractTableModel> sorter = new TableRowSorter<AbstractTableModel>(tProductsModel) {
			@Override
			public Comparator<?> getComparator(int column) {
				switch(column) {
					case 2: return new Comparator<String>() {
						@Override
						public int compare(String s1, String s2) {
							Double d1 = Double.parseDouble(s1),
								d2 = Double.parseDouble(s2);
							return d1.compareTo(d2);
						}		
					};
					default: return new Comparator<String>() {
						@Override
						public int compare(String s1, String s2) {
							return s1.compareToIgnoreCase(s2);
						}		
					};
					
				}
			}
		};
		tProducts.setRowSorter(sorter);
	}

	public ProductController getProductController() {
		return pController;
	}

	public void updateProducts(){
		tProducts.revalidate();
	}
}
