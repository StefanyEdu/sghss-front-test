package pages;


import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;

public class CadastroProjetoPage {

    public void botaoCadastroProjeto() {
        $(byClassName("projetos-route-create-button"))
                .shouldBe(Condition.visible, Duration.ofSeconds(60))
                .click();
    }

    public void criarProjetoInformacoesInicias(String escola) {
        $(byXpath("//div/p[contains(text(),'Selecione a instituição')]"))
                .click();
        $(byXpath("//input")).setValue(escola).pressEnter();

        $(byXpath("//div/p[contains(text(),'Selecione o(s) assessor(es)')]")).shouldBe(Condition.visible, Duration.ofSeconds(60))
                .click();

        $(byXpath("//p[contains(text(),'Selecione o(s) assessor(es)')]/../..//input")).setValue("CARLA SANTOS SILVA");
        $(byXpath("//b[text()='CARLA SANTOS SILVA']")).click();

        // usando metodo dentro click ele só clica
        //$(byXpath("//label[text()='Assessor(es) pedagógico(s)']/..")).click(ClickOptions.usingJavaScript());

        //educador

//        $(byXpath("//div/p[contains(text(),'Selecione o(s) educador(es)...')]")).shouldBe(Condition.visible, Duration.ofSeconds(60))
//                .click();
//        $(byXpath("//p[contains(text(),'Selecione o(s) educador(es)...')]/../..//input")).setValue("CARLA SANTOS SILVA").pressEnter();
//        $(byXpath("//b[text()='CARLA SANTOS SILVA']")).click();






    }
}
