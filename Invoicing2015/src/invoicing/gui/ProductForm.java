package invoicing.gui;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class ProductForm extends JApplet{
	public void init() {
		JLabel title = new JLabel("Add Product");
		getContentPane().add(title);
	}

	public static void main(String[] args) {
		GuiUtilities.show(new ProductForm(), "Product Form", 600,  400);

	}

}
