package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.base.DataBase;
import ru.netology.data.DataHelper;
import ru.netology.page.LogPage;

import static com.codeborne.selenide.Selenide.open;


public class TestSQL {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clean() {
        DataBase.cleanDatabase();
    }

    String login = DataHelper.getAuthInfo().getLogin();
    String password = DataHelper.getAuthInfo().getPassword();
    String invalidPassword = DataBase.getInvalidPassword();

    @Test
    void shouldLoginToTheSystem() {
        Configuration.holdBrowserOpen = true;
        var loginPage = new LogPage();
        var verificationPage = loginPage.validLogIn(login, password);
        var code = DataBase.verificationCode();
        verificationPage.validVerify(code);
    }

    @Test
    void shouldSystemIsBlocked() {
        Configuration.holdBrowserOpen = true;
        var logPage = new LogPage();
        logPage.invalidLogIn(login, invalidPassword);
        logPage.invalidLogIn(login, invalidPassword);
        logPage.invalidLogIn(login, invalidPassword);
        logPage.blockMessage();
    }

}


