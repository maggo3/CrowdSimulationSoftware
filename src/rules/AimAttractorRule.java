package rules;

import java.util.ArrayList;

import basic.Attractor;
import basic.Human;
import basic.Settings;
import basic.Utils;
import basic.Vector2D;

public class AimAttractorRule extends Rule {
	
	private Attractor attractor;
	
	public AimAttractorRule(Attractor attr) {
		this.attractor = attr;
	}

	@Override
	public Vector2D change(Human h, ArrayList<Human> flock) {
		Vector2D path = Vector2D.substract(attractor.getLocation(), h.getLocation());
		
		double distance = path.distance();
		path.normalize();
		
		if (distance < Settings.SPRITE_SLOW_DOWN_DISTANCE) {
			//from 0 to 2
			double m = Utils.map(distance, 0, Settings.SPRITE_SLOW_DOWN_DISTANCE, 0, 2);
			path.multiply(m);
		} else {
			path.multiply(2);
		}
		
		Vector2D steer = Vector2D.substract(path, h.getVelocity());
		steer.limit(0.1);
		
		Vector2D accel = h.getAcceleration();
		accel.add(steer);
		
		return Vector2D.multScalar(accel, 1.0);
	}
}
