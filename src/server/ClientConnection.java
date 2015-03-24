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
			
			while(running){
				
				Object o = in.readObject();
				
				try {
					username = (String) o;
					char first = Character.toUpperCase(username.charAt(0));
					username = first + username.substring(1);
				} catch (Exception e){
					display(o.toString() + " by user: " + username + ", clientid: " + id);
					
					Server.getInstance().broadcastClick((Click)o);
				}
				
				
			}
		} catch (Exception e){
			display(e.toString());
			display(e.getMessage());
			display(e.getCause().toString());
		}
		
	}
	
	public boolean sendClick(Click o){
		try {
			out.writeObject(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setScore(){
		try {
			out.writeObject(Server.getInstance().getScore());
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
