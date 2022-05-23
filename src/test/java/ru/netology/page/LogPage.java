package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LogPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void logIn(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
    }

    public VerificationPage validLogIn(String login, String password) {
        logIn(login, password);
        return new VerificationPage();
    }

    public void clearingField() {
        loginField.doubleClick().sendKeys(Keys.DELETE);
        passwordField.doubleClick().sendKeys(Keys.DELETE);
    }

    public void invalidLogIn(String login, String password) {
        clearingField();
        logIn(login, password);
        errorNotification.shouldBe(visible);
    }

    public void blockMessage() {
        errorNotification.shouldBe(visible).shouldHave(exactText("Система заблокирована"));
    }

}
