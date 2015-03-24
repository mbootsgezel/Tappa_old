package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Monster extends JPanel implements Runnable{

	private static Monster instance;
	
	private ImageIcon idle;
	private ImageIcon hit;

	private JLabel label;

	private long startTime = System.currentTimeMillis();
	private long time = 0L;
	
	private boolean damaged;

	private Monster() {
		//this.setLayout(new BorderLayout());
		this.setLayout(null);
		this.setSize(350, 350);
		this.damaged = false;
		try {
			BufferedImage image = ImageIO.read(new File(getClass().getResource("/res/idle.png").toURI()));
			BufferedImage image2 = ImageIO.read(new File(getClass().getResource("/res/hit.png").toURI()));
			
			BufferedImage newimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			BufferedImage newimage2 = new BufferedImage(image2.getWidth(), image2.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			AffineTransform transform = new AffineTransform();
			transform.scale(0.9, 0.9);
			AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			
			image = scaleOp.filter(image, newimage);
			image2 = scaleOp.filter(image2, newimage2);
			
			idle = new ImageIcon(image);
			hit = new ImageIcon(image2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		label = new JLabel();
		
		label.setBounds(0, -15, 400, 400);
		
		label.setIcon(idle);

		add(label, BorderLayout.CENTER);

		this.setVisible(true);
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
				label.setIcon(hit);
				Thread.sleep(500);
				label.setIcon(idle);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	public void hit(){
		damaged = true;
	}

	public static Monster getInstance(){
		if(instance == null){
			instance = new Monster();
		}
		return instance;
	}

}
