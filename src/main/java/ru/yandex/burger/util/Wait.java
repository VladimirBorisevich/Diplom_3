package ru.yandex.burger.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
    public void waitVisibilityOf(WebDriver driver, By element, int time) {
        new WebDriverWait(driver, time)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitVisibilityOfEmailField(WebDriver driver, int time) {
        waitVisibilityOf(driver, By.xpath(".//label[text()='Email']/../input"), time);
    }

    public void waitVisibilityOfMakeOrderButton(WebDriver driver, int time) {
        waitVisibilityOf(driver, By.xpath(".//button[text()='Оформить заказ']"), time);
    }

    public void waitVisibilityOfExitButton(WebDriver driver, int time) {
        waitVisibilityOf(driver, By.xpath(".//button[text()='Выход']"), time);
    }

    public void waitVisibilityOfRegisterButton(WebDriver driver, int time) {
        waitVisibilityOf(driver, By.xpath(".//a[text()='Зарегистрироваться']"), time);
    }
}
