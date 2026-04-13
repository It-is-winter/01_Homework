package com.hw.jdbc.weather.mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hw.jdbc.weather.mvc.model.vo.Weather;

public class WeatherDao {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public List<Weather> findAll() {
		List<Weather> weathers = new ArrayList();
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rset = null;
		String sql = """
						SELECT
							   * 
						  FROM 
				    		   WEATHER 
				    	 ORDER 
				    	    BY 
				    	       WEATHER_NO
					 """;
		
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql)) {
			while(rset.next()) {
				int weatherNo = rset.getInt("WEATHER_NO");
				String weatherName = rset.getString("WEATHER_CONDITION");
				String temperature = rset.getString("TEMPERATURE");
				long msNo = rset.getLong("MS_NO");
				
				weathers.add(new Weather(weatherNo, weatherName, temperature, msNo));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return weathers;
	}
	
	public Weather findById(int weatherId) {
		Weather weather = null;
		String sql = """
						SELECT 
						       *
						  FROM 
						       WEATHER
						 WHERE 
						       WEATHER_NO = 
					 """;
		sql += weatherId;
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "C##SJ", "SJ");
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql)) {
			if(rset.next()) {
				int weatherNo = rset.getInt("WEATHER_NO");
				String weatherName = rset.getString("WEATHER_CONDITION");
				String temperature = rset.getString("TEMPERATURE");
				long msNo = rset.getLong("MS_NO");
				weather = new Weather(weatherNo, weatherName, temperature, msNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return weather;
	}

}
