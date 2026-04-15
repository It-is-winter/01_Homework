package com.hw.weather.mvc.run;

import com.hw.weather.mvc.v4.common.JdbcTemplate;
//import com.hw.weather.mvc.v1.view.WeatherView;
//import com.hw.weather.mvc.v2.view.WeatherView;
//import com.hw.weather.mvc.v3.view.WeatherView;
import com.hw.weather.mvc.v4.view.WeatherView;

public class WeatherRun {

	public static void main(String[] args) {
		JdbcTemplate.registDriver();
		new WeatherView().mainMenu();

	}

}
