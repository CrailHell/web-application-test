import cz.actum.pages.FirstPage;
import cz.actum.pages.SecondPage;
import cz.actum.utils.DriverProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class QAAutomationTest {
    private static WebDriver driver;
    private static List<String> favoriteMoviesList;
    private static String favoriteColor;

    @BeforeClass
    public static void setUp() {
        driver = new DriverProvider().prepare();
        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSeY_W-zSf2_JxR4drhbgIxdEwdbUbE4wXhxHZLgxZGiMcNl7g/viewform");

        favoriteMoviesList = Arrays.asList(
                "The Green Mile", "Forrest Gump", "The Shawshank Redemption", "Leon", "12 Angry Men");

        favoriteColor = "Black";
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void googleFormTest() {
        LocalDate localDate = LocalDate.now().plusDays(5);

        FirstPage firstPage = new FirstPage(driver)
                .checkAllCheckThisCheckBoxes()
                .setDate(localDate.getDayOfMonth(), localDate.getMonthValue());

        firstPage.clickOnNextButton();

        assertTrue(firstPage.isCurrentMonthErrorMessageDisplayed());

        firstPage
                .setCurrentMonth(LocalDate.now().getMonth().toString())
                .clickOnNextButton()
                .setFavoriteMovies(getRandomMoviesList())
                .setFavoriteColor(favoriteColor)
                .clickOnBackButton();

        String reversingMonth = new StringBuilder(LocalDate.now().getMonth().toString()).reverse().toString();

        SecondPage secondPage = firstPage
                .setCurrentMonth(reversingMonth)
                .clickOnNextButton();

        assertTrue(secondPage.isFavoriteMoviesListNotNull());
        assertTrue(secondPage.isFavoriteColorSelected());

        secondPage
                .clickOnNextButton()
                .clickOnYesRadioButton()
                .clickOnSubmitButton();
    }

    private List<String> getRandomMoviesList() {
        Collections.shuffle(favoriteMoviesList);
        return favoriteMoviesList.subList(0, 3);
    }
}
