package com.hw.weather.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MsRun {

	public static void main(String[] args) {
		// 필요한 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String sql = "SELECT * FROM MS";
		
		// dbms 선택
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 계정 선택 후 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1", "C##SJ", "SJ");
			
			// 새편집기 열기
			stmt = conn.createStatement();
			
			// sql 요청을 보내고 응답 받기
			result = stmt.executeQuery(sql);
			
			// ResultSet의 결과를 하나하나 출력해주기
			System.out.println("기상위성 정보");
			while(result.next()) { // 행이 얼마나 있을지 알 수 없음
				int msNo = result.getInt("MS_NO");
				String msName = result.getString("MS_NAME");
				
				System.out.println("기상위성 번호 : " + msNo + ",\t기상위성 이름 : " + msName);
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
