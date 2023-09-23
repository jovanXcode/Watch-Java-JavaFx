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
import javafx.scene.control.ButtonType;
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
import watch.Cart;
import watch.Brand;
import watch.Watch;

public class BuyWatch {

	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	GridPane gridContainer1;
	GridPane gridContainer2;
	GridPane gridContainer3;
	GridPane gridContainer4;
	GridPane button;
	int qs=0;
	MenuBar menuBar;
    Menu Menu, Account;
    MenuItem Buy,History,Logout;

	TableColumn pid,cid,pname,pprice,pstock;
	TableColumn caid,caname,caprice,caquantity;
	Label lid,lname,lprice,lstock,lbrand;
    TableView<Watch> table;
    TableView<Cart> table1;
    ObservableList<Watch> data ;
    ObservableList<Cart> data3 ;
    ObservableList<String> data1 ;
    ObservableList<String> data2 ;
	TextField id,name,price,brand;
	Spinner stock;
	Button add,checkout,clear;
	private Connect connect = Connect.getInstance();
	private Vector<User> users;
	private String id_user;
	public BuyWatch(String id) {
		this.id_user =id;
		initialize();
		set();
		build();
		button();
		
		Main.gotoPage(scene);
		
	}
	
	void initialize() {

		 menuBar = new MenuBar();
		 Menu = new Menu("Transaction");
		 Account = new Menu("User");
		 Logout = new MenuItem("LogOut");
		 Buy = new MenuItem("Buy");
		 History = new MenuItem("History");
		 
		 menuBar.getMenus().addAll(Menu, Account);
		 Menu.getItems().addAll(Buy, History);
		 Account.getItems().addAll(Logout);
		
		borderContainer =new BorderPane();
		gridContainer = new GridPane();
		gridContainer1 = new GridPane();
		gridContainer2 = new GridPane();
		gridContainer3 = new GridPane();
		gridContainer4 = new GridPane();

		data = FXCollections.observableArrayList();
		data1 = FXCollections.observableArrayList();
		data2 = FXCollections.observableArrayList();
		data3 = FXCollections.observableArrayList();

		
		button = new GridPane();
		scene = new Scene(borderContainer, 720, 720);
		

	    table = new TableView();
	    table.setPrefSize(550, 300);
	    table1 = new TableView();
	    table1.setPrefSize(550, 300);
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
		lstock = new Label("Quantity");
		lstock.setPrefSize(150, 10);
		lbrand = new Label("Watch Brand");
		lbrand.setPrefSize(150, 10);
		
	    
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
		price = new TextField();
		price.setPrefSize(150, 10);
		brand = new TextField();
		stock = new Spinner(1, 100, 1);
		stock.setPrefSize(150, 10);
		stock.setPrefSize(150, 10);

		add = new Button("Add Watch To Cart");
		add.setPrefSize(150, 10);

		clear = new Button("Clear Cart");
		clear.setPrefSize(100, 10);
		checkout = new Button("Checkout");
	}
	
	void load_data() {
		String query = String.format("SELECT * FROM watch join brand on watch.brandid = brand.brandid");
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data.add(new Watch(rs.getString("watchId"),rs.getString("watchName"),rs.getString("watchStock"),rs.getString("watchPrice"),rs.getString("brandName")));
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	void load_data2() {
		String query = String.format("SELECT watch.watchId, watch.watchName, watch.watchPrice, cart.quantity FROM watch join cart on watch.watchId = cart.watchId WHERE cart.userId = '%s'", id_user);
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data3.add(new Cart(rs.getString("watchId"),id_user,rs.getString("quantity"),rs.getString("watchName"),rs.getString("watchPrice")));
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	void load_data1() {
		String query = String.format("SELECT * FROM brand");
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data1.add(rs.getString("brandName"));
				data2.add(rs.getString("brandId"));
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
		gridContainer3.setAlignment(Pos.CENTER);
	    
		gridContainer3.setVgap(10);
		gridContainer3.setHgap(10);
	    
		gridContainer2.setVgap(10);
		gridContainer2.setHgap(10);
		

		button.setAlignment(Pos.CENTER);
	    
		button.setVgap(10);
		button.setHgap(10);

        table.setItems(data);
        table1.setItems(data3);
		

		load_data();
		load_data1();
		load_data2();
			
		
	
		
		
		
			
	}
	
	
	public void button() {
		

		Logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new pagelogin();
				
			}
		});
		
