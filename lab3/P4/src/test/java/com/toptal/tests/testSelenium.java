package com.toptal.tests;// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.util.concurrent.TimeUnit;

@ExtendWith(SeleniumJupiter.class)
public class testSelenium {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Test
    public void untitled() {
        driver.get("https://www.toptal.com/talent/apply");
        driver.manage().window().setSize(new Dimension(1920, 1031));
        driver.findElement(By.linkText("Apply as a Freelancer")).click();
        driver.findElement(By.cssSelector(".has-default_value")).click();
        driver.findElement(By.cssSelector(".is-active > .custom_select-options_item_title")).click();
        driver.findElement(By.id("talent_create_applicant_full_name")).click();
        driver.findElement(By.id("talent_create_applicant_full_name")).click();
        driver.findElement(By.id("talent_create_applicant_email")).click();
        driver.findElement(By.id("talent_create_applicant_password")).click();
        driver.findElement(By.cssSelector(".layout_layer")).click();
        driver.findElement(By.id("talent_create_applicant_password_confirmation")).click();
        driver.findElement(By.cssSelector(".layout_layer")).click();
        driver.findElement(By.id("save_new_talent_create_applicant")).click();
        {
            WebElement element = driver.findElement(By.linkText("Sign in with LinkedIn"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
    }
}
