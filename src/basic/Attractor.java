package basic;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Attractor extends Sprite {

	private Circle c;
	
	
	public Attractor(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width,
			double height) {
		super(layer, location, velocity, acceleration, width, height);
	}

	@Override
	public Node createView() {
		c = new Circle(10);
		c.setStroke(Color.GREEN);
        c.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.3));
		return c;
	}

	public void removeFromDisplay() {
		getChildren().remove(c);
	}

}
