package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import modal.ContratoCentralizadoModal;
import utils.IdContratos;
import utils.ScreenshotHelper;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

public class ContratoCentralizadoEntePublicoPage {
    ScreenshotHelper screenshotHelper = new ScreenshotHelper();

    public void contratoCentralizadoEntePublico(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos, String url) {
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Inicio do cadastro de contrato ente publico");
        cadastroDadosCidade(contratoCentralizadoModal);
        enviarArquivo("//input[@id='upload-assinado-null']", "src/test/resources/aditivo.pdf");
        analiseOperacionalEntePublico(contratoCentralizadoModal, idContratos);
        screenshotHelper.takeScreenshot("analise operacional");
        documentoAnaliseOperacional(contratoCentralizadoModal, url);
        documentoAssinadoContrato(contratoCentralizadoModal, url, idContratos);
        screenshotHelper.takeScreenshot("upload do contrato");
        documentoExtratoContrato(contratoCentralizadoModal, url, idContratos);
        screenshotHelper.takeScreenshot("upload de extrato");
    }

    public void contratoEntePublicoStatusAguardandoAssinatura(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos, String url) {
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Inicio do cadastro de contrato ente publico");
        cadastroDadosCidade(contratoCentralizadoModal);
        enviarArquivo("//input[@id='upload-assinado-null']", "src/test/resources/aditivo.pdf");
        analiseOperacionalEntePublico(contratoCentralizadoModal, idContratos);
        screenshotHelper.takeScreenshot("analise operacional");
        documentoAnaliseOperacional(contratoCentralizadoModal, url);
        contratoStatusAssinatura(contratoCentralizadoModal, url);
        screenshotHelper.takeScreenshot("Aguardando Assinatura");
    }

