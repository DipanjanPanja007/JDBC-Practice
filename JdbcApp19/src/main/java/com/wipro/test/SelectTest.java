package com.wipro.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			Properties properties=new Properties();
			FileInputStream fis=new FileInputStream("src/main/resources/db.properties");
			/* To load all properties  to the Java properties object */
			properties.load(fis);
			
			String url=properties.getProperty("url");
			String username=properties.getProperty("user");
			String password=properties.getProperty("pass");
			
			con=DriverManager.getConnection(url, username, password);
			
			
			if(con!=null) {
				st=con.createStatement();
			}
			
			if(st!=null) {
				rs=st.executeQuery("SELECT * FROM STUDENT");
			}
			
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}// end of finally
	}// end of main
}// end of class
