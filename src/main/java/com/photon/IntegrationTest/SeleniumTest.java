package com.photon.IntegrationTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chrome//chromedriver.exe");
		WebDriver chromeDriver = new ChromeDriver();

		chromeDriver.get("https://www.google.com");

	}

}