    public void contratoEntePublicoPontoAtencao(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos, String url) {
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Inicio do cadastro de contrato ente publico");
        cadastroDadosCidade(contratoCentralizadoModal);
        enviarArquivo("//input[@id='upload-assinado-null']", "src/test/resources/aditivo.pdf");
        analiseOperacionalEntePublico(contratoCentralizadoModal, idContratos);
        screenshotHelper.takeScreenshot("analise operacional");
        contratoComPontoAtencao(url, contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Ponto de atenção");

    }

    public void contratoEntePublicoComAditivo(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos,String url) {
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Inicio do cadastro de contrato ente publico");
        cadastroDadosCidade(contratoCentralizadoModal);
        enviarArquivo("//input[@id='upload-assinado-null']", "src/test/resources/aditivo.pdf");
        analiseOperacionalEntePublico(contratoCentralizadoModal, idContratos);
        screenshotHelper.takeScreenshot("analise operacional");
        documentoAnaliseOperacional(contratoCentralizadoModal, url);
        documentoAssinadoContrato(contratoCentralizadoModal, url, idContratos);
        screenshotHelper.takeScreenshot("upload do contrato");
        documentoExtratoContrato(contratoCentralizadoModal, url, idContratos);
        screenshotHelper.takeScreenshot("upload de extrato");
        aditivoContrato(contratoCentralizadoModal,idContratos);
        screenshotHelper.takeScreenshot("Cadastro de aditivo");
        valicacaoContratoAditivo(contratoCentralizadoModal,idContratos);
        analiseOperacionalAditivo(contratoCentralizadoModal,idContratos);
        screenshotHelper.takeScreenshot("Analise operacional aditivo");
        documentoAnaliseOperacionalAditivo(contratoCentralizadoModal,url);
        documentoAssinadoAditivo(contratoCentralizadoModal,url);
        screenshotHelper.takeScreenshot("upload de contrato assinado");
        documentoExtratoAditivo(contratoCentralizadoModal,url);
        screenshotHelper.takeScreenshot("upload de extrato");
    }


    private void clicarAcessoContrato() {
        open(DEV+"contratos");
    }

    private void clicarBotaoCadastrarContrato() {
        clicarElementoPorXpath("//span[text()='Cadastrar Contrato']");
        clicarElementoPorXpath("//*[(@id='select-contratos')]");
        clicarElementoPorId("value_centralizado");

    }

    private void cadastroDadosCoop(ContratoCentralizadoModal contratoCentralizadoModal) {
        clicarElementoPorXpath("//span[text()='Contrato Ente Público']");
        clicarElementoPorXpath("//button[@label='Iniciar Contratação']");
        clicarElementoPorId("contrato-ente-publico-dados-cooperativa-email");
        definirValorPorId("contrato-ente-publico-dados-cooperativa-email", "stefany_eduarda@sicredi.com.br");
        clicarElementoPorXpath("//div[@id='contrato-ente-publico-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-ente-publico-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div/div/input",
                contratoCentralizadoModal.getCoop());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorXpath("//button[@label='Próxima']");
    }

    private void cadastroDadosCidade(ContratoCentralizadoModal contratoCentralizadoModal) {
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-ente-publico-estado']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-ente-publico-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input",
                contratoCentralizadoModal.getEstado());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-ente-publico-cidade']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-ente-publico-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input",
                contratoCentralizadoModal.getCidade());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorId("contrato-dados-contrato-ente-publico-data-inicio");
        definirValorPorId("contrato-dados-contrato-ente-publico-data-inicio", contratoCentralizadoModal.getDataInicio());
        clicarElementoPorId("contrato-dados-contrato-ente-publico-data-fim");
        definirValorPorId("contrato-dados-contrato-ente-publico-data-fim", contratoCentralizadoModal.getDataFim());
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");
    }


    private void analiseOperacionalEntePublico(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        esperar(1000);
        clicarElementoPorXpathComTempo("//button[@label='Análise Operacional']", 60);
        clicarElementoPorXpath("(//label[text()='Observações']/following::textarea)[1]");
        definirValorPorXpathClicar("(//label[text()='Observações']/following::textarea)[1]", "Contrato pronto para seguir");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("//button[@label='Enviar']");
    }

    private void documentoAnaliseOperacional(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/analise-operacional");
        esperar(900);
        clicarElementoPorXpathComTempo("//button[@label='Aprovar documento']", 60);
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Contrato pronto");
        enviarArquivo("//input[@id='upload-assinado-null']", "src/test/resources/aditivo.pdf");
        clicarElementoPorXpath("(//button[@label='Aprovar documento'])[2]");
    }

    private void contratoComPontoAtencao(String url, ContratoCentralizadoModal contratoCentralizadoModal) {
        open(url + contratoCentralizadoModal.getId() + "/analise-operacional");
        clicarElementoPorXpath("//button[@label='Ponto de atenção']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Controto com Ponto de atenção");
        enviarArquivo("//input[@id='upload-assinado-null']", "src/test/resources/aditivo.pdf");
        esperar(900);
        clicarElementoPorXpath("(//button[@label='Ponto de atenção'])[2]");
    }

    private void contratoStatusAssinatura(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/validacao");
        clicarElementoPorXpath("//button[@label='Iniciar Processo Assinatura']");
        verificarElementoVisivelPorXpath("//div[text()='Aguardando Assinatura']");
    }

    private void documentoAssinadoContrato(ContratoCentralizadoModal contratoCentralizadoModal, String url, IdContratos idContratos) {
        esperar(1000);
        open(url + contratoCentralizadoModal.getId() + "/validacao");
        clicarElementoPorXpath("//button[@label='Iniciar Processo Assinatura']");
        verificarElementoExistentePorXpath("//div[text()='Aguardando Assinatura']");
        enviarArquivo("//input[@id='upload-assinatura-ente-publico']", "src/test/resources/aditivo.pdf");
    }

    private void documentoExtratoContrato(ContratoCentralizadoModal contratoCentralizadoModal, String url, IdContratos idContratos) {
        open(url + contratoCentralizadoModal.getId() + "/arquivos");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        esperar(1000);
        verificarElementoExistentePorXpath("//div[text()='Aguardando extrato']");
        enviarArquivo("//input[@id='upload-extrato-ente-publico']", "src/test/resources/aditivo.pdf");

    }

    private void enviarArquivo(String fileInputXpath, String filePath) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);
    }

    private void aditivoContrato(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        clicarElementoPorId("contrato-ente-publico-arquivos-criar-aditivo");
        clicarElementoPorXpath("//button[@label='Próxima']");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrlAditivo(urlAtual));
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");
    }

    private void analiseOperacionalAditivo(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        clicarElementoPorXpathComTempo("//button[@label='Análise Operacional']", 60);
        clicarElementoPorXpath("(//label[text()='Observações']/following::textarea)[1]");
        definirValorPorXpathClicar("(//label[text()='Observações']/following::textarea)[1]", "Contrato pronto para seguir");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("//button[@label='Enviar']");
    }

    private void documentoAnaliseOperacionalAditivo(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/analise-operacional");
        clicarElementoPorXpathComTempo("//button[@label='Aprovar documento']", 60);
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Contrato proonto");
        enviarArquivo("//input[@id='upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/aditivo.pdf");
        clicarElementoPorXpath("(//button[@label='Aprovar documento'])[2]");
    }

    private void pontoAtencaoAditivo(String url, ContratoCentralizadoModal contratoCentralizadoModal) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/analise-operacional");
        clicarElementoPorXpath("//button[@label='Ponto de atenção']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Controto com Ponto de atenção");
        enviarArquivo("//input[@id='upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/aditivo.pdf");
        clicarElementoPorXpath("(//button[@label='Ponto de atenção'])[2]");
    }

    private void valicacaoContratoAditivo(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        enviarArquivo("//input[@id='upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/aditivo.pdf");
    }

    private void statusAssinaturaAditivo(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/validacao");
        clicarElementoPorXpath("//button[@label='Iniciar Processo Assinatura']");
        verificarElementoVisivelPorXpath("//div[text()='Aguardando Assinatura']");
    }

    private void documentoAssinadoAditivo(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/validacao");
        clicarElementoPorXpath("//button[@label='Iniciar Processo Assinatura']");
        verificarElementoExistentePorXpath("//div[text()='Aguardando Assinatura']");
        enviarArquivo("//input[@id='upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/aditivo.pdf");
    }

    private void statusAguardandoExtratoAditivo(ContratoCentralizadoModal contratoCentralizadoModal, String url, IdContratos idContratos) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/arquivos");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        verificarElementoExistentePorXpath("//div[text()='Aguardando extrato']");

    }

    private void documentoExtratoAditivo(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/arquivos");
        enviarArquivo("//input[@id='upload-extrato-ente-publico-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/aditivo.pdf");

    }

    private void analiseOperacionalDistrato(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        clicarElementoPorXpathComTempo("//button[@label='Análise Operacional']", 60);
        clicarElementoPorXpath("(//label[text()='Observações']/following::textarea)[1]");
        definirValorPorXpathClicar("(//label[text()='Observações']/following::textarea)[1]", "Contrato certo");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("//button[@label='Enviar']");

    }

    private void documentoAnaliseOperacionalDistrato(ContratoCentralizadoModal contratoCentralizadoModal, String url) {
        open(url + contratoCentralizadoModal.getId() + "/distrato/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/analise-operacional");
        clicarElementoPorXpathComTempo("//button[@label='Aprovar documento']", 60);
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Contrato pronto");
        enviarArquivo("//input[@id='upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/contratoassinado.pdf");
    }

    private void validacaoDistrato(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        enviarArquivo("//input[@id='upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']", "src/test/resources/aditivo.pdf");

    }


    private void enviarArquivoDistrato(String url, ContratoCentralizadoModal contratoCentralizadoModal, String fileInputXpath, String filePath) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);
    }


    private void clicarElementoPorXpath(String xpath) {
        $(byXpath(xpath)).click();
    }

    private void clicarElementoPorXpathComTempo(String xpath, int timeoutSeconds) {
        $(byXpath(xpath)).shouldBe(visible, Duration.ofSeconds(timeoutSeconds)).click();
    }

    private void clicarElementoPorXpathDuploClick(String xpath) {
        $(byXpath(xpath)).doubleClick();
    }

    private void clicarElementoPorIdDuploClick(String id) {
        $(byId(id)).doubleClick();
    }

    private void clicarElementoPorId(String id) {
        $(byId(id)).click();
    }

    private void clicarElementoPorNome(String name) {
        $(byName(name)).click();
    }

    private void clicarElementoPorNome(String name, int timeoutSeconds) {
        $(byName(name)).shouldBe(visible, Duration.ofSeconds(timeoutSeconds)).click();
    }

    private void definirValorPorXpath(String xpath, String valor) {
        $(byXpath(xpath)).val(valor);
    }

    private void definirValorPorXpathClicar(String xpath, String valor) {
        $(byXpath(xpath)).val(valor).click();
    }

    private void definirValorPorId(String id, String valor) {
        $(byId(id)).val(valor);
    }

    private void definirValorPorNome(String name, String valor) {
        $(byName(name)).val(valor);
    }

    private void exibirElementoPorId(String id) {
        Selenide.executeJavaScript("document.querySelector('#" + id + "').style.display='block';");
    }

    private void verificarElementoVisivelPorXpath(String xpath) {
        $(byXpath(xpath)).shouldBe(visible);
    }

    private void verificarElementoExistentePorId(String id, int timeoutSeconds) {
        $(byId(id)).shouldBe(Condition.exist, Duration.ofSeconds(timeoutSeconds));
    }

    private void verificarElementoExistentePorXpath(String xpath) {
        $(byXpath(xpath)).shouldBe(Condition.exist);
    }

    private void esperar(int milliseconds) {
        sleep(milliseconds);
    }

}
