package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {

	public static void main(String[] args) {
		/* Jdbc 07 */
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		try {
			
			/* Step#1: Load and Register driver */
//			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Step#2: Estabilize connection between Java application and Database   */
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","infinite");
			
			/* Step#3: Creat Statement Object */
			if(con!=null) {
				st=con.createStatement();
			}
			
			/* Step#4: Send and execute SQL query */
			if(st!=null) {
				rs=st.executeQuery("SELECT SID,SNAME,SMARKS FROM STUDENT");
			}
			
			/* Step#5: Process(print) result from resultset */
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getString(1)+" \t"+rs.getString(2)+"\t"+rs.getString(3));
				}
			}
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(sc!=null) {
				try {
					sc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}/* end of finally */

	

	}

}
