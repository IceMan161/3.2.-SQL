package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
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

//    @AfterAll
//    static void clean() {
//        DataBase.cleanDatabase();
//    }

    String login = DataHelper.getAuthInfo().getLogin();
    String password = DataHelper.getAuthInfo().getPassword();
    String invalidPassword = DataBase.getInvalidPassword();

    @Test
    void shouldLoginToTheSystem() {
        val loginPage = new LogPage();
        val verificationPage = loginPage.validLogIn(login, password);
        val code = DataBase.verificationCode();
        verificationPage.validVerify(code);
    }

    @Test
    void shouldSystemIsBlocked() {
        Configuration.holdBrowserOpen = true;
        val logPage = new LogPage();
        logPage.invalidLogIn(login, invalidPassword);
        logPage.invalidLogIn(login, invalidPassword);
        logPage.invalidLogIn(login, invalidPassword);
        logPage.blockMessage();
    }

}


