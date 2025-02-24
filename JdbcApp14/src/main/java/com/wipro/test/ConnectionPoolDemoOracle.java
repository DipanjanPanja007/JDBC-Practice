package com.wipro.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnectionPoolDemoOracle {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			
			/* Load and register Driver Software */
			//Class.forName("oracle.jdbc.OracleDriver");
			
			/* estabilize connection between Java Application and Database software */
			OracleConnectionPoolDataSource ds=new oracle.jdbc.pool.OracleConnectionPoolDataSource();
			ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUser("system");
			ds.setPassword("infinite");
			
			con=ds.getConnection();
			
			/* create statement object*/
			if(con!=null) {
				st=con.createStatement();
			}
			
			/* execute query */
			if(st!=null) {
				rs=st.executeQuery("SELECT * FROM STUDENT");
			}
			
			/* process the result */
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t");
				}
			}
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
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
		}// end of finally
	}// end of main
}// end of class
