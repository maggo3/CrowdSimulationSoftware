package basic;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Region {

	private ArrayList<Human> humanArray;
	private ArrayList<Avoid> avoidArray;
	private double startX;
	private double startY;
	private double widthh;
	private double heightt;
	
	public Cell(Layer layer, String name, int id, double x, double y, double width, double height) {
		
		this.startX = x;
		this.startY = y;
		this.widthh = width;
		this.heightt = height;
		
		//create Rectangle
		Rectangle rectangle = new Rectangle(width, height);
        rectangle.setStroke(Color.GREY);
        rectangle.setFill(Color.LIGHTSTEELBLUE);
        
        //create Lable
        Label label = new Label(name + " ID=" + id);
        label.setScaleX(0.6);
        label.setScaleY(0.6);
        
        //set position
        relocate(x, y);

        getChildren().addAll(rectangle, label);
        
        layer.getChildren().add(this);
        
        //ArrayList
        humanArray = new ArrayList<Human>();
        avoidArray = new ArrayList<Avoid>();
	}
	
	public void addHuman(Human h) {
		humanArray.add(h);
	}
	
	public void removeHuman(Human h) {
		humanArray.remove(h);
	}
	
	public void addAvoid(Avoid a) {
		avoidArray.add(a);
	}
	
	public void removeAvoid(Avoid a) {
		avoidArray.remove(a);
	}
	
	public ArrayList<Human> getHumans() {
		return humanArray;
	}

	public boolean has(Vector2D location) {
		double x = location.x;
		double y = location.y;
		
		if (x >= startX && x < (startX + widthh)) {
			if (y >= startY && y < (startY + heightt)) {
				return true;
			}
		} 
		
		return false;
	}
}
