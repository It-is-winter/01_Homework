package com.hw.weather.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KmaRun {

	public static void main(String[] args) {
		// 필요한 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String sql = "SELECT * FROM KMA";
		
		// dbms 선택
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 계정 선택 후 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			
			// 새편집기
			stmt = conn.createStatement();
			
			// sql 요청하고 응답받기
			result = stmt.executeQuery(sql);
			
			System.out.println("기상청 정보");
			// 응답받은 ResultSet을 하나하나 출력해주기
			while(result.next()) {
				int kmaNo = result.getInt("KMA_NO");
				String kmaName = result.getString("KMA_NAME");
				String kmaLoc = result.getString("KMA_LOCATION");
				
				System.out.println("기상청 번호 : " + kmaNo + ", \t기상청 이름 : " + kmaName + "\t기상청 위치 : " + kmaLoc);
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
