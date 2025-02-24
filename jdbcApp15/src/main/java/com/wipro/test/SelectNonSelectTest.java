package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Scanner sc=null;
		try {
			sc=new Scanner(System.in);
			String sqlQuery=null;
			/* Read input */
			if(sc!=null) {
				System.out.println("Enter SQL Query ( SELECT or Non-SELECT");
				sqlQuery=sc.nextLine();
			}
			
			/* Load and register Driver Software */
			//Class.forName("oracle.jdbc.OracleDriver");
			
			/* Estabilize the connection  */
//			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "infinite");
			con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.2:1521:xe", "system", "infinite");   // localhost -> ip address
			System.out.println(con.getClass().getName());
			
			
			/* creat PrepareStatement Object */
			if(con!=null) {
				pst=con.prepareStatement(sqlQuery);
			}
			
			/* send and execute Database Query in Database Software */
			
			/* *********  execute method   *********
			 *  returns true if it get select query
			 *  and returns false if get non-select query
			 * */
			if(pst!=null) {
				boolean flag=pst.execute();           
				
				if(flag==true) {   // -> select query
					System.out.println("Select SQL query executed");
					/* gather and process ResultSet */
					rs=pst.getResultSet();
					
					/* process the ResultSet */
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t");
						}// end of while
					}//end of if
				}
				else {                  // -> non select query
					System.out.println("Non-Select SQL query executed");
					/* gather the result*/
					int count=pst.getUpdateCount();
					System.out.println("No of records are effected is : "+count);					
				}// end of else
			}
			
			
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(sc!=null) {
				try {
					sc.close();
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
		}//end of finally
	}//end of main
}//end of class
