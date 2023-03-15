package ru.yandex.burger.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By signInButton = By.xpath(".//button[text()='Войти']");
    private By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private By resetPassword = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Заполненией емейла")
    public void fillEmail(WebDriver driver, String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение пароля")
    public void fillPassword(WebDriver driver, String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие кнопки Войти")
    public void clickSignInButton(WebDriver driver) {
        driver.findElement(signInButton).click();
    }

    @Step("Нажатие кнопки Зарегистироваться")
    public void clickRegisterButton(WebDriver driver) {
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие кнопки Восстановить пароль")
    public void clickResetPasswordButton(WebDriver driver) {
        driver.findElement(resetPassword).click();
    }

    @Step("Заполнение полей логина")
    public void makeSignIn(WebDriver driver, String email, String password) {
        fillEmail(driver, email);
        fillPassword(driver, password);
        clickSignInButton(driver);
    }

    @Step("Проверка видимости кнопки Войти")
    public boolean isSignInButtonVisible(WebDriver driver) {
        return driver.findElement(signInButton).isDisplayed();
    }
}
