package com.hw.weather.mvc.v3.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hw.weather.mvc.v3.model.dto.WeatherDto;
import com.hw.weather.mvc.v3.model.vo.Weather;

public class WeatherDao {
	public List<Weather> findAll(Connection conn) {
		List<Weather> weathers = new ArrayList();
		String sql = """
						SELECT
							   WEATHER_NO
							 , WEATHER_CONDITION
							 , TEMPERATURE
							 , MS_NO 
						  FROM 
				    		   WEATHER 
				    	 ORDER 
				    	    BY 
				    	       WEATHER_NO
					 """;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery()) {
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
	
	public Weather findById(Connection conn, int weatherId) {
		Weather weather = null;
		String sql = """
						SELECT 
						       WEATHER_NO
							 , WEATHER_CONDITION
							 , TEMPERATURE
							 , MS_NO
						  FROM 
						       WEATHER
						 WHERE 
						       WEATHER_NO = ?
					 """;
		sql += weatherId;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery()) {
			pstmt.setInt(1, weatherId);
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
	
	public int createWeather(Connection conn, WeatherDto weatherDto) {
		int result = 0;
		String sql = """
						INSERT 
				 	      INTO 
				 			   WEATHER  
				 	    VALUES 
							   (
							   SEQ_WEATHER.NEXTVAL
							 , ?
							 , ?
							 , ?
							   )
					 """;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, weatherDto.getWeatherName().toUpperCase());
			pstmt.setString(2, weatherDto.getTemperature().toUpperCase());
			pstmt.setLong(3, weatherDto.getMsId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateWeather(Connection conn, WeatherDto weatherDto) {
		int result = 0;
		String sql = """
						UPDATE 
				               WEATHER
				    	   SET 
				    		   WEATHER_CONDITION = ?
				    		 , TEMPERATURE = ? 
				    		 , MS_NO = ? 
				         WHERE 
				    		   WEATHER_NO = ?
					 """;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, weatherDto.getWeatherName().toUpperCase());
			pstmt.setString(2, weatherDto.getTemperature().toUpperCase());
			pstmt.setLong(3, weatherDto.getMsId());
			pstmt.setInt(4, weatherDto.getWeatherId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteWeather(Connection conn, int weatherId) {
		int result = 0;
		String sql = """
						DELETE 
						  FROM 
						       WEATHER
						 WHERE 
						       WEATHER_NO = ?
					 """;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, weatherId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int checkId(Connection conn, int weatherId) {
		int result = 0;
		String sql = """
						SELECT 
							   WEATHER_NO
						  FROM 
						       WEATHER
						 WHERE 
						       WEATHER_NO = ?
					 """;
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, weatherId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
