package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import modal.ContratoCentralizadoModal;
import org.openqa.selenium.WebElement;
import utils.GeradorDatas;
import utils.IdContratos;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ContratoCentralizadoPage {

    public void filtroContrato(ContratoCentralizadoModal contratoCentralizadoModal) {
        open("https://auniaofazavida.hom.sicredi.net/admin/contratos");
        $(byId("contratos-search-text-field")).click();
        $(byId("contratos-search-text-field")).val(contratoCentralizadoModal.getId());
        $(byXpath("//button[@class='search-text-field-search-button']//img[1]")).click();
        $(byId("contrato-item-item-button")).click();

    }

    //Contrato centralizado ente-publico


    //CONTRATO ENTE PRIVADO
    public void contratoEntePrivado(ContratoCentralizadoModal contratoCentralizadoModal) {
        $(byText("Contrato Ente Privado")).click();
        $(byId("contrato-dados-cooperativa-email")).click();
        $(byId("contrato-dados-cooperativa-email")).val("qa@gmail.com");
        $(byXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoCentralizadoModal.getCoop());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]//div)[1]")).click();
        $(byXpath("(//button[@label='Próxima'])[1]")).click();
        $(byId("contrato-dados-contrato-find-school")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoCentralizadoModal.getEstado()).click();
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoCentralizadoModal.getCidade());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoCentralizadoModal.getEscola());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
        $(byId("contrato-dados-contrato-find-school-modal-ok")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoCentralizadoModal.getProfessorGestor());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byId("contrato-dados-contrato-data-inicio")).click();
        $(byId("contrato-dados-contrato-data-inicio")).val(contratoCentralizadoModal.getDataInicio());
        $(byId("contrato-dados-contrato-data-fim")).click();
        $(byId("contrato-dados-contrato-data-fim")).val(contratoCentralizadoModal.getDataFim());
        $(byXpath("(//button[@label='Próxima'])[2]")).click();
        $(byXpath("(//button[@label='Próxima'])[3]")).click();
        $(byXpath("//button[@label='Concluir e Avançar Etapa']")).click();
        $(byXpath("//button[@label='Gerar contrato']")).click();
    }

    public void anexoDocumentoAssinado() {


    }

    public void AnaliseOperacionalEntePrivado() {
        $(byXpath("(//span[text()='Próxima'])[3]")).click();
        $(byXpath("//button[@label='Concluir e Avançar Etapa']")).click();
        $(byXpath("//button[@label='Análise Operacional']")).click();
        $(byXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]"))
                .click();
        $(byXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]"))
                .val("Contrato automatizado");

    }


}




