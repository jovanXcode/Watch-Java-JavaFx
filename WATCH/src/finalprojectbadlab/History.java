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
import watch.Cart;
import watch.Brand;
import watch.Watch;
import watch.Transaction;

public class History {

	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	GridPane gridContainer1;
	GridPane gridContainer2;
	GridPane button;
	int qs=0;
	MenuBar menuBar;
    Menu Menu, Account;
    MenuItem Buy,History,Logout;

	TableColumn tid,uid,tdate;
	TableColumn q,w,e,r,t,y,u;
	Label lid,lname,lprice,lstock,lbrand;
    TableView<Transaction> table;
    TableView<Transaction> table1;
    ObservableList<Transaction> data ;
    ObservableList<Transaction> data3 ;
    ObservableList<String> data1 ;
    ObservableList<String> data2 ;
	TextField id,name,price,brand;
	Spinner stock;
	Button add,checkout;
	private Connect connect = Connect.getInstance();
	private Vector<User> users;
	private String id_user;
	public History(String id) {
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

		data = FXCollections.observableArrayList();
		data1 = FXCollections.observableArrayList();
		data2 = FXCollections.observableArrayList();
		data3 = FXCollections.observableArrayList();

		
		button = new GridPane();
		scene = new Scene(borderContainer, 720, 720);
		

	    table = new TableView();
	    table1 = new TableView();
	    table.setPrefSize(700, 350);
	    table1.setPrefSize(700, 350);
		tid = new TableColumn("Transaction ID");
		uid = new TableColumn("User ID");
		tdate = new TableColumn("Transaction Date");
		q = new TableColumn("Transaction ID");
		w = new TableColumn("Watch ID");
		e = new TableColumn("Watch Name");
		r = new TableColumn("Watch Brand");
		t = new TableColumn("Watch Price");
		y = new TableColumn("Quantity");
		u = new TableColumn("Sub Total");

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

		add = new Button("Add to Cart");
		add.setPrefSize(75, 10);
		checkout = new Button("Checkout");
	}
	
	void load_data() {
		System.out.println(id_user);
		String query = String.format("SELECT * FROM headertransaction WHERE userId = '%s'", id_user);
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data.add(new Transaction(rs.getString("transactionId"),rs.getString("userId"),rs.getString("transactiondate"),""));
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	void load_data2(String idsss) {
		data3.clear();
		String query = String.format("SELECT * FROM detailtransaction join watch on detailtransaction.watchid = watch.watchid join brand on watch.brandid=brand.brandid WHERE transactionId = '%s'", idsss);
		
		//Execute query
		ResultSet rs = connect.execQuery(query);
		//validate user input
		try {
			while(rs.next()) {
				data3.add(new Transaction(rs.getString("transactionId"),rs.getString("watchID"),rs.getString("watchName"),rs.getString("Brandname"),rs.getString("watchPrice"),rs.getString("Quantity")));
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
	    
		gridContainer2.setVgap(10);
		gridContainer2.setHgap(10);
		

		button.setAlignment(Pos.CENTER);
	    
		button.setVgap(10);
		button.setHgap(10);

        table.setItems(data);
        table1.setItems(data3);
		

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
		table.setOnMouseClicked(new EventHandler<Event>() {
            
			
			
			
			@Override
			public void handle(Event arg0) {
				int temp = table.getSelectionModel().getSelectedIndex();
				load_data2(data.get(temp).getTransactionID());

				
			}
		
		
		
		});
		
	}
	
	
	void build() {
    	borderContainer.setTop(menuBar);

    	tid.setCellValueFactory(
                new PropertyValueFactory<>("TransactionID"));
    	uid.setCellValueFactory(
                new PropertyValueFactory<>("UserID"));
    	tdate.setCellValueFactory(
                new PropertyValueFactory<>("TransactionDate"));

		table.getColumns().addAll( tid,uid, tdate);
		table1.getColumns().addAll( q,w,e,r,t,y,u);
		

		q.setCellValueFactory(
                new PropertyValueFactory<>("TransactionID"));
		w.setCellValueFactory(
                new PropertyValueFactory<>("WatchId"));
		e.setCellValueFactory(
                new PropertyValueFactory<>("WatchName"));
		r.setCellValueFactory(
                new PropertyValueFactory<>("WatchBrand"));
		t.setCellValueFactory(
                new PropertyValueFactory<>("WatchPrice"));
		y.setCellValueFactory(
                new PropertyValueFactory<>("WatchQuantity"));
		u.setCellValueFactory(
                new PropertyValueFactory<>("SubTotal"));


		
		gridContainer.add(gridContainer1, 0, 0);

		gridContainer1.add(table, 0, 0);
		gridContainer1.add(table1, 0, 1);

		gridContainer2.add(lid, 0, 0);
		gridContainer2.add(id, 0, 1);
		gridContainer2.add(lname, 0, 2);
		gridContainer2.add(name, 0, 3);
		gridContainer2.add(lstock, 0, 5);
		gridContainer2.add(stock, 0, 6);
		gridContainer2.add(lbrand, 0, 7);
		gridContainer2.add(brand, 0, 8);
		gridContainer2.add(button, 0, 9);

		button.add(add, 0, 0);
		button.add(checkout, 1, 0);
		
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

