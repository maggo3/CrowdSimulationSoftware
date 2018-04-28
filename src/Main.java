import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aquafx_project.AquaFx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage window;
	private Scene welcomeScene, simulationScene;
	private Button startBtn;
	private Label frameLbl;
	
	static Random random = new Random();
	Layer playground;
	//List<Attractor> allAttractors = new ArrayList<Attractor>();
	List<Human> allHumans = new ArrayList<Human>();
	
	private AnimationTimer animationTimer;
	private long lastTime, diffTime;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//set Style
		AquaFx.style();
		window = primaryStage;
		
		makeWelcomeScreen();	
		
		//events
		startBtn.setOnAction(e -> {
			makeSimulationScreen();
			window.setScene(simulationScene);
			setUpGame();
			
			for (Sprite human : allHumans) {
				human.display();
				System.out.println("X= "+human.getLocation().x + ", Y=" +human.getLocation().y);
			}
			
			startGame();
		});
	
		
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

	private void startGame() {
		animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				diffTime = 1000000000 / (now - lastTime);
				frameLbl.setText("FPS: " + diffTime);
				
				allHumans.forEach(Sprite::move);
				allHumans.forEach(Sprite::display);
				
				lastTime = now;
			}
			
		};
		
		animationTimer.start();
	}

	private void setUpGame() {
		//add humans
        for( int i = 0; i < Settings.HUMAN_COUNT; i++) {
            addHumans();
        }

        //add attractors	
        
	}

	private void addHumans() {
		//Layer layer = playground;
		
		//random location
		double x = random.nextDouble() * playground.getWidth();
		double y = random.nextDouble() * playground.getHeight();
		
		//dimension
		double width = 10; //50 for triangle
        double height = width / 2.0;

        //create human data
        Vector2D location = new Vector2D(x,y);
        Vector2D velocity = new Vector2D(0,0);
        Vector2D acceleration = new Vector2D(0,0);

        //create sprite and add to layer
        Human human = new Human(playground, location, velocity, acceleration, width, height);

        //register human
        allHumans.add(human);
	}

	private void makeSimulationScreen() {
		UI simulationUI = new UI(window);
		simulationScene = simulationUI.setUp();
		simulationUI.setBackground("grid.gif");
		BorderPane root = simulationUI.getLayout();
		playground = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		Pane layerPane = new Pane();
		frameLbl = new Label("FPS: 0");
		frameLbl.setStyle("-fx-padding: 10px");
		layerPane.getChildren().addAll(playground, frameLbl);
		root.setCenter(layerPane);
		simulationUI.createDragAndZoomEvens(simulationScene, playground);
	}

	private void makeWelcomeScreen() {
		UI welcomeUI = new UI(window);
		welcomeScene = welcomeUI.setUp();
		welcomeUI.setBackground("Title.jpg");	

		VBox centerMenu = new VBox();
		startBtn = new Button("Start");
		centerMenu.setAlignment(Pos.CENTER);
		//start.setStyle("-fx-background-color: #333333;");
		centerMenu.getChildren().addAll(startBtn);
		welcomeUI.getLayout().setCenter(centerMenu);
		
		window.setScene(welcomeScene);
		window.show();
	}
}
