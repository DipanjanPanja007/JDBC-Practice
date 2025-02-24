package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {

		Connection con=null;
		Statement st=null;          /* Statement of sql package not bean package */
		ResultSet rs=null;
		try {
			
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
				rs=st.executeQuery("SELECT DEPTNO, DNAME,  LOC FROM DEPT");
			}
			
			/* Step#5: Process(print) result from resultset */
			if(rs!=null) {
				System.out.println(" DEPTNO  \t DNAME  \t \tLOC \n=================================");
				while(rs.next()) {
					System.out.println(" "+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
				}
			}
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
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
