package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import server.Click;
import server.Client;
import server.Entity;


public class MouseController implements MouseListener{
	
	public MouseController() {
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Client.getInstance().sendClick(new Entity(Entity.CLICK, new Click(e.getX(), e.getY())));
		Client.getInstance().setHealth(new Entity(Entity.HEALTH, ScorePanel.getInstance().getHealth()));
		Monster.getInstance().hit();
		//display("Mouse clicked @ X" + e.getX() + " & Y" + e.getY());
		//Client.getInstance().splash(e.getX(), e.getY());
		//ScorePanel.getInstance().updateScore(1, 10);
		//Client.getInstance().sendClick(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
