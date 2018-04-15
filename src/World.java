import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class World {
	
	private Group g;
	
	public World() {
		
	}

	public Group create() {
		g = new Group();
		
		Circle c = new Circle(50,Color.BLUE);
		c.setCenterX(500);
		c.setCenterY(500);
		Circle c2 = new Circle(20,Color.RED);
		c2.setCenterX(700);
		c2.setCenterY(750);
		g.getChildren().addAll(c,c2);
		//simulationUI.getLayout().getChildren().add(group);
		
		return g;
	}
}
