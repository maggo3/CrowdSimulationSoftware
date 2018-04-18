import com.sun.javafx.geom.Vec2d;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {
	
	Vec2d velocity;
	Vec2d acceleration;
	Vec2d location;
	
	//double maxSpeed = Settings.SPRITE_MAX_SPEED;
	//double maxForce = Settings.SPRITE_MAX_FORCE;
	
	Node view;
	
	double width;
	double height;
	double centerX;
	double centerY;
	double radius;
	
	double angle;
	
	Layer layer = null;
	
	public Sprite (Layer layer, Vec2d location, Vec2d velocity, Vec2d acceleration, double width, double height) {
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
	
	public void display() {
        relocate(location.x - centerX, location.y - centerY);
        //setRotate(Math.toDegrees(angle));
    }
	
	public Vec2d getLocation() {
		return location;
	}
}
