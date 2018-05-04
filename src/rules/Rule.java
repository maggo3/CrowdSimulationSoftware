package rules;

import java.util.ArrayList;

import basic.Human;
import basic.Vector2D;

public abstract class Rule {
	
	private double weight;
	
	public Rule() {
		this.weight = 1;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

	public Vector2D getChangeVector(Human h, ArrayList<Human> flock) {
		return Vector2D.multScalar(change(h,flock),weight);
	}
	
	public abstract Vector2D change(Human h, ArrayList<Human> flock);
}
