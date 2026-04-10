package com.hw.jdbc.weather.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class KmaMsRun {

	public static void main(String[] args) {
		// 필요한 변수 선언
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		int result = 0;
		
		System.out.print("기상청과 기상위성을 연결할 식별번호를 입력해주세요 >");
		String kmaMsNo = sc.nextLine();
		System.out.print("기상정보를 제공받을 기상청의 번호를 입력해주세요 >");
		String kmaNo = sc.nextLine();
		System.out.print("기상정보를 제공한 기상위성의 번호를 입력해주세요 >");
		String msNo2 = sc.nextLine();
		
		sb.append("INSERT INTO KMA_MS VALUES('");
		sb.append(kmaMsNo);
		sb.append("', '");
		sb.append(kmaNo);
		sb.append("', '");
		sb.append(msNo2);
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
