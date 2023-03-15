package ru.yandex.burger;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.burger.page.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Проверка работоспособности перехода по разделу Булки")
    public void checkBreadCanBeSelected() {
        MainPage mainPage = new MainPage();
        mainPage.clickSauceButton(driver);
        mainPage.clickBreadButton(driver);

        assertTrue(mainPage.isBreadSelected(driver));
    }

    @Test
    @DisplayName("Проверка работоспособности перехода по разделу Соусы")
    public void checkSauceCanBeSelected() {
        MainPage mainPage = new MainPage();
        mainPage.clickSauceButton(driver);

        assertTrue(mainPage.isSauceSelected(driver));
    }

    @Test
    @DisplayName("Проверка работоспособности перехода по разделу Начинки")
    public void checkFillingCanBeSelected() {
        MainPage mainPage = new MainPage();
        mainPage.clickFillingButton(driver);

        assertTrue(mainPage.isFillingSelected(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
