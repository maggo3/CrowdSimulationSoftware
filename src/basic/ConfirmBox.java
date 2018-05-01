package basic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

	static boolean answer = true;
	
	public static boolean display(String title, String msg) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
				
		//creating Labels
		Label lbl = new Label(msg);
		
		//creating two buttons
		Button yesB = new Button("Yes");
		Button noB = new Button("No");
		
		yesB.setOnAction(e -> {
			answer = true;
			window.close();
		});
		noB.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		HBox topMenu = new HBox();
		topMenu.setAlignment(Pos.CENTER);
		topMenu.getChildren().add(lbl);
		HBox centerMenu = new HBox();
		centerMenu.setAlignment(Pos.CENTER);
		centerMenu.getChildren().addAll(yesB, noB);
		centerMenu.setSpacing(10);
		
		
		BorderPane layout = new BorderPane();
		layout.setTop(topMenu);
		layout.setCenter(centerMenu);
		layout.setPadding(new Insets(10, 10, 10, 10));
		
		
		Scene s = new Scene(layout, 200,100);
		window.setScene(s);
		window.showAndWait();
		
		return answer;
	}
}
