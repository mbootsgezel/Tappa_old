import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Server implements Runnable{

	private ServerSocket serversocket = null;
	private Socket socket = null;
	private int port;
	private boolean running;
//	private Scanner scanner;
	private DataInputStream is = null;
	private PrintStream os = null;
	private String line;

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
			
//			while(running){
//				socket = serversocket.accept();
//				System.out.println("sUcc");
//			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			System.out.println("b4 acc");
			socket = serversocket.accept();
			is = new DataInputStream(socket.getInputStream());
			os = new PrintStream(socket.getOutputStream());
			System.out.println("after acc");
			
			while(running){
				line = is.readLine();
				os.println(line);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
//		try{
//			socket = serversocket.accept();
//			System.out.println("succ2");
////			scanner = new Scanner(System.in);
//
//			is = new DataInputStream(socket.getInputStream());
//			os = new PrintStream(socket.getOutputStream());
//			
//			while (running) {	
//				System.out.println("derp");
//				System.out.println(is.readLine());
//				
////				if(scanner.next().equals("stop")){
////					this.stop();
////				}
//				
//				if(!running){
//					break;
//				}
//			}
//			
//			try {
//				serversocket.close();
//				System.out.println("Server closed");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}catch(IOException e){
//			e.printStackTrace();
//		}

	}
	
	public void stop(){
		running = false;
		System.out.println("stoppign");
	}

}
