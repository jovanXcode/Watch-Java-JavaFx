package finalprojectbadlab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	static Stage stage;
	
	
	
	public static void gotoPage(Scene scene) {

		stage.setScene(scene);
		stage.show();
		stage.setTitle("Rumah Makan");
		
	}
	
	

	

	
	
	public static void main(String[] args) {
		launch();

	}
	
	
	
	
	
	
	
	
	@Override
	public void start(Stage s) throws Exception {
		stage = s;
		
		new pagelogin();
		
		
	}

}





	

