package basic;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Human extends Sprite {
	
	private Circle c;
	
	public Human(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
		super(layer, location, velocity, acceleration, width, height);
	}

	public Shape getObject() {
		return c;
	}
	
	@Override
	public Node createView() {
		//return Utils.createArrowImageView( (int) width);
		//size Human 0.5 minimun to see
		c = new Circle(4, Color.BLUE);
		return c;
	}
}
