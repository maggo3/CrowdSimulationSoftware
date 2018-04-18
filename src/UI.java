import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UI {

	private BorderPane rootLayout;
	private Stage window;
	private double dragDeltaX;
	private double dragDeltaY;
	
	
	public UI(Stage window) {
		this.window = window;
	}

	public Scene setUp() {
		window.setTitle("Crowd Simulation Software");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		rootLayout = new BorderPane();
		makeMenu();
		Scene root = new Scene(rootLayout, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		//root.setFill(Color.GREY);
		return root;
	}

	private void closeProgram() {
		boolean answer = ConfirmBox.display("Attention", "Bist du sicher?");
		if(answer)  window.close();
	}

	private void makeMenu() {
		Menu menu1 = new Menu("File");
	    Menu menu2 = new Menu("Options");
	    Menu menu3 = new Menu("Help");
	    MenuBar menuBar = new MenuBar();
	    
	    menuBar.getMenus().addAll(menu1, menu2, menu3);
	    menuBar.setUseSystemMenuBar(true);
	    
	    MenuItem openFile = new MenuItem("Open..");
		menu1.getItems().add(openFile);
		
	    rootLayout.getChildren().add(menuBar);
	}

	public void createDragAndZoomEvens(Scene root, Layer g) {
		root.setOnZoom(new EventHandler<ZoomEvent>() {
			@Override
			public void handle(ZoomEvent event) {
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
	}
	
	public void setBackground(String url) {
		Image img = new Image("file:" + url);
		rootLayout.setBackground(new Background
				(new BackgroundImage
						(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
								BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
	}
	
	public BorderPane getLayout() {
		return this.rootLayout;
	}
}
