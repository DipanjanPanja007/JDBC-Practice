package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		
		/* Write a Jdbc Application that gives employee details (empno, ename, job, sal ) for the  employee who is having highest salary*/
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			/* Step#1: Load and Register driver */
			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Step#2: Estabilize connection between Java application and Database   */
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system" ,"infinite" );
			
			/* Step#3: Creat Statement Object */
			if(con!=null) {
				st=con.createStatement();
			}
			
			/* Step#4: Send and execute SQL query */
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			/* Step#5: Process(print) result from resultset */
			System.out.println(" EMPNO \t ENAME      JOB              SAL");
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getString(1)+" \t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				}
			}
			
		} catch (SQLException se) {
			if(se.getErrorCode() >=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid Column name or Keyword");
			}
			se.printStackTrace();
		}
		catch (Exception e) {
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
