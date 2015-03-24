package server;

public class User {
	
	private String username;
	
	public User(String username) {
		this.username = username;
	}
	
	public String getName(){
		return username;
	}

}
