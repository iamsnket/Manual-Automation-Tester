package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By signInBtn = By.xpath("//button[@type='submit']");
    private final By message = By.xpath("//*[@data-rht-toaster]//*[@role='status' or @aria-live or self::div or self::p or self::span][normalize-space()!='']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
    }

    public String getMessage() {
        WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(message));
        String text = toast.getText().trim();
        if (text.isEmpty()) {
            String tc = toast.getAttribute("textContent");
            text = (tc == null) ? "" : tc.trim();
        }
        return text;
    }
}