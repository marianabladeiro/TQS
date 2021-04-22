import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SearchSteps {
    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get(url);
    }

    @And("I type {string}")
    public void iType(String searchQuery) {
        webDriver.findElement(By.name("q")).sendKeys(searchQuery);
    }

    @And("I press Enter")
    public void iPressEnter() {
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
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

    @And("I select {string} as the departure city")
    public void iSelectDepartureCity(String city) {
        Select c = new Select(webDriver.findElement(By.name("fromPort")));
        c.selectByVisibleText(city);
    }

    @And("I select {string} as the destination city")
    public void iSelectDestinationCity(String city) {
        Select c = new Select(webDriver.findElement(By.name("toPort")));
        c.selectByVisibleText(city);
    }

    @And("I click {string}")
    public void iPressFindFlights(String findFlight) {
        webDriver.findElement(By.xpath("//input[@value='"+findFlight+"']")).click();
    }

    @And("I click {string} on row {int}")
    public void iPressChooseFlight(String chooseFlight, int row) {
        webDriver.findElement(By.xpath("//tr["+row+"]/td/input")).click();

    }

    @And("I type {string} in the {string} field")
    public void fillPurchase(String s, String fieldName) {
        webDriver.findElement(By.name(fieldName)).sendKeys(s);
    }
}
