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
	private UI simulationUI;
	private Scene simulationScene;
	private Button startBtn;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//set Style
		AquaFx.style();
		window = primaryStage;
		
		//make Screens
		makeWelcomeScreen();	
		makeSimulationScreen();
		
		//events
		startBtn.setOnAction(e -> window.setScene(simulationScene));
	
		//for Testing
		/*
		Circle c = new Circle(50,Color.BLUE);
		c.setCenterX(500);
		c.setCenterY(500);
		Circle c2 = new Circle(20,Color.RED);
		c2.setCenterX(700);
		c2.setCenterY(750);
		group.getChildren().addAll(c,c2);
		simulationUI.getLayout().getChildren().add(group);
		*/
		/*
		Image img = new Image("file:grid.gif");
		ImageView iv = new ImageView();
		iv.setImage(img);
		iv.setFitWidth(window.getWidth());
		iv.setPreserveRatio(true);
		g.getChildren().add(iv);
		ui2.getLayout().getChildren().add(g);
		*/	
	}

	private void makeSimulationScreen() {
		simulationUI = new UI(window, 1400,800);
		simulationScene = simulationUI.setUp();
		World world1 = new World();
		Group group = world1.create();
		simulationUI.getLayout().getChildren().add(group);
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
		startBtn = new Button("Start");
		centerMenu.setAlignment(Pos.CENTER);
		//start.setStyle("-fx-background-color: #333333;");
		centerMenu.getChildren().addAll(startBtn);
		topMenu.getChildren().addAll(b);
		
		//welcomeUI.getLayout().setCenter(centerMenu);
		welcomeUI.getLayout().setTop(topMenu);
		welcomeUI.getLayout().setCenter(centerMenu);
		//ystem.out.println(welcomeUI.getLayout());
	}
}
