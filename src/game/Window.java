package game;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(400, 300);
		this.add(Container.getInstance());
		this.setLocationRelativeTo(null);
		this.addMouseListener(new MouseController());
		this.setVisible(true);
	}

	@Override
	public void run() {
		
	}

}
