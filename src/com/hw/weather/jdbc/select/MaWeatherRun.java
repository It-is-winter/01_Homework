package com.hw.weather.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaWeatherRun {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String sql = "SELECT * FROM MA_WEATHER";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				int weatherNo = result.getInt("WEATHER_NO");
				int kmaNo = result.getInt("KMA_NO");
				
				System.out.println("날씨 번호 : " + weatherNo + ",\t기상청 번호 : " + kmaNo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
