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
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

public class ContratoCentralizadoAssessorPage {
 ScreenshotHelper screenshotHelper = new ScreenshotHelper();

    public void cadastroContratoAssessorCentralizado(ContratoCentralizadoModal contratoCentralizadoModal,String url,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoCentralizadoModal);
        cadastraDadosAssessor(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos ,url);
        screenshotHelper.takeScreenshot("Contatro com status analise operacional");
        gerarContrato(contratoCentralizadoModal,url);
        esperar(1000);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getId() + "']" ,"src/test/resources/contratoassinado.pdf");
        screenshotHelper.takeScreenshot("Contrato assinado");
    }
    public void cadastroContratoStatusAnaliseOperacional(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos,String url){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoCentralizadoModal);
        cadastraDadosAssessor(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos,url);
        screenshotHelper.takeScreenshot("Contatro com status analise operacional");

    }
    public void cadastroContratoAssessorComAditivo(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos, String url){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoCentralizadoModal);
        cadastraDadosAssessor(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos ,url);
        screenshotHelper.takeScreenshot("Contatro com status analise operacional");
        gerarContrato(contratoCentralizadoModal,url);
        esperar(1000);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getId() + "']" ,"src/test/resources/contratoassinado.pdf");
        screenshotHelper.takeScreenshot("Contrato assinado");
        esperar(1000);
        cadastroAditivoContrato(contratoCentralizadoModal,idContratos);
        screenshotHelper.takeScreenshot("Gerando Aditivo");

    }
    public void cadastroContratoAssessorComAditivoStatusAnaliseOperacional(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos, String url){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoCentralizadoModal);
        cadastraDadosAssessor(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos ,url);
        screenshotHelper.takeScreenshot("Contatro com status analise operacional");
        gerarContrato(contratoCentralizadoModal,url);
        esperar(1000);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getId() + "']" ,"src/test/resources/contratoassinado.pdf");
        screenshotHelper.takeScreenshot("Contrato assinado");
        esperar(1000);
        cadastroAditivoContrato(contratoCentralizadoModal,idContratos);
        screenshotHelper.takeScreenshot("Gerando Aditivo");
        analiseOperacionalAditivo(url,contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Realizado a analise operacional");
    }
    public void cadastroContratoAssessorComAditivoAssinado(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos, String url,String data){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoCentralizadoModal);
        cadastraDadosAssessor(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos ,url);
        screenshotHelper.takeScreenshot("Contatro com status analise operacional");
        gerarContrato(contratoCentralizadoModal,url);
        esperar(1000);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getId() + "']" ,"src/test/resources/contratoassinado.pdf");
        screenshotHelper.takeScreenshot("Contrato assinado");
        esperar(1000);
        cadastroAditivoContrato(contratoCentralizadoModal,idContratos);
        screenshotHelper.takeScreenshot("Gerando Aditivo");
        analiseOperacionalAditivo(url,contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Realizado a analise operacional");
        gerarContratoAditivoNaoAssinado(contratoCentralizadoModal,url);
        enviarArquivoAditivo(url,contratoCentralizadoModal,"//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']","src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("gerado contrato do aditivo");

    }
    public void cadastroContratoDistrato(ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos, String url,String data){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoCentralizadoModal);
        cadastraDadosAssessor(contratoCentralizadoModal);
        screenshotHelper.takeScreenshot("Contrato cadastrado");
        analiseOperacionalContrato(contratoCentralizadoModal,idContratos ,url);
        screenshotHelper.takeScreenshot("Contatro com status analise operacional");
        gerarContrato(contratoCentralizadoModal,url);
        esperar(1000);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getId() + "']" ,"src/test/resources/contratoassinado.pdf");
        screenshotHelper.takeScreenshot("Contrato assinado");
        cadastroDistrato(data,contratoCentralizadoModal,idContratos);
        screenshotHelper.takeScreenshot("Gerando distrato");
        gerarDistratoAssinado(url,contratoCentralizadoModal);
        enviarArquivoDistrato(url,contratoCentralizadoModal,"//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']","src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Gerando distrato");
    }


    private void clicarAcessoContrato() {
        open(DEV+"contratos");
    }

    private void clicarBotaoCadastrarContrato() {
        clicarElementoPorXpath("//span[text()='Cadastrar Contrato']");
        clicarElementoPorXpath("//*[(@id='select-contratos')]");
        clicarElementoPorId("value_centralizado");

    }

    private void cadastraDadosCoop(ContratoCentralizadoModal contratoCentralizadoModal) {
        clicarElementoPorXpath("//span[text()='Contrato Assessor']");
        clicarElementoPorId("contrato-dados-cooperativa-email");
        definirValorPorId("contrato-dados-cooperativa-email","stefany_eduarda@sicredi.com.br");
        clicarElementoPorXpathComTempo("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]",60);
        definirValorPorXpathClicar("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div/div/input"
        ,contratoCentralizadoModal.getCoop());
        clicarElementoPorXpathDuploClick("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorXpath("//span[text()='Próxima']");
        clicarElementoPorXpathComTempo("//div[@id='contrato-dados-assessor-empresa']/div[1]/div[1]/div[1]/div[1]",60);

    }


    private void cadastraDadosAssessor(ContratoCentralizadoModal contratoCentralizadoModal) {
        clicarElementoPorXpath("//div[@id='contrato-dados-assessor-empresa']/div/div");
        definirValorPorXpath("//div[@id='contrato-dados-assessor-empresa']/div/div/div/div/div/div/input",contratoCentralizadoModal.getEmpresa());
        clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]//div)[1]");
        clicarElementoPorXpath("//div[@id='contrato-dados-assessor-assessor']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-assessor-assessor']/div[1]/div[1]/div[1]/div[1]/div/div/input",contratoCentralizadoModal.getAssessor());
        clicarElementoPorXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]");
        clicarElementoPorXpath("(//input[@type='checkbox'])[1]");
        exibirElementoPorId("valorAssessoriaCentralizado");
        esperar(1000);
        clicarElementoPorId("valorAssessoriaCentralizado");
        definirValorPorId("valorAssessoriaCentralizado",contratoCentralizadoModal.getValorHoraAssessoria());
        clicarElementoPorXpath("(//input[@type='checkbox'])[2]");
        exibirElementoPorId("valorFormacaoCentralizado");
        clicarElementoPorId("valorFormacaoCentralizado");
        definirValorPorId("valorFormacaoCentralizado", contratoCentralizadoModal.getValorHoraFormatacao());
        clicarElementoPorXpath("(//input[@type='checkbox'])[3]");
        exibirElementoPorId("valorOutrosCentralizado");
        definirValorPorId("valorOutrosCentralizado", contratoCentralizadoModal.getValorHoraOutro());
        clicarElementoPorXpath("(//input[@type='checkbox'])[4]");
        exibirElementoPorId("valorReuniaoCentralizado");
        definirValorPorId("valorReuniaoCentralizado",contratoCentralizadoModal.getValorReuniao());
        clicarElementoPorId("next-button");
      //  clicarElementoPorXpath("(//button[@id='next-button'])[2]");
        clicarElementoPorId("contrato-dados-contrato-data-inicio");
        definirValorPorId("contrato-dados-contrato-data-inicio","06062023");
        clicarElementoPorId("button-next-etapa");

    }
    private void analiseOperacionalContrato(ContratoCentralizadoModal contratoCentralizadoModal,
                                            IdContratos idContratos,String url){
        verificarElementoVisivelPorXpath("//button[@label='Análise Operacional']");
        clicarElementoPorXpath("//button[@label='Análise Operacional']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]","Contrato Teste");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
        clicarElementoPorXpath("//button[@label='Enviar']");
        esperar(1000);
        open(url + contratoCentralizadoModal.getId() + "/analise-operacional");
        esperar(1000);
        clicarElementoPorXpath("//button[@label='Finalizar revisão']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Contrato de teste automatizado");
        clicarElementoPorXpath("//button[@label='Enviar']");
        esperar(1000);
    }
    private void gerarContrato(ContratoCentralizadoModal contratoCentralizadoModal,String url){
        open(url + contratoCentralizadoModal.getId() + "/validacao");
        esperar(100);
        verificarElementoVisivelPorXpath("//button[@label='Gerar contrato']");
        clicarElementoPorXpath("//button[@label='Gerar contrato']");
        esperar(1000);
    }

    private void enviarArquivo(String fileInputXpath, String filePath) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);

    }
    private void cadastroAditivoContrato(ContratoCentralizadoModal contratoCentralizadoModal,IdContratos idContratos){
        clicarElementoPorId("contrato-arquivos-criar-aditivo");
        clicarElementoPorXpath("//span[text()='Próxima']");
        clicarElementoPorId("next-button");
        clicarElementoPorXpath("(//button[@id='next-button'])[2]");
        clicarElementoPorId("contrato-dados-contrato-data-inicio");
        definirValorPorId("contrato-dados-contrato-data-inicio",contratoCentralizadoModal.getDataInicio());
        clicarElementoPorId("button-next-etapa");
        clicarElementoPorXpathComTempo("//button[@label='Análise Operacional']",60);
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]","Contrato Teste");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrlAditivo(urlAtual));
        clicarElementoPorXpath("//button[@label='Enviar']");
    }

    private void analiseOperacionalAditivo(String url,ContratoCentralizadoModal contratoCentralizadoModal){
        esperar(1000);
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/analise-operacional");
        clicarElementoPorXpath("//button[@label='Finalizar revisão']");
        clicarElementoPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]","Contrato teste automatizado");
        clicarElementoPorXpath("//button[@label='Enviar']");
    }
    private void gerarContratoAditivoNaoAssinado(ContratoCentralizadoModal contratoCentralizadoModal, String url){
        open(url + contratoCentralizadoModal.getId() + "/aditivo/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/validacao");
        clicarElementoPorXpath("//button[@label='Gerar aditivo']");
    }

    private void enviarArquivoAditivo(String url,ContratoCentralizadoModal contratoCentralizadoModal,String fileInputXpath, String filePath) {
        $(byXpath("//div[text()='Aguardando Assinatura']")).shouldBe(Condition.exist, Duration.ofSeconds(60));
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);
    }

    private void cadastroDistrato(String data, ContratoCentralizadoModal contratoCentralizadoModal, IdContratos idContratos){
        esperar(1000);
        clicarElementoPorId("contrato-arquivos-criar-distrato");
        clicarElementoPorXpath("//button[@label='Próxima']");
        clicarElementoPorId("next-button");
        clicarElementoPorXpath("//label[text()='Data do distrato']/following::input");
        definirValorPorXpath("//label[text()='Data do distrato']/following::input",data);
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");
        clicarElementoPorXpathComTempo("//button[@label='Análise Operacional']",60);
        clicarElementoPorXpath("//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following-sibling::div");
        definirValorPorXpath("(//label[text()='Descreva detalhadamente as modificações que deseja efetuar no contrato, indicando a(s) cláusula(s) a ser(em) alterada(s)']/following::textarea)[1]",
                "Contrato de teste automatizado");
        String urlAtual = WebDriverRunner.url();
        contratoCentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrlAditivo(urlAtual));
        clicarElementoPorXpath("//button[@label='Enviar']");

    }
    private void gerarDistratoAssinado(String url,ContratoCentralizadoModal contratoCentralizadoModal){
        open(url + contratoCentralizadoModal.getId() + "/distrato/" + contratoCentralizadoModal.getIdAditivoDistrato() + "/arquivos");
        verificarElementoExistentePorXpath("//input[@id='contrato-arquivos-upload-assinado-" + contratoCentralizadoModal.getIdAditivoDistrato() + "']");
    }
    private void enviarArquivoDistrato(String url,ContratoCentralizadoModal contratoCentralizadoModal,String fileInputXpath, String filePath) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);
    }



    private void clicarElementoPorXpath(String xpath) {
        $(byXpath(xpath)).click();
    }

    private void clicarElementoPorXpathComTempo(String xpath,int timeoutSeconds){
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

