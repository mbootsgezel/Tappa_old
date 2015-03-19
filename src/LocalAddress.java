import java.net.InetAddress;
import java.net.UnknownHostException;


public class LocalAddress {
	
	public LocalAddress() {
		try {
			InetAddress IP = InetAddress.getLocalHost();
			System.out.println("IP of my system is = "+IP.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
