package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import modal.ContratoCentralizadoModal;
import modal.ContratoDesecentralizadoModal;
import utils.GeradorDatas;
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

public class ContratoCentralizadoEntePrivadoPage {
    ScreenshotHelper screenshotHelper = new ScreenshotHelper();

    public void cadastroContratoEntePrivadoVigente(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos, String url){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        dadosCoop(contratoCentralizadoModal);
        dadosEscola(contratoCentralizadoModal);
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos);
        analiseOperacionalFaseII(url,contratoCentralizadoModal);
        documentoAssinadoContrato(url,contratoCentralizadoModal);
    }


    private void clicarAcessoContrato() {
        open(DEV+"contratos");
    }

    private void clicarBotaoCadastrarContrato() {
        clicarElementoPorXpath("//span[text()='Cadastrar Contrato']");
        clicarElementoPorXpath("//*[(@id='select-contratos')]");
        clicarElementoPorId("value_centralizado");

    }

    private void dadosCoop(ContratoCentralizadoModal contratoCentralizadoModal) {
        clicarElementoPorXpath("//span[text()='Contrato Ente Privado']");
        clicarElementoPorId("contrato-dados-cooperativa-email");
        definirValorPorId("contrato-dados-cooperativa-email", "stefany_eduarda@terceiros.sicredi.com.br");
        clicarElementoPorXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div/div");
        definirValorPorXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div/div/div/div/div/div/input",contratoCentralizadoModal.getCoop());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        clicarElementoPorXpath("(//button[@label='Próxima'])[1]");

    }

    private void dadosEscola(ContratoCentralizadoModal contratoCentralizadoModal) {
        clicarElementoPorId("contrato-dados-contrato-find-school");
        esperar(40);
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div/div/div/div/div//div/input",
                contratoCentralizadoModal.getEstado());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div/div/div/div");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div/div/div/div/div/div/input",
                contratoCentralizadoModal.getCidade());
        clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]/div/div/input",
                contratoCentralizadoModal.getEscola());
        clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]");
        clicarElementoPorId("contrato-dados-contrato-find-school-modal-ok");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]/div/div/input",
                contratoCentralizadoModal.getProfessorGestor());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorId("contrato-dados-contrato-data-inicio");
        definirValorPorId("contrato-dados-contrato-data-inicio", contratoCentralizadoModal.getDataInicio());
        clicarElementoPorId("contrato-dados-contrato-data-inicio");
        definirValorPorId("contrato-dados-contrato-data-fim", contratoCentralizadoModal.getDataFim());
        screenshotHelper.takeScreenshot("dados da escola");
        clicarElementoPorXpath("(//button[@label='Próxima'])[2]");
        clicarElementoPorXpath("(//button[@label='Próxima'])[3]");
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");
    }

    private void analiseOperacionalContrato(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        clicarElementoPorXpath("//button[@label='Análise Operacional']");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Tudo certo");
        clicarElementoPorXpath("//button[@label='Enviar']");
    }

    private void analiseOperacionalFaseII(String url, ContratoCentralizadoModal contratoCentralizadoModal) {
        open(url + contratoCentralizadoModal.getId() + "/analise-operacional");
        clicarElementoPorXpath("//button[@label='Finalizar revisão']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Tudo certo");
        clicarElementoPorXpath("//button[@label='Enviar']");

    }

    private void documentoAssinadoContrato(String url, ContratoCentralizadoModal contratoCentralizadoModal) {
        open(url + contratoCentralizadoModal.getId() + "/validacao");
        clicarElementoPorXpath("//button[@label='Gerar contrato']");
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getId() + "']",
                "src/test/resources/contratoassinado.pdf");

    }

    private void cadastroAditivoContrato(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos,String url) {
        clicarElementoPorId("contrato-arquivos-criar-aditivo");
        clicarElementoPorXpath("//button[@label='Próxima']");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("(//button[@label='Próxima'])[2]");
        clicarElementoPorXpath("(//button[@label='Próxima'])[3]");
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");

    }
    private void documentoAssindadoAditivo(String url,ContratoCentralizadoModal contratoCentralizadoModal){
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/validacao");
        clicarElementoPorXpath("//button[@label='Gerar aditivo']");
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']",
                "src/test/resources/contratoassinado.pdf");

    }
    private void cadastroDistratoContrato(){
            clicarElementoPorId("contrato-arquivos-criar-distrato");
            clicarElementoPorXpath("//button[@label='Próxima']");
            clicarElementoPorId("contrato-dados-contrato-data-distrato");
            definirValorPorId("contrato-dados-contrato-data-distrato",new GeradorDatas().dataEventoInicio());
            clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");

    }
    private void analiseOperacionalContratoAditivoDistrato(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos) {
        clicarElementoPorXpath("//button[@label='Análise Operacional']");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Tudo certo");
        clicarElementoPorXpath("//button[@label='Enviar']");
    }

    private void analiseOperacionalFaseIIAditivoDistrato(String url, ContratoCentralizadoModal contratoCentralizadoModal) {
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/analise-operacional");
        clicarElementoPorXpath("//button[@label='Finalizar revisão']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Tudo certo");
        clicarElementoPorXpath("//button[@label='Enviar']");

    }
    private void documentoAssindadoDistrato(String url,ContratoCentralizadoModal contratoCentralizadoModal,String tipoContrato){
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/validacao");
        clicarElementoPorXpath("//button[@label='Gerar aditivo']");
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']",
                "src/test/resources/contratoassinado.pdf");

    }



    private void enviarArquivo(String fileInputXpath, String filePath) {
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

