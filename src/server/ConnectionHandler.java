package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable{

	private BufferedReader br = null;
	private DataOutputStream os = null;
	private boolean running;
	private String line;

	private ArrayList <SocketThread> sockets = new <SocketThread> ArrayList();

	public ConnectionHandler() {

		// TODO Auto-generated constructor stub
	}

	public void start(){
		this.run();
	}

	@Override
	public void run() {

		running = true;


		try{
			while(running) {
				
				for(int i = 0; i < sockets.size(); i++){
					//sockets.get(i).getOutput().writeBytes("test");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}



	}

	public void newConnection(SocketThread socket){
		System.out.println("Server - Connection accepted");
		sockets.add(socket);
	}

}
