package com.hw.jdbc.weather.mvc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hw.jdbc.weather.mvc.model.dto.WeatherDto;
import com.hw.jdbc.weather.mvc.model.vo.Weather;

public class WeatherDao {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	public List<Weather> findAll() {
		List<Weather> weathers = new ArrayList();
		String sql = """
						SELECT
							   * 
						  FROM 
				    		   WEATHER 
				    	 ORDER 
				    	    BY 
				    	       WEATHER_NO
					 """;
		
		try(Connection conn = DriverManager.getConnection(url, "C##SJ", "SJ");
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
		try(Connection conn = DriverManager.getConnection(url, "C##SJ", "SJ");
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
	
	public int createWeather(WeatherDto weatherDto) {
		String sql = "INSERT "
				+ 	   "INTO "
				+ 			"WEATHER "
				+ 	 "VALUES("
				+ 			 weatherDto.getWeatherId() + ", "
				+ 	   "'" + weatherDto.getWeatherName() + "', "
				+	   "'" + weatherDto.getTemperature() + "', "
				+ 			 weatherDto.getMsId()
				+ 			")";
		return withConnection(sql);
	}
	
	public int updateWeather(WeatherDto weatherDto) {
		String sql = "UPDATE "
				   +        "WEATHER "
				   + 	"SET "
				   + 		"WEATHER_CONDITION = '" + weatherDto.getWeatherName() + "', "
				   + 		"TEMPERATURE = '" + weatherDto.getTemperature() + "', "
				   + 		"MS_ID = " + weatherDto.getMsId()
				   +  "WHERE "
				   + 		"WEATHER_ID = " + weatherDto.getWeatherId();
		return withConnection(sql);
	}
	
	public int deleteWeather(int weatherId) {
		String sql = """
						DELETE 
						  FROM 
						       WEATHER
						 WHERE 
						       WEATHER_ID = 
					 """;
		sql += weatherId;
		return withConnection(sql);
	}
	
	private int withConnection(String sql) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(url,"C##SJ", "SJ");
			Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			result = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
