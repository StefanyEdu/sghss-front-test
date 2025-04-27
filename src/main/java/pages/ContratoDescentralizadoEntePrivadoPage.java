package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import modal.ContratoDesecentralizadoModal;
import utils.IdContratos;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

public class ContratoDescentralizadoEntePrivadoPage {
    public void cadastrarContratoEntePrivadoEmEdicao(ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);

    }
    public void cadastrarContratoEntePrivadoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
    }
    public void cadastrarContratoEntePrivadoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
    }
    public void cadastrarContratoEntePrivadoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);

    }
    public void cadastrarContratoEntePrivadoVigenteComAditivoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
        cadastroAditivoContratoEntePrivado(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
    }
    public void cadastrarContratoEntePrivadoEncerradoComAditivoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
        cadastroAditivoContratoEntePrivado(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
    }
    public void cadastrarContratoEntePrivadoNaoIniciadoComAditivoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
        cadastroAditivoContratoEntePrivado(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
    }
    public void cadastrarContratoEntePrivadoVigenteComAditivoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos){
        clicarAcessoContrato();
        clicarBotaoCadastrarContrato();
        cadastraDadosCoop(contratoDesecentralizadoModal);
        cadastraDadosEscolaEProfessor(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getId() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
        cadastroAditivoContratoEntePrivado(contratoDesecentralizadoModal,idContratos);
        enviarArquivo("//input[@id='contrato-arquivos-upload-assinado-" + contratoDesecentralizadoModal.getIdAditivoDistrato() +"']","src/test/resources/contratoassinado.pdf",contratoDesecentralizadoModal,idContratos);
    }


    private void clicarAcessoContrato() {
        open(DEV+"contratos");
    }

    private void clicarBotaoCadastrarContrato() {
        clicarElementoPorXpath("//span[text()='Cadastrar Contrato']");
        clicarElementoPorXpath("//span[text()='Contrato Ente Privado']");
    }

    private void cadastraDadosCoop(ContratoDesecentralizadoModal contratoDesecentralizadoModal) {
        clicarElementoPorXpath("//button[@label='Iniciar Contratação']");
        clicarElementoPorId("contrato-dados-cooperativa-email");
    definirValorPorId("contrato-dados-cooperativa-email","stefany_eduarda@terceiros.sicredi.com.br");
    clicarElementoPorXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]");
    definirValorPorXpath("//div[@id='contrato-dados-cooperativa-cooperativas']/div[1]/div[1]/div[1]/div[1]/div/div/input",contratoDesecentralizadoModal.getCoop());
    clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]");
    clicarElementoPorXpath("//button[@label='Próxima']");

    }

    private void cadastraDadosEscolaEProfessor(ContratoDesecentralizadoModal contratoDesecentralizadoModal, IdContratos idContratos) {
        clicarElementoPorXpath("//span[text()='Adicionar escola']");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input",contratoDesecentralizadoModal.getEstado());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input",contratoDesecentralizadoModal.getCidade());
        clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]");
        esperar(1000);
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]/div/div/input",contratoDesecentralizadoModal.getEscola());
        clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]");
        clicarElementoPorId("contrato-dados-contrato-find-school-modal-ok");
        clicarElementoPorXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='contrato-dados-contrato-professorgestor']/div[1]/div[1]/div[1]/div[1]/div/div/input",contratoDesecentralizadoModal.getProfessorGestor());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorId("contrato-dados-contrato-data-inicio");
        definirValorPorId("contrato-dados-contrato-data-inicio",contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorId("contrato-dados-contrato-data-fim");
        definirValorPorId("contrato-dados-contrato-data-fim",contratoDesecentralizadoModal.getDataFim());
        clicarElementoPorXpath("//input[@data-indeterminate='false']");
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");
        esperar(2000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
    }


    private void enviarArquivo(String fileInputXpath, String filePath,ContratoDesecentralizadoModal contratoDesecentralizadoModal,IdContratos idContratos) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);

    }
    private void cadastroAditivoContratoEntePrivado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,
                                                    IdContratos idContratos){
        esperar(1000);
        clicarElementoPorId("contrato-arquivos-criar-aditivo");
        clicarElementoPorXpath("//button[@label='Próxima']");
        clicarElementoPorId("contrato-dados-contrato-data-inicio");
        definirValorPorId("contrato-dados-contrato-data-inicio",contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorId("contrato-dados-contrato-data-fim");
        definirValorPorId("contrato-dados-contrato-data-fim",contratoDesecentralizadoModal.getDataFim());
        clicarElementoPorXpath("//input[@data-indeterminate='false']");
        clicarElementoPorXpath("//button[@label='Concluir e Avançar Etapa']");
        esperar(2000);
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setIdAditivoDistrato(idContratos.idContratoUrlAditivo(urlAtual));

    }
    private void cadastroDistratoContratoEntePrivado(ContratoDesecentralizadoModal contratoDesecentralizadoModal){
        clicarElementoPorId("contrato-arquivos-criar-distrato");
        clicarElementoPorXpath("//button[@label='Próxima']");

    }


    private void clicarElementoPorXpath(String xpath) {
        $(byXpath(xpath)).click();
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
        $(byId(id)).shouldBe(exist, Duration.ofSeconds(timeoutSeconds));
    }

    private void verificarElementoExistentePorXpath(String xpath) {
        $(byXpath(xpath)).shouldBe(exist);
    }

    private void esperar(int milliseconds) {
        sleep(milliseconds);
    }


}
