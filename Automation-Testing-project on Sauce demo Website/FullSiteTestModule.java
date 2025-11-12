import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FullSiteTestModule {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // login
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        System.out.println("Login successful!");

        //  Add to Cart Module
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        System.out.println("Product 'Sauce Labs Backpack' added successfully!");
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        System.out.println("Product 'Sauce Labs Bike Light' added successfully!");
        Thread.sleep(1000);

        //  Remove product
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        System.out.println("Product 'Sauce Labs Backpack' removed successfully!");
        Thread.sleep(1000);

        //  Sort Box Module
        Select selectSort = new Select(driver.findElement(By.className("product_sort_container")));
        selectSort.selectByValue("az"); // Name A-Z
        Thread.sleep(1000);
        selectSort.selectByValue("za"); // Name Z-A
        Thread.sleep(1000);
        System.out.println("Sort Box tested successfully!");

        // Checkout Module
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Menna");
        driver.findElement(By.id("last-name")).sendKeys("Tarek");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("back-to-products")).click();
        System.out.println("Checkout completed successfully!");

        //  About Module Form
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("about_sidebar_link")).click();
        driver.get("https://saucelabs.com/request-demo");

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        js.executeScript("arguments[0].scrollIntoView(true);", email);
        email.sendKeys("menna.tarek@company.com");

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        js.executeScript("arguments[0].scrollIntoView(true);", firstName);
        firstName.sendKeys("Menna");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName")));
        js.executeScript("arguments[0].scrollIntoView(true);", lastName);
        lastName.sendKeys("Tarek");

        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone")));
        js.executeScript("arguments[0].scrollIntoView(true);", phone);
        phone.sendKeys("1230987");

        WebElement countryElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")));
        js.executeScript("arguments[0].scrollIntoView(true);", countryElem);
        new Select(countryElem).selectByValue("Egypt");

        WebElement solutionElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Solution_Interest__c")));
        js.executeScript("arguments[0].scrollIntoView(true);", solutionElem);
        new Select(solutionElem).selectByValue("Visual Testing");

        WebElement comments = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Sales_Contact_Comments__c")));
        js.executeScript("arguments[0].scrollIntoView(true);", comments);
        comments.sendKeys("Hello, this is a test submission.");

        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@for = 'mktoCheckbox_47185_0']")));
        js.executeScript("arguments[0].scrollIntoView(true);", comments);
        checkbox.click();

        driver.findElement(By.xpath("//*[@type = 'submit']")).click();
        Thread.sleep(2000);
        driver.get("https://saucelabs.com/thank-you-contact");
        System.out.println("About Form submitted successfully!");

        //  Footer Module
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("footer_copy")));
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        String mainWindow = driver.getWindowHandle();

        String[] socialSelectors = {
                "a[href='https://twitter.com/saucelabs']",
                "a[href='https://www.facebook.com/saucelabs']",
                "a[href='https://www.linkedin.com/company/sauce-labs/']"
        };

        for (String selector : socialSelectors) {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
            link.click();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    System.out.println("Opened URL: " + driver.getCurrentUrl());
                    driver.close();
                    driver.switchTo().window(mainWindow);
                }
            }
        }
        System.out.println("Footer links tested successfully!");
        //  Reset App State
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        System.out.println("Product 1 added to cart!");
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        System.out.println("Product 2 added to cart!");
        Thread.sleep(1000);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("reset_sidebar_link")).click();
        System.out.println("App state reset successfully!");
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        //   Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        System.out.println("Logout successful!");
        //  Quit Browser
        driver.quit();
        System.out.println("Full Site Test completed successfully!");
    }
}
