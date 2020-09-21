package vrp;

import java.util.Random;

import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;

public class Depot extends Circle {
	static Random random=new Random();
	int nom,x,y,demand;
	public Depot(int nom,int x,int y) {
		super(x, y, 2);
		this.x=x;
		this.y=y;
		Tooltip.install(this,new Tooltip("Depot"));
	}
	
	@Override
	public String toString() {
		return "D,"+this.nom+","+this.x+","+this.y;
	}
	
}


