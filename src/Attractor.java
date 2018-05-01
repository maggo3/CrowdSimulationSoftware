import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Attractor extends Sprite {

	public Attractor(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width,
			double height) {
		super(layer, location, velocity, acceleration, width, height);
	}

	@Override
	public Node createView() {
		Circle c = new Circle(10);
		c.setStroke(Color.GREEN);
        c.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.3));
		return c;
	}

}
