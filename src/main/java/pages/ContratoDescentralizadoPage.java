package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import modal.ContratoDesecentralizadoModal;
import org.openqa.selenium.WebElement;
import utils.GeradorDatas;
import utils.IdContratos;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ContratoDescentralizadoPage {
    public void acessoContrato() {
        $(byXpath("(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')])[1]")).click();
        $(byXpath("//span[text()='Contratos']")).click();
    }

    public void botaoCadastrarContrato() {
        $(byXpath("//span[text()='Cadastrar Contrato']")).click();

    }

    public void contratoDescentralizadoAssessor(
            ContratoDesecentralizadoModal contratoDesecentralizadoModal
            , IdContratos idContratos

    ) {
        $(byXpath("//span[text()='Contrato Assessor']")).click();

        $(byXpath("//span[text()='Iniciar Contratação']")).click();
        $(byId("contrato-dados-cooperativa-email")).click();
        $(byId("contrato-dados-cooperativa-email")).val("qa@gmail.com");
        $(byXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getCoop());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).doubleClick();
        $(byXpath("//span[text()='Próxima']")).click();
        $(byXpath("//div[@id='contrato-dados-assessor-empresa']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-assessor-empresa']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getEmpresa());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]//div)[1]")).doubleClick();
        $(byXpath("//div[@id='contrato-dados-assessor-assessor']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-assessor-assessor']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getAssessor());
        $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
        Selenide.executeJavaScript("document.querySelector('#valorAssessoria').style.display='block';");
        $(byId("valorAssessoria")).click();
        $(byId("valorAssessoria"))
                .shouldBe(Condition.exist)
                .val(contratoDesecentralizadoModal.getValorHoraAssessoria());

        Selenide.executeJavaScript("document.querySelector('#valorFormacao').style.display='block';");
        $(byId("valorFormacao")).click();
        $(byId("valorFormacao"))
                .shouldBe(Condition.exist)
                .val(contratoDesecentralizadoModal.getValorHoraFormatacao());

        Selenide.executeJavaScript("document.querySelector('#valorOutros').style.display='block';");
        $(byId("valorOutros")).click();
        $(byId("valorOutros"))
                .shouldBe(Condition.exist)
                .val(contratoDesecentralizadoModal.getValorHoraOutro());
        Selenide.executeJavaScript("document.querySelector('#valorReuniao').style.display='block';");
        $(byId("valorReuniao")).click();
        $(byId("valorReuniao"))
                .shouldBe(Condition.exist)
                .val(contratoDesecentralizadoModal.getValorReuniao());

        $(byXpath("(//span[text()='Próxima'])[2]")).click();

        $(byId("contrato-dados-contrato-data-inicio")).click();
        $(byId("contrato-dados-contrato-data-inicio")).val(contratoDesecentralizadoModal.getDataInicio());
        $(byId("contrato-dados-contrato-data-fim")).click();
        $(byId("contrato-dados-contrato-data-fim")).val(contratoDesecentralizadoModal.getDataFim());
        $(byXpath("(//label[text()='Data Fim do contrato']/following::input)[2]")).click();
        $(byXpath("//span[text()='Concluir e Avançar Etapa']")).click();
    }

    public void arquivoAsssinado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) throws InterruptedException {
        sleep(10000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        File arquivo_pdf = new File("src/test/resources/contratoassinado.pdf");
        $(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() + "']"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        WebElement pdf = Selenide.webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() + "']"));
        pdf.sendKeys(arquivo_pdf.getAbsolutePath());
        sleep(10000);
    }

    public void aditivoAssessor() {
        sleep(1000);
        $(byId("contrato-arquivos-criar-aditivo")).click();
        $(byXpath("//button[@label='Próxima']")).click();
        $(byId("next-button")).click();
        $(byXpath("//input[@data-indeterminate='false']")).click();
        $(byId("button-next-etapa")).click();
    }

    public void arquivoAsssinadoAditivo(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) {
        sleep(10000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        File arquivo_pdf = new File("src/test/resources/contratoassinado.pdf");
        $(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() + "']"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        WebElement pdf = Selenide.webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() + "']"));
        pdf.sendKeys(arquivo_pdf.getAbsolutePath());
        sleep(1000);

    }

    public void distratoContratoCentralizadoAssessor(String data, ContratoDesecentralizadoModal contratoDesecentralizadoModal) {
        $(byId("contrato-arquivos-criar-distrato")).click();
        $(byXpath("//button[@label='Próxima']")).click();
        $(byId("next-button")).click();
        $(byXpath("//label[text()='Data do distrato']/following::input")).click();
        $(byXpath("//label[text()='Data do distrato']/following::input")).val(data);
        $(byXpath("//input[@data-indeterminate='false']")).click();
        $(byXpath("//button[@label='Concluir e Avançar Etapa']")).click();

    }

    public void gerandoDistratoAssessor(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) {
        sleep(1000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrlAditivo(urlAtual));
        File arquivo_pdf = new File("src/test/resources/aditivo.pdf");
        $(byXpath("//div[text()='Em Edição']")).shouldBe(Condition.exist, Duration.ofSeconds(60));
        $(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() + "']"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        WebElement pdf = Selenide
                .webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() + "']"));
        pdf.sendKeys(arquivo_pdf.getAbsolutePath());
        sleep(1000);
    }

    public void contratoDescentralizadoEntePublico(ContratoDesecentralizadoModal contratoDesecentralizadoModal,
                                                   String dataInicio, String dataFim, IdContratos idContratos) {
        $(byXpath("//span[text()='Contrato Ente Público']")).click();

        $(byXpath("//span[text()='Iniciar Contratação']")).click();
        $(byId("contrato-ente-publico-dados-cooperativa-email")).click();
        $(byId("contrato-ente-publico-dados-cooperativa-email")).val("qa@gmail.com");
        $(byXpath("//div[@id='contrato-ente-publico-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-ente-publico-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input"))
                .val(contratoDesecentralizadoModal.getCoop());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).doubleClick();
        $(byXpath("//span[text()='Próxima']")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-ente-publico-estado']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-ente-publico-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getEstado());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-ente-publico-cidade']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-ente-publico-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60))
                .val(contratoDesecentralizadoModal.getCidade());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byId("contrato-dados-contrato-ente-publico-data-inicio")).click();
        $(byId("contrato-dados-contrato-ente-publico-data-inicio")).val(dataInicio);
        $(byId("contrato-dados-contrato-ente-publico-data-fim")).val(dataFim);
        $(byXpath("(//label[text()='Data Fim do contrato']/following::input)[2]")).click();
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        $(byXpath("//span[text()='Concluir e Avançar Etapa']")).click();

    }

    public void arquivoContratoEntePublico(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) {
        //contrato
        sleep(1000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        File arquivo_pdf = new File("src/test/resources/aditivo.pdf");
        $(byXpath("//div[text()='Em Edição']")).shouldBe(Condition.exist, Duration.ofSeconds(60));
        WebElement pdf = Selenide
                .webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='upload-assinado-null']"));
        pdf.sendKeys(arquivo_pdf.getAbsolutePath());
    }

    public void arquivoExtratoEntePublico(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) {
        //Extrato
        sleep(1000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        $(byXpath("//div[text()='Aguardando extrato']")).shouldBe(Condition.exist, Duration.ofSeconds(60));
        File arquivoExtrato_pdf = new File("src/test/resources/aditivo.pdf");

        WebElement pdfExtrato = Selenide
                .webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='upload-extrato-ente-publico-descentralizado']"));
        pdfExtrato.sendKeys(arquivoExtrato_pdf.getAbsolutePath());

    }

    public void arquivoPlanoTrabalhoEntePublico(ContratoDesecentralizadoModal contratoDesecentralizadoModal) {
        //Plano de trabalho
        File arquivoPlanoDeExtrato_pdf = new File("src/main/resources/aditivo.pdf");
        $(byXpath("//div[text()='Aguardando extrato']")).shouldBe(Condition.exist, Duration.ofSeconds(60));
        $(byXpath("//button[@id='upload-extrato-" + contratoDesecentralizadoModal.getId() + "']"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        WebElement pdfPlanoTrabalho = Selenide
                .webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='upload-extrato-" + contratoDesecentralizadoModal.getId() + "']"));
        pdfPlanoTrabalho.sendKeys(arquivoPlanoDeExtrato_pdf.getAbsolutePath());
    }

    //ENTE PRIVADO

    public void contratoDescentralizadoEntePrivado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,
                                                   GeradorDatas geradorDatas,
                                                   IdContratos idContratos) {
        $(byXpath("//span[text()='Contrato Ente Privado']")).click();
        $(byXpath("//span[text()='Iniciar Contratação']")).click();
        $(byId("contrato-dados-cooperativa-email")).click();
        $(byId("contrato-dados-cooperativa-email")).val("qa@gmail.com");
        $(byXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getCoop());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
        $(byXpath("//button[@label='Próxima']")).click();
        $(byXpath("//span[text()='Adicionar escola']")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getEstado());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getCidade());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
        sleep(2000);
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getEscola()).shouldBe(Condition.visible);
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
        $(byId("contrato-dados-contrato-find-school-modal-ok")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(contratoDesecentralizadoModal.getProfessorGestor());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byId("contrato-dados-contrato-data-inicio")).click();
        $(byId("contrato-dados-contrato-data-inicio")).val(contratoDesecentralizadoModal.getDataInicio());
        $(byId("contrato-dados-contrato-data-fim")).click();
        $(byId("contrato-dados-contrato-data-fim")).val(contratoDesecentralizadoModal.getDataFim());
        $(byXpath("//input[@data-indeterminate='false']")).click();
        $(byXpath("//button[@label='Concluir e Avançar Etapa']")).click();
        sleep(1000);
    }

    public void arquivoAssinadoEntePrivado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) {
    sleep(1000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        File arquivo_pdf = new File("src/test/resources/contratoassinado.pdf");
        $(byXpath("//div[text()='Em Edição']")).shouldBe(Condition.exist, Duration.ofSeconds(60));
        WebElement pdf = Selenide
                .webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() + "']"));
        pdf.sendKeys(arquivo_pdf.getAbsolutePath());

    }

    public void aditivoEntePrivado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,
                                   IdContratos idContratos) {
        sleep(1000);
        $(byId("contrato-arquivos-criar-aditivo")).click();
        $(byXpath("//button[@label='Próxima']")).click();
        $(byId("contrato-dados-contrato-data-inicio")).click();
        $(byId("contrato-dados-contrato-data-inicio")).val(contratoDesecentralizadoModal.getDataInicio());
        $(byId("contrato-dados-contrato-data-fim")).click();
        $(byId("contrato-dados-contrato-data-fim")).clear();
        $(byId("contrato-dados-contrato-data-fim")).val(contratoDesecentralizadoModal.getDataFim());
        $(byXpath("//input[@data-indeterminate='false']")).click();
        $(byXpath("//button[@label='Concluir e Avançar Etapa']")).click();
        File arquivo_pdf = new File("src/test/resources/contratoassinado.pdf");
        $(byXpath("//div[text()='Em Edição']")).shouldBe(Condition.exist, Duration.ofSeconds(90));
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrlAditivo(urlAtual));
        WebElement pdf = Selenide
                .webdriver()
                .driver()
                .getWebDriver()
                .findElement(byXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() + "']"));
        pdf.sendKeys(arquivo_pdf.getAbsolutePath());
sleep(1000);
    }
}
