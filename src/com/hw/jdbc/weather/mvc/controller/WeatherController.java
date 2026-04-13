package com.hw.jdbc.weather.mvc.controller;

import java.util.List;

import com.hw.jdbc.weather.mvc.model.dto.WeatherDto;
import com.hw.jdbc.weather.mvc.model.service.WeatherService;
import com.hw.jdbc.weather.mvc.model.vo.Weather;

public class WeatherController {
	WeatherService weatherService = new WeatherService();
	
	public List<Weather> findAll() {
		return weatherService.findAll();
	}
	
	public Weather findById(int weatherId) {
		return weatherService.findById(weatherId);
	}
	
	public int createWeather(WeatherDto weatherDto) {
		return weatherService.createWeather(weatherDto);
	}
	
	public int updateWeather(WeatherDto weatherDto) {
		return weatherService.updateWeather(weatherDto);
	}
	
	public int deleteWeather(int weatherId) {
		return weatherService.deleteWeather(weatherId);
	}

}
