package invoicing.gui;

import java.awt.FlowLayout;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class ProductForm extends JApplet{
	public void init() {
		JLabel title = new JLabel("Add Product");
		setLayout(new FlowLayout());
		title.setBounds(100, 100, 300, 50);
		getContentPane().add(title);
	}

	public static void main(String[] args) {
		GuiUtilities.show(new ProductForm(), "Product Form", 600,  400);

	}

}
