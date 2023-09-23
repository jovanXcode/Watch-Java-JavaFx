package finalprojectbadlab;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import User.User;
import connection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
import watch.Brand;

public class ManageBrand {

	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	GridPane gridContainer1;
	GridPane gridContainer2;
	GridPane button;

	MenuBar menuBar;
    Menu Menu, Account;
    MenuItem ManageProduct,ManageBrand,Logout;
	
	
	TableColumn cid,cname;
	Label lid,lname;
    TableView<Brand> table;
    ObservableList<Brand> data ;
	TextField id,name;
	Button insert,update,delete;
	private Connect connect = Connect.getInstance();
	private Vector<User> users;
	
	public ManageBrand() {
		initialize();
		set();
		build();
		button();
		
		Main.gotoPage(scene);
		
	}
	
	void initialize() {
		
		menuBar = new MenuBar();
		Menu = new Menu("Management");
		Account = new Menu("User");
		ManageProduct = new MenuItem("ManageProduct");
		ManageBrand = new MenuItem("ManageBrand");
		Logout = new MenuItem("LogOut");
		 
		menuBar.getMenus().addAll(Menu, Account);
		Menu.getItems().addAll(ManageProduct,ManageBrand);
		Account.getItems().addAll(Logout);
		
		borderContainer =new BorderPane();
		gridContainer = new GridPane();
		gridContainer1 = new GridPane();
		gridContainer2 = new GridPane();
		
		data = FXCollections.observableArrayList();

		
		button = new GridPane();
		scene = new Scene(borderContainer, 720, 320);
		

	    table = new TableView();
		table.setPrefSize(350, 200);
	    cid = new TableColumn("Brand Id");
	    cname = new TableColumn("Brand Name");

		lname = new Label("Name");
		lname.setPrefSize(150, 10);
		lid = new Label("ID");
		lid.setPrefSize(150, 10);
		
	    
		name = new TextField();
		name.setPrefSize(150, 10);
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
		id = new TextField();
		id.setPrefSize(150, 10);

		insert = new Button("Insert");
		insert.setPrefSize(50, 10);
		update = new Button("Update");
		insert.setPrefSize(50, 10);
		delete = new Button("Delete");
		insert.setPrefSize(50, 10);
	}
	
	void load_data() {
		String query = String.format("SELECT * FROM brand");
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data.add(new Brand(rs.getString("BrandId"),rs.getString("BrandName")));
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	void set() {
	/*
		GridPane.setHalignment(registerLbl, HPos.CENTER);
		GridPane.setHalignment(registerBtn, HPos.CENTER);
		GridPane.setHalignment(loginsLbl, HPos.CENTER);
		*/

		gridContainer.setAlignment(Pos.CENTER);
	    
		gridContainer.setVgap(10);
		gridContainer.setHgap(10);
		
		gridContainer1.setAlignment(Pos.CENTER);
	    
		gridContainer1.setVgap(10);
		gridContainer1.setHgap(10);
		
		gridContainer2.setAlignment(Pos.CENTER);
	    
		gridContainer2.setVgap(10);
		gridContainer2.setHgap(10);
		

		button.setAlignment(Pos.CENTER);
	    
		button.setVgap(10);
		button.setHgap(10);
		
        table.setItems(data);
		
		
		load_data();
			
		
	
		
		
		
			
	}
	
	
	public void button() {
		

		Logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new pagelogin();
				
			}
		});
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
		insert.setOnAction(e -> {
			String ids = id.getText();
			String names = name.getText();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			
			
			try {
			if(names.isBlank()) {
				alert.setHeaderText("error");
				alert.setContentText("Please fill the blank fields");
				alert.show();
			}else if(names.length() < 5 || names.length() > 20) {
				alert.setHeaderText("error");
				alert.setContentText("name length must be between 5-20 characters");
			    alert.show();
			}else {
				System.out.println("BERHASIL!");
				//insert db
				String query2 = String.format("insert into brand VALUES (0,'%s')", names);
				connect.execUpdate(query2);
				alert.setHeaderText("Message");
				alert.setContentText("Brand Inserted");
				alert.show();
				data.clear();
				load_data();

			}
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		});
		table.setOnMouseClicked(new EventHandler<Event>() {
            
			
			
			
			@Override
			public void handle(Event arg0) {
				int temp = table.getSelectionModel().getSelectedIndex();
				id.setText(data.get(temp).getBrandId());
				name.setText(data.get(temp).getBrandName());
				
				

				
			}
		
		
		
		});
		
		update.setOnAction(e -> {
			if(!id.getText().equals("")) {
				String ids = id.getText();
				String names = name.getText();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				
				try {
				if(names.isBlank()) {
					alert.setHeaderText("error");
					alert.setContentText("Please fill the blank fields");
					alert.show();
				}else if(names.length() < 5 || names.length() > 20) {
					alert.setHeaderText("error");
					alert.setContentText("name length must be between 5-20 characters");
				    alert.show();
				}else {
					System.out.println("BERHASIL!");
					//insert db
					String query2 = String.format("UPDATE brand SET BrandName = '%s' where BrandId ='%s'", names,ids);
					connect.execUpdate(query2);
					alert.setHeaderText("Message");
					alert.setContentText("Brand Updated");
					alert.show();
					data.clear();
					load_data();
	
				}
				}catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a brand from the table first!");
				alert.show();
				
			}
			
			
		});
		
		delete.setOnAction(e -> {
			if(!id.getText().equals("")) {
				String ids = id.getText();
				String names = name.getText();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				
				try {
					System.out.println("BERHASIL!");
					//insert db
					String query2 = String.format("DELETE FROM brand where BrandId ='%s'", ids);
					connect.execUpdate(query2);
					alert.setHeaderText("Message");
					alert.setContentText("Brand Deleted");
					alert.show();
					data.clear();
					load_data();
				}catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a brand from the table first!");
				alert.show();
				
			}
			
			
		});
	}
	
	
	void build() {
    	borderContainer.setTop(menuBar);
		
		table.getColumns().addAll(cid, cname);
		

		cid.setCellValueFactory(
                new PropertyValueFactory<>("BrandId"));
		cname.setCellValueFactory(
                new PropertyValueFactory<>("BrandName"));
		
		gridContainer.add(gridContainer1, 0, 0);
		gridContainer.add(gridContainer2, 1, 0);

		gridContainer1.add(table, 0, 0);

		gridContainer2.add(lname, 0, 0);
		gridContainer2.add(name, 0, 1);
		gridContainer2.add(button, 0, 3);

		button.add(insert, 0, 0);
		button.add(update, 1, 0);
		button.add(delete, 2, 0);
		
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

