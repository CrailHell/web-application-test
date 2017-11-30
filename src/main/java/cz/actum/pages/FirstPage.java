package cz.actum.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FirstPage extends BasePage {
    @FindBy(xpath = "//div[@data-value='Check this']")
    private List<WebElement> checkThisCheckBoxes;

    @FindBy(xpath = "//input[@jsname='YPqjbf' and @max='31']")
    private WebElement dayInputField;

    @FindBy(xpath = "//input[@jsname='YPqjbf' and @max='12']")
    private WebElement monthInputField;

    @FindBy(xpath = "//input[contains(@aria-label, 'Check that this question')]")
    private WebElement currentMonthInputField;

    @FindBy(id = "i.err.1133270322")
    private WebElement currentMonthErrorMessage;

    @FindBy(xpath = "//div[@role='button' and @jsname='OCpkoe']")
    private WebElement nextButton;

    public FirstPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FirstPage checkAllCheckThisCheckBoxes() {
        checkThisCheckBoxes.forEach(WebElement::click);
        return this;
    }

    public FirstPage setDate(int day, int month) {
        dayInputField.sendKeys(String.valueOf(day));
        monthInputField.sendKeys(String.valueOf(month));
        return this;
    }

    public FirstPage setCurrentMonth(String currentMonth) {
        currentMonthInputField.clear();
        currentMonthInputField.sendKeys(currentMonth);
        currentMonthInputField.submit();
        return this;
    }

    public SecondPage clickOnNextButton() {
        nextButton.click();
        return new SecondPage(driver);
    }

    public boolean isCurrentMonthErrorMessageDisplayed() {
        return currentMonthErrorMessage.isDisplayed();
    }
}
