package basic;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Avoid extends Sprite {

	public Avoid(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width,
			double height) {
		super(layer, location, velocity, acceleration, width, height);
	}

	@Override
	public Node createView() {
		Circle c = new Circle(10);
		c.setStroke(Color.RED);
        c.setFill(Color.RED.deriveColor(1, 1, 1, 0.8));
		return c;
	}

}
