package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestUpdate {
	private static final String UPDATE_EMP="UPDATE EMP SET SAL=SAL+(SAL*?/100) WHERE JOB IN(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pst=null;
		Scanner sc=null;
		
		try {
			
			sc=new Scanner(System.in);
			int hikePersentage=0;
			String desig1=null,desig2=null,desig3=null;
			
			/* Step#1: Load and Register driver */
//			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Step#2: Estabilize connection between Java application and Database   */
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system" ,"infinite");
			
			if(con!=null) {
				pst=con.prepareStatement(UPDATE_EMP);
			}
			
			/* Read input*/
			if(pst!=null) {
				if(sc!=null) {
					System.out.print("Enter Salary hike percentage: ");
					hikePersentage=sc.nextInt();
					System.out.print("Enter Employee Designation #1: ");
					desig1=sc.next().toUpperCase();
					System.out.print("Enter Employee Designation #2: ");
					desig2=sc.next().toUpperCase();
					System.out.print("Enter Employee Designation #3: ");
					desig3=sc.next().toUpperCase();
					
					pst.setInt(1, hikePersentage);
					pst.setString(2, desig1);
					pst.setString(3, desig2);
					pst.setString(4, desig3);
				}
			}
			
			
			
			/* Step#4: Send and execute SQL query */
			int rowCount=pst.executeUpdate();
			if(rowCount==0) {
				System.out.println("Records not updated");
			}
			else {
				System.out.println(rowCount+" no of rows are updated");
			}
			
		} catch (SQLException se) {
			if(se.getErrorCode()<=900 && se.getErrorCode()<=999) {
				System.out.println("Invalid col name, table name or keyword");
			}
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(sc!=null) {
				try {
					sc.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
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
		}// end of finaly
	}// end of main
}// end of class
