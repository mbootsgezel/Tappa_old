package server;
import java.io.Serializable;


public class Click implements Serializable{
	
	protected static final long serialVersionUID = 1L;
	
	public int x;
	public int y;
	
	public Click(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "X=" + x + ", Y=" + y;
	}

}
