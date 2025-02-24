package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MySQLInsertTestSurrogatePrimarykey {
	
	public static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT (SNAME,SADDR,SAVG) VALUES(?,?,?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst=null;
		Scanner sc=null;
		try {
			
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter how many number of student details you wanna insert: ");
				count=sc.nextInt();
			}
			
			/* Load and Register driver */
//			Class.forName("oracle.jdbc.OracleDriver");
			
			/* Estabilize connection between Java application and Database  */
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dipanjan01","root" ,"root");
			
			/* Creat Prepared Statement Object having preCompiled SQL query */
			if(con!=null) {
				pst=con.prepareStatement(STUDENT_INSERT_QUERY);
			}
			
			/* Input student details and set them to query parameters  and execute pst  :) */
			if(sc!=null && pst!=null) {
				for(int i=1;i<=count;i++) {
					System.out.println("Enter "+i+" student details: ");
					System.out.print("Enter student name: ");
					String name=sc.next();
					
					System.out.print("Enter student address: ");
					String add=sc.next();
					
					System.out.print("Enter student marks: ");
					Double marks=sc.nextDouble();
					
					// set to pre-compiled query parameter
					pst.setString(1, name);
					pst.setString(2, add);
					pst.setDouble(3, marks);
					
					int result=pst.executeUpdate();      // problem was here
					System.out.println(result);
					if(result==0) {
						System.out.println("Sorry Boss, "+i+" Student details not inserted.");
					}
					else {
						System.out.println("Yess Boss, "+i+" Student details inserted.");
					}
					
				}				
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();
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
		}// end of finally
	}//end of main
}// end of class
