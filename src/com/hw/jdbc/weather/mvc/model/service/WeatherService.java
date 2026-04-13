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
/*	public int createWeather(WeatherDto weatherDto) {
		String weatherName = weatherDto.getWeatherName();
		if(weatherDto != null) {
			if(validateName(weatherName)) {
				weathers.add(new Weather(weatherDto.getWeatherId(), weatherName, weatherDto.getTemperature(), weatherDto.getMsId()));
				return 1;
			}
		}
		return 0;
	}*/
	/*
	 * Update weather information
	 */
/*	public int updateWeather(int weatherId, WeatherDto weatherDto) {
		int index = indexOf(weatherId);
		String weatherName = weatherDto.getWeatherName();
		if(index != -1) { // weather found
			if(validateName(weatherName)) {
				weathers.set(index, new Weather(weatherId, weatherName, weatherDto.getTemperature(), weatherDto.getMsId()));
				return 1;
			}
		}
		return 0;
	}*/
	/*
	 * Delete weather information
	 */
/*	public int deleteWeather(int weatherId) {
		int index = indexOf(weatherId);
		if(index != -1) { // weather found
			weathers.remove(index);
			return 1;
		}
		return 0;
	}
	
	private boolean validateName(String weatherName) {
		if(0 < weatherName.length() && weatherName.length() < 20) {
			return true;
		}
		return false;
	}
*/
}
