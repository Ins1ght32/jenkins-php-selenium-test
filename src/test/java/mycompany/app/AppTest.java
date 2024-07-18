import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class AppSecondaryTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Initialize HtmlUnitDriver
        driver = new HtmlUnitDriver(true); // 'true' enables JavaScript

        // Navigate to the login page
        driver.get("http://192.168.1.188/login.php"); // Replace with your login page URL
    }

    @Test
    public void testSuccessfulLogin() {
        // Locate the email and password fields
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        
        // Enter the valid credentials
        emailField.sendKeys("user@example.com");
        passwordField.sendKeys("password1234");
        
        // Submit the form
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
        
        // Assert the user is redirected to the dashboard
        assertTrue(driver.getCurrentUrl().contains("dashboard.php"));
    }

    @Test
    public void testUnsuccessfulLoginWrongEmail() {
        // Locate the email and password fields
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        
        // Enter invalid email and valid password
        emailField.sendKeys("wrong@example.com");
        passwordField.sendKeys("password1234");
        
        // Submit the form
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
        
        // Assert the error message is displayed
        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assertTrue(errorMsg.getText().contains("Login failed"));
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        // Locate the email and password fields
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        
        // Enter valid email and invalid password
        emailField.sendKeys("user@example.com");
        passwordField.sendKeys("wrongpassword");
        
        // Submit the form
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
        
        // Assert the error message is displayed
        WebElement errorMsg = driver.findElement(By.className("error-msg"));
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
