package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LogPage;

import static com.codeborne.selenide.Selenide.open;


public class TestSQL {

    @BeforeEach
    void shouldSet() {
        open("http://localhost:9999");
    }


    @Test
    void shouldGoVerificationPage() {
        var logPage = new LogPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = logPage.validLogin(authInfo);
    }


}


