package ru.yandex.burger.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
    private By constructor = By.xpath(".//p[text()='Конструктор']");
    private By stellarBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");

    @Step("Переход по логотипу конструктора")
    public void clickConstructor(WebDriver driver) {
        driver.findElement(constructor).click();
    }

    @Step("Переход по логотипу стеллар бургер")
    public void clickStellarBurgers(WebDriver driver) {
        driver.findElement(stellarBurgers).click();
    }

    @Step("Переход по логотипу личного кабинета")
    public void clickPersonalAccount(WebDriver driver) {
        driver.findElement(personalAccount).click();
    }

}
