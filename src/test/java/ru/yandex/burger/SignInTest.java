package ru.yandex.burger;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.burger.client.UserApi;
import ru.yandex.burger.model.User;
import ru.yandex.burger.page.*;
import ru.yandex.burger.util.Wait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignInTest {
    WebDriver driver;
    User user;
    UserApi userApi = new UserApi();
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    Wait wait = new Wait();
    RegisterPage registerPage = new RegisterPage();
    ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
    HeaderPage headerPage = new HeaderPage();

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();
        // Заранее создаем пользователя через апи
        user = new User("stellarburger12345678@yahoo.com", "stellarburger12345678", "burger");
        userApi.createUser(user);
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт»")
    public void signInFromMainPage() {
        mainPage.clickLoginButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        wait.waitVisibilityOfMakeOrderButton(driver, 1);

        assertTrue(mainPage.isMakeOrderButtonVisible(driver));
    }

    @Test
    @DisplayName("вход по кнопке «Личный кабинет» на главной")
    public void signInWithPersonalAccountButton() {
        headerPage.clickPersonalAccount(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        wait.waitVisibilityOfMakeOrderButton(driver, 1);

        assertTrue(mainPage.isMakeOrderButtonVisible(driver));
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void signInFromRegistrationPage() {
        mainPage.clickLoginButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        loginPage.clickRegisterButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        // Url должен соответствовать .../register
        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/register");
        registerPage.clickSignInButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        // Url должен соответствовать .../login
        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");

        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        wait.waitVisibilityOfMakeOrderButton(driver, 1);

        assertTrue(mainPage.isMakeOrderButtonVisible(driver));
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void signInFromResetPasswordPage() {
        mainPage.clickLoginButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        loginPage.clickResetPasswordButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        resetPasswordPage.clickSignInButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);

        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        wait.waitVisibilityOfMakeOrderButton(driver, 1);

        assertTrue(mainPage.isMakeOrderButtonVisible(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
        Response loginResponse = userApi.loginUser(user);
        String accessToken = loginResponse.then().extract().path("accessToken");
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
}
