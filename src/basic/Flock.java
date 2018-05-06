package basic;
import java.util.ArrayList;
import java.util.Random;

import rules.Rule;

public class Flock {
	
	private ArrayList<Human> flock;
	private ArrayList<Rule> rules;
	private static Random random = new Random();
	private Layer playground;
	
	public Flock(int flockSize, Layer l) {
		this.playground = l;
		flock = new ArrayList<Human>();
		rules = new ArrayList<Rule>();
		for( int i = 0; i < flockSize; i++) { 
            addHumans();
        }
	}

	private void addHumans() {
		//Layer layer = playground;

		//random location
		double x = random.nextDouble() * playground.getWidth();
		double y = random.nextDouble() * playground.getHeight();
		
		//dimension
		double width = 2; //50 for triangle
        double height = 2; //width / 2.0; 

        //create human data
        Vector2D location = new Vector2D(x,y);
        Vector2D velocity = new Vector2D(0,0);
        Vector2D acceleration = new Vector2D(0,0);

        //create sprite and add to layer
        Human human = new Human(playground, location, velocity, acceleration, width, height);

        //register human
        flock.add(human);
	}

	public void update() {
		for ( Human h : flock) {
			//h.seek(new Vector2D(200,200));
			calculateVelocity(h);
			//limiSpeed(h);
		}
		for ( Human h : flock) {
			h.move();
			h.display();
		}
	}
	
	/*
	private void limiSpeed(Human h) {
		// TODO Auto-generated method stub
		
	}
	*/

	private void calculateVelocity(Human h) {
		Vector2D change = new Vector2D(0,0);
		for (Rule r : rules) {
			change.add(r.getChangeVector(h, flock)); 
		}
		h.addVelocity(change);
	}

	public void addRule(Rule r) {
		rules.add(r);
	}
}
