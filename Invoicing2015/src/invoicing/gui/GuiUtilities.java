package invoicing.gui;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GuiUtilities {
	public static void show(JFrame frame, String name, int width, int height){
		frame.setName(name);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(width, height);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

	public static void show(JApplet applet, String name, int width, int height){
	    JFrame frame = new JFrame(name);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(applet);
	    frame.setSize(width, height);
	    frame.setLocationRelativeTo(null);
	    applet.init();
	    applet.start();
	    frame.setVisible(true);

	}
	
	public static void show(JPanel panel, String name, int width, int height){
	    JFrame frame = new JFrame(name);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(panel);
	    frame.setSize(width, height);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	
}
