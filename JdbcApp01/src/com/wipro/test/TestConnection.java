package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

	public static void main(String[] args) {

		Connection con=null;
		try {
			
			/* Step#1: Load and Register driver */
			/* Step#2: Estabilize connection between Java application and Database   */
			/* Step#3: Creat Statement Object */
			/* Step#4: Send and execute SQL query */
			/* Step#5: Process(print) result from resultset */
			
			/*Step#1: Load and Register Driver*/
			/*oracle.jdbc.driver.OracleDriver driver=new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(driver); */
			
//			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "infinite");
			
			if(con!=null) {
				System.out.println("Connection Estabized");
			}
			else {
				System.out.println("Connection failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}

}
