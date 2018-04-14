import com.aquafx_project.AquaFx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ZoomEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage window;
	
	private double dragDeltaX;
	private double dragDeltaY;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AquaFx.style();
		window = primaryStage;
		
		UI ui = new UI(window, 1400,800);
		Scene root = ui.setUp();
		
		window.setScene(root);
		window.show();
		
		Circle c = new Circle(50,Color.BLUE);
		c.setCenterX(500);
		c.setCenterY(500);
		Circle c2 = new Circle(20,Color.RED);
		c2.setCenterX(700);
		c2.setCenterY(750);
		Group g = new Group();
		g.getChildren().addAll(c,c2);
		ui.getLayout().getChildren().add(g);
		
		root.setOnZoom(new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {
				//c.setRadius(c.getRadius() * event.getZoomFactor());
				//g.s
				//ui.getLayout().setScaleX(ui.getLayout().getScaleX() * event.getZoomFactor());
				//ui.getLayout().setScaleY(ui.getLayout().getScaleY() * event.getZoomFactor());
				g.setScaleX(g.getScaleX() * event.getZoomFactor());
				g.setScaleY(g.getScaleY() * event.getZoomFactor());
			}
		});
		
		root.setOnZoom(e -> {
			g.setScaleX(g.getScaleX() * e.getZoomFactor());
			g.setScaleY(g.getScaleY() * e.getZoomFactor());
		});
		
		
		root.setOnMousePressed(e -> {
			dragDeltaX = g.getLayoutX() - e.getSceneX();
			dragDeltaY = g.getLayoutY() - e.getSceneY();
		});
		
		root.setOnMouseDragged(e -> {	
			g.setLayoutX(e.getSceneX() + dragDeltaX);
			g.setLayoutY(e.getSceneY() + dragDeltaY);
		});
		
		root.setOnMouseReleased(e -> {
			//
		});
		//Button b = new Button("OK");
		//b.setStyle("-fx-background-color: #333333;");
		//StackPane layout = new StackPane();
		//layout.getChildren().add(b);
		//b.setOnAction(e -> window.setScene(scene2));
		//b2.setOnAction(e -> window.setScene(scene1));
	}
}
