package basic;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class World extends Region {
	
	private Node view;
	private Rectangle rect;
	
	public World(Layer layer) {
		//double width = layer.getWidth();
		//double height = layer.getHeight();
		//setPrefSize(width, height);
        this.view = createView();
        
        getChildren().add(view);
        layer.getChildren().add(this);
	}
	
//	public void display() {
//        //relocate(location.x - centerX, location.y - centerY);
//		relocate(c.getCenterX(), c.getCenterY());
//    }
	
	private Node createView() {
		rect = new Rectangle();
		rect.setX(500);
		rect.setY(0);
		rect.setWidth(10);
		rect.setHeight(500);
		rect.setFill(Color.GRAY);
		//rect.setArcWidth(20);
		//rect.setArcHeight(20);
		return rect;
	}
	
}
