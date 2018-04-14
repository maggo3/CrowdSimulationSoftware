import com.aquafx_project.AquaFx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage window;
	private Group g;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AquaFx.style();
		window = primaryStage;
		
		UI ui = new UI(window, 1400,800);
		Scene root = ui.setUp();
		
		g = new Group();
		
		ui.createDragAndZoomEvens(root, g);

		/*
		Circle c = new Circle(50,Color.BLUE);
		c.setCenterX(500);
		c.setCenterY(500);
		Circle c2 = new Circle(20,Color.RED);
		c2.setCenterX(700);
		c2.setCenterY(750);
		g.getChildren().addAll(c,c2);
		ui.getLayout().getChildren().add(g);
		*/
		
		
		window.setScene(root);
		window.show();
		
		
		UI ui2 = new UI(window, 1400,800);
		Scene scene2 = ui2.setUp();
		//ui2.getLayout().setStyle("-fx-background-color: BLACK");
		Image img = new Image("file:Title.jpg");
		ui2.getLayout().setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		//new BackgroundImage("", BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)
		
		HBox centerMenu = new HBox();
		Button b = new Button("OK");
		centerMenu.getChildren().add(b);
		//b.setStyle("-fx-background-color: #333333;");
		ui.getLayout().setCenter(centerMenu);
		//g.getChildren().add(b);
		b.setOnAction(e -> window.setScene(scene2));
		//StackPane layout = new StackPane();
		//layout.getChildren().add(b);
		//b.setOnAction(e -> window.setScene(scene2));
		//b2.setOnAction(e -> window.setScene(scene1));
	}
}
