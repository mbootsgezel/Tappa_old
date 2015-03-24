package server;
import game.ScorePanel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


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
			display("Connecting to: " + host);
			socket = new Socket(host, port);
			
			out = new ObjectOutputStream(socket.getOutputStream());
	        in = new ObjectInputStream(socket.getInputStream());

			running = true;
			
			setUser(System.getProperty("user.name"));
			
			while(running){
				
				Object o = in.readObject();
				
				try {
					splash((Click)o);
				} catch (Exception e) {
					setScore((int)o);
				}
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void splash(Click o){
		//display("Click received from server " + o.toString());
		ScorePanel.getInstance().updateScore(1, 1);
		/*
		 * TODO
		 * Draw splash on screen with random ±10x ±10y
		 * 
		 */
	}
	
	public void setUser(String name){
		try {
			out.writeObject(name);
		} catch (Exception e) {
		}
	}
	
	public void setScore(int score){
		ScorePanel.getInstance().setScore(score);
	}
	
	public boolean sendClick(Click o){
		try {
			out.writeObject(o);
			return true;
		} catch (Exception e) {
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
