package server;
import game.Window;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Server implements Runnable{
	
	private static Server instance;

	private ServerSocket serversocket = null;
	private Socket socket = null;
	private int port;
	private boolean running;
	private boolean acceptMore;
	private Scanner scanner;
	private BufferedReader br = null;
	private DataOutputStream os = null;
	private String line;
	private ConnectionHandler connections;

	private Server(int port) {
		this.port = port;

	}

	public void start(){
		this.run();
	}

	@Override
	public void run() {
		
		acceptMore = true;
		
		new Thread(connections = new ConnectionHandler()).start();
		
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Server - Server waiting for Clients on port: " + port+ ".");

			while(acceptMore){
				socket = serversocket.accept();
				new Thread(new SocketThread(socket));
			}
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("Server - Server is running ");
			
			running = true;
			
			while(running) {
				
				line = br.readLine();
				System.out.println("Server - client clicked @ " + line);
				
				os.writeBytes(line + "\n");
				System.out.println("Server - response sent to client");
				

				if(!running){
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try {
                serversocket.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
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
