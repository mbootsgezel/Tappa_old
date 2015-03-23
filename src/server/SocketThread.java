package server;

import game.ScorePanel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketThread implements Runnable{

	private BufferedReader br = null;
	private DataOutputStream os = null;
	private String line;
	private Socket socket;
	private boolean running;

	public SocketThread(Socket socket) {
		System.out.println("Server - Connection accepted");
		this.socket = socket;
		this.run();
	}

	@Override
	public void run() {
		try{
			running = true;
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new DataOutputStream(socket.getOutputStream());
			
			while(running) {
				
				line = br.readLine();
				System.out.println("Server - client clicked @ " + line);
				
				os.writeBytes(line + "\n");
				System.out.println("Server - response sent to client");
				

				if(!running){
					break;
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}
	
	public DataOutputStream getOutput(){
		return os;
	}

}
