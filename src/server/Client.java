package server;
import game.ScorePanel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client implements Runnable{
	
	private static Client instance;

	private int port;
	private String host;
	private Socket socket;
	private boolean running;
	private BufferedReader is = null;
	private BufferedReader br = null;
	private DataOutputStream os = null;
	private String message;
	private String response;

	private Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	

	public void start(){
		this.run();
	}

	@Override
	public void run() {
		try {
			System.out.println("Client - Connecting to " + host + ": " + port);
			socket = new Socket(host, port);

			is = new BufferedReader(new InputStreamReader(System.in));

			os = new DataOutputStream(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			running = true;
			
			while(running){
//				message = is.readLine();
//				System.out.println("Client - input received");

//				os.writeBytes(message + '\n');
//				System.out.println("Client - message was sent to server");
				
				response = br.readLine();
				System.out.println("Client - received response from server: " + response);
				//ScorePanel.getInstance().updateScore(1, 10);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Client - Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
	}
	
	public void splash(int x, int y){
		if(is != null && os != null && br != null){
			try {
				message = Integer.toString(x) + ',' + Integer.toString(y) + '\n';
				os.writeBytes(message);
				
				//response = br.readLine();
				ScorePanel.getInstance().updateScore(1, 10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
		System.out.println("Client - Stopping Client");
	}
	
	
	public static Client getInstance(){
		return instance;
	}
	
	public static Client newInstance(String host, int port){
		if(instance == null){
			instance = new Client(host, port);
		}
		return instance;
	}

}