		Buy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new BuyWatch(id_user);
				
			}
		});
		
		History.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new History(id_user);
				
			}
		});
		
		add.setOnAction(e -> {
			if(!id.getText().equals("")) {
				String ids = id.getText();
				String stocks = String.valueOf(stock.getValueFactory().getValue());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				
				try {
					System.out.println("BERHASIL!");
					//insert db
					String query2 = String.format("insert into cart VALUES ('%s','%s','%s')", id_user,ids,stocks);
					connect.execUpdate(query2);
					query2 = String.format("UPDATE watch set watchStock='%s' WHERE watchId='%s'", qs-Integer.parseInt(stocks),ids);
					connect.execUpdate(query2);
					alert.setHeaderText("Message");
					alert.setContentText("Cart Inserted");
					alert.show();
					data.clear();
					data3.clear();
					load_data();
					load_data1();
					load_data2();
	
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
		table.setOnMouseClicked(new EventHandler<Event>() {
            
			
			
			
			@Override
			public void handle(Event arg0) {
				int temp = table.getSelectionModel().getSelectedIndex();
				id.setText(data.get(temp).getWatchId());
				name.setText(data.get(temp).getWatchName());
				brand.setText(data.get(temp).getBrandId());
				stock.getValueFactory().setValue(Integer.parseInt(data.get(temp).getWatchStock()));
				price.setText(data.get(temp).getWatchPrice());
				qs=Integer.parseInt(data.get(temp).getWatchStock());
				

				
			}
		
		
		
		});
		
		checkout.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Confirmation");
			alert.setContentText("Do you want to Checkout");
			alert.showAndWait().ifPresent(type -> {
			if(type== ButtonType.OK) {
				String ids="";
				try {
					System.out.println("BERHASIL!");
					//insert db
					
					String query = String.format("insert into headertransaction values (0,'%s','%s')", id_user,java.time.LocalDate.now());
					
					//Execute query
					 connect.execUpdate(query);
					
					
					query = String.format("SELECT * from headertransaction ");
					
					//Execute query
					ResultSet rs = connect.execQuery(query);
					//validate user input
					try {
						while(rs.next()) {
							ids=rs.getString("transactionId");
						}
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					query = String.format("SELECT watch.watchId, watch.watchName, watch.watchPrice, cart.quantity FROM watch join cart on watch.watchId = cart.watchId WHERE cart.userId = '%s'", id_user);
					
					//Execute query
					rs = connect.execQuery(query);
					//validate user input
					try {
						while(rs.next()) {
							String query2 = String.format("insert into detailtransaction VALUES('%s','%s','%s')",ids,rs.getString("watchId"),rs.getString("quantity"));
							connect.execUpdate(query2);
						}
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					String query2 = String.format("DELETE from cart  where userId ='%s'", id_user);
					connect.execUpdate(query2);
					Alert alerts = new Alert(AlertType.INFORMATION);
					alerts.setHeaderText("Message");
					alerts.setContentText("Checkout Successfully");
					alerts.show();
					data.clear();
					load_data();
					data3.clear();
					load_data2();
	
				}catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			});
		});
		
		clear.setOnAction(e -> {
			String ids="";
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Confirmation");
			alert.setContentText("Are you sure want to clear cart?");
			alert.showAndWait().ifPresent(type -> {
			if(type== ButtonType.OK) {
			
			try {
				System.out.println("BERHASIL!");
				//insert db
				
				
				String query2 = String.format("DELETE from cart  where userId ='%s'", id_user);
				connect.execUpdate(query2);
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setHeaderText("Message");
				alert1.setContentText("Cart Clear");
				alert1.show();
				data.clear();
				load_data();
				data3.clear();
				load_data2();

			}catch (Exception e1) {
				e1.printStackTrace();
			}
			}
			});
			
			
		});
		
	}
	
	
	void build() {
    	borderContainer.setTop(menuBar);

	    caid = new TableColumn("Watch ID");
	    caname = new TableColumn("User ID");
	    caprice = new TableColumn("Watch ID");
	    caquantity = new TableColumn("Quantity");

	    caid.setCellValueFactory(
                new PropertyValueFactory<>("watchId"));
	    caname.setCellValueFactory(
                new PropertyValueFactory<>("userId"));
	    caquantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));

		table.getColumns().addAll( pid, pname,cid,pprice,  pstock);
		table1.getColumns().addAll( caname,caid,  caquantity);
		

		cid.setCellValueFactory(
                new PropertyValueFactory<>("brandId"));
		pid.setCellValueFactory(
                new PropertyValueFactory<>("watchId"));
		pname.setCellValueFactory(
                new PropertyValueFactory<>("watchName"));
		pstock.setCellValueFactory(
                new PropertyValueFactory<>("watchStock"));
		pprice.setCellValueFactory(
                new PropertyValueFactory<>("watchPrice"));


	    pid = new TableColumn("ID");
	    cid = new TableColumn("Brand ID");
	    pname = new TableColumn("Name");
	    pstock = new TableColumn("Stock");
		
		gridContainer.add(gridContainer1, 0, 0);


		gridContainer1.add(table, 0, 0);
		gridContainer1.add(gridContainer2, 0, 1);
		gridContainer1.add(table1, 0, 2);
		gridContainer1.add(gridContainer3, 0, 3);

		gridContainer2.add(lstock, 0, 0);
		gridContainer2.add(stock, 1, 0);
		gridContainer2.add(add, 2, 0);

		gridContainer3.add(clear, 0, 0);
		gridContainer3.add(checkout, 1, 0);
		
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

