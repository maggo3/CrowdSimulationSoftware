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
	
	Vector2D mouseLocation = new Vector2D( 0, 0);
	
	static Random random = new Random();
	Layer playground;
	List<Attractor> allAttractors = new ArrayList<Attractor>();
	List<Human> allHumans = new ArrayList<Human>();
	private FlockManager flockManager;
	
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
				//System.out.println("HUMAN: X= "+human.getLocation().x + ", Y=" +human.getLocation().y);
			}
			allAttractors.forEach(a -> {
				a.display();
				//System.out.println("ATTRACTOR: X= "+a.getLocation().x + ", Y=" +a.getLocation().y);
			});
			
			addListeners();
			
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

	private void addListeners() {
		simulationScene.setOnMousePressed(e -> {
			mouseLocation.x = e.getX();
			mouseLocation.y = e.getY();
			//System.out.println(mouseLocation);
			
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
				
				//FPS
				lastTime = now;
			}
			
		};
		
		animationTimer.start();
	}

	private void setUpGame() {
        //add attractors	
        for( int i = 0; i < Settings.ATTRACTOR_COUNT; i++) { //Settings.ATTRACTOR_COUNT
            addAttractors();
        }
        
        //add FlockManager
        flockManager = new FlockManager();
        Flock f1 = new Flock(Settings.HUMAN_COUNT, playground);
        f1.addRule(new Rule() );
        //f1.addRule(new AimAttractor(1, f1));
        //f1.addRule(new KeepDistance(40, f1));
        flockManager.add(f1);
        
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

	private void addHumans() {
		//Layer layer = playground;
		
		//random location
		double x = random.nextDouble() * playground.getWidth();
		double y = random.nextDouble() * playground.getHeight();
		
		//dimension
		double width = 10; //50 for triangle
        double height = 10; //width / 2.0; 

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
