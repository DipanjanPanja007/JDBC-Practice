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
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system" ,"infinite" );
			
			/* Step#3: Creat Statement Object */
			if(con!=null) {
				st=con.createStatement();
			}
			/* Step#4: Send and execute SQL query */
			if(st!=null) {
//				rs=st.executeQuery("SELECT EMPNO, ENAME, JOB, SAL FROM EMP");
				rs=st.executeQuery("SELECT EMPNO, ENAME, JOB, SAL FROM EMP");
			}
			
			/* Step#5: Process(print) result from resultset */
			System.out.println("EMPNO \t ENAME \t JOB \t SAL");
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+"\t "+rs.getString(4));
				}
			}
			
		} catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid Column name or Keyword ");
			}
			se.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
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
			
		}/*end of finally*/

	}/*end of main*/

}/*end of class*/
