package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class HealthBar extends JPanel{

	private JProgressBar health;
	private int maxHealth;
	private int currentHealth;

	public HealthBar(int maxHealth) {
		this.health = new JProgressBar(0, maxHealth);
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;

		health.setStringPainted(true);
		health.setForeground(Color.GREEN);
		health.setString(maxHealth + "/" + maxHealth);
		health.setValue(maxHealth);
		health.setPreferredSize(new Dimension(750, 40));

		this.setSize(Window.WIDTH, 50);
		this.add(health);
	}

	public void damage(int dmg){
		if(currentHealth - dmg <= 0){
			currentHealth = 0;
		} else {
			currentHealth = currentHealth - dmg;	
		}
		health.setValue(currentHealth);
		health.setString(currentHealth + "/" + maxHealth);
		
	}

	public void setHealth(int newHealth){
		if(newHealth != 0) {
			health.setString(newHealth + "/" + maxHealth);
			health.setValue(newHealth);
			currentHealth = newHealth;


		}
	}

	public int getHealth(){
		return currentHealth;
	}

}
