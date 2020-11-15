package com.test.astakhova;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.util.List;

public class HomeworkFormPages {
    public WebDriver driver;
    public HomeworkFormPages(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    @FindBy(css="div[data-params*='Check that'] div[aria-label='Check this']")
    private List<WebElement> checkThisCbs;
    public void clickCheckThisCbs() {
        for (WebElement i : checkThisCbs){
            i.click();
        }
    }

    @FindBy(css="div[data-params*='Select date'] input")
    private WebElement dateField;
    public void setDate(String date){
        dateField.sendKeys(date);
    }

    @FindBy(css="div[class*=appsMaterialWizButtonEl")
    private List<WebElement> nextBackBtn;
    public void clickNextButton() {
        if (nextBackBtn.size() == 1) {
            nextBackBtn.get(0).click();
        }
        else {
            nextBackBtn.get(1).click();
        }
        waitForLoad();
    }

    public void clickBackButton(){
        nextBackBtn.get(0).click();
    }

    public void clickSubmitButton(){
        nextBackBtn.get(1).click();
    }

    @FindBy(css="div[data-params*='current month'] div.freebirdFormviewerComponentsQuestionBaseValidationError")
    private WebElement warningMsg;
    public boolean warningIsDisplayed(){
        waitForLoad();
        try {
            return warningMsg.isDisplayed();
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    @FindBy(css="div[data-params*='current month'] input")
    private WebElement currentMonthFld;
    public void setMonth(String month){
        currentMonthFld.clear();
        currentMonthFld.sendKeys(month);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector("div.freebirdFormviewerComponentsQuestionBaseErrorIcon")));
    }

    public String getSetMonth(){
        return currentMonthFld.getAttribute("data-initial-value");
    }

    @FindBy(css="div[data-params*='favorite movie'] textarea")
    private WebElement moviesListFld;
    public void setFavoriteMovies(String movieList){
        moviesListFld.sendKeys(movieList);
    }

    public boolean checkIfMoviesSet(){
        return !moviesListFld.getAttribute("data-initial-value").equals("");
    }

    @FindBy(css="div[data-params*='favorite color']")
    private WebElement colorList;
    public void setColor(String color){
        try {
            colorList.findElement(By.cssSelector(String.format("[data-value='%s']", color))).click();
        }
        catch (NoSuchElementException e){
            Assert.fail("Unknown color.");
        }
    }

    public boolean checkIfColorSet(){
        try{
            colorList.findElement(By.cssSelector("label[class*=isChecked]"));
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    @FindBy(css="div[data-params*='done']")
    private WebElement answerList;
    public void setDone (String answer) {
        try {
            answerList.findElement(By.cssSelector(String.format("[data-value='%s']", answer))).click();
        }
        catch (NoSuchElementException e){
            Assert.fail("Unknown answer.");
        }
    }
}


