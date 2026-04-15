package com.hw.weather.mvc.v3.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.hw.weather.mvc.v3.controller.WeatherController;
import com.hw.weather.mvc.v3.exception.WeatherConditionLengthOutOfBoundsException;
import com.hw.weather.mvc.v3.model.dto.WeatherDto;
import com.hw.weather.mvc.v3.model.vo.Weather;

/**
 * 기상청에서, 위성으로부터 제공받은 날씨정보를 추가, 수정, 삭제하고
 * 사용자들이 원하는 날씨를 보여줌
 */
public class WeatherView {
	Scanner sc = new Scanner(System.in);
	WeatherController weatherController = new WeatherController();
	int result;
	public void mainMenu() {
		while(true) {
			System.out.println();
			System.out.println("=============================================================");
			System.out.println("\t\t\t날씨 정보 프로그램");
			System.out.println("=============================================================");
			System.out.println("1. 날씨 정보 보여주기");
			System.out.println("2. 날씨 정보 추가하기");
			System.out.println("3. 날씨 정보 수정하기");
			System.out.println("4. 날씨 정보 삭제하기");
			System.out.println("0. 프로그램 종료하기");
			System.out.println();
			System.out.print("원하는 메뉴를 선택해주세요 >");
			int menu = 0;
			try {
				menu = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("메뉴번호로 입력해주세요.");
				sc.nextLine();
				continue;
			}
			
			switch(menu) {
			case 1 : userMenu(); break;
			case 2 : createWeather(); break;
			case 3 : updateWeather(); break;
			case 4 : deleteWeather(); break;
			case 0 : sc.close(); return;
			default : System.out.println("없는 메뉴입니다. 다시 입력해주세요"); break;
			}
		}
	}
	
	private void userMenu() {
		while(true) {
			System.out.println();
			System.out.println("=============================================================");
			System.out.println("\t\t\t날씨 정보 보기");
			System.out.println("=============================================================");
			System.out.println("1. 전체 날씨 보기");
			System.out.println("2. 날씨 정보 자세히 보기");
			System.out.println("3. 돌아가기");
			System.out.println();
			System.out.print("어떤 메뉴를 확인하시겠습니까? ");
			int menu = 0;
			try {
				menu = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("메뉴번호로 입력해주세요.");
				sc.nextLine();
				continue;
			}
			
			switch(menu) {
			case 1 : findAll(); break;
			case 2 : findById(); break;
			case 3 : return;
			default : System.out.println("없는 메뉴입니다. 다시 입력해주세요."); break;
			}
		}
	}
	
	private void findAll() {
		List<Weather> weathers = weatherController.findAll();
		System.out.println("-------------------------------------------------------");
		if(!weathers.isEmpty()) {
			for(Weather weather : weathers) {
				StringBuilder sb = new StringBuilder();
				sb.append("날씨 번호 : ");
				sb.append(weather.weatherId());
				sb.append(",\t날씨 종류 : ");
				sb.append(weather.weatherName());
				sb.toString();
				System.out.println(sb);
			}
		} else {
			System.out.println("조회할 수 있는 날씨 정보가 없습니다. 날씨 정보를 추가해주세요.");
		}
		System.out.println("-------------------------------------------------------");
	}
	
