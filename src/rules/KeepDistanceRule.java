package rules;

import java.util.ArrayList;

import basic.Flock;
import basic.Human;
import basic.Vector2D;

public class KeepDistanceRule extends Rule {

	private final int DISTANCE;
	private final Flock FLOCK;
	
	public KeepDistanceRule(int distance, Flock flock) {
		this.DISTANCE = distance;
		this.FLOCK = flock;
	}

	@Override
	public Vector2D change(Human human, ArrayList<Human> flock) {
		Vector2D vel = human.getVelocity();
		Vector2D accel = human.getAcceleration();
		
		flock.forEach(h -> {
			if (h != human) {
				Vector2D path = Vector2D.substract(h.getLocation(), human.getLocation());
				double distance = path.distance();
				
				if ( distance <= DISTANCE ) {
					Vector2D steer = Vector2D.substract(path, human.getVelocity());
					steer.limit(0.1);
					accel.sub(steer); 
				}
			}
		});
		return accel.multScalar(accel, 0.5);
	}
}
