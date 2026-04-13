package com.hw.jdbc.weather.mvc.model.dto;

public class WeatherDto {
	private int weatherId;
	private String weatherName;
	private String temperature;
	private long msId;
	public WeatherDto() {}
	public WeatherDto(String weatherName, String temperature, long msId) {
		this.weatherName = weatherName;
		this.temperature = temperature;
		this.msId = msId;
	}
	public WeatherDto(int weatherId, String weatherName, String temperature, long msId) {
		this.weatherId = weatherId;
		this.weatherName = weatherName;
		this.temperature = temperature;
		this.msId = msId;
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
	public long getMsId() {
		return msId;
	}
	public void setMsId(long msId) {
		this.msId = msId;
	}

}
