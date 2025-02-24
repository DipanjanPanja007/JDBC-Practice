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
				rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			}
			
			/* Step#5: Process(print) result from resultset */
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4));
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
		}/* end of finally */
	

	}

}
