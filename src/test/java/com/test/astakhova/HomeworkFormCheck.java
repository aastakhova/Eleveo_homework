package com.test.astakhova;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.BeforeClass;

public class HomeworkFormCheck {
    public static WebDriver driver;
    public static HomeworkFormPages formPage;
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/anastasia/IdeaProjects/Eleveo_homework/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLScNx9xK2LM-G3Z3fJXOQapiSK1IAoNXc_67MyS-soTfhDXotA/viewform");
    }

    @Test
    public void homeworkFormCheck() throws InterruptedException {
        formPage = new HomeworkFormPages(driver);
        // 1) - Fill first and second question
        formPage.clickCheckThisCbs();
        formPage.setDate(Helper.getFormattedTodayDateWithOffset(6));
        // 2) Validate that third question is mandatory
        formPage.clickNextButton();
        Assert.assertTrue("Third question is not mandatory.", formPage.warningIsDisplayed());
        // 3) Fill third question and go to another step
        formPage.setCurrentMonth(Helper.getCurrentMonthName());
        Thread.sleep(1000);
        formPage.clickNextButton();
        Thread.sleep(1000);
        // 4) - Fill next questions
        formPage.setFavoriteMovies(Helper.getFavoriteMovies());
        //Helper.getFavoriteMovies();
        Thread.sleep(3000);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
