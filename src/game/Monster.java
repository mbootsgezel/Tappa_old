package game;

import java.awt.BorderLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Monster extends JPanel implements Runnable{

	private static Monster instance;

	private ImageIcon idle;
	private ImageIcon hit;

	private JLabel label;

	private long startTime = System.currentTimeMillis();
	private long time = 0L;

	private volatile boolean damaged;

	private Monster() {
		this.setLayout(new BorderLayout());
		this.setLayout(null);
		this.setSize(480, 480);
		this.damaged = false;
		try {
			idle = getResizedImage("/resources/idle.png");
			hit = getResizedImage("/resources/hit.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		label = new JLabel();

		label.setBounds(180, 40, 400, 400);

		label.setIcon(idle);

		add(label, BorderLayout.CENTER);

		this.setVisible(true);
	}

	@Override
	public void run() {
		while(true){
			if(damaged){
				try {
					label.setIcon(hit);
					Thread.sleep(100);
					label.setIcon(idle);
					damaged = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}
	}
	
	public ImageIcon getResizedImage(String url){
		try {
			BufferedImage img = ImageIO.read(new File(getClass().getResource(url).toURI()));
			BufferedImage newimage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			AffineTransform transform = new AffineTransform();
			transform.scale(0.9, 0.9);
			AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			img = scaleOp.filter(img, newimage);
			return new ImageIcon(img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
