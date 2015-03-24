package server;
import game.ScorePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection extends Thread {
	
	private Socket socket;
	private int id;
	private CurrentDate d = new CurrentDate();
	
	private boolean running;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private ObjectOutputStream serverOut;
	
	public ClientConnection(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
		try {
			display("Connected from: " + socket.getInetAddress().getHostName() + ", ID = " + id);
		} catch (Exception e) {
			display("Connected from unknown host.");
		}
		
		try {
			display("Getting IO for connection: " + id);
			out = new ObjectOutputStream(socket.getOutputStream());
	        in = new ObjectInputStream(socket.getInputStream());
	        //serverOut = new ObjectOutputStream();
	        display("Succesfully got IO for connection: " + id);
		} catch (IOException e) {
			display("Failed to load IO: " + e);
		}
		display("----------- Connection established -----------");
		this.start();
	}

	@Override
	public void run() {
		running = true;
		try {
			while(running){
				Click o = (Click) in.readObject();
				display(o.toString() + " by client: " + id);
				ScorePanel.getInstance().updateScore(1, 1);
				//sendClick(o);
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
	
	public int getID(){
		return id;
	}
	
	public void display(String s){
		System.out.println(d.now() + " - Server - " + s);
	}

}
