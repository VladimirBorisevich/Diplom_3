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
import ru.yandex.burger.page.LoginPage;
import ru.yandex.burger.page.MainPage;
import ru.yandex.burger.page.RegisterPage;
import ru.yandex.burger.util.Wait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    WebDriver driver;
    User user;
    UserApi userApi = new UserApi();
    MainPage mainPage = new MainPage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    Wait wait = new Wait();

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();
        user = new User("stellarburger12345678@yahoo.com", "stellarburger12345678", "burger");
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void successfulRegisterTest() {
        mainPage.clickLoginButton(driver);
        wait.waitVisibilityOfRegisterButton(driver, 1);
        loginPage.clickRegisterButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        registerPage.makeRegistration(driver, user.getName(), user.getEmail(), user.getPassword());
        wait.waitVisibilityOfRegisterButton(driver, 1);

        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
    }

    @Test
    @DisplayName("Проверка появления дополнительного блока с ошибкой при введении некорректного пароля")
    public void checkShortPasswordMakesErrorMessage() {
        mainPage.clickLoginButton(driver);
        wait.waitVisibilityOfRegisterButton(driver, 1);
        loginPage.clickRegisterButton(driver);
        wait.waitVisibilityOfEmailField(driver, 1);
        // Меняем пароль на некорректный (меньше 5 символов)
        user.setPassword("abc");
        registerPage.makeRegistration(driver, user.getName(), user.getEmail(), user.getPassword());

        assertTrue(registerPage.isIncorrectPasswordVisible(driver));
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
