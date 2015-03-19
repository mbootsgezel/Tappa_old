package game;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Container extends JPanel{
	
	private static Container instance;
	
	private Container() {
		this.setSize(800, 600);
		this.add(ScorePanel.getInstance());		
		this.setVisible(true);
		ScorePanel.getInstance().setBounds(350, 25, 100, 50);
	}
	
	public static Container getInstance(){
		if(instance == null){
			instance = new Container();
		}
		return instance;
	}

}
