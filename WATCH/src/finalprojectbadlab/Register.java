package finalprojectbadlab;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import User.User;
import connection.Connect;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

//import jdk.internal.misc.FileSystemOption;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;

public class Register {

	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label loginsLbl,registerLbl,nameLbl, emailLbl, passwordLbl,confirmpasswordLbl,genderLbl;
	
	RadioButton userMale, userFemale;
	ToggleGroup genders;
	TextField nameTF,emailTF;
	PasswordField passwordPF,confirmpasswordPF;
	FlowPane userFp;
	Button registerBtn;
	private Connect connect = Connect.getInstance();
	private Vector<User> users;
	
	public Register() {
		initialize();
		set();
		build();
		button();
		
		Main.gotoPage(scene);
		
	}
	
	void initialize() {
		borderContainer =new BorderPane();
		gridContainer = new GridPane();
		scene = new Scene(borderContainer, 720, 720);
		
		registerLbl = new Label("REGISTER");
		registerLbl.setFont(Font.font("Courier", FontWeight.BOLD,12));
		
		
		
		nameLbl = new Label("Name");
		nameLbl.setPrefSize(150, 10);
		loginsLbl = new Label("Back to Login");
		emailLbl = new Label("Email");
		passwordLbl = new Label("Password");
		confirmpasswordLbl = new Label("Confirm Password");
		genderLbl = new Label("Gender");
		genders = new ToggleGroup();
		
	    
		nameTF = new TextField();
		nameTF.setPrefSize(400, 10);
//		nameTF.setOnAction(new EventHandler<ActionEvent>() {
			
//		
//			@Override
//			public void handle(ActionEvent arg0) {
//				registerBtn.setOnAction(e ->{
//					if(e.getSource() == registerBtn) {
//						String name = nameTF.getText();
//						String password = passwordPF.getText();
//						String passwords = confirmpasswordPF.getText();
//						String email = emailTF.getText();
//						
//						if(name.length() < 5 || name.length() > 20) {
//							new pagelogin();
//						}else if
//					       (password.length() < 5 && password.length() > 20 && password.matches("[a-zA-Z0-9]*")) {
//							new pagelogin();
//						}else if
//					       (passwords == password) {
//							new pagelogin();
//						}else if
//					       (email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
//							new pagelogin();
//						}//else if
//					       
//						
//						
//					}
//				});
//				
//			}
//		});
//	    
		emailTF = new TextField();
		
		passwordPF = new PasswordField();
		confirmpasswordPF = new PasswordField();
		
		userMale = new RadioButton("Male");
		userFemale = new RadioButton("Female");
		userFp = new FlowPane(userMale, userFemale);
		userFp.setAlignment(Pos.CENTER);
	    
		registerBtn = new Button("Register");
		registerBtn.setPrefSize(100, 10);
	}
	
