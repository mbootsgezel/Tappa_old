package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseController implements MouseListener{
	
	public MouseController() {
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Client - Mouse clicked @ X" + e.getX() + " & Y" + e.getY());
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
