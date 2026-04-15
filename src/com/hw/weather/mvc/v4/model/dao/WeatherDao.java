package com.hw.weather.mvc.v4.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hw.weather.mvc.v4.model.dto.WeatherDto;
import com.hw.weather.mvc.v4.model.dto.WeatherMsDto;
import com.hw.weather.mvc.v4.model.vo.Weather;

public class WeatherDao {
	private Properties prop = new Properties();
	public WeatherDao() {
		// Load weather-mapper.xml in DAO constructor to use SQL queries
		try {
			prop.loadFromXML(new FileInputStream("resources/weather-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<WeatherMsDto> findAll(Connection conn) {
		List<WeatherMsDto> weathers = new ArrayList();
		String sql = prop.getProperty("findAll");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery()) {
			while(rset.next()) {
				int weatherNo = rset.getInt("WEATHER_NO");
				String weatherName = rset.getString("WEATHER_CONDITION");
				weathers.add(new WeatherMsDto(weatherNo, weatherName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return weathers;
	}
	
	public WeatherMsDto findById(Connection conn, int weatherId) {
		WeatherMsDto weather = null;
		String sql = prop.getProperty("findById");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, weatherId);
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					int weatherNo = rset.getInt("WEATHER_NO");
					String weatherName = rset.getString("WEATHER_CONDITION");
					String temperature = rset.getString("TEMPERATURE");
					String msName = rset.getString("MS_NAME");
					weather = new WeatherMsDto(weatherNo, weatherName, temperature, msName);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return weather;
	}
	
	public int createWeather(Connection conn, WeatherDto weatherDto) {
		int result = 0;
		String sql = prop.getProperty("createWeather");
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
		String sql = prop.getProperty("updateWeather");
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
		String sql = prop.getProperty("deleteWeather");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, weatherId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
