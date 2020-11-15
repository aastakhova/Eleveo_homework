package com.test.astakhova;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class HomeworkFormCheck {
    public static WebDriver driver;
    public static HomeworkFormPages formPage;
    @BeforeClass
    public static void setup() {
        String browser = System.getProperty("browser");
        System.out.println(browser);
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("firefoxdriver"));
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", ConfProperties.getProperty("iedriver"));
                driver = new InternetExplorerDriver();
                break;
            default:
                Assert.fail("Unknown browser.");
        }
        driver.get(ConfProperties.getProperty("login_page"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void homeworkFormCheck() {
        formPage = new HomeworkFormPages(driver);
        // 1) - Fill first and second question
        formPage.clickCheckThisCbs();
        formPage.setDate(Helper.getFormattedTodayDateWithOffset(6));
        // 2) Validate that third question is mandatory
        formPage.clickNextButton();
        Assert.assertTrue("Third question is not mandatory.", formPage.warningIsDisplayed());
        // 3) Fill third question and go to another step
        formPage.setMonth(Helper.getCurrentMonthName());
        formPage.clickNextButton();
        // 4) - Fill next questions
        formPage.setFavoriteMovies(Helper.getFavoriteMovies());
        formPage.setColor("Green");
        // 5) Go back to first step
        formPage.clickBackButton();
        // 6) Reverse text in third question
        formPage.setMonth(Helper.reverseString(formPage.getSetMonth()));
        // 7) Go to second step
        formPage.clickNextButton();
        // 8) Check that both questions are still filed
        Assert.assertTrue("First question is not filled.", formPage.checkIfMoviesSet());
        Assert.assertTrue("Second question is not filled.", formPage.checkIfColorSet());
        // 9) Go to last step
        formPage.clickNextButton();
        // 10) Fill last question and send form
        formPage.setDone("Yes");
        formPage.clickSubmitButton();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
