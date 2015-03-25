package game;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Container extends JPanel{
	
	private static Container instance;
	
	private Monster monster;
	
	private Container() {
		new Thread(monster = Monster.getInstance()).start();
		this.setLayout(new BorderLayout());
		this.setSize(Window.WIDTH, Window.HEIGHT);
		this.add(ScorePanel.getInstance(), BorderLayout.NORTH);	
		this.add(Monster.getInstance(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public static Container getInstance(){
		if(instance == null){
			instance = new Container();
		}
		return instance;
	}

}
