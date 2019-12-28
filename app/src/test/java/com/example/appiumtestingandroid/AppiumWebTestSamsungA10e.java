package com.example.appiumtestingandroid;

import com.google.common.collect.ImmutableBiMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumWebTestSamsungA10e {

    private static AndroidDriver driver;

    @BeforeSuite
    public static void setUp() throws MalformedURLException {
        // Set the app
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.UDID, "RF8MB1CV9QH");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung A10e");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability("appium:chromeOptions", ImmutableBiMap.of("w3c", false)); //this option disable w3c mode for chromedriver
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterMethod
    public void restartApp(){
        // Restart the app after each test case
        driver.resetApp();
    }

    @Test(enabled = false, priority = 0)
    public void loginOnMainPage() {
        driver.get("https://m.facebook.com");
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"), "URL doesn't match");
        driver.findElement(By.id("m_login_email")).sendKeys("mylogin");
        driver.findElement(By.id("m_login_password")).sendKeys("mypassword");
        driver.findElement(By.name("login")).click();
        }

    @Test(enabled = true, priority = 1)
    public void createNewAccount() {
        driver.get("https://m.facebook.com");
        driver.findElement(By.id("signup-button")).click();
        driver.findElement(By.id("firstname_input")).sendKeys("testFirstName");
        driver.findElement(By.id("lastname_input")).sendKeys("testLastName");
        driver.findElement(By.id("login_link")).click();
        }

    @Test(enabled = false, priority = 2)
    public void pressOnHelpButton(){
        driver.get("https://m.facebook.com");
        driver.findElement(By.xpath("//*[@id=\"help-link\"]")).click();
    }

    @AfterSuite
    public static void teardown(){
        // Close the app
        driver.quit();
    }

}