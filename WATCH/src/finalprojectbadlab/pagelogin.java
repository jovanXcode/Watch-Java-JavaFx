package finalprojectbadlab;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import connection.Connect;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import User.User;


public class pagelogin {

	
	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label emailLbl, passLbl, registerLbl,loginLbl;
	TextField emailTF;
	PasswordField passPF;
	TextField nameField;
	Button loginBtn;
	private Vector<User> users;
	private Connect connect = Connect.getInstance();

	
	public pagelogin() {
		initialize();
		set();
		build();
		button();
		
		Main.gotoPage(scene);
		
	}
	
	void initialize() {
		borderContainer =new BorderPane();
		gridContainer = new GridPane();
		scene = new Scene(borderContainer, 520, 520);
		
		loginLbl = new Label("LOGIN");
		loginLbl.setFont(Font.font("Courier", FontWeight.BOLD,12));
		
		emailLbl = new Label("Email");
		passLbl = new Label("Password");
		emailTF = new TextField();
		passPF = new PasswordField();
		
		loginBtn = new Button("Login");
		
		registerLbl = new Label("Register Instead");
		
	


}

	void set() {
		GridPane.setHalignment(loginLbl, HPos.CENTER);
		GridPane.setHalignment(loginBtn, HPos.CENTER);
		GridPane.setHalignment(registerLbl, HPos.CENTER);
		gridContainer.setAlignment(Pos.CENTER);
	    
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
		
	    registerLbl.setStyle("-fx-text-fill:blue;");
	    
		registerLbl.setOnMouseEntered(new EventHandler<Event>() {
            
			
			
			
			@Override
			public void handle(Event arg0) {
				registerLbl.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 12));
				
				

				
			}
		
		
		
		});
		
		registerLbl.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				registerLbl.setFont(Font.font("Courier",12));
				
			}
		
		
		
		});
		
		registerLbl.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
                
				new Register();
				
			}
		});
	}
		
		
		
	
	
	
	void build() {
		gridContainer.add(loginLbl, 0, 0,2,1);
		gridContainer.add(emailLbl, 0, 1);
		gridContainer.add(emailTF, 1, 1);
		gridContainer.add(passLbl, 0, 2);
		gridContainer.add(passPF, 1, 2);
		gridContainer.add(loginBtn, 0, 3,2,1);
		gridContainer.add(registerLbl, 0, 4,2,1);
		
		borderContainer.setCenter(gridContainer);
		
	}
	
	
	public void button() {
		loginBtn.setOnAction(e -> {
			String email = emailTF.getText();
			String password = passPF.getText();
			Alert alert = new Alert(AlertType.ERROR);
			
			String query = String.format("SELECT * FROM user  WHERE UserEmail ='%s' AND UserPassword = '%s'", email, password);
			
			ResultSet rs = connect.execQuery(query);
			try {
				if(email.isBlank() || password.isBlank()) {
					alert.setHeaderText("Error");
					alert.setContentText("Email/password is empty!");
					alert.show();
				} else if(rs.next()) {
					System.out.println("Login berhasil!!!");	
					if(rs.getString("UserRole").equalsIgnoreCase("Admin")) {
						System.out.println("INI USER ROLE ADMINS");
						new MainForm();
					}else if(rs.getString("UserRole").equalsIgnoreCase("Customer")) {
						System.out.println("Ini role user");
						new CustomerForm(rs.getString("userId"));
					}else  {
						System.out.println(rs.getString("UserRole"));
					}
					
				}else {
					alert.setHeaderText("error");
					alert.setContentText("Invalid email/password!");
					alert.show();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			
			
			
		});
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
