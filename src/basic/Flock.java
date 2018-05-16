package basic;
import java.util.ArrayList;
import java.util.Random;

import rules.Rule;

public class Flock {
	
	private ArrayList<Human> flock;
	private ArrayList<Rule> rules;
	private static Random random = new Random();
	private Layer playground;
	private ArrayList<Cell> cells;
	
	public Flock(int flockSize, Layer l, ArrayList<Cell> grid) {
		this.playground = l;
		this.cells = grid;
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
		double width = 5; //50 for triangle
        double height = 5; //width / 2.0; 

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
			manageCells(h);
			ArrayList<Cell> nearCells = getCellsFromHumanPosition(h.getLocation());
			for (Cell c : nearCells) {
				calculateVelocity(h, c);
			}
			//limiSpeed(h);
		}
		for ( Human h : flock) {
			h.move();
			h.display();
		}
	}
	
	private void manageCells(Human h) {
		for (Cell c : cells) {
			if (c.has(h.getLocation())) {
				if(!c.getHumans().contains(h)) {
					c.addHuman(h);
					System.out.println("now Add");
				}
			} else {
				if( c.getHumans().contains(h)) {
					if (c.getHumans().size() > 0) 
						c.removeHuman(h);
						System.out.println("now Remove");
				}
			}
		}
	}

	private ArrayList<Cell> getCellsFromHumanPosition(Vector2D location) {
		
		return cells;
	}

	/*
	private void limiSpeed(Human h) {
		// TODO Auto-generated method stub
		
	}
	*/

	private void calculateVelocity(Human h, Cell cell) {
		Vector2D change = new Vector2D(0,0);
		for (Rule r : rules) {
			change.add(r.getChangeVector(h, cell.getHumans())); 
		}
		h.addVelocity(change);
	}

	public void addRule(Rule r) {
		rules.add(r);
	}
	
	public void removeRule(Rule r) {
		rules.remove(r);
	}
}
