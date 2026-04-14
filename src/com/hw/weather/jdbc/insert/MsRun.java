package com.hw.weather.jdbc.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MsRun {

	public static void main(String[] args) {
		// 필요한 변수 선언
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		int result; // 응답 결과를 담을 변수
		
		// 사용자에게 어떤값을 insert할지 입력받기
		System.out.print("추가하고 싶은 위성 번호를 입력해주세요 >");
		int msNo = 0;
		try {
			msNo = sc.nextInt();
			sc.nextLine();
		} catch(InputMismatchException e) {
			e.printStackTrace();
		}
		System.out.print("추가하실 위성의 이름을 입력해주세요 >");
		String msName = sc.nextLine();
		
		sb.append("INSERT INTO MS VALUES (");
		sb.append(msNo);
		sb.append(", '");
		sb.append(msName);
		sb.append("')");
		
		sql = sb.toString();
		// 어떤 서버를 사용하겠다! -> oracle
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 계정 연결(ip주소, port번호, 사용자 이름, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
//			System.out.println("계정연결");
			
			// auto commit 끄기
			conn.setAutoCommit(false);
			
			// Statement 객체 생성 <- 새 편집기 열기
			stmt = conn.createStatement();
//			System.out.println("새편집기열기");
			
			// SQL문 요청하고 응답받기
			result = stmt.executeUpdate(sql);
//			System.out.println("sql문 넘기기");
			
			if(result > 0) { // INSERT 성공
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
