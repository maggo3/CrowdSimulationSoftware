import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI {

	private int window_width;
	private int window_height;
	private BorderPane rootLayout;
	private Stage window;
	
	public UI() {
		this.window_width = 1000;
		this.window_height = 700;
	}
	
	public UI(Stage window, int window_width, int window_height) {
		this.window_width = window_width;
		this.window_height = window_height;
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
		Scene root = new Scene(rootLayout, window_width, window_height);
		root.setFill(Color.GREY);
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
	
	public Pane getLayout() {
		return rootLayout;
	}
}
