package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestMySQL {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			/* Load JDBC driver class */
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			/* Estabilize connection  */
//			con=DriverManager.getConnection("jdbc:mysql:///dipanjan01", "root", "root");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dipanjan01", "root", "root");
//			System.out.println(con.getClass().getName());
			
//			/* create JDBC statement object */
			if(con!=null) {
				st=con.createStatement();
			}
			
			/* send and execute SQL Select query in database Software to get ResultSet Object */
			if(st!=null) {
				rs=st.executeQuery("SELECT * FROM STUDENT");
			}
			
			/* Process ResultSet Object */
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4));
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}//end of finally
	}//end of main
}//end of class
