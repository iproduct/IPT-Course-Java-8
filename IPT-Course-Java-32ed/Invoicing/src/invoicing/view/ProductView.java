package invoicing.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductView extends JApplet {
	public static final String[] FIELD_NAMES = {
		"name", "price", "author", "publisher", "year", "isbn"
	};
	
	
	private GridBagLayout gridbag = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	private Map<String, JTextField> fields = new HashMap<>();
	
	public void init() {
		
		setLayout(gridbag);
		
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.CENTER;
		
		JLabel title = new JLabel("Add New Product");
		title.setFont(new Font("SansSerif", Font.PLAIN, 22));
		gridbag.setConstraints(title, c);
		add(title);
		
		//add text fields
		for(String lbl : FIELD_NAMES) {
			makeField(lbl);
		}
	}

	private void makeField(String label){
		StringBuilder sb = new StringBuilder(label.substring(0,1).toUpperCase());
		sb.append(label.substring(1,label.length()));
		JLabel lbl = new JLabel(sb.toString());
		c.weightx = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(5, 30, 5, 10);
		gridbag.setConstraints(lbl, c);
		add(lbl);
		
		//add JTextFiled
		JTextField jtf = new JTextField();
		fields.put(label, jtf);
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		gridbag.setConstraints(jtf, c);
		add(jtf);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame mainWindow = new JFrame("Product Demo");
				JApplet applet = new ProductView();
				mainWindow.add(applet);
				applet.init();
				mainWindow.setSize(800, 600);
				mainWindow.setLocationRelativeTo(null);
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainWindow.setVisible(true);
			}
		});
	}

}
