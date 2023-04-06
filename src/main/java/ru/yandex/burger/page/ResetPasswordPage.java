package ru.yandex.burger.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ResetPasswordPage {
    private By signInButton = By.xpath(".//a[@Class='Auth_link__1fOlj']");

    @Step("Переход по кнопке Войти")
    public void clickSignInButton(WebDriver driver) {
        driver.findElement(signInButton).click();
    }
}
