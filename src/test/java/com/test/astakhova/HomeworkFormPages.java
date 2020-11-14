package com.test.astakhova;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomeworkFormPages {
    public WebDriver driver;
    public HomeworkFormPages(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css="div[aria-label='Check this'] div[class$='exportInnerBox']")
    private List<WebElement> checkThisCbs;
    public void clickCheckThisCbs() {
        for (WebElement i : checkThisCbs){
            i.click();
        }
    }

    @FindBy(css="input[type='date']")
    private WebElement dateField;
    public void setDate(String date){
        dateField.sendKeys(date);
    }

    @FindBy(css="span.appsMaterialWizButtonPaperbuttonLabel")
    private List<WebElement> nextBackBtn;
    public void clickNextButton() {
        if (nextBackBtn.size() == 1) {
            nextBackBtn.get(0).click();
        }
        else {
            nextBackBtn.get(1).click();
        }
    }

    public void clickBackButton(){
        nextBackBtn.get(0).click();
    }

    public void clickSubmitButton(){
        nextBackBtn.get(1).click();
    }

    @FindBy(css="div.freebirdFormviewerComponentsQuestionBaseErrorIcon")
    private WebElement warningMsg;
    public boolean warningIsDisplayed(){
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


