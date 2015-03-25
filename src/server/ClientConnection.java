package server;
import game.ScorePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection extends Thread {
	
	private Socket socket;
	private int id;
	private String username;
	private CurrentDate d = new CurrentDate();
	
	private boolean running;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientConnection(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
		try {
			display("Connection from: " + socket.getInetAddress().getHostAddress() + ", ID = " + id);
		} catch (Exception e) {
			display("Connected from unknown host.");
		}
		
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
	        in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			display("Failed to load IO: " + e);
		}
		this.start();
	}

	@Override
	public void run() {
		running = true;
		try {
			
			setScore();
			setHealth();
			setStage();
			
			while(running){
				
				Entity o = (Entity) in.readObject();
				
				try {
					switch(o.getType()){
						case Entity.NAME:
							username = o.getMessage();
							char first = Character.toUpperCase(username.charAt(0));
							username = first + username.substring(1);
							break;
						case Entity.HEALTH:
							Server.getInstance().setHealth(o.getValue());
							break;
						case Entity.STAGE:
							Server.getInstance().setStage(o.getValue());
							break;
						case Entity.CLICK:
							display(o.getClick().toString() + " by user: " + username + ", clientid: " + id);
							Server.getInstance().broadcastClick(o);
							break;
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				
				
			}
		} catch (Exception e){
			display(e.toString());
			display(e.getMessage());
			display(e.getCause().toString());
		}
		
	}
	
	public boolean sendClick(Entity e){
		try {
			out.writeObject(e);
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
	
	public void setScore(){
		try {
			out.writeObject(new Entity(Entity.SCORE, Server.getInstance().getScore()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setStage(){
		try {
			out.writeObject(new Entity(Entity.STAGE, Server.getInstance().getStage()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHealth(){
		try {
			out.writeObject(new Entity(Entity.HEALTH, Server.getInstance().getHealth()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getID(){
		return id;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void display(String s){
		System.out.println(d.now() + " - Server - " + s);
	}

}
