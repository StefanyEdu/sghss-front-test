package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import modal.ContratoDesecentralizadoModal;
import utils.GeradorDatas;
import utils.IdContratos;
import utils.ScreenshotHelper;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static constans.RotasDaFuncionalidade.CONTRATO_ENTE_PUBLICO_DESCENTRALIZADO;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

public class ContratoDescentralizadoEntePublicoPage {
    ScreenshotHelper screenshotHelper = new ScreenshotHelper();


    public void cadastroContratoEdicao(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        esperar(1000);
        clicarElementoPorXpath("(//button[@type='button'])[5]");
        clicarElementoPorXpath("//li[text()='Desativar']");
    }

    public void cadastroContratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Encerrado");
        limpaBaseContrato();
    }

    public void cadastroContratoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Vigente");
        limpaBaseContrato();
    }

    public void cadastroContratoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Não iniciado");
        limpaBaseContrato();
    }

    public void cadastroContratoAguardandoExtrato(ContratoDesecentralizadoModal contratoDesecentralizadoModal,
                                                  IdContratos idContratos, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContratoAguardandoExtrato("Aguardando extrato");
        String urlAtual = WebDriverRunner.url();
        contratoDesecentralizadoModal.setId(idContratos.idContratoUrl(urlAtual));
    }
    public void limpaBaseDepoisNotificacao(ContratoDesecentralizadoModal contratoDesecentralizadoModal){
        open(DEV+CONTRATO_ENTE_PUBLICO_DESCENTRALIZADO+"edicao-de-contratos/"+contratoDesecentralizadoModal.getId()+"/documentos" );
        esperar(300);
        limpaBaseContrato();
    }

    public void cadastroContratoEncerradoComAditivoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas){
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Encerrado");
        contratoDesecentralizadoModal.setDataFim(new GeradorDatas().dataFimContratoNaoIniciado());
        cadastrarAditivo("Aguardando extrato",contratoDesecentralizadoModal,"2","2","Vigente");
        limpaBaseContratoAditivo("2");
        limpaBaseContrato();

    }

    public void cadastroContratoVigenteComAditivoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas){
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Vigente");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataInicioContratoNaoIniciado());
        contratoDesecentralizadoModal.setDataFim(new GeradorDatas().dataFimContratoNaoIniciado());
        cadastrarAditivo("Aguardando extrato",contratoDesecentralizadoModal,"2","2","Não iniciado");
        limpaBaseContratoAditivo("2");
        limpaBaseContrato();

    }

    public void cadastroContratoNaoIniciadoComAditivoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Não iniciado");
        cadastrarAditivo("Aguardando extrato",contratoDesecentralizadoModal,"2","2","Não iniciado");
        limpaBaseContratoAditivo("2");
        limpaBaseContrato();
    }

    public void cadastroContratoVigenteComAditivoVigente(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas){
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Vigente");
        cadastrarAditivo("Aguardando extrato",contratoDesecentralizadoModal,"2","2","Vigente");
        limpaBaseContratoAditivo("2");
        limpaBaseContrato();

    }
    public void cadastroContratoVigenteComAditivoVigenteComDistratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas){
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Vigente");
        cadastrarAditivo("Aguardando extrato",contratoDesecentralizadoModal,"2","2","Vigente");
        cadastrarDistrato(contratoDesecentralizadoModal,"Encerrado","3","3","2");
        limpaBaseContratoDistrato("3");
        limpaBaseContratoAditivo("2");
        limpaBaseContrato();

    }

    public void cadastroContratoNaoIniciadoComAditivoNaoIniciadoDistratoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Não iniciado");
        cadastrarAditivo("Aguardando extrato",contratoDesecentralizadoModal,"2","2","Não iniciado");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataInicioContratoNaoIniciado());
        cadastrarDistrato(contratoDesecentralizadoModal, "Não iniciado", "3", "3", "2");
        limpaBaseContratoDistrato("3");
        limpaBaseContratoAditivo("2");
        limpaBaseContrato();
    }



    public void cadastroContratoVigenteComDistratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal,
                                                            Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Vigente");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataEventoInicio());
        cadastrarDistrato(contratoDesecentralizadoModal, "Encerrado", "2", "2", "1");
        limpaBaseContratoDistrato("2");
        limpaBaseContrato();
    }

    public void cadastroContratoNaoIniciadoComDistratoNaoIniciado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContrato("Aguardando extrato", "Não iniciado");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataInicioContratoNaoIniciado());
        cadastrarDistrato(contratoDesecentralizadoModal, "Não iniciado", "2", "2", "1");
        limpaBaseContratoDistrato("2");
        limpaBaseContrato();
    }


    public void cadastroContratoAguardandoExtratoComDistratoEncerrado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas){
        clicarBotaoCadastrarContrato();
        cadastroDadosCoop(contratoDesecentralizadoModal);
        cadastroDadosCidadeEstado(contratoDesecentralizadoModal, programas);
        cadastrarDocumentosContratoAguardandoExtrato("Aguardando extrato");
        contratoDesecentralizadoModal.setDataInicio(new GeradorDatas().dataEventoInicio());
        cadastrarDistrato(contratoDesecentralizadoModal, "Encerrado", "2", "2", "1");
        limpaBaseContratoDistrato("2");
        limpaBaseContrato();

    }


    private void clicarBotaoCadastrarContrato() {
        open(DEV + "contratos");
        clicarElementoPorXpath("//span[text()='Cadastrar Contrato']");
        clicarElementoPorXpath("//span[text()='Contrato Ente Público']");
        screenshotHelper.takeScreenshot("Contrato descentralizado");
    }

    private void cadastroDadosCoop(ContratoDesecentralizadoModal contratoDesecentralizadoModal) {
        clicarElementoPorNome("emailCoop");
        definirValorPorNome("emailCoop", "stefany_eduarda@sicredi.com.br");
        clicarElementoPorXpath("//div[@id='cooperativas']/div[1]/div[1]/div[1]/div");
        definirValorPorXpath("//div[@id='cooperativas']/div[1]/div[1]/div[1]/div/div/div/input", contratoDesecentralizadoModal.getCoop());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        screenshotHelper.takeScreenshot("Dados da cooperativa");
        clicarElementoPorXpath("//button[@label='Prosseguir']");


    }

    private void cadastroDadosCidadeEstado(ContratoDesecentralizadoModal contratoDesecentralizadoModal, Integer programas) {
        clicarElementoPorXpath("(//label[text()='Programas educacionais*']/following::input)[1]");
        screenshotHelper.takeScreenshot("programas educacionais");
        programasEducacionais(programas);
        esperar(700);
        clicarElementoPorIdDuploClick("estado");
        definirValorPorXpath("//*[@id='estado']/div/div/div/div/div/div/input", contratoDesecentralizadoModal.getEstado());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorId("cidade");
        definirValorPorXpath("//*[@id='cidade']/div/div/div/div/div/div/input", contratoDesecentralizadoModal.getCidade());
        esperar(100);
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        screenshotHelper.takeScreenshot("Dados do contrato");
        clicarElementoPorIdDuploClick("data-inicio");
        definirValorPorId("data-inicio", contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorId("data-fim");
        definirValorPorId("data-fim", contratoDesecentralizadoModal.getDataFim());
        clicarElementoPorXpath("//input[@type='checkbox']");
        screenshotHelper.takeScreenshot("Contrato preenchimento");
        clicarElementoPorXpath("//button[@label='Prosseguir']");
        verificarElementoVisivelPorXpath("(//span[text()='Em Edição'])[1]", 30);


    }

    private void programasEducacionais(Integer programas) {
        if (programas == 1) {
            clicarElementoPorXpath("//li[text()='A União Faz a Vida']");
        } else if (programas == 2) {
            clicarElementoPorXpath("//li[text()='A União Faz a Vida']");
            clicarElementoPorXpath("//li[text()='Cooperativas Escolares']");

        } else if (programas == 3) {
            clicarElementoPorXpath("//li[text()='Cooperativas Escolares']");
            clicarElementoPorXpath("//li[text()='Jornada da Educação Financeira nas Escolas']");

        } else if (programas == 4) {
            clicarElementoPorXpath("//li[text()='A União Faz a Vida']");
            clicarElementoPorXpath("//li[text()='Cooperativas Escolares']");
            clicarElementoPorXpath("//li[text()='Jornada da Educação Financeira nas Escolas']");
        }


    }


    private void cadastrarDocumentosContrato(String statusContrato, String statusContratoFinal) {
        esperar(5000);
        clicarElementoPorXpath("(//button[@type='button'])[5]");
        clicarElementoPorXpath("//li[text()='Documentos']");
        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Contrato assinado + plano de trabalho']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");

        verificarElementoVisivelPorXpath("(//span[text()='" + statusContrato + "'])[1]", 40);

        clicarElementoPorXpath("(//button[@type='button'])[5]");
        clicarElementoPorXpath("//li[text()='Documentos']");
        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Extrato']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");

        verificarElementoVisivelPorXpath("(//span[text()='" + statusContratoFinal + "'])[1]", 40);
        screenshotHelper.takeScreenshot("Contrato " + statusContratoFinal);

    }

    private void cadastrarDocumentosContratoAguardandoExtrato(String statusContrato) {
        esperar(5000);
        clicarElementoPorXpath("(//button[@type='button'])[5]");
        clicarElementoPorXpath("//li[text()='Documentos']");
        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Contrato assinado + plano de trabalho']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");
        verificarElementoVisivelPorXpath("(//span[text()='" + statusContrato + "'])[1]", 40);

    }


    private void enviarArquivo(String fileInputXpath, String filePath) {
        File arquivo = new File(filePath);
        SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
        fileInput.uploadFile(arquivo);
        esperar(9000);

    }

    private void cadastrarAditivo(String statusContrato,ContratoDesecentralizadoModal contratoDesecentralizadoModal,String botaoDocumento,String parametroBotao, String statusContratoFinal){
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
        clicarElementoPorXpath("(//li[text()='Documentos'])[" + botaoDocumento + "]");

        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Aditivo assinado + plano de trabalho']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");

        verificarElementoVisivelPorXpath("(//span[text()='" + statusContrato + "'])[1]", 40);

        clicarElementoPorXpath("(//button[@id='actions'])[" + parametroBotao + "]");
        clicarElementoPorXpath("(//li[text()='Documentos'])[" + botaoDocumento + "]");
        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Extrato']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");
        verificarElementoVisivelPorXpath("(//span[text()='" + statusContratoFinal + "'])[1]", 40);
        screenshotHelper.takeScreenshot("Aditivo "+statusContratoFinal);
    }


    private void cadastrarDistrato(ContratoDesecentralizadoModal contratoDesecentralizadoModal, String statusContrato, String parametroBotao, String botaoDocumento, String campo) {
        clicarElementoPorXpath("//button[@label='Criar distrato']");
        clicarElementoPorXpathDuploClick("(//input[@id='data-distrato'])[" + campo + "]");
        esperar(10);
        definirValorPorXpath("(//input[@id='data-distrato'])[" + campo + "]", contratoDesecentralizadoModal.getDataInicio());
        clicarElementoPorXpath("(//span[text()='Concluir'])[" + parametroBotao + "]");
        verificarElementoVisivelPorXpath("//div[text()='Confirmar a criação de distrato?']", 60);
        clicarElementoPorId("contratos-modal-confirm__confirm");
        clicarElementoPorXpath("(//button[@id='actions'])[" + parametroBotao + "]");
        clicarElementoPorXpath("(//li[text()='Documentos'])[" + botaoDocumento + "]");

        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Distrato assinado + plano de trabalho']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");

        verificarElementoVisivelPorXpath("(//span[text()='" + statusContrato + "'])[1]", 40);

        clicarElementoPorXpath("(//button[@id='actions'])[" + parametroBotao + "]");
        clicarElementoPorXpath("(//li[text()='Documentos'])[" + botaoDocumento + "]");
        clicarElementoPorXpath("//label[text()='Tipos de documentos*']/following::input");
        clicarElementoPorXpath("//li[text()='Extrato']");
        screenshotHelper.takeScreenshot("modal para upload dos arquivos");
        enviarArquivo("//input[@type='file']", "src/test/resources/aditivo.pdf");
        screenshotHelper.takeScreenshot("Anexo do documentos");
        clicarElementoPorXpath("//span[text()='Salvar']");
        verificarElementoVisivelPorXpath("(//span[text()='" + statusContrato + "'])[1]", 40);

        screenshotHelper.takeScreenshot("Distrato " + statusContrato);
    }

    private void limpaBaseContrato() {
        clicarElementoPorXpath("(//button[@type='button'])[5]");
        clicarElementoPorXpath("//li[text()='Excluir documentos']");
        clicarElementoPorXpath("//span[text()='Sim']");
        clicarElementoPorXpath("//li[text()='Desativar']");
    }

    private void limpaBaseContratoAditivo(String parametroBotao) {
        clicarElementoPorXpath("(//button[@id='actions'])[" + parametroBotao + "]");
        clicarElementoPorXpath("//li[text()='Excluir documentos']");
        clicarElementoPorXpath("//span[text()='Sim']");
        clicarElementoPorXpath("//li[text()='Desativar aditivo']");
    }


    private void limpaBaseContratoDistrato(String parametroBotao) {
        clicarElementoPorXpath("(//button[@id='actions'])[" + parametroBotao + "]");
        clicarElementoPorXpath("//li[text()='Excluir documentos']");
        clicarElementoPorXpath("//span[text()='Sim']");
        clicarElementoPorXpath("//li[text()='Desativar distrato']");
    }




    private void clicarElementoPorXpath(String xpath) {
        $(byXpath(xpath)).click();
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
    private void limparElementoPorId(String id) {
        $(byId(id)).clear();
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

    private void verificarElementoVisivelPorXpath(String xpath, Integer timeoutSeconds) {
        $(byXpath(xpath)).shouldBe(visible, Duration.ofSeconds(timeoutSeconds));
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
