package ru.netology.web.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageObjectsTest {
    int Balance1;
    int Balance2;
    int endBalance1;
    int endBalance2;
    int sum;
    DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        Balance1 = dashboardPage.extractBalance((dashboardPage.card1));
        Balance2 = dashboardPage.extractBalance((dashboardPage.card2));
    }

    @Test
    @DisplayName("Перевод денег с первой карты на вторую")
    void shouldTransferMoneyFromFirstToSecondCard() {
        sum = 100;
        var MoneyTransferPage = dashboardPage.clickButton(dashboardPage.card2);
        var cardNum = DataHelper.getFirstCard().getCardNumber();
        var dashboardPage2 = MoneyTransferPage.successfulTopUp(Integer.toString(sum), cardNum);
        endBalance1 = dashboardPage2.extractBalance((dashboardPage2.card1));
        endBalance2 = dashboardPage2.extractBalance((dashboardPage2.card2));
        assertEquals(Balance1 - sum, endBalance1);
        assertEquals(Balance2 + sum, endBalance2);
    }

    @Test
    @DisplayName("Перевод денег сo второй карты на первую")
    void shouldTransferMoneyFromSecondToFirstCard() {
        sum = 100;
        var MoneyTransferPage = dashboardPage.clickButton(dashboardPage.card1);
        var cardNum = DataHelper.getSecondCard().getCardNumber();
        var dashboardPage2 = MoneyTransferPage.successfulTopUp(Integer.toString(sum), cardNum);
        endBalance1 = dashboardPage2.extractBalance((dashboardPage2.card1));
        endBalance2 = dashboardPage2.extractBalance((dashboardPage2.card2));
        assertEquals(Balance1 + sum, endBalance1);
        assertEquals(Balance2 - sum, endBalance2);
    }

    @Test
    @DisplayName("Не должен переводить больше, чем есть на карте")
    void shouldNotTransferMoreThanAvailable() {
        sum = Balance1 + 100;
        var MoneyTransferPage = dashboardPage.clickButton(dashboardPage.card2);
        var cardNum = DataHelper.getFirstCard().getCardNumber();
        var dashboardPage2 = MoneyTransferPage.unsuccessfulTopUp(Integer.toString(sum), cardNum);
        endBalance1 = dashboardPage2.extractBalance((dashboardPage2.card1));
        endBalance2 = dashboardPage2.extractBalance((dashboardPage2.card2));
        assertEquals(Balance1 - sum, endBalance1);
        assertEquals(Balance2 + sum, endBalance2);
    }
}

