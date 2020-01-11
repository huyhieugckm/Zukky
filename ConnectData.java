package testDb;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectData {
	private String dbName = "database1";
	private String userName = "huyhieu";
	private String password = "1234";
	private String url = "jdbc:mariadb://localhost:3306/";
	public static Connection conn = null;
	public void ConnectDb() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(Exception e) {}
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
		}catch(Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	public void CloseDb() {
		try {
			conn.close();
		}catch(Exception e) {}
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
