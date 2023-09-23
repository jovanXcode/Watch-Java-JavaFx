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
import javafx.scene.control.Spinner;
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
import watch.Watch;

public class ManageWatch {

	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	GridPane gridContainer1;
	GridPane gridContainer2;
	GridPane button;

	MenuBar menuBar;
    Menu Menu, Account;
    MenuItem ManageWatch,ManageBrand,Logout;
	
	TableColumn pid,cid,pname,pprice,pstock;
	Label lid,lname,lprice,lstock,lbrand;
    TableView<Watch> table;
    ObservableList<Watch> data ;
    ObservableList<String> data1 ;
    ObservableList<String> data2 ;
	TextField id,name,price;
	ComboBox<String> brand;
	Spinner stock;
	Button insert,update,delete;
	private Connect connect = Connect.getInstance();
	private Vector<User> users;
	
	public ManageWatch() {
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
		ManageWatch = new MenuItem("ManageWatch");
		ManageBrand = new MenuItem("ManageBrand");
		Logout = new MenuItem("LogOut");
		 
		menuBar.getMenus().addAll(Menu, Account);
		Menu.getItems().addAll(ManageWatch,ManageBrand);
		Account.getItems().addAll(Logout);
		
		borderContainer =new BorderPane();
		gridContainer = new GridPane();
		gridContainer1 = new GridPane();
		gridContainer2 = new GridPane();

		data = FXCollections.observableArrayList();
		data1 = FXCollections.observableArrayList();
		data2 = FXCollections.observableArrayList();

		
		button = new GridPane();
		scene = new Scene(borderContainer, 750, 450);
		

	    table = new TableView();
	    table.setPrefSize(530, 410);
	    pid = new TableColumn("Watch ID");
	    cid = new TableColumn("Watch Brand");
	    pname = new TableColumn("Watch Name");
	    pprice = new TableColumn("Watch Price");
	    pstock = new TableColumn("Watch Stock");

		lname = new Label("Watch Name");
		lname.setPrefSize(150, 10);
		lid = new Label("Watch Id");
		lid.setPrefSize(150, 10);
		lprice = new Label("Price");
		lprice.setPrefSize(150, 10);
		lstock = new Label("Stock");
		lstock.setPrefSize(150, 10);
		lbrand = new Label("Watch Brand");
		lbrand.setPrefSize(150, 10);
		
	    
		name = new TextField();
		name.setPrefSize(150, 10);
		id = new TextField();
		id.setPrefSize(150, 10);
		price = new TextField();
		price.setPrefSize(150, 10);
		stock = new Spinner(1, 100, 1);
		stock.setPrefSize(150, 10);
		brand = new ComboBox<>();
		brand.setItems(data1);
		stock.setPrefSize(150, 10);

		insert = new Button("Insert");
		insert.setPrefSize(50, 10);
		update = new Button("Update");
		insert.setPrefSize(50, 10);
		delete = new Button("Delete");
		insert.setPrefSize(50, 10);
	}
	
