package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LogIn {

    private static WebElement element = null;

    //Method for enter Login user name
    public static WebElement LoginField(WebDriver driver) {
        element = driver.findElement(By.id("userName_email"));
        return element;
    }

    //Entering User Pin Number
    public static WebElement PinField(WebDriver driver) {
        element = driver.findElement(By.id("password"));
        return element;
    }

    //Click Login button
    public static WebElement ClickLogin(WebDriver driver) {
        element = driver.findElement(By.className("btnDefault"));
        return element;
    }
}
