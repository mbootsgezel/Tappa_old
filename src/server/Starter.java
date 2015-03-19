package server;

public class Starter {
	
	public static void main(String args[]){
		
		new LocalAddress();
		
		ThreadHandler th = ThreadHandler.getInstance();
		
		th.newWindow();
		//th.newServer();
		th.newClient("buzzgaming.nl", 1500);
		
	}

}
