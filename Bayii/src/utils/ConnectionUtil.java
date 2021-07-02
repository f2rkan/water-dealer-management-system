package utils;

import java.sql.*;

public class ConnectionUtil {

	
	Connection conn = null;
	
	public static Connection conDB() {
    	
    	try {
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/otomasyon", "root", "mysql");
    		return con;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }	
}
