package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import server.Click;
import server.Client;
import server.CurrentDate;


public class MouseController implements MouseListener{
	
	private CurrentDate d;
	
	public MouseController() {
		
		this.d = new CurrentDate();
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		display("Mouse clicked @ X" + e.getX() + " & Y" + e.getY());
		Client.getInstance().sendClick(new Click(e.getX(),e.getY()));
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
	
	public void display(String s){
		System.out.println(d.now() + " - Game - " + s);
	}

}
