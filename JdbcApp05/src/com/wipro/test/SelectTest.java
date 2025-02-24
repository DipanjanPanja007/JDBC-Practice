package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		try {
			sc=new Scanner(System.in);
			
			System.out.print("Enter lowerbound of salary to check: ");
			float minSalary=sc.nextFloat();
			System.out.print("Enter lowerbound of salary to check: ");
			float maxSalary=sc.nextFloat();
			
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+minSalary+" AND SAL<="+maxSalary;
			
			/* Step#1: Load and Register driver */
			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Step#2: Estabilize connection between Java application and Database   */
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","infinite");
			
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
				boolean flag=false;
				System.out.println("Details of employee whose salary is between "+minSalary+" and "+maxSalary);
				System.out.println("EMPNO \t ENAME \t\t JOB \t \t  SAL");
				while(rs.next()) {
					flag=true;
					System.out.println(" "+rs.getInt(1)+" \t "+rs.getString(2)+" \t "+rs.getString(3)+" \t "+rs.getInt(4));
				}
				if(flag==false) {
					System.out.println("No such entry found as this range of salary.");
				}
			}		
			
		} catch (SQLException se) {	
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid colume number or table name in SQL ");
				se.printStackTrace();
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(sc!=null) {
				try {
					sc.close(); 								/* close Scanner object */
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close(); 								/* close ResultSet object */
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close(); 								/* close Statement object */
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close(); 								/* close Connection object */
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}	
			
		}/* end of finally */
	}/* end of main method */
}/* end of class */