	private void findById() {
		findAll();
		
		Weather weather = null;
		while(true) {
			System.out.print("자세한 정보를 확인할 날씨의 번호를 입력해주세요. >");
			String weatherId = sc.nextLine();
			int id = 0;
			try {
				id = Integer.parseInt(weatherId);
			} catch(NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			
			weather = weatherController.findById(id);
			if(weather != null) {
				break;
			} else {
				System.out.println("입력하신 번호의 날씨 정보가 없습니다. 다시 입력해주세요.");
				System.out.println();
			}
		}
		
		System.out.println("-------------------------------------------------------");
		StringBuilder sb = new StringBuilder();
		sb.append("날씨 번호 : ");
		sb.append(weather.weatherId());
		sb.append(",\t날씨 종류 : ");
		sb.append(weather.weatherName());
		sb.append(",\t기온 : ");
		sb.append(weather.temperature());
		sb.append(",\t정보를 제공받은 위성번호 : ");
		sb.append(weather.msId());
		sb.toString();
		System.out.println(sb);
		System.out.println("-------------------------------------------------------");
	}
	
	private void createWeather() {
		String weatherName = "";
		while(true) {
			System.out.print("추가하실 날씨의 종류를 입력해주세요. >");
			weatherName = sc.nextLine();
			System.out.print("추가하실 날씨의 기온을 입력해주세요. >");
			String temp = sc.nextLine();
			int mId = 0;	
			while(true) {
				System.out.print("추가하실 날씨의 정보를 제공받은 위성의 번호를 입력해주세요. >");
				String msId = sc.nextLine();
				try {
					mId = Integer.parseInt(msId);
					break;
				} catch(NumberFormatException e) {
					System.out.println("숫자로 입력해주세요.");
					System.out.println();
				}
			}
			
			WeatherDto weather = new WeatherDto(weatherName, temp, mId);
			try {
				result = weatherController.createWeather(weather);
				break;
			} catch(WeatherConditionLengthOutOfBoundsException e) {
				System.out.println("날씨 종류의 이름이 너무 짧거나 깁니다. 다시 작성해주세요.");
			}
			System.out.println();
		}
		if(result != 0) {
			System.out.println(weatherName + " 날씨가 추가되었습니다.");
		} else {
			System.out.println(weatherName + " 날씨 추가에 실패하였습니다..");			
		}
	}
	
	private void updateWeather() {
		findAll();
		
		int id =0;
		String weatherName = "";
		String temp = "";
		int msId = 0;
		while(true) {
			System.out.print("수정하고 싶으신 날씨의 번호를 입력해주세요. >");
			String weatherId = sc.nextLine();
			try {
				id = Integer.parseInt(weatherId);
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력해주세요.");
				System.out.println();
				continue;
			}
			
			Weather weather = weatherController.findById(id);
			if(weather != null) {
				break;
			} else {
				System.out.println("입력하신 번호의 날씨정보가 없습니다. 다시 입력해주세요.");
				System.out.println();
			}
		}
			System.out.print("날씨의 종류를 입력해주세요. >");
			weatherName = sc.nextLine();
			System.out.print("날씨의 기온을 입력해주세요. >");
			temp = sc.nextLine();
		while(true) {
			System.out.print("날씨 정보를 제공받은 위성의 번호를 입력해주세요. >");
			try {
				msId = sc.nextInt();
				sc.nextLine();
				break;
			} catch(InputMismatchException e) {
				System.out.println("숫자로 입력해주세요.");
				sc.nextLine();
				System.out.println();
			}
		}
		
		WeatherDto weather = new WeatherDto(id, weatherName, temp, msId);
		result = weatherController.updateWeather(weather);
		System.out.println();
		if(result != 0) {
			System.out.println(id + "번 날씨 수정완료되었습니다.");
		} else {
			System.out.println(id + "번 날씨 수정실패하였습니다.");			
		}
	}
	
	private void deleteWeather() {
		findAll();
		
		int id = 0;
		while(true) {
			System.out.print("삭제하고 싶으신 날씨의 번호를 입력해주세요. >");
			String weatherId = sc.nextLine();
			try {
				id = Integer.parseInt(weatherId);
			} catch(NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
				System.out.println();
				continue;
			}
			Weather weather = weatherController.findById(id);
			if(weather != null) {
				break;
			} else {
				System.out.println("삭제를 원하시는 날씨가 존재하지 않습니다. 다시 입력해주세요.");
				System.out.println();
			}
		}
		
		result = weatherController.deleteWeather(id);
		System.out.println();
		if(result != 0) {
			System.out.println(id + "번 날씨 삭제 완료되었습니다.");
		} else {
			System.out.println(id + "번 날씨 삭제 실패하였습니다.");			
		}
	}

}
