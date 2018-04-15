import com.aquafx_project.AquaFx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage window;
	private Group group;
	private UI simulationUI;
	private Scene simulationScene;
	private Button start;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AquaFx.style();
		window = primaryStage;
		
		makeWelcomeScreen();
		
		group = new Group();
		makeSimulationScreen();
		
		/*
		Image img = new Image("file:grid.jpg");
		ImageView iv = new ImageView();
		iv.setImage(img);
		iv.setFitWidth(window.getWidth());
		iv.setPreserveRatio(true);
		g.getChildren().add(iv);
		ui2.getLayout().getChildren().add(g);
		*/
	
		Circle c = new Circle(50,Color.BLUE);
		c.setCenterX(500);
		c.setCenterY(500);
		Circle c2 = new Circle(20,Color.RED);
		c2.setCenterX(700);
		c2.setCenterY(750);
		group.getChildren().addAll(c,c2);
		simulationUI.getLayout().getChildren().add(group);
		
		
		//Events
		start.setOnAction(e -> window.setScene(simulationScene));
		
	}

	private void makeSimulationScreen() {
		simulationUI = new UI(window, 1400,800);
		simulationScene = simulationUI.setUp();
		simulationUI.createDragAndZoomEvens(simulationScene, group);
		simulationUI.setBackground("grid.gif");
		//siulationUI.getLayout().setStyle("-fx-background-color: BLACK");
	}

	private void makeWelcomeScreen() {
		UI welcomeUI = new UI(window, 1400,800);
		Scene root = welcomeUI.setUp();
		welcomeUI.setBackground("Title.jpg");	
		
		window.setScene(root);
		window.show();
		
		VBox topMenu = new VBox();
		Button b = new Button("lol");
		VBox centerMenu = new VBox();
		start = new Button("Start");
		centerMenu.setAlignment(Pos.CENTER);
		//start.setStyle("-fx-background-color: #333333;");
		centerMenu.getChildren().addAll(start);
		topMenu.getChildren().addAll(b);
		
		//welcomeUI.getLayout().setCenter(centerMenu);
		welcomeUI.getLayout().setTop(topMenu);
		welcomeUI.getLayout().setCenter(centerMenu);
		//ystem.out.println(welcomeUI.getLayout());
	}
}
