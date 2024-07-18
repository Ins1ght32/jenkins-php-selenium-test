import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class AppSecondaryTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Initialize HtmlUnitDriver with JavaScript enabled
        driver = new HtmlUnitDriver(true);
        wait = new WebDriverWait(driver, 10); // 10 seconds wait time

        // Navigate to the login page
        driver.get("http://192.168.1.188/"); // Replace with your login page URL

        // Optional: Print page source for debugging
        System.out.println(driver.getPageSource());
    }

    @Test
    public void testSuccessfulLogin() {
        // Wait for the email field to be present
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));

        // Enter the valid credentials
        emailField.sendKeys("user@example.com");
        passwordField.sendKeys("password1234");

        // Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();

        // Assert the user is redirected to the dashboard
        assertTrue(wait.until(ExpectedConditions.urlContains("dashboard.php")));
    }

    @Test
    public void testUnsuccessfulLoginWrongEmail() {
        // Wait for the email field to be present
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));

        // Enter invalid email and valid password
        emailField.sendKeys("wrong@example.com");
        passwordField.sendKeys("password1234");

        // Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();

        // Assert the error message is displayed
        WebElement errorMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("error-msg")));
        assertTrue(errorMsg.getText().contains("Login failed"));
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        // Wait for the email field to be present
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));

        // Enter valid email and invalid password
        emailField.sendKeys("user@example.com");
        passwordField.sendKeys("wrongpassword");

        // Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();

        // Assert the error message is displayed
        WebElement errorMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("error-msg")));
        assertTrue(errorMsg.getText().contains("Login failed"));
    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
