package server;
import game.Window;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Starter {
	
	public static void main(String[] args) {
		//new Thread(new Server(1500)).start();
		new Thread(new Client("172.19.86.183", 1500)).start();
		new Thread(new Window()).start();
		//new Thread(new Client("172.19.86.183", 1500)).start();
		//new Thread(new Client("172.19.86.183", 1500)).start();

	}

}
