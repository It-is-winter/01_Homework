package com.hw.weather.mvc.v4.model.service;

import static com.hw.weather.mvc.v4.common.JdbcTemplate.close;
import static com.hw.weather.mvc.v4.common.JdbcTemplate.commit;
import static com.hw.weather.mvc.v4.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.hw.weather.mvc.v4.exception.WeatherConditionLengthOutOfBoundsException;
import com.hw.weather.mvc.v4.model.dao.WeatherDao;
import com.hw.weather.mvc.v4.model.dto.WeatherDto;
import com.hw.weather.mvc.v4.model.dto.WeatherMsDto;

public class WeatherService {
	/*
	 * Find all weather condition
	 */
	public List<WeatherMsDto> findAll() {
		Connection conn = getConnection();
		List<WeatherMsDto> result = new WeatherDao().findAll(conn);
		return result;
	}
	/*
	 * Select weather detail : weatherId, weatherName, temperature, msId
	 */
	public WeatherMsDto findById(int weatherId) {
		Connection conn = getConnection();
		return new WeatherDao().findById(conn, weatherId);
	}
	/*
	 * Create a new weather information
	 */
	public int createWeather(WeatherDto weatherDto) {
		if(weatherDto != null) {
			String weatherName = weatherDto.getWeatherName();
			if(validateName(weatherName)) {
				throw new WeatherConditionLengthOutOfBoundsException();
			}
		}
		Connection conn = getConnection();
		int result = new WeatherDao().createWeather(conn, weatherDto);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}
	/*
	 * Update weather information
	 */
	public int updateWeather(WeatherDto weatherDto) {
		if(weatherDto != null) {
			String weatherName = weatherDto.getWeatherName();
			if(validateName(weatherName)) {
				throw new WeatherConditionLengthOutOfBoundsException();
			}
		}
		Connection conn = getConnection();
		int result = new WeatherDao().updateWeather(conn, weatherDto);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}
	/*
	 * Delete weather information
	 */
	public int deleteWeather(int weatherId) {
		Connection conn = getConnection();
		int result = 0;
		result = new WeatherDao().deleteWeather(conn, weatherId);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}
	
	private boolean validateName(String weatherName) {
		if(0 > weatherName.length() || weatherName.length() > 20) {
			return true;
		}
		return false;
	}

}
