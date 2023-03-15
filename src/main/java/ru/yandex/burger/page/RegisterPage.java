package ru.yandex.burger.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By incorrectPassword = By.xpath("//p[text()='Некорректный пароль']");
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By signInButton = By.xpath(".//a[text()='Войти']");

    @Step("Заполнение емейла")
    public void fillEmail(WebDriver driver, String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение имени")
    public void fillName(WebDriver driver, String name) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполнение пароля")
    public void fillPassword(WebDriver driver, String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Переход по кнопке Войти")
    public void clickSignInButton(WebDriver driver) {
        driver.findElement(signInButton).click();
    }

    @Step("Нажатие кнопки регистрации")
    public void clickRegisterButton(WebDriver driver) {
        driver.findElement(registerButton).click();
    }

    @Step("Выполнение регистрации")
    public void makeRegistration(WebDriver driver, String name, String email, String password) {
        fillName(driver, name);
        fillEmail(driver, email);
        fillPassword(driver, password);
        clickRegisterButton(driver);
    }

    @Step("Проверка видимости блока с ошибкой")
    public boolean isIncorrectPasswordVisible(WebDriver driver) {
        return driver.findElement(incorrectPassword).isDisplayed();
    }
}
