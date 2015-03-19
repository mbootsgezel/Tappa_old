import java.net.Socket;


public class Client implements Runnable{
	
	private int port;
	private String host;
	private Socket socket;
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
		System.out.println("New client created");
	}

	public void start(){
		this.run();
	}
	
	@Override
	public void run() {
		System.out.println("Client running");
		try {
			System.out.println("Connecting to " + host + ": " + port);
			socket = new Socket(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
	}

}
