package pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

public class NotificacoesPage {

    public String noticacaoes() {
        open(HOM+"projetos/pufv");
        $(byXpath("//header[@id='navbar-container']/div[1]/div[2]/button[1]"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        String textoNotificacao =
                $(byXpath("//div[@id='notificationsMenu']/div[3]/ul[1]/div[2]/li[1]/div[1]/div[2]/p[2]/span[1]"))
                        .shouldBe(Condition.exist,Duration.ofSeconds(60))
                        .innerText();

        return textoNotificacao;

    }


}
