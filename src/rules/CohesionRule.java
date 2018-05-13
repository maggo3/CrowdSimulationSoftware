package rules;

import java.util.ArrayList;

import basic.Human;
import basic.Vector2D;

public class CohesionRule extends Rule {
	
	private final double DISTANCE;
	
	public CohesionRule(double distance) {
		this.DISTANCE = distance;
	}
	
	@Override
	public Vector2D change(Human human, ArrayList<Human> flock) {
		
		Vector2D sum = new Vector2D(0,0);
		int count = 0;
		
		for (Human other : flock) {
			Vector2D path = Vector2D.substract(other.getLocation(), human.getLocation());
			double d = path.distance();
			
			if ((d > 0) && (d < DISTANCE)) {
				sum.add(other.getLocation());
				count++;
			}
		}
		
		if (count > 0) {
			sum.div(count);
			Vector2D desired = Vector2D.substract(sum, human.getLocation());
			desired.limit(0.05);
			return Vector2D.multScalar(desired, 0.5);
		} else {
			return new Vector2D(0,0);
		}
	}

}
