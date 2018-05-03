package rules;

import java.util.ArrayList;

import basic.Flock;
import basic.Human;
import basic.Settings;
import basic.Utils;
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
//		flock.forEach(h -> {
//			if (h != human) {
//				Vector2D path = Vector2D.substract(h.getLocation(), human.getLocation());
//				double distance = path.distance();
//				//path.normalize();
////				if (distance < Settings.SPRITE_SLOW_DOWN_DISTANCE) {
////				//from 0 to 2
////				double m = Utils.map(distance, 0, Settings.SPRITE_SLOW_DOWN_DISTANCE, 0, 2);
////				path.multiply(m);
////				} else {
////				path.multiply(2);
////				}
//				if ( distance > 0 && distance <= DISTANCE ) {
//					Vector2D steer = Vector2D.substract(path, human.getVelocity());
//					steer.limit(0.1);
//					accel.sub(steer); 
//				}
//			}
//		});
//		return accel.multScalar(accel, 0.5);
		
		Vector2D steer = new Vector2D(0,0);
		int count = 0;
		for (Human other : flock) {
			Vector2D path = Vector2D.substract(human.getLocation(), other.getLocation());
			double d = path.distance();
			if ( d>0 && d < DISTANCE) {
				Vector2D diff = Vector2D.substract(human.getLocation(), other.getLocation());
				diff.normalize();
				diff.multiply(1/d);
				steer.add(diff);
				count++;
			}
		}
		return steer;
	}
}
