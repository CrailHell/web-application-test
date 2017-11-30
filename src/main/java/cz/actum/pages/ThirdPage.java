package cz.actum.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThirdPage extends BasePage {
    @FindBy(xpath = "//div[@aria-label='Yes']")
    private WebElement yesRadioButton;

    @FindBy(xpath = "//div[@aria-label='No']")
    private WebElement noRadioButton;

    @FindBy(xpath = "//div[@role='button' and @jsname='GeGHKb']")
    private WebElement backButton;

    @FindBy(xpath = "//div[@role='button' and @jsname='M2UYVd']")
    private WebElement submitButton;


    public ThirdPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ThirdPage clickOnYesRadioButton() {
        yesRadioButton.click();
        return this;
    }

    public ThirdPage clickOnNoRadioButton() {
        noRadioButton.click();
        return this;
    }

    public SecondPage clickOnBackButton() {
        backButton.click();
        return new SecondPage(driver);
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }
}
