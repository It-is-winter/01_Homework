package com.hw.jdbc.weather.mvc.model.service;

import java.util.List;

import com.hw.jdbc.weather.mvc.model.dao.WeatherDao;
import com.hw.jdbc.weather.mvc.model.dto.WeatherDto;
import com.hw.jdbc.weather.mvc.model.vo.Weather;

public class WeatherService {
	WeatherDao weatherDao = new WeatherDao();
	/*
	 * Find all weather condition
	 */
	public List<Weather> findAll() {
		return weatherDao.findAll();
	}
	/*
	 * Select weather detail : weatherId, weatherName, temperature, msId
	 */
	public Weather findById(int weatherId) {
		return weatherDao.findById(weatherId);
	}
	/*
	 * Create a new weather information
	 */
	public int createWeather(WeatherDto weatherDto) {
		String weatherName = weatherDto.getWeatherName();
		int result = 0;
		if(weatherDto != null) {
			if((weatherName != null) && validateName(weatherName)) {
				result = weatherDao.createWeather(weatherDto);
			}
		}
		return result;
	}
	/*
	 * Update weather information
	 */
	public int updateWeather(WeatherDto weatherDto) {
		String weatherName = weatherDto.getWeatherName();
		int result = 0;
		if(weatherDto != null) { // weather found
			if((weatherName != null) && validateName(weatherName)) {
				result = weatherDao.updateWeather(weatherDto);
			}
		}
		return result;
	}
	/*
	 * Delete weather information
	 */
	public int deleteWeather(int weatherId) {
		return weatherDao.deleteWeather(weatherId);
	}
	
	private boolean validateName(String weatherName) {
		if(0 < weatherName.length() && weatherName.length() < 20) {
			return true;
		}
		return false;
	}

}
