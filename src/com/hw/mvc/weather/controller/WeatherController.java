package com.hw.mvc.weather.controller;

import java.util.List;

import com.hw.mvc.weather.model.dto.WeatherDto;
import com.hw.mvc.weather.model.service.WeatherService;
import com.hw.mvc.weather.model.vo.Weather;

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
	
	public int updateWeather(int weatherId, WeatherDto weatherDto) {
		return weatherService.updateWeather(weatherId, weatherDto);
	}
	
	public int deleteWeather(int weatherId) {
		return weatherService.deleteWeather(weatherId);
	}

}
