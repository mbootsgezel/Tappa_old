import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server implements Runnable{

	private ServerSocket serversocket;
	private Socket socket;
	private int port;
	private boolean running;
	private Scanner scanner;

	public Server(int port) {
		this.port = port;

	}

	public void start(){
		this.run();
	}

	@Override
	public void run() {

		running = true;
		try {
			while (running) {
				
				System.out.println("Server waiting for Clients on port: " + port+ ".");
				serversocket = new ServerSocket(port);
				socket = serversocket.accept();
				scanner = new Scanner(System.in);
				
				if(scanner.next().equals("stop")){
					this.stop();
				}
				
				if(!running){
					break;
				}
			}
			
			try {
				serversocket.close();
				System.out.println("Server closed");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void stop(){
		running = false;
		System.out.println("stoppign");
	}

}
