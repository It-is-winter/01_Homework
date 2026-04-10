package com.hw.weather.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class WeatherRun {

	public static void main(String[] args) {
		// 필요한 변수 선언
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		int result = 0;
		
		System.out.print("추가하실 날씨 번호를 입력해주세요 >");
		String weatherNo = sc.nextLine();
		System.out.print("추가하실 날씨 종류를 영어로 입력해주세요 >");
		String weatherCon = sc.nextLine();
		System.out.print("추가하실 날씨의 기온을 입력해주세요(화씨 : (F) / 섭씨 : (C)) >");
		String temp = sc.nextLine();
		System.out.print("추가하신 날씨를 관측한 위성의 번호를 입력해주세요 >");
		String msNo = sc.nextLine();
		
		sb.append("INSERT INTO WEATHER VALUES('");
		sb.append(weatherNo);
		sb.append("', '");
		sb.append(weatherCon);
		sb.append("', '");
		sb.append(temp);
		sb.append("', '");
		sb.append(msNo);
		sb.append("')");
		
		sql = sb.toString();
		
		// dbms 선택하기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 계정 선택 후 접속하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			conn.setAutoCommit(false);
			
			// 새편집기 열기
			stmt = conn.createStatement();
			
			// sql문 요청하고 응답 받기
			result = stmt.executeUpdate(sql);
			if(result > 0) {
				conn.commit();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sc.close();
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
