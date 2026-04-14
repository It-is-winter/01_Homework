package com.hw.weather.mvc.v3.model.service;

import java.sql.Connection;
import java.util.List;

import static com.hw.weather.mvc.v3.common.JdbcTemplate.*;

import com.hw.weather.mvc.v3.exception.DuplicateWeatherNoException;
import com.hw.weather.mvc.v3.exception.WeatherConditionLengthOutOfBoundsException;
import com.hw.weather.mvc.v3.model.dao.WeatherDao;
import com.hw.weather.mvc.v3.model.dto.WeatherDto;
import com.hw.weather.mvc.v3.model.vo.Weather;

public class WeatherService {
	WeatherDao weatherDao = new WeatherDao();
	/*
	 * Find all weather condition
	 */
	public List<Weather> findAll() {
		Connection conn = getConnection();
		List<Weather> result = weatherDao.findAll(conn);
		return result;
	}
	/*
	 * Select weather detail : weatherId, weatherName, temperature, msId
	 */
	public Weather findById(int weatherId) {
		Connection conn = getConnection();
		return weatherDao.findById(conn, weatherId);
	}
	/*
	 * Create a new weather information
	 */
	public int createWeather(WeatherDto weatherDto) {
		if(weatherDto != null) {
			if(checkId(weatherDto.getWeatherId())) {
				throw new DuplicateWeatherNoException();
			}
			String weatherName = weatherDto.getWeatherName();
			if((weatherName != null) && validateName(weatherName)) {
				throw new WeatherConditionLengthOutOfBoundsException();
			}
		}
		Connection conn = getConnection();
		int result = weatherDao.createWeather(conn, weatherDto);
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
			if((weatherName != null) && validateName(weatherName)) {
				throw new WeatherConditionLengthOutOfBoundsException();
			}
		}
		Connection conn = getConnection();
		int result = weatherDao.updateWeather(conn, weatherDto);
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
		if(checkId(weatherId)) {
			throw new DuplicateWeatherNoException();
		}
		result = weatherDao.deleteWeather(conn, weatherId);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}
	
	private boolean validateName(String weatherName) {
		if(0 > weatherName.length() && weatherName.length() > 20) {
			return true;
		}
		return false;
	}
	
	private boolean checkId(int weatherId) {
		Connection conn = getConnection();
		if(weatherDao.checkId(conn, weatherId) > 0) {
			return true;
		}
		return false;
	}

}
