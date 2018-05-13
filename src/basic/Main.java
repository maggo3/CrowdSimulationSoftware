package basic;
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
import rules.AimAttractorRule;
import rules.AlignmentRule;
import rules.AvoidObstacleRule;
import rules.CohesionRule;
import rules.KeepDistanceRule;
import rules.ObserveWorldRule;

public class Main extends Application {
	
	private Stage window;
	private Scene welcomeScene, simulationScene;
	private Button startBtn;
	private Label frameLbl;
	Vector2D mouseLocation = new Vector2D( 0, 0);
	static Random random = new Random();
	Layer playground;
	List<Attractor> allAttractors = new ArrayList<Attractor>();
	List<Human> allHumans = new ArrayList<Human>();
	List<Avoid> allAvoids = new ArrayList<Avoid>();
	private FlockManager flockManager;
	private AnimationTimer animationTimer;
	private long lastTime, diffTime;
	private World w;
	private Flock f1;
	private AimAttractorRule aar;
	
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
			}
			allAttractors.forEach(a -> {
				//a.display();
			});
			
			//listeners
			addListeners();
			
			//start the simulation
			startGame();
		});
	}

	private void addListeners() {
		simulationScene.setOnMousePressed(e -> {
			mouseLocation.x = e.getX();
			mouseLocation.y = e.getY();
			//System.out.println(mouseLocation);
			
			if ( allAttractors.size() > 0 ) {
				f1.removeRule(aar);
				allAttractors.get(0).removeFromDisplay();
				allAttractors.remove(0);
			}
			addAttractors();
			aar = new AimAttractorRule(allAttractors.get(0));
			f1.addRule(aar);
			allAttractors.get(0).setLocation(mouseLocation.x, mouseLocation.y);
			//allAttractors.get(0).display();
		});
		
		
	}

	private void startGame() {
		animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				//FPS
				diffTime = 1000000000 / (now - lastTime);
				frameLbl.setText("FPS: " + diffTime);
				
				//Logic
				flockManager.update();
				
/**
 * this is an old method that works with more attractors
 */
//				allHumans.forEach(human -> {
//					//human.seek(attractor.getLocation());
//					allAttractors.forEach(attractor -> {
//						human.seek(attractor.getLocation());
//					});
//					human.move();
//					human.display();
//				});
				
				allAttractors.forEach(Sprite::display);
				allAvoids.forEach(Sprite::display);
				
				//FPS
				lastTime = now;
			}
			
		};
		
		animationTimer.start();
	}

	private void setUpGame() {
		//add World
		w = new World(playground);
		
        //add Attractors
		/*
        for(int i = 0; i < Settings.ATTRACTOR_COUNT; i++) {
            addAttractors();
        }
        */
        
        //add Avoids
        for (int i = 0; i < Settings.AVOID_COUNT; i++) {
            addAvoid();
        }
        
        //add FlockManager
        flockManager = new FlockManager();
        f1 = new Flock(Settings.HUMAN_COUNT * 50, playground);
        
        //f1.addRule(new AimAttractorRule(allAttractors.get(0)));
        f1.addRule(new KeepDistanceRule(Settings.KEEP_DISTANCE_DISTANCE));
        f1.addRule(new CohesionRule(Settings.COHESION_DISTANCE));
        f1.addRule(new AvoidObstacleRule(Settings.AVOID_OBSTACLE_DISTANCE, allAvoids));
        //f1.addRule(new AlignmentRule());
        //f1.addRule(new ObserveWorldRule(w));
        
        flockManager.add(f1);
        
	}

	private void addAvoid() {
		//Layer layer = playground;
		
		//center Avoid
		double x = playground.getWidth()/2;
		double y = playground.getHeight()/2;
		
		//dimensions
		double width = 10; //100
		double height = 10; //100
		
		//create Attractor data
		Vector2D location = new Vector2D(x,y);
		Vector2D velocity = new Vector2D(0,0);
		Vector2D acceleration = new Vector2D(0,0);
		
		//add Attractor and add to layer
		Avoid avoid = new Avoid(playground, location, velocity, acceleration, width, height);
		
		//register Avoid
		allAvoids.add(avoid);
	}

	private void addAttractors() {
		//Layer layer = playground;
		
		//center Attractor
		//double x = 100; //playground.getWidth()/2;
		//double y = 100; //playground.getHeight()/2;
		double x = random.nextDouble() * playground.getWidth();
		double y = random.nextDouble() * playground.getHeight();
		
		//dimensions
		double width = 10; //100
		double height = 10; //100
		
		//create Attractor data
		Vector2D location = new Vector2D(x,y);
		Vector2D velocity = new Vector2D(0,0);
		Vector2D acceleration = new Vector2D(0,0);
		
		//add Attractor and add to layer
		Attractor attractor = new Attractor(playground, location, velocity, acceleration, width, height);
		
		//register Attractor
		allAttractors.add(attractor);		
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
		//simulationUI.createDragAndZoomEvens(simulationScene, playground);
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
