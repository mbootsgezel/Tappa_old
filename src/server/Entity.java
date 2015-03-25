package server;

import java.io.Serializable;

public class Entity implements Serializable{
	
	protected static final long serialVersionUID = 1L;
	
	public static final int CLICK = 0, LEVEL = 1, STAGE = 2, SCORE = 3, HEALTH = 4, NAME = 5;
	private int type;
	private String message;
	
	private static int value;
	
	private Click c;
	
	public Entity(int type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public Entity(int type, int msg){
		this.type = type;
		value = msg;
	}
	
	public Entity(int type, Click o){
		this.type = type;
		this.c = o;
	}
	
	public Click getClick(){
		return c;
	}
	
	public int getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}
	public int getValue(){
		return value;
	}

}
