package server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Connection implements Runnable{

	private Socket socket;
	private boolean running;
	private String line;
	private BufferedReader reader = null;
	private DataOutputStream output = null;

	public Connection(Socket socket) {
		System.out.println("Client connected!");
		this.socket = socket;
		this.run();
		
		
	}

	@Override
	public void run() {
		running = true;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());
				

			while(running) {

				line = reader.readLine();
				System.out.println("Server - client clicked @ " + line);

				output.writeBytes(line + "\n");
				System.out.println("Server - response sent to client");


				if(!running){
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	
	public void disconnect(){
		running = false;
	}


}
