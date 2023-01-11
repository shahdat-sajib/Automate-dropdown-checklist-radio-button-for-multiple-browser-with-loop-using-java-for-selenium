package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static WebDriver selectBrowser(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")) { //Driver for Chrome browser
            WebDriverManager.chromedriver().arch64().setup();
            driver= new ChromeDriver();
            System.out.println("I'm in the Chrome browser!");
        } else if (browserName.equalsIgnoreCase("firefox")) { //Driver for Firefox browser
            WebDriverManager.firefoxdriver().arch64().setup();
            driver= new FirefoxDriver();
            System.out.println("I'm in the Firefox browser!");
        } else if (browserName.equalsIgnoreCase("edge")) { //Driver for Edge browser
            WebDriverManager.edgedriver().arch64().setup();
            driver = new EdgeDriver();
            System.out.println("I'm in the Edge browser!");
        } else {
            System.out.println("Invalid Browser");
        }
        return driver;
    }

    //Method for scrolling the page
    public static void scrollPage(WebDriver driver, String path){
        WebElement element = driver.findElement(By.xpath(path));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

    //method for radio button selection
    public static void selectRadioButton(WebDriver driver){
        scrollPage(driver, "//span[contains(text(),'Radio buttons')]");

        WebElement radioElement = driver.findElement(By.xpath("//input[@value='female']"));
        boolean selectState = radioElement.isSelected();

        if (!selectState) {radioElement.click();}
    }

    //method for checkbox selection
    public static void selectCheckBox(WebDriver driver){
        scrollPage(driver, "//span[contains(text(),'Checkboxes')]");

        WebElement checkboxElement = driver.findElement(By.xpath("//input[@value='Car']"));
        boolean selectState = checkboxElement.isSelected();
        if (!selectState) {checkboxElement.click();}
        else {checkboxElement.click();}
        checkboxElement = driver.findElement(By.xpath("//input[@value='Bike']"));
        if (selectState) {checkboxElement.click();}
    }

    //method for dropdown selection
    public static void selectDropdown(WebDriver driver){
        scrollPage(driver, "//span[contains(text(),'Dropdown')]");
        WebElement dropdown = driver.findElement(By.xpath("//select"));
        dropdown.click();
        Select selectDropdown = new Select(driver.findElement(By.xpath("//select")));
        selectDropdown.selectByIndex(3);
        dropdown.click();
    }

    //main method
    public static void main(String[] args) {
        String[] browsers = {"chrome"};
        for (String browser : browsers) {
            WebDriver driver = selectBrowser(browser);
            if(driver == null){
                System.out.println("Task Ended");
            }else {
                for (int i = 0; i < 3; i++) {
                    driver.get("https://ultimateqa.com/simple-html-elements-for-automation");
                    selectRadioButton(driver);
                    selectCheckBox(driver);
                    selectDropdown(driver);
                    try {
                        Thread.sleep(1500);
                        System.out.println("Waited for 1.5 seconds.");
                    } catch (InterruptedException e) {System.out.println("Interrupted while sleeping.");}
                }
                driver.quit();
            }
        }
    }
}