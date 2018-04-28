import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Human extends Sprite {
	
	public Human(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
		super(layer, location, velocity, acceleration, width, height);
	}

	@Override
	public Node createView() {
		//return Utils.createArrowImageView( (int) width);
		Circle c = new Circle(10, Color.GREEN);
		return c;
	}
}
