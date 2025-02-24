package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateInsertTestOracle {

	private static final String INSERT_DATE_QUERY="INSERT INTO PERSON_INFO_DATES VALUES (PID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		  Scanner sc=null;
		  Connection con=null;
		  PreparedStatement pst=null;
		  
		  try {
			  sc=new Scanner(System.in);
			  String name=null,dob=null,doj=null,dom=null;
			  if(sc!=null) {
				  System.out.println("Enter person name: ");
				  name=sc.next();
				  
				  System.out.println("Enter DOB (dd-MMM-yyyy): ");
				  dob=sc.next();
				  
				  System.out.println("Enter DOJ (yyyy-MM-dd): ");
				  doj=sc.next();
				  
				  System.out.println("Enter DOM (MMM-dd-yyyy): "); 
				  dom=sc.next();
				  
				  /* DOB */
				  /* Convert date from string to java.util.Date class object */
				  SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
				  java.util.Date utdob=sdf.parse(dob);
				  
				  /* Convert java.util.Date class object to java.sql.Date class object */
				  long l=utdob.getTime();
				  java.sql.Date sqdob=new java.sql.Date(l);
				  
				  /* DOJ */
				  /* for DOJ(yyyy-MM-dd) -> direct conversion from string to java.sql.Date class Object */
				  /* Convert string value to java.sql.Date class Object */
				  java.sql.Date sqdoj= java.sql.Date.valueOf(doj);
				  
				  /* DOM */
				  /* Convert date from string to java.util.Date class object */
				  SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
				  java.util.Date utdom=sdf2.parse(dom);
				  
				  /* Convert java.util.Date class object to java.sql.Date class object */
				  l=utdom.getTime();
				  java.sql.Date sqdom=new java.sql.Date(l);
				  
				  
				  
				  /* Load JDBC Driver class */
//				  Class.forName("oracle.jdbc.OracleDriver");
				  
				  /* Estabilize connection  */
				  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "infinite");
				   
				  /* Create PreparedStatement Object */
				  if(con!=null) {
					  pst=con.prepareStatement(INSERT_DATE_QUERY);
				  }				  
				  
				  /* Set values to query parameter */
				  if(pst!=null) {
					  pst.setString(1,name);
					  pst.setDate(2, sqdob);
					  pst.setDate(3, sqdoj);
					  pst.setDate(4, sqdom);
				  }
				  
				  /* execute query */
				  int count=0;
				  if(pst!=null) {
					  count=pst.executeUpdate();
				  }
				  if(count==0) {
					  System.out.println("Records are not inserted");
				  }
				  else {
					  System.out.println("Record inserted ");
				  }
			  }
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null) {
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(con!=null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// end of finally
	}// end of main
}// end of class
