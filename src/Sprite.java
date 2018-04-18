
import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {
	
	Vector2D velocity;
	Vector2D acceleration;
	Vector2D location;
	
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
		//constant speed
		Vector2D speed = new Vector2D(2,2);
		location.add(speed);
	}
	
	public void display() {
        relocate(location.x - centerX, location.y - centerY);
        //setRotate(Math.toDegrees(angle));
    }
	
	public Vector2D getLocation() {
		return location;
	}
}
