package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferPage {
    private SelenideElement sumField = $("div[data-test-id=amount] input");
    private SelenideElement accountField = $("span[data-test-id=from] input");
    private SelenideElement Button = $("button[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public DashboardPage successfulTopUp(String sum, String cardNum) {
        sumField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        sumField.setValue(sum);
        accountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        accountField.setValue(cardNum);
        Button.click();
        return new DashboardPage();
    }

    public DashboardPage unsuccessfulTopUp(String sum, String cardNum) {
        sumField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        sumField.setValue(sum);
        accountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        accountField.setValue(cardNum);
        Button.click();
        return new DashboardPage();
    }
}