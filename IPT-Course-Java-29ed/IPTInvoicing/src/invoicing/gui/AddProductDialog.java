package invoicing.gui;

import invoicing.controller.ProductController;
import invoicing.entity.Measure;
import invoicing.util.ProductValidationException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblProductId;
	private JTextField tfProductID;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfSupplier;
	private InvoicingApp application;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddProductDialog dialog = new AddProductDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddProductDialog() {
		setTitle("Add New Product");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblProductId = new JLabel("Product ID:");

		JLabel lblNewLabel = new JLabel("Product Name:");
		
		JLabel lblPrice = new JLabel("Price:");
		
		JLabel lblSupplier = new JLabel("Supplier:");
		
		JLabel lblDescription = new JLabel("Description:");
		
		tfProductID = new JTextField();
		tfProductID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		
		tfSupplier = new JTextField();
		tfSupplier.setColumns(10);
		
		JTextArea taDescription = new JTextArea();
		
		JScrollPane jspDescription = new JScrollPane(taDescription);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProductId)
						.addComponent(lblNewLabel)
						.addComponent(lblPrice)
						.addComponent(lblSupplier)
						.addComponent(lblDescription))
					.addGap(51)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(jspDescription, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addComponent(tfName, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addComponent(tfProductID, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addComponent(tfPrice, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addComponent(tfSupplier, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductId)
						.addComponent(tfProductID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(tfPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupplier)
						.addComponent(tfSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(jspDescription, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							application.getProductController().addProduct(
								tfProductID.getText(),
								tfName.getText(), 
								tfSupplier.getText(), 
								tfPrice.getText(), 
								taDescription.getText(),
								Measure.PCS);
							application.updateProducts();
							dispose();
						} catch (ProductValidationException e1) {
							JOptionPane.showMessageDialog(AddProductDialog.this, 
									e1.getMessage(), "Invalid Data", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public AddProductDialog(InvoicingApp app) {
		this();
		application = app;
	}

}
