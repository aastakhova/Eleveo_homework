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
        formPage.setMonth(Helper.getCurrentMonthName());
        Thread.sleep(1000);
        formPage.clickNextButton();
        Thread.sleep(1000);
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
        Thread.sleep(1000);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
