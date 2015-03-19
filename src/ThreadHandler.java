
public class ThreadHandler implements Runnable{
	
	private static ThreadHandler instance;
	
	private ThreadHandler() {
		run();
	}

	@Override
	public void run() {
		//new Thread(new Client("172.19.82.155", 25000)).start();
		new Thread(new Server(25000)).start();
	}
	
	public static ThreadHandler getInstance(){
		if(instance == null){
			instance = new ThreadHandler();
		}
		return instance;
	}

}
