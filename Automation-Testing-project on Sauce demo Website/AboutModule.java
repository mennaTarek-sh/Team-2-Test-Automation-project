import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutModule {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("about_sidebar_link"))).click();

        driver.get("https://saucelabs.com/request-demo");

        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("Email"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", email);
        email.sendKeys("menna.tarek@company.com");

        WebElement firstName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", firstName);
        firstName.sendKeys("Menna");

        WebElement lastName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", lastName);
        lastName.sendKeys("Tarek");

        WebElement phone = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("Phone"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", phone);
        phone.sendKeys("1230987");

        WebElement countryElem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("Country"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", countryElem);
        Select countryDropdown = new Select(countryElem);
        countryDropdown.selectByValue("Egypt");

        //  Solution Interest
        WebElement solutionElem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("Solution_Interest__c"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", solutionElem);
        Select solutionDropdown = new Select(solutionElem);
        solutionDropdown.selectByValue("Visual Testing");

        WebElement comments = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("Sales_Contact_Comments__c"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", comments);
        comments.sendKeys("Hello, this is a test submission.");

        //  checkbox
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@for = 'mktoCheckbox_47185_0']"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", comments);
        checkbox.click();
        // "Let's Talk"
        driver.findElement(By.xpath("//*[@type = 'submit']")).click();

        // (ThankYou Page)
        driver.get("https://saucelabs.com/thank-you-contact");

        System.out.println("Form submitted successfully!");
        Thread.sleep(2000);
        driver.quit();
    }
}
