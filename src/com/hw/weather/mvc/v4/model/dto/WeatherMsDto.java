package com.hw.weather.mvc.v4.model.dto;

public class WeatherMsDto {
	private int weatherId;
	private String weatherName;
	private String temperature;
	private String msName;
	public WeatherMsDto() {
		super();
	}
	public WeatherMsDto(int weatherId, String weatherName) {
		super();
		this.weatherId = weatherId;
		this.weatherName = weatherName;
	}
	public WeatherMsDto(int weatherId, String weatherName, String temperature, String msName) {
		super();
		this.weatherId = weatherId;
		this.weatherName = weatherName;
		this.temperature = temperature;
		this.msName = msName;
	}
	public int getWeatherId() {
		return weatherId;
	}
	public void setWeatherId(int weatherId) {
		this.weatherId = weatherId;
	}
	public String getWeatherName() {
		return weatherName;
	}
	public void setWeatherName(String weatherName) {
		this.weatherName = weatherName;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getMsName() {
		return msName;
	}
	public void setMsName(String msName) {
		this.msName = msName;
	}

}
