package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import modal.ContratoDesecentralizadoModal;
import utils.GeradorDatas;
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

public class ContratosDescentralizadosAssessorPage {
    ScreenshotHelper screenshotHelper = new ScreenshotHelper();

    public void cadastrarContratoAssessorEmEdicao(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);

    }
    public void cadastroContratoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal,Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Vigente");
    }
    public void cadastroContratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Encerrado");
    }
    public void cadastroContratoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Não iniciado");
    }

    public void acessoContratoNotificacao(){
        open(HOM+"contratos");
    }

    public void cadastroContratoComAditivoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Vigente");
        cadastrarAditivo( "Vigente",contratoDesecentralizadoModal);
    }
    public void cadastroContratoComAditivoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Encerrado");
        contratoDesecentralizadoModal.setDataFim(new GeradorDatas().dataEventFim());
        cadastrarAditivo( "Vigente",contratoDesecentralizadoModal);
    }
    public void cadastroContratoComAditivoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Vigente");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataInicioContratoNaoIniciado());
        contratoDesecentralizadoModal.setDataFim(new GeradorDatas().dataFimContratoNaoIniciado());
        cadastrarAditivo( "Não iniciado",contratoDesecentralizadoModal);
    }
    public void cadastroContratoComAditivoNaoIniciadoAmbos(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Não iniciado");
        cadastrarAditivo( "Não iniciado",contratoDesecentralizadoModal);
    }

    public void cadastroContratoVigenteComDistratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Vigente");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataEventoInicio());
        cadastrarDistrato(contratoDesecentralizadoModal,"Encerrado","2","2","1");
    }
    public void cadastroContratoVigenteAditivoComDistratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Vigente");
        cadastrarAditivo("Vigente",contratoDesecentralizadoModal);
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataEventoInicio());
        cadastrarDistrato(contratoDesecentralizadoModal,"Encerrado","3","3","2");
    }
    public void cadastroContratoComAditivoNaoIniciadoAmbosComDistrato(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Não iniciado");
        cadastrarAditivo( "Não iniciado",contratoDesecentralizadoModal);
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataInicioContratoNaoIniciado());
        cadastrarDistrato(contratoDesecentralizadoModal,"Não iniciado","3","3","2");
    }

    public void cadastroContratoNaoIniciadoComDistrato(ContratoDesecentralizadoModal contratoDesecentralizadoModal,Integer programa){
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosAssessor(contratoDesecentralizadoModal,programa);
        contratoAssinado("Não iniciado");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataInicioContratoNaoIniciado());
        cadastrarDistrato(contratoDesecentralizadoModal,"Não iniciado","2","2","1");
    }





    private void clicarBotaoCadastrarContrato() {
        open(HOM+"contratos");
        clicarElementoPorXpath("//span[text()='Cadastrar Contrato']");
        clicarElementoPorXpath("//span[text()='Contrato Assessor']");
        screenshotHelper.takeScreenshot("Contrato descentralizado");
    }

    private void cadastraDadosCoop(ContratoDesecentralizadoModal contratoDesecentralizadoModal) {
        clicarElementoPorNome("emailCoop");
        definirValorPorNome("emailCoop","stefany_eduarda@sicredi.com.br");
        clicarElementoPorXpath("//div[@id='cooperativas']/div[1]/div[1]/div[1]/div");
        definirValorPorXpath("//div[@id='cooperativas']/div[1]/div[1]/div[1]/div/div/div/input",contratoDesecentralizadoModal.getCoop());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        screenshotHelper.takeScreenshot("Dados da cooperativa");
        clicarElementoPorXpath("//button[@label='Prosseguir']");

    }

    private void cadastraDadosAssessor(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarElementoPorXpath("//div[@id='empresa']/div[1]/div[1]/div[1]/div");
        definirValorPorXpath("//div[@id='empresa']/div[1]/div[1]/div[1]/div/div/div/input",contratoDesecentralizadoModal.getEmpresa());
        clicarElementoPorXpath("(//div[@class='new-auto-complete-field__menu-list css-11unzgr']//div)[1]");
        clicarElementoPorXpath("//div[@id='assessor']/div[1]/div[1]/div[1]/div");
        definirValorPorXpath("//div[@id='assessor']/div[1]/div[1]/div[1]/div/div/div/input",contratoDesecentralizadoModal.getAssessor());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        screenshotHelper.takeScreenshot("Dados do Assessor");
        clicarElementoPorXpath("(//label[text()='Programas educacionais*']/following::input)[1]");
        screenshotHelper.takeScreenshot("programas educacionais");
        programasEducacionais(programas);
        esperar(1000);
        clicarElementoPorIdDuploClick("valor-hora-assessoria");
        definirValorPorId("valor-hora-assessoria",contratoDesecentralizadoModal.getValorHoraAssessoria());
        clicarElementoPorId("valor-hora-formacao");
        limparElementoPorId("valor-hora-formacao");
        definirValorPorId("valor-hora-formacao",contratoDesecentralizadoModal.getValorHoraFormatacao());
        clicarElementoPorId("valor-hora-reuniao");
        definirValorPorId("valor-hora-reuniao",contratoDesecentralizadoModal.getValorReuniao());
        clicarElementoPorId("valor-hora-outros");
        definirValorPorId("valor-hora-outros",contratoDesecentralizadoModal.getValorHoraOutro());
        screenshotHelper.takeScreenshot("Dados do contrato");
        clicarElementoPorId("data-inicio");
        definirValorPorId("data-inicio",contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorId("data-fim");
        definirValorPorId("data-fim",contratoDesecentralizadoModal.getDataFim());
        clicarElementoPorXpath("//input[@type='checkbox']");
        screenshotHelper.takeScreenshot("Contrato preenchimento");
        clicarElementoPorXpath("//button[@label='Prosseguir']");
        verificarElementoVisivelPorXpath("(//span[text()='Em Edição'])[1]",30);


    }

    private void programasEducacionais(Integer programas){
        if (programas==1){
        clicarElementoPorXpath("//li[text()='A União Faz a Vida']");
        } else if (programas==2) {
            clicarElementoPorXpath("//li[text()='A União Faz a Vida']");
            clicarElementoPorXpath("//li[text()='Cooperativas Escolares']");

        } else if (programas == 3) {
            clicarElementoPorXpath("//li[text()='Cooperativas Escolares']");
            clicarElementoPorXpath("//li[text()='Jornada da Educação Financeira nas Escolas']");

        } else if (programas==4) {
            clicarElementoPorXpath("//li[text()='A União Faz a Vida']");
            clicarElementoPorXpath("//li[text()='Cooperativas Escolares']");
            clicarElementoPorXpath("//li[text()='Jornada da Educação Financeira nas Escolas']");
        }


    }
    private void enviarArquivo(String fileInputXpath, String filePath) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);

    }
    private void contratoAssinado(String statusContrato){
        esperar(5000);
        clicarElementoPorXpath("(//button[@type='button'])[5]");
        clicarElementoPorXpath("//li[text()='Documento assinado']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']","src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");
        verificarElementoVisivelPorXpath("(//span[text()='"+statusContrato+"'])[2]",40);
        screenshotHelper.takeScreenshot("Contrato " +statusContrato);

    }
    private void cadastrarAditivo(String statusContrato,ContratoDesecentralizadoModal contratoDesecentralizadoModal){
        clicarElementoPorXpath("//button[@label='Criar aditivo']");
        clicarElementoPorId("data-inicio");
        limparElementoPorId("data-inicio");
        definirValorPorId("data-inicio",contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorId("data-fim");
        limparElementoPorId("data-fim");
        definirValorPorId("data-fim",contratoDesecentralizadoModal.getDataFim());
        clicarElementoPorXpath("(//span[text()='Concluir'])[2]");
        verificarElementoVisivelPorXpath("//div[text()='Confirmar a criação de aditivo?']",60);
        clicarElementoPorId("contratos-modal-confirm__confirm");
        clicarElementoPorXpath("(//button[@id='actions'])[2]");
        clicarElementoPorXpath("(//li[text()='Documento assinado'])[2]");
        enviarArquivo("//input[@type='file']","src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        esperar(90);
        clicarElementoPorXpath("//span[text()='Salvar']");
        verificarElementoVisivelPorXpath("//span[text()='"+statusContrato+"']",40);
        screenshotHelper.takeScreenshot("Aditivo "+statusContrato);
    }

    private void cadastrarDistrato(ContratoDesecentralizadoModal contratoDesecentralizadoModal,String statusContrato,String parametroBotao,String botaoDocumento,String campo){
        clicarElementoPorXpath("//button[@label='Criar distrato']");
        clicarElementoPorXpathDuploClick("(//input[@id='data-distrato'])["+campo+"]");
        esperar(10);
        definirValorPorXpath("(//input[@id='data-distrato'])["+campo+"]",contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorXpath("(//span[text()='Concluir'])["+parametroBotao+"]");
        verificarElementoVisivelPorXpath("//div[text()='Confirmar a criação de distrato?']",60);
        clicarElementoPorId("contratos-modal-confirm__confirm");
        clicarElementoPorXpath("(//button[@id='actions'])["+parametroBotao+"]");
        clicarElementoPorXpath("(//li[text()='Documento assinado'])["+botaoDocumento+"]");
        enviarArquivo("//input[@type='file']","src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        esperar(90);
        clicarElementoPorXpath("//span[text()='Salvar']");
        verificarElementoVisivelPorXpath("//span[text()='"+statusContrato+"']",40);
        screenshotHelper.takeScreenshot("Distrato "+statusContrato);
    }



    private void clicarElementoPorXpath(String xpath) {
        $(byXpath(xpath)).click();
    }

    private void clicarElementoPorIdDuploClick(String id) {
        $(byId(id)).doubleClick();
    }
    private void clicarElementoPorXpathDuploClick(String xpath) {
        $(byXpath(xpath)).doubleClick();
    }

    private void clicarElementoPorId(String id) {
        $(byId(id)).click();
    }
    private void limparElementoPorId(String id) {
        $(byId(id)).clear();
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

    private void definirValorPorId(String id, String valor) {
        $(byId(id)).val(valor);
    }

    private void definirValorPorNome(String name, String valor) {
        $(byName(name)).val(valor);
    }

    private void exibirElementoPorId(String id) {
        Selenide.executeJavaScript("document.querySelector('#" + id + "').style.display='block';");
    }

    private void verificarElementoVisivelPorXpath(String xpath,Integer timeoutSeconds) {
        $(byXpath(xpath)).shouldBe(visible,Duration.ofSeconds(timeoutSeconds));
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
