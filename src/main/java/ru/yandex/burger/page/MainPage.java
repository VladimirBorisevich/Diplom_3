package ru.yandex.burger.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By breadButton = By.xpath(".//span[text()='Булки']/..");
    private By sauceButton = By.xpath(".//span[text()='Соусы']/..");
    private By fillingButton = By.xpath(".//span[text()='Начинки']/..");
    private By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    @Step("Переход по кнопке Войти в аккаунт")
    public void clickLoginButton(WebDriver driver) {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Булки")
    public void clickBreadButton(WebDriver driver) {
        driver.findElement(breadButton).click();
    }

    @Step("Нажатие кнопки Соусы")
    public void clickSauceButton(WebDriver driver) {
        driver.findElement(sauceButton).click();
    }

    @Step("Нажатие кнопки Начинки")
    public void clickFillingButton(WebDriver driver) {
        driver.findElement(fillingButton).click();
    }

    @Step("Проверка того, что выбрана категория Булки")
    public boolean isBreadSelected(WebDriver driver) {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(breadButton, "class", "current"));
    }

    @Step("Проверка того, что выбрана категория Соусы")
    public boolean isSauceSelected(WebDriver driver) {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(sauceButton, "class", "current"));
    }

    @Step("Проверка того, что выбрана категория Начинки")
    public boolean isFillingSelected(WebDriver driver) {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(fillingButton, "class", "current"));
    }

    @Step("Проверка видимости кнопки Оформить заказ")
    public boolean isMakeOrderButtonVisible(WebDriver driver) {
        return driver.findElement(makeOrderButton).isDisplayed();
    }
}
