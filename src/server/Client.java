package server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Client implements Runnable{
	
	
	private Socket socket;
	private int id;
	private int port;
	private String host;
	private String response;
	private boolean running;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private CurrentDate d = new CurrentDate();
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			display("Connecting to: " + host);
			
			socket = new Socket(host, port);

			out = new ObjectOutputStream(socket.getOutputStream());
	        in = new ObjectInputStream(socket.getInputStream());

			running = true;
			
			sendClick(new Click(250, 500));
			sendClick(new Click(223, 525));
			
			while(running){
				display("Waiting for incoming clicks from server..");
				Click o = (Click) in.readObject();
				splash(o);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void splash(Click o){
		display("Click received from server " + o.toString());
	}
	
	public boolean sendClick(Click o){
		try {
			out.writeObject(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public int getID(){
		return id;
	}
	
	public void display(String s){
		System.out.println(d.now() + " - Client - " + s);
	}

}
