import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	private DataInputStream is = null;
	private DataOutputStream os = null;

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
			serversocket = new ServerSocket(port);
			System.out.println("Server waiting for Clients on port: " + port+ ".");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			socket = serversocket.accept();
			System.out.println("succ2");
//			scanner = new Scanner(System.in);

			is = new DataInputStream(socket.getInputStream());
			os = new DataOutputStream(socket.getOutputStream());
			
			while (running) {	
				System.out.println("derp");
				System.out.println(is.readLine());
				
//				if(scanner.next().equals("stop")){
//					this.stop();
//				}
				
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
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public void stop(){
		running = false;
		System.out.println("stoppign");
	}

}
