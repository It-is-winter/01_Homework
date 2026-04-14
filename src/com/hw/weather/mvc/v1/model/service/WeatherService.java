package com.hw.weather.mvc.v1.model.service;

import java.util.ArrayList;
import java.util.List;

import com.hw.weather.mvc.v1.model.dto.WeatherDto;
import com.hw.weather.mvc.v1.model.vo.Weather;

public class WeatherService {
	List<Weather> weathers = new ArrayList();
	/*
	 * Find all weather condition
	 */
	public List<Weather> findAll() {
		return weathers;
	}
	/*
	 * Select weather detail : weatherId, weatherName, temperature, msId
	 */
	public Weather findById(int weatherId) {
		int index = indexOf(weatherId);
		if(index != -1) { // weather found
			return weathers.get(index);
		}
		return null;
	}
	/*
	 * Create a new weather information
	 */
	public int createWeather(WeatherDto weatherDto) {
		String weatherName = weatherDto.getWeatherName();
		if(weatherDto != null) {
			if(validateName(weatherName)) {
				weathers.add(new Weather(weatherDto.getWeatherId(), weatherName, weatherDto.getTemperature(), weatherDto.getMsId()));
				return 1;
			}
		}
		return 0;
	}
	/*
	 * Update weather information
	 */
	public int updateWeather(int weatherId, WeatherDto weatherDto) {
		int index = indexOf(weatherId);
		String weatherName = weatherDto.getWeatherName();
		if(index != -1) { // weather found
			if(validateName(weatherName)) {
				weathers.set(index, new Weather(weatherId, weatherName, weatherDto.getTemperature(), weatherDto.getMsId()));
				return 1;
			}
		}
		return 0;
	}
	/*
	 * Delete weather information
	 */
	public int deleteWeather(int weatherId) {
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
	
	private int indexOf(int weatherId) {
		int index = -1;
		for(int i = 0; i < weathers.size(); i++) {
			if(weathers.get(i).weatherId() == weatherId) {
				index = i;
			}
		}
		return index;
	}

}
