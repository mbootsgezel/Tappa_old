package game;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(800, 600);
		this.add(Container.getInstance());
		this.setLocationRelativeTo(null);
		this.addMouseListener(new MouseController());
		this.setVisible(true);
	}
	
	public void start(){
		this.run();
	}

	@Override
	public void run() {
		
	}

}
