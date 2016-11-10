import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class ButtonApplet extends JApplet{
	private JButton 
		btnAdd = new JButton("Add Data"),
		btnDelete = new JButton("Delete Data");
	private JTextArea jtaData = new JTextArea(20,70);
	private int counter = 0;
	
	@Override
	public void init() {
		setLayout(new FlowLayout());
		add(new JScrollPane(jtaData));
		add(btnAdd);
		add(btnDelete);
		btnAdd.addActionListener((ActionEvent e) -> {
			jtaData.append("Data, data " + ++counter + "\n");
		});
		
	};

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		JFrame main = new JFrame("My Applet");
		ButtonApplet applet = new ButtonApplet();
		SwingUtilities.invokeLater(() -> {
			main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			main.setSize(800, 500);
			main.setLocationRelativeTo(null);
			main.add(applet);
			applet.init();
			applet.start();
			main.setVisible(true);
		});
	}

}
