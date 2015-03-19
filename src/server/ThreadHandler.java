package server;

import game.Window;

public class ThreadHandler implements Runnable{
	
	private static ThreadHandler instance;
	
	private Server server;
	private Client client;
	private Window window;
	
	private ThreadHandler() {
		run();
	}

	@Override
	public void run() {
		
	}
	
	public Window newWindow(){
		new Thread(window = new Window()).start();
		return window;
	}
	
	public Server newServer(){
		new Thread(server = Server.newInstance(1500)).start();
		return server;
	}
	
	public Server newServer(int port){
		new Thread(server = Server.newInstance(port)).start();
		return server;
	}
	
	public Client newClient(){
		new Thread(client = Client.newInstance("localhost", 1500)).start();
		return client;
	}
	
	public Client newClient(String host, int port){
		new Thread(client = Client.newInstance(host, port)).start();
		return client;
	}
	
	public static ThreadHandler getInstance(){
		if(instance == null){
			instance = new ThreadHandler();
		}
		return instance;
	}

}
