package com.hw.weather.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class KmaRun {

	public static void main(String[] args) {
		// 필요한 변수 선언
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		int result = 0;
		
		System.out.print("추가하실 기상청 번호를 입력해주세요 >");
		String kmaNo = sc.nextLine();
		System.out.print("추가하실 기상청의 이름을 입력해주세요 >");
		String kmaName = sc.nextLine();
		System.out.print("추가하실 기상청의 위치를 입력해주세요 >");
		String kmaLocation = sc.nextLine();
		
		sb.append("INSERT INTO KMA VALUES('");
		sb.append(kmaNo);
		sb.append("', '");
		sb.append(kmaName);
		sb.append("', '");
		sb.append(kmaLocation);
		sb.append("')");
		
		sql = sb.toString();
		
		// 접속할 dbms 선택 -> oracle
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 계정 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			
			conn.setAutoCommit(false);
			// 새 편집기
			stmt = conn.createStatement();
			
			// sql문 요청하고 응답받기
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
