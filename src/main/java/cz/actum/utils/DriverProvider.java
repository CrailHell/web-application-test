package cz.actum.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverProvider {
    public WebDriver prepare() {
        System.setProperty("webdriver.gecko.driver", getClass().getClassLoader().getResource("driver/geckodriver.exe").getPath());
        return new FirefoxDriver();
    }
}
