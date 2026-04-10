package com.hw.weather.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MaWeatherRun {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		int result = 0;
		
		System.out.print("기상청에게 제공할 날씨종류의 번호를 입력해주세요 >");
		String weatherNo = sc.nextLine();
		System.out.print("날씨정보를 제공받을 기상청의 번호를 입력해주세요 >");
		String kmaNo = sc.nextLine();
		
		sb.append("INSERT INTO MA_WEATHER VALUES('");
		sb.append(weatherNo);
		sb.append("', '");
		sb.append(kmaNo);
		sb.append("')");
		
		sql = sb.toString();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
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
