package finalprojectbadlab;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;

public class MainForm {
	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label blaLbl;
	MenuBar menuBar;
    Menu Menu, Account;
    MenuItem ManageProduct,ManageBrand,Logout;

public MainForm() {
	initialize();
	set();
	build();
	
	Main.gotoPage(scene);
	
}
    
    
    void initialize() {
	 borderContainer = new BorderPane();
	 gridContainer = new GridPane();
	 scene = new Scene(borderContainer,520,520);
	  
    
	
	 
    
	 menuBar = new MenuBar();
	 Menu = new Menu("Management");
	 Account = new Menu("User");
	 Logout = new MenuItem("LogOut");
	 ManageBrand = new MenuItem("Manage Brand");
	 ManageProduct = new MenuItem("Manage Product");
	 
	 menuBar.getMenus().addAll(Menu, Account);
	 Menu.getItems().addAll(ManageProduct,ManageBrand);
	 Account.getItems().addAll(Logout);
    }
    
    
    void set() {
	gridContainer.setAlignment(Pos.CENTER);
	  
	gridContainer.setVgap(20);
	gridContainer.setHgap(20);
	
	ManageProduct.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			new ManageWatch();
			
		}
	});
	
	ManageBrand.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			new ManageBrand();
			
		}
	});
	
	
	Logout.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			new pagelogin();
			
		}
	});
	
	
	
   
    }
 
    void build() {
	    
    	
    	 
    	borderContainer.setTop(menuBar);
    	borderContainer.setCenter(gridContainer);
    	
    
    	
    	
    }
 
 
 
 
 
 
 
public void createWindow(String title, int width, int height, Node node) {
	Window window = new Window();
	window.getContentPane().getChildren().add(node);
	
	window.getRightIcons().add(new CloseIcon(window));
	window.setPrefSize(width, height);
	
	window.setLayoutX(scene.getWidth() / 2 - width / 2);
    window.setLayoutX(scene.getHeight() / 2 - height / 2);
    
    window.setTitle(title);
    
    borderContainer.getChildren().add(window);


	}


}