	void set() {
	
		GridPane.setHalignment(registerLbl, HPos.CENTER);
		GridPane.setHalignment(registerBtn, HPos.CENTER);
		GridPane.setHalignment(loginsLbl, HPos.CENTER);
		
		
		gridContainer.setAlignment(Pos.CENTER);
	    
		gridContainer.setVgap(10);
		gridContainer.setHgap(10);
		
		

		loginsLbl.setStyle("-fx-text-fill:blue;");
//	    registerBtn.setStyle("-fx-background-color:red;");
			
	    
        loginsLbl.setOnMouseEntered(new EventHandler<Event>() {
            
			
			
			
			@Override
			public void handle(Event arg0) {
				loginsLbl.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 12));
				
				

				
			}
		
		
		
		});
		
		loginsLbl.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				loginsLbl.setFont(Font.font("Courier",12));
				
			}
		
		
		
		});
		
		loginsLbl.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
                
				new pagelogin();
				
			}
		});	
			
		registerBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
                
				new pagelogin();
				
			}
		});	
			
		
	
		
		
		
			
	}
	
	
	public void button() {
		registerBtn.setOnAction(e -> {
			String username = nameTF.getText();
			String password = passwordPF.getText();
			String confirmPassword = confirmpasswordPF.getText();
			String email = emailTF.getText();
			String gender = "";
		
			if(userMale.isSelected()) {
				gender = userMale.getText();
			}else if(userFemale.isSelected()){
				gender = userFemale.getText();
			}
			
			Alert alert = new Alert(AlertType.ERROR);
			
			//Query
			String query = String.format("SELECT useremail FROM user WHERE useremail = '%s'", email);
			
			ResultSet rs = null;
			
			try {
				rs = connect.execQuery(query);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			
			
			try {
			if(username.isBlank() || password.isBlank() || confirmPassword.isBlank() || email.isBlank()) {
				alert.setHeaderText("error");
				alert.setContentText("Please fill the blank fields");
				alert.show();
			}else if(username.length() < 5 || username.length() > 20) {
				alert.setHeaderText("error");
				alert.setContentText("username length must be between 5-20 characters");
			    alert.show();
			}else if(password.length() < 5 || password.length() > 20) {
				alert.setHeaderText("error");
				alert.setContentText("password length must be between 5-20 characters");
			    alert.show();
			}else if(!confirmPassword.equals(password)) {
				alert.setHeaderText("error");
				alert.setContentText("password mismatch");
			    alert.show();
			}else if(!matcher.matches()) {
				alert.setHeaderText("error");
				alert.setContentText("Incorrect email format!");
			    alert.show();
			}else {
				System.out.println("BERHASIL!");
				//insert db
				User user = new User (0,  username,  password,  email,  gender, "Customer");
				user.insertuser();
			}
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		});
	}
	
	
	void build() {
		gridContainer.add(registerLbl, 0, 0,2,1);
		gridContainer.add(nameLbl, 0, 1);
		gridContainer.add(nameTF, 1, 1);
		gridContainer.add(genderLbl, 0, 2);
		gridContainer.add(userFp, 1, 2);
		gridContainer.add(emailLbl, 0, 3);
	    gridContainer.add(emailTF, 1, 3 );
		gridContainer.add(passwordLbl, 0, 4);
		gridContainer.add(passwordPF, 1, 4);
		gridContainer.add(confirmpasswordLbl, 0, 5);
		gridContainer.add(confirmpasswordPF, 1, 5);
		gridContainer.add(registerBtn, 0, 9,2,1);
		gridContainer.add(loginsLbl, 0, 10,2,1);
		
		borderContainer.setCenter(gridContainer);
		genders.getToggles().addAll(userMale, userFemale);
	
	}
	
//	 void addData(String username, String password, String email, Integer phone_number, String address,String role, String nationality) {
//		//Insert to database
//		
//		//Buat query
//		String query = "INSERT INTO user VALUES('0','"+username + "','"+password+"','"+email +"',"+phone_number +",'" + address+"','" + role + "','" + nationality + "')";
//	    
//		
//		//Execute query
//	    connect.execQuery(query);
//	}
//	
//	
//	 void getData() {
//	        users.removeAllElements();
//			
//			//Select from database 
//			//Buat query
//			
//	       
//	        
//	        
//	        
//	        
//	        String query = "SELECT * FROM user";
//			connect.rs = connect.execQuery(query);
//			
//			
//			
//			
//			
//			
//			try {
//				while(connect.rs.next()) {
//					Integer id = connect.rs.getInt("id");
//					String username = connect.rs.getString("name");
//					String password = connect.rs.getString("password");
//					String email = connect.rs.getString("email");
//					Integer phonenumber = connect.rs.getInt("phone_number");
//				    String address = connect.rs.getString("address");
//				    String gender = connect.rs.getString("gender");
//				    String role = connect.rs.getString("role");
//				    String nationality = connect.rs.getString("nationality");
//				    
//				    users.add(new User(id, phonenumber, username, password,email, address,gender, role, nationality));
//				}
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//			
//		}
//	
	
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

