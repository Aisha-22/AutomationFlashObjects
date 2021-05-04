package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PlacingR3Bet {

    private static WebElement element = null;
    //Select Play Now
    public static WebElement PlayNow(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id='sp-menu']/div/div/ul/li[2]")))
                .build().perform();
        return element;
    }
    //EZiWIN Game from the game Lobby
    public static WebElement ClickEZiWIN(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='sp-menu']/div/div/ul/li[2]/div/div/div/div/div/div/div/div[2]/div/div/ul/li[6]/a[1]/span"));
        return element;
    }
    //Select Aqua Zaka Game
    public static WebElement AquqZakaGame(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='sp-component']/div/section[2]/div/div[3]/div/figure/figcaption/button"));
        return element;
    }
}
