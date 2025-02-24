package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {

	public static void main(String[] args) {

		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			String initialChar=null;
			System.out.print("Enter First letter of name to check: ");
			if(sc!=null) {
				initialChar=sc.next();
			}
			/* Convert initial charector into SQL query*/
			initialChar=initialChar.toUpperCase()+"%";          // A% type			
		
			/* Step#1: Load and Register driver */
			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Step#2: Estabilize connection between Java application and Database   */
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "infinite");
			
			/* Step#3: Creat Statement Object */
			if(con!=null) {
				st=con.createStatement();
			}
			
			/* Step#4: Send and execute SQL query */
			if(st!=null) {
				rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE '"+initialChar+"'");
			}
			
			/* Step#5: Process(print) result from resultset */
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(" "+rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t"+rs.getString(4));
				}
			}
			
			
			
		} catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid colume number or table name in SQL ");
				se.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			/* Step#6: Close Connection */
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
		}/* close finally block*/
	

	}

}
