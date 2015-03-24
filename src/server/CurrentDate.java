package server;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private Date now = new Date();
	
	public String now(){
		return sdf.format(now);
	}

}
