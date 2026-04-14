package com.hw.weather.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KmaMs {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String sql = "SELECT * FROM KMA_MS";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				int kmaMsNo = result.getInt("KMA_MS_NO");
				int kmaNo = result.getInt("KMA_NO");
				int msNo2 = result.getInt("MS_NO2");
				
				System.out.println("기상청과 위성의 번호 : " + kmaMsNo + ", \t기상청 번호 : " + kmaNo + ",\t위성 번호 : " + msNo2);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(result != null && !result.isClosed()) {
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
