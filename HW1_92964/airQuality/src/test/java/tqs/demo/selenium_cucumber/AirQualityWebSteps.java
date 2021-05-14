package tqs.demo.selenium_cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class AirQualityWebSteps {
    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get(url);
    }

    @And("I click {string}")
    public void iClickCache(String cache) {
        webDriver.findElement(By.xpath("//a[contains(text(),'Cache')]")).click();
        //fnfkrfekfe
        //vnefne
    }

    @Then("I should be shown results including {string}")
    public void iShouldBeShownResultsIncluding(String result) {
        try {
            webDriver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + result + "\" not available in results");
        } finally {
            webDriver.quit();
        }
    }

    @And("I type {string} in search bar")
    public void iType(String city) {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement input = webDriver.findElement(By.id("city"));
        input.sendKeys(city);

    }

}
