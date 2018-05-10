package basic;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
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
		double a = 0.7 * width;
		double b = 0.2 * width;
		double strokeWidth = 0.2 * width;
		
		Group g = new Group();
		c = new Circle(width, Color.BLUE);
		
		Line l1 = new Line();
		l1.setStartX(-a);
		l1.setStartY(b);
		l1.setEndX(0);
		l1.setEndY(-a);
		
		Line l2 = new Line();
		l2.setStartX(0);
		l2.setStartY(-a);
		l2.setEndX(a);
		l2.setEndY(b);
		
		l1.setStrokeWidth(strokeWidth);
		l2.setStrokeWidth(strokeWidth);
		
		l1.setStroke(Color.WHITE);
		l2.setStroke(Color.WHITE);
		
		g.getChildren().addAll(c, l1, l2);
		
		return g;
	}
}
