package game;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{
	
	private JLabel scoreLabel = new JLabel("0");
	
	private int score = 0;
	
	private static ScorePanel instance;
	
	private ScorePanel() {
		this.add(scoreLabel);
	}
	
	public void setScore(int score){
		this.score = score;
		scoreLabel.setText(Integer.toString(score));
	}
	
	public void updateScore(int newScore, int scoreMultiplier){
		score = score + (newScore*scoreMultiplier);
		scoreLabel.setText(Integer.toString(score));
	}
	
	public static ScorePanel getInstance(){
		if(instance == null){
			instance = new ScorePanel();
		}
		return instance;
	}

}
