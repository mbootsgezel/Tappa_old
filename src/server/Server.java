package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Server implements Runnable {

	private static int uniqueId;
	private int port;
	private int uniqueid;
	private boolean keepGoing;
	
	private ServerSocket serverSocket;
	
	private Socket socket = null;
	
	private ObjectInputStream in;
	
	private ArrayList <ClientConnection> clients;
	private CurrentDate d = new CurrentDate();

	public Server(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		keepGoing = true;
		clients = new ArrayList <ClientConnection>();
		try{
			
			serverSocket = new ServerSocket(port);
			
			display("Waiting for Clients on port " + port + ".");
			
			display("-------------- Server initiated --------------");
			
			while(keepGoing){

				clients.add(new ClientConnection(serverSocket.accept(), ++uniqueid));
				
				if(!keepGoing){
					break;
				}
			}
			

		} catch (IOException e) {
			display(e.toString());
		}
		
	}
	
	public boolean broadcastClick(Click o){
		try {
			for(int i = 0; i < clients.size(); i++){
				clients.get(i).sendClick(o);
			}
			return true;
		} catch (Exception e){
			return false;
		}
	}

	protected void stop() {
		keepGoing = false;
		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {

		}
	}

	public void display(String s){
		System.out.println(d.now() + " - Server - " + s);
	}

}
