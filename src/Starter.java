
public class Starter {
	
	public static void main(String args[]){
		
		ThreadHandler th = ThreadHandler.getInstance();
		
		public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			Server server = new Server(25000);
			new Thread(server).start();
			
			String s = scanner.next();
			
			
			if(s.equals("stop")){
				server.stop();
			}

		}
		
	}

}
