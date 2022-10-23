package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection buttonCard = $$("button[data-test-id=action-deposit]");
    private SelenideElement card1 = $("div[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement card2 = $("div[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public MoneyTransferPage clickButton1(SelenideElement card1) {
        this.card1.find("button[data-test-id=action-deposit]").click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage clickButton2(SelenideElement card2) {
        this.card2.find("button[data-test-id=action-deposit]").click();
        return new MoneyTransferPage();
    }

    public int clickCard() {
        val text = buttonCard.first().text();
        return extractBalance(text);
//       card2.find("button[data-test-id=action-deposit]").click();
//       return new MoneyTransferPage();
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
//   public int extractBalance() {
//       String[] text = card1.innerText().split(" ");
//       return Integer.parseInt(text[5]);
//   }
}
