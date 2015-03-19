package server;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;


public class Server implements Runnable{
	
	private static Server instance;

	private ServerSocket serversocket = null;
	private Socket socket = null;
	private int port;
	private boolean running;
	private Scanner scanner;
	private BufferedReader br = null;
	private DataOutputStream os = null;
	private String line;

	private Server(int port) {
		this.port = port;

	}

	public void start(){
		this.run();
	}

	@Override
	public void run() {

		try {
			serversocket = new ServerSocket(port);
			System.out.println("Server - Server waiting for Clients on port: " + port+ ".");

			socket = serversocket.accept();
			System.out.println("Server - Connection accepted");
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new DataOutputStream(socket.getOutputStream());
			System.out.println("Server - Server is running ");
			
			running = true;
			
			while(running) {
				
				line = br.readLine();
				System.out.println("Server - message received from client");
				System.out.println("Server - client clicked @ " + line);
				
				os.writeBytes(line + "\n");
				System.out.println("Server - response sent to client");
				

				if(!running){
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop(){
		running = false;
		System.out.println("Server - Stopping Server");

		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {

		}
	}
	
	public static Server getInstance(){
		return instance;
	}
	
	public static Server newInstance(int port){
		if(instance == null){
			instance = new Server(port);
		}
		return instance;
	}

}
