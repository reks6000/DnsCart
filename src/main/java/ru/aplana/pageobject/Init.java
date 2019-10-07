package ru.aplana.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Init {
    private static TestProperties props = TestProperties.getInstance();
    private static WebDriver driver;
    private static Wait<WebDriver> wait;
    private static Framework fw;

    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", props.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 2, 500);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty("timeout.global")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty("timeout.pageLoad")), TimeUnit.SECONDS);
        driver.get(props.getProperty("url"));
        fw = new Framework(driver, wait);
    }

    public static Framework getFramework() {
        return fw;
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void tearDown() {
//        sleep(1000);
        driver.quit();
    }





}
