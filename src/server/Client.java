package server;
import game.Monster;
import game.ScorePanel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client implements Runnable{
	
	private static Client instance;
	
	private Socket socket;
	private int id;
	private int port;
	private String host;
	private String response;
	private boolean running;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private CurrentDate d = new CurrentDate();
	
	private Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			display("Local IP = " +  new LocalAddress().getLocalIP());
			display("Connecting to: " + host);
			socket = new Socket(host, port);
			
			out = new ObjectOutputStream(socket.getOutputStream());
	        in = new ObjectInputStream(socket.getInputStream());

			running = true;
			
			setUser(new Entity(Entity.NAME, System.getProperty("user.name")));
			
			while(running){
				
				Entity o = (Entity) in.readObject();
				
				switch(o.getType()){
				case Entity.SCORE:
					setScore(o.getValue());
					break;
				case Entity.CLICK:
					splash(o);
					break;
				case Entity.HEALTH:
					display("Health updated.");
					ScorePanel.getInstance().setHealth(o.getValue());
					break;
				case Entity.STAGE:
					display("Nothing to do with stages yet.");
					//ScorePanel.getInstance().s
				}
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void splash(Entity o){
		//display("Click received from server " + o.toString());
		ScorePanel.getInstance().updateScore(100, 1);
		if(ScorePanel.getInstance().getHealth() <= 0){
			Monster.getInstance().dead();
		} else {
			Monster.getInstance().hit();
		}
		
		/*
		 * TODO
		 * Draw splash on screen with random ±10x ±10y
		 * 
		 */
	}
	
	public void setUser(Entity e){
		try {
			out.writeObject(e);
		} catch (Exception exc) {
		}
	}
	
	public void setHealth(Entity e){
		try {
			out.writeObject(e);
		} catch (Exception exc) {
		}
	}
	
	public void setScore(int score){
		ScorePanel.getInstance().setScore(score);
	}
	

	
	public boolean sendClick(Entity e){
		try {
			out.writeObject(e);
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
	
	public int getID(){
		return id;
	}
	
	public void display(String s){
		System.out.println(d.now() + " - Client - " + s);
	}
	
	public static Client newInstance(String host, int port){
		if(instance == null){
			instance = new Client(host, port);
		}
		return instance;
	}
	
	public static Client getInstance(){
		return instance;
	}

}
