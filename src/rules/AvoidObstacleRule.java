package rules;

import java.util.ArrayList;
import java.util.List;

import basic.Avoid;
import basic.Human;
import basic.Vector2D;

public class AvoidObstacleRule extends Rule {

	private final int DISTANCE;
	private final List<Avoid> avoids;
	
	public AvoidObstacleRule(int distance, List<Avoid> allAvoids) {
		this.DISTANCE = distance;
		this.avoids = allAvoids;
	}
	
	@Override
	public Vector2D change(Human human, ArrayList<Human> flock) {
		Vector2D steer = new Vector2D(0,0);
		int count = 0;
		for (Avoid other : avoids) {
			Vector2D path = Vector2D.substract(human.getLocation(), other.getLocation());
			double d = path.distance();
			if ( (d > 0) && (d < DISTANCE)) {
				//Vector2D diff = Vector2D.substract(human.getLocation(), other.getLocation());
				path.normalize();
				path.div(d);
				steer.add(path);
				count++;
			}
		}
		if (count > 0) {
			//steer.div(count);
		}
		return Vector2D.multScalar(steer, 1.0);
	}

}
