package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			// step#1: load and register
			//Class.forName("oracle.jdbc.OracleDriver");
			
			// step#2: Estabilize connection between Java application and database
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "infinite");
			// step#3: Creat Statement Object
			if(con!=null) {
				st=con.createStatement();
			}
			//Step#5: Process(print) result from resultset
			if(st!=null) {
				rs=st.executeQuery("SELECT COUNT(*) FROM DEPT");
			}
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				System.out.println("Records count in Dept table is : "+count);
			}
			
		}// end of try main body 
		catch (SQLException se) {
			if(se.getErrorCode() >=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid Column name or Keyword");
			}
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
		}// end of finally
	}// end of main method
}// end of class
