import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MyApplet extends JApplet{
	
	@Override
	public void init() {
		getContentPane().add(new JButton("Click Me!"), BorderLayout.SOUTH);
	};

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		JFrame main = new JFrame("My Applet");
		MyApplet applet = new MyApplet();
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
