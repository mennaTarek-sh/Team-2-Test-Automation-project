import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginModule {
    public static void main(String[] args) throws InterruptedException {

        String[][] users = {
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

         // 1) Test all users with the correct password
        for (int i = 0; i < users.length; i++) {
            String username = users[i][0];
            String password = users[i][1];

            driver.get("https://www.saucedemo.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

            System.out.println("\nTesting user " + (i + 1) + ": " + username + " (correct password)");
            driver.findElement(By.id("user-name")).clear();
            driver.findElement(By.id("user-name")).sendKeys(username);
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();
            Thread.sleep(1200);
            // Check if login redirected to inventory page

            if (driver.getCurrentUrl().contains("/inventory.html")) {
                System.out.println(" Login successful for: " + username);
                // perform logout for successful login

                wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
                Thread.sleep(700);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
                Thread.sleep(700);
            } else {
                String msg = "";
                try {
                    msg = driver.findElement(By.cssSelector(".error-message-container")).getText();
                } catch (Exception e) {
                    msg = "Unknown error";
                }
                System.out.println(" Login failed for " + username + ": " + msg);
            }

            System.out.println("-----------------------------------");
        }

        // 2) Single test for wrong password using standard_user

        System.out.println("\nSingle WRONG-password test using standard_user:");
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1200);

        String wrongMsg = "";
        try {
            // read the error message shown for wrong credentials
            wrongMsg = driver.findElement(By.cssSelector(".error-message-container")).getText();
        } catch (Exception e) {
            wrongMsg = "Unknown error";
        }
        System.out.println(" Wrong-password result for standard_user: " + wrongMsg);

        // finish
        driver.quit();
        System.out.println("\nTesting finished for all users!");
    }
}

