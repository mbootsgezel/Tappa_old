package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Monster extends JPanel implements Runnable{
	
	private static Monster instance;
	
	private BufferedImage idle;
	private BufferedImage hit;
	private BufferedImage frame;
	
	private boolean isIdle = true;
	
	private Monster() {
		try {
		 	BufferedImage idle = ImageIO.read(new File("/res/idle.jpg"));
			BufferedImage hit = ImageIO.read(new File("/res/hit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			frame = idle;
			if(!isIdle){
				frame = hit;
			}
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (frame != null) {
            int x = (getWidth() - frame.getWidth()) / 2;
            int y = (getHeight() - frame.getHeight()) / 2;
            g2d.drawImage(frame, x, y, this);
        }
        g2d.dispose();
    }
    
    public void hit(){
    	isIdle = false;
    	repaint();
    	Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isIdle = true;
                repaint();
            }
        });
    }
	
	public static Monster getInstance(){
		if(instance == null){
			instance = new Monster();
		}
		return instance;
	}
	
}
