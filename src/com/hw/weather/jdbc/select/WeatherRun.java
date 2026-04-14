package com.hw.weather.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WeatherRun {

	public static void main(String[] args) {
		// 필요 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String sql = "SELECT * FROM WEATHER";
		
		// DBMS 선택
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 계정 선택 후 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			
			// 새 편집기
			stmt = conn.createStatement();
			
			// sql 요청하고 응답 받기
			result = stmt.executeQuery(sql);
			// 응답받은 ResultSet을 하나하나 출력해주기
			System.out.println("날씨 정보");
			while(result.next()) {
				int weatherNo = result.getInt("WEATHER_NO");
				String weatherCon = result.getString("WEATHER_CONDITION");
				String temp = result.getString("TEMPERATURE");
				int msNo = result.getInt("MS_NO");
				
				System.out.println("날씨 번호 : " + weatherNo + ", \t날씨 종류 : " + weatherCon + ",\t기온 : " + temp + ", \t정보를 제공받은 위성 번호 : " + msNo);
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
