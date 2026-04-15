package com.hw.weather.mvc.v4.controller;

import java.util.List;

import com.hw.weather.mvc.v4.model.dto.WeatherDto;
import com.hw.weather.mvc.v4.model.dto.WeatherMsDto;
import com.hw.weather.mvc.v4.model.service.WeatherService;

public class WeatherController {
	WeatherService weatherService = new WeatherService();
	
	public List<WeatherMsDto> findAll() {
		return weatherService.findAll();
	}
	
	public WeatherMsDto findById(int weatherId) {
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