	void load_data() {
		String query = String.format("SELECT * FROM watch join brand on watch.brandid = brand.brandid");
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data.add(new Watch(rs.getString("watchId"),rs.getString("watchName"),rs.getString("watchStock"),rs.getString("watchPrice"),rs.getString("BrandName")));
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	void load_data1() {
		data1.clear();
		data2.clear();
		String query = String.format("SELECT * FROM brand");
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data1.add(rs.getString("BrandName"));
				data2.add(rs.getString("BrandId"));
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
        brand.setItems(data1);
		

		load_data();
		load_data1();
			
		
	
		
		
		
			
	}
	
	
	public void button() {
		

		Logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new pagelogin();
				
			}
		});
		ManageWatch.setOnAction(new EventHandler<ActionEvent>() {
			
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
			String brands = data2.get(brand.getSelectionModel().getSelectedIndex());
			String stocks = String.valueOf(stock.getValueFactory().getValue());
			String prices = price.getText();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			
			
			try {
			if(names.isBlank()) {
				alert.setHeaderText("Message");
				alert.setContentText("Please fill the blank fields");
				alert.show();
			}else {
				System.out.println("BERHASIL!");
				//insert db
				String query2 = String.format("insert into watch VALUES (0,'%s','%s','%s','%s')", brands,names,prices,stocks);
				connect.execUpdate(query2);
				alert.setHeaderText("Message");
				alert.setContentText("New watch successfully inserted!");
				alert.show();
				data.clear();
				load_data();
				load_data1();

			}
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		});
		table.setOnMouseClicked(new EventHandler<Event>() {
            
			
			
			
			@Override
			public void handle(Event arg0) {
				int temp = table.getSelectionModel().getSelectedIndex();
				id.setText(data.get(temp).getWatchId());
				name.setText(data.get(temp).getWatchName());
				brand.getSelectionModel().select(data.get(temp).getBrandId());
				stock.getValueFactory().setValue(Integer.parseInt(data.get(temp).getWatchStock()));
				price.setText(data.get(temp).getWatchPrice());
				
				

				
			}
		
		
		
		});
		
		update.setOnAction(e -> {
			if(!id.getText().equals("")) {
			String ids = id.getText();
			String names = name.getText();
			String brands = data2.get(brand.getSelectionModel().getSelectedIndex());
			String stocks = String.valueOf(stock.getValueFactory().getValue());
			String prices = price.getText();
			System.out.println("sad "+ids);
				Alert alert = new Alert(AlertType.INFORMATION);
				
				
				try {
				if(names.isBlank()) {
					alert.setHeaderText("Message");
					alert.setContentText("Please fill the blank fields");
					alert.show();
				}else {
					System.out.println("BERHASIL!");
					//insert db
					System.out.println(ids);
					System.out.println(names);
					System.out.println(brands);
					System.out.println(stocks);
					System.out.println(prices);
					String query2 = String.format("UPDATE watch SET WatchName = '%s',WatchStock = '%s',WatchPrice = '%s',BrandId = '%s' where WatchId ='%s'", names,stocks,prices,brands,ids);
					connect.execUpdate(query2);
					alert.setHeaderText("Message");
					alert.setContentText("Watch succesfully updated!");
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
				alert.setContentText("You must select a watch from the table first!");
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
					String query2 = String.format("DELETE FROM watch where WatchId ='%s'", ids);
					connect.execUpdate(query2);
					alert.setHeaderText("Message");
					alert.setContentText("Watch Deleted");
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
				alert.setContentText("You must select a watch from the table first!");
				alert.show();
				
			}
			
			
			
		});
	}
	
	
	void build() {
    	borderContainer.setTop(menuBar);
    	brand.setPromptText("Chose one");
		table.getColumns().addAll( pid, pname,cid, pprice, pstock);
		

		cid.setCellValueFactory(
                new PropertyValueFactory<>("brandId"));
		pid.setCellValueFactory(
                new PropertyValueFactory<>("watchId"));
		pname.setCellValueFactory(
                new PropertyValueFactory<>("watchName"));
		pprice.setCellValueFactory(
                new PropertyValueFactory<>("watchPrice"));
		pstock.setCellValueFactory(
                new PropertyValueFactory<>("watchStock"));
		

	    pid = new TableColumn("ID");
	    cid = new TableColumn("Brand ID");
	    pname = new TableColumn("Name");
	    pprice = new TableColumn("Price");
	    pstock = new TableColumn("Stock");
		
		gridContainer.add(gridContainer1, 0, 0);
		gridContainer.add(gridContainer2, 1, 0);

		gridContainer1.add(table, 0, 0);

		gridContainer2.add(lname, 0, 0);
		gridContainer2.add(name, 0, 1);
		gridContainer2.add(lprice, 0, 2);
		gridContainer2.add(price, 0, 3);
		gridContainer2.add(lstock, 0, 4);
		gridContainer2.add(stock, 0, 5);
		gridContainer2.add(lbrand, 0, 6);
		gridContainer2.add(brand, 0, 7);
		gridContainer2.add(button, 0, 8);

		button.add(insert, 0, 0);
		button.add(update, 1, 0);
		button.add(delete, 2, 0);
		
		borderContainer.setCenter(gridContainer);
	
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

