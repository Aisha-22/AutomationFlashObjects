package com.company;

import PageObjectModel.LogIn;
import PageObjectModel.PlacingR3Bet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        //To set the path of Chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //To Launch National Lottery
        driver.get("https://www.nationallottery.co.za/");
        //Maximize screen
        driver.manage().window().maximize();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Data Driven Test
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFCell cell;

        // Import excel sheet.
        File file = new File("C:\\Users\\user\\Documents\\AutomationApproachDocuments\\AutomationProjectAssignmentOne\\DataSheetForAquaZaka.xlsx");
        // Load the file.
        FileInputStream fileLoad = new FileInputStream(file);
        // Load the DataSheetForAquaZaka workbook
        workbook = new XSSFWorkbook(fileLoad);
        // Load the sheet in which data is stored.
        sheet= workbook.getSheetAt(0);

        //Close Popup
        driver.findElement(By.className("closeBTN")).click();
        for(int i = 0; i <= sheet.getLastRowNum(); i++){
            /*I have added test data in the cell A2 as "0710191715" and B2 as "pin"
				Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on
				and first column (A) as 0 and second column (B) as 1 and so on*/

            //Import data for Email
            cell = sheet.getRow(i).getCell(0);
            cell.setCellType(CellType.STRING);
            //Login to ONLP, Entering Mobile Number
            LogIn.LoginField(driver).sendKeys(cell.getStringCellValue());

            //Import data for Password
            cell = sheet.getRow(i).getCell(1);
            cell.setCellType(CellType.STRING);
            //Entering Pin Number
            LogIn.PinField(driver).sendKeys(cell.getStringCellValue());

            //hover over homepage
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.xpath("//*[@id=\"sp-menu\"]/div/div/ul/li[1]")))
                    .build().perform();

            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            //Click Login Button
            LogIn.ClickLogin(driver).click();

            Thread.sleep(3000);
            //Select Play Now to display game Lobby
            PlacingR3Bet.PlayNow(driver);

            //EZiWIN Game from the game Lobby
            PlacingR3Bet.ClickEZiWIN(driver).click();

            //Select Aqua Zaka Game
            PlacingR3Bet.AquqZakaGame(driver).click();

            Thread.sleep(3000);
            //Switching to iFrame
            new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("spl_iframe")));
            System.out.println("3");

            System.out.println(driver.getPageSource());
        }
    }
}
