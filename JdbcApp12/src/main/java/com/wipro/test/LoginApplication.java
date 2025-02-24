package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApplication {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		try {
			sc=new Scanner(System.in);
			String user=null,pass=null;
			String query="";
			System.out.print("Enter Username: ");
			user=sc.nextLine().toUpperCase();
			System.out.print("Enter PassWord: ");
			pass=sc.nextLine().toUpperCase();
			query="SELECT COUNT(*) FROM IRCTC_BOOKING WHERE UNAME='"+user+"' AND UPWD='"+pass+"'";
			
			
			/* Step#1: Load and Register driver */
//			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Step#2: Estabilize connection between Java application and Database   */
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system" ,"infinite" );
			
			/* Step#3: Creat Statement Object */
			if(con!=null) {
				st=con.createStatement();
			}
			/* Step#4: Send and execute SQL query */
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			/* Step#5: Process(print) result from resultset */
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				if(count==0) {
					System.out.println("No user found with the given userid and password");
				}
				else {
					System.out.println("Valid userid and password");
				}
			}
		} catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid Column name or Keyword ");
			}
			if(se.getErrorCode()==12899) {
				System.out.println("Value too large for column");
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
		}//end of finally
	}// end of main
}// end of class
