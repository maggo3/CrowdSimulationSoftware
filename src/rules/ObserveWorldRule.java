package rules;

import java.util.ArrayList;

import basic.Human;
import basic.Vector2D;
import basic.World;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ObserveWorldRule extends Rule {

	private World w;
	
	public ObserveWorldRule(World w) {
		this.w = w;
	}

	@Override
	public Vector2D change(Human h, ArrayList<Human> flock) {
		
		
		//System.out.println(h.getObject());
		Shape intersect = Shape.intersect(h.getObject(), w.getObject());
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            //collisionDetected = true;
        	System.out.println("yes");
        	w.getObject().setFill(Color.ALICEBLUE);
         }
       
		return new Vector2D(0,0);
	}

}
