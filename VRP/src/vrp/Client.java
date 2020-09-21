/**
 * 
 */
package vrp;

import java.util.Random;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author airou
 *
 */
public class Client extends Circle{
	static Random random=new Random();
	int nom,x,y,demand;
	
	public Client(int nom,int x,int y,int demand) {
		super(x, y, 5);
		this.nom=nom;
		this.x=x;
		this.y=y;
		this.demand=demand;
		this.setScaleX(0.2);
		this.setScaleY(0.2);
		this.setFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		Tooltip.install(this,new Tooltip("Customer : "+nom+"\nDemand : "+this.demand));
	}
	
	@Override
	public String toString() {
		return "C,"+this.nom+","+this.x+","+this.y;
	}
}
