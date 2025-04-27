package pages;


import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public void digitarPerfil(String perfil) {
        $(byId("username"))
                .shouldBe(Condition.visible, Duration.ofSeconds(60))
                .sendKeys(perfil);
    }

    public void digitarSenha(String senha) {
        $(byId("password"))
                .shouldBe(Condition.visible, Duration.ofSeconds(60))
                .sendKeys(senha);
    }

    public void clicarEntrar() {
        $(byId("submit-button"))
                .shouldBe(Condition.visible, Duration.ofSeconds(60))
                .click();
    }
}
