package com.test.astakhova;
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

    @FindBy(css="input[type='date']")
    private WebElement dateField;

    @FindBy(css="span.appsMaterialWizButtonPaperbuttonLabel")
    private List<WebElement> nextBackBtn;

    @FindBy(css="")
    private WebElement submitBtn;

    @FindBy(css="div.freebirdFormviewerComponentsQuestionBaseErrorIcon")
    private WebElement warningMsg;

    @FindBy(css="div[data-params*='current month'] input")
    private WebElement currentMonthFld;

    @FindBy(css="div[data-params*='favorite movie'] textarea")
    private WebElement moviesListFld;

    public void clickCheckThisCbs() {
        for (int i=0; i<checkThisCbs.size(); i++){
            checkThisCbs.get(i).click();
        }
    }

    public void setDate(String date){
        dateField.sendKeys(date);
    }

    public void clickNextButton() {
        nextBackBtn.get(0).click();
    }

    public boolean warningIsDisplayed(){
        try {
            return warningMsg.isDisplayed();
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
    public void setCurrentMonth(String month){
        currentMonthFld.sendKeys(month);
    }

    public void setFavoriteMovies(String movieList){
        //System.out.println(moviesListFld.getAttribute("class"));
        moviesListFld.sendKeys(movieList);
    }
}


