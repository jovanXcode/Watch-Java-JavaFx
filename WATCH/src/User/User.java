package User;

import connection.Connect;

public class User {
	//Attribute
	private int id;
	private String username;
	private String password;
	private String email;
	private String gender;
	private String role;
	private Connect connect = Connect.getInstance();
	//constructor
	public User(int id, String username, String password, String email, String gender,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.role = role;
	}

	public void insertuser() {
		String query2 = String.format("insert into user VALUES (0,'%s','%s','%s','%s','Customer')", this.username,this.email,this.password,this.gender);
		connect.execUpdate(query2);
	}
	
	//getter setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
