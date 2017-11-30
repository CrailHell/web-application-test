package cz.actum.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SecondPage extends BasePage {
    private WebDriverWait wait;

    @FindBy(xpath = "//textarea")
    private WebElement favoriteMoviesInputField;

    @FindBy(xpath = "//div[@role='listbox']")
    private WebElement favoriteColorButton;

    @FindBy(xpath = "//div[@class='exportSelectPopup quantumWizMenuPaperselectPopup']/div[@role='option']")
    private List<WebElement> favoriteColorsList;

    @FindBy(xpath = "//div[@class='quantumWizMenuPaperselectOption freebirdThemedSelectOptionDarkerDisabled exportOption isSelected']")
    private WebElement selectedFavoritColorItem;

    @FindBy(xpath = "//div[@role='button' and @jsname='GeGHKb']")
    private WebElement backButton;

    @FindBy(xpath = "//div[@role='button' and @jsname='OCpkoe']")
    private WebElement nextButton;

    public SecondPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public SecondPage setFavoriteMovies(List<String> favoriteMoviesList) {
        favoriteMoviesList.forEach(movie -> {
            favoriteMoviesInputField.sendKeys(movie);
            favoriteMoviesInputField.sendKeys(Keys.ENTER);
        });
        return this;
    }

    public SecondPage setFavoriteColor(String colorName) {
        favoriteColorButton.click();
        favoriteColorsList.stream()
                .filter(val -> val.getAttribute("data-value").equalsIgnoreCase(colorName))
                .findFirst()
                .ifPresent(element -> {
                    element.click();
                    wait.until(ExpectedConditions.invisibilityOf(element));
                });

        return this;
    }

    public FirstPage clickOnBackButton() {
        backButton.click();
        return new FirstPage(driver);
    }

    public ThirdPage clickOnNextButton() {
        nextButton.click();
        return new ThirdPage(driver);
    }

    public boolean isFavoriteMoviesListNotNull() {
        return !favoriteMoviesInputField.getText().isEmpty();
    }

    public boolean isFavoriteColorSelected() {
        return !selectedFavoritColorItem.getText().isEmpty();
    }
}
