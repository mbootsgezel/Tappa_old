package game;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class ScorePanel extends JPanel{
	
	private JLabel scoreLabel = new JLabel("0");
	
	private HealthBar health = new HealthBar(1337);
	
	private int score = 0;
	
	private static ScorePanel instance;
	
	private ScorePanel() {
		this.setLayout(new BorderLayout());
		this.setSize(800, 50);
		
		this.add(scoreLabel, BorderLayout.CENTER);
		this.add(health);
	}
	
	public void setScore(int score){
		this.score = score;
		scoreLabel.setText(Integer.toString(score));
	}
	
	public void updateScore(int newScore, int scoreMultiplier){
		score = score + (newScore*scoreMultiplier);
		scoreLabel.setText(Integer.toString(score));
		health.damage(newScore*scoreMultiplier);
	}
	
	public static ScorePanel getInstance(){
		if(instance == null){
			instance = new ScorePanel();
		}
		return instance;
	}

}
