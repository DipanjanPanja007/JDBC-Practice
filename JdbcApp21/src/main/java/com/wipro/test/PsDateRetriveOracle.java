package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PsDateRetriveOracle {
	private static final String RETRIVE_DATES_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			/* Load JDBC Driver class */
//			  Class.forName("oracle.jdbc.OracleDriver");
			  
			  /* Estabilize connection  */
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "infinite");
			   
			  /* Create PreparedStatement Object */
			  if(con!=null) {
				  pst=con.prepareStatement(RETRIVE_DATES_QUERY);
			  }		
			  
			  /* Execute query */
			  if(pst!=null) {
				  rs=pst.executeQuery();
			  }
			  
			  /* process the resultset object */
			  
//			  if(rs!=null) {
//				  while(rs.next()) {
//					  System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t");
//				  }
//			  }
			  
			  if(rs!=null) {
				  while(rs.next()) {
					  int pid=rs.getInt(1);
					  String pname=rs.getString(2);
					  
					  java.sql.Date sqdob=rs.getDate(3);
//					  System.out.println(sqdob);                                        // yyyy-MM-dd  (1980-04-12)
					  java.sql.Date sqdoj=rs.getDate(4);
					  java.sql.Date sqdom=rs.getDate(5);
					  
					  /* convert java.sql.Date class object to String value */
					  SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
//					  SimpleDateFormat sdf=new SimpleDateFormat("dd-yyyy-MM");           // how I pass in the SimpleDateFormat argment, result will show as argument
					  String sdob=sdf.format(sqdob);
					  String sdoj=sdf.format(sqdoj);
					  String sdom=sdf.format(sqdom);				  
					  
					  System.out.println(pid+"\t"+pname+"\t"+sdob+"\t"+sdoj+"\t"+sdom+"\t");
					  
					  
					  
					  												/*		Experimental Purpose Only       */
					  /* If I want to convert java.util.Date class object to java.util.Date class object */
					  /* Here we use java.util.Date class reference to refer java.sql.Date class object
					   *       [java.util.Date is super class of java.sql.Date class]                                    */									  
//					  System.out.println("ud is in the class: "+sqdob.getClass());
//					  System.out.println(sqdob);                              // -> java.sql.Date class object gives only date info
//					  java.util.Date ud=new java.util.Date(sqdob.getTime());
//					  System.out.println("ud is in the class: "+ud.getClass());
//					  System.out.println(ud);                              // -> java.sql.Date class object gives date info and time							  
					  
					  
				  }// end of while
			  }// end of if
			  
			  
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(pst!=null) {
					pst.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}// end of main
}// end of class
