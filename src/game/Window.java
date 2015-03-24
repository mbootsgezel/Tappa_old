package game;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public Window(int width, int height) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.WIDTH = width;
		this.HEIGHT = height;
		this.setSize(WIDTH, HEIGHT);
		this.add(Container.getInstance());
		this.setLocationRelativeTo(null);
		this.addMouseListener(new MouseController());
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void run() {
		
	}

}
