package basic;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {
	
	Vector2D velocity;
	Vector2D acceleration;
	Vector2D location;
	
	double maxSpeed = Settings.SPRITE_MAX_SPEED;
	double maxForce = Settings.SPRITE_MAX_FORCE;
	
	Node view;
	
	double width;
	double height;
	double centerX;
	double centerY;
	double radius;
	
	double angle;
	
	Layer layer = null;
	
	public Sprite (Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
		this.layer = layer;
		this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
        
        setPrefSize(width, height);
        this.view = createView();
        
        getChildren().add(view);
        layer.getChildren().add(this);
	}

	public abstract Node createView();
	
	public void move() {
		//velocity.add(acceleration)
		velocity.limit(maxSpeed);
		location.add(velocity);
		angle = velocity.heading2D();
		acceleration.multiply(0);
	}
	
	public void display() {
        relocate(location.x - centerX, location.y - centerY);
        setRotate(Math.toDegrees(angle));
    }
	
	/*
	public void seek(Vector2D target) {
		Vector2D path = Vector2D.substract(target, location);
		
		double distance = path.distance();
		path.normalize();
		
		if (distance < Settings.SPRITE_SLOW_DOWN_DISTANCE) {
			//from 0 to 2
			double m = Utils.map(distance, 0, Settings.SPRITE_SLOW_DOWN_DISTANCE, 0, maxSpeed);
			path.multiply(m);
		} else {
			path.multiply(maxSpeed);
		}
		
		Vector2D steer = Vector2D.substract(path, velocity);
		steer.limit(maxForce);
		
		applyForce(steer);
	}
	*/

	/*
	private void applyForce(Vector2D force) {
		acceleration.add(force);
	}
	*/

	public Vector2D getLocation() {
		return location;
	}
	
	public void setLocation(double x, double y) {
		location.x = x;
		location.y = y;
	}
	
	public void setLocationOffset(double offsetX, double offsetY) {
		location.x += offsetX;
        location.y += offsetY;
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector2D vel) {
		velocity = vel;
	}
	
	public void addVelocity(Vector2D vel) {
		velocity.add(vel);
	}
		
	public Vector2D getAcceleration() {
		return acceleration;
	}
	
	public void setAcceleration(Vector2D accel) {
		acceleration = accel;
	}
}
