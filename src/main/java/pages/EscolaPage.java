package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import lombok.Setter;
import modal.EscolaModal;
import modal.PeriodoModal;
import org.openqa.selenium.WebElement;
import utils.GeradorDatas;
import utils.GeradorNomeDataRg;
import utils.ScreenshotHelper;
import com.codeborne.selenide.SelenideElement;
import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Getter
@Setter
public class EscolaPage {
    private String nomeEscola = "Teste de escola";
    private Integer modalidade;
    private Integer programas;

    private ScreenshotHelper screenshotHelper = new ScreenshotHelper();
    private static final String CADASTRAR_COOP_BUTTON = "//p[text()='Cadastrar cooperativas escolares']/following-sibling::button";
    private static final String NOME_COOP_INPUT = "nomeCooperativaEscolar";
    private static final String DATA_FUNDACAO_INPUT = "dataFundacao_automacao_id";
    private static final String SUBMIT_BUTTON = "form-add-coop__btn-save-automacao-id";
    private static final String CADASTRO_ANO_BUTTON = "(//div[@class='custom-route-button__container-unit']//button)[2]";
    private static final String CADASTRO_ANO_MODAL = "(//div[text()='Cadastro de dados do ano do Cooperativas Escolares'])[1]";
    private static final String FILE_INPUT_0 = "//input[@type='file' and @class='0']";
    private static final String FILE_INPUT_1 = "//input[@type='file' and @class='1']";
    private static final String FILE_INPUT_2 = "//input[@type='file' and @class='2']";
    private static final String FILE_INPUT_3 = "//input[@type='file' and @class='3']";
    private static final String BUTTON_ANO_IMPLEMENTACAO = "anoImplementacao";
    private static final String ABA_COOPESCOLAR = "//span[text()='Cooperativas Escolares']";

    public void cadastroCooperativaEscolar(String nomeCooperativaEscolar, String dataFuncacao, String ano) {
        try {
            cadastroAnoCooperativaEscolares(ano);
            clicarBotaoCadastrarCooperativa();
            preencherDadosCooperativa(nomeCooperativaEscolar, dataFuncacao);
            enviarArquivo(FILE_INPUT_0, "src/test/resources/teste.pdf");
            enviarArquivo(FILE_INPUT_1, "src/test/resources/teste.pdf");
            enviarArquivo(FILE_INPUT_2, "src/test/resources/teste.pdf");
            enviarArquivo(FILE_INPUT_3, "src/test/resources/teste.pdf");
            finalizarCadastro();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clicarBotaoCadastrarCooperativa() {
        clicarElementoPorXpath(CADASTRAR_COOP_BUTTON);
    }


    private void preencherDadosCooperativa(String nomeCooperativaEscolar, String dataFuncacao) {
        clicarElementoPorNome(NOME_COOP_INPUT);
        definirValorPorNome(NOME_COOP_INPUT, nomeCooperativaEscolar);
        exibirElementoPorId(DATA_FUNDACAO_INPUT);
        clicarElementoPorId(DATA_FUNDACAO_INPUT);
        definirValorPorId(DATA_FUNDACAO_INPUT, dataFuncacao);
        esperar(9000);
    }

    private void enviarArquivo(String fileInputXpath, String filePath) {
            File arquivo = new File(filePath);
            SelenideElement fileInput = $(byXpath(fileInputXpath)).shouldBe(exist);
            fileInput.uploadFile(arquivo);
            esperar(9000);

    }

    private void finalizarCadastro() {
        esperar(9000);
        clicarElementoPorXpath("(//label[text()='Nome da cooperativa escolar*']/following::input)[1]");
        screenshotHelper.takeScreenshot("Informações da coop e documentos");
        esperar(8000);
        exibirElementoPorId(SUBMIT_BUTTON);
        clicarElementoPorId("form-add-coop__btn-save-automacao-id");
        esperar(9000);
        screenshotHelper.takeScreenshot("Cadastro da coop escolar");
        clicarElementoPorXpath(CADASTRO_ANO_BUTTON);
        verificarElementoVisivelPorXpath(CADASTRO_ANO_MODAL);
    }

    public void clicarBotaoEscola() {
        clicarElementoPorXpath("(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')])[1]");
        clicarElementoPorId("left-sidebar-escolas");
        clicarElementoPorXpath("//span[text()='Cadastrar Escola']");
    }

    public String gerarNomeEscolaAutomacao(GeradorNomeDataRg geradorNomeDataRg) {
        setNomeEscola(nomeEscola + " " + geradorNomeDataRg.geradorNomes());
        return getNomeEscola();
    }

    public void cadastrarEscola(GeradorNomeDataRg geradorNomeDataRg, GeradorDatas geradorDatas, EscolaModal escolaModal) {
        preencherDadosEscola(geradorNomeDataRg, escolaModal);
        preencherEnderecoEscola(escolaModal);
        screenshotHelper.takeScreenshot("Cadastro da escola");
        clicarElementoPorId("escola-edit-save");
        clicarElementoPorId("confirm-modal-confirm");
    }

    private void preencherDadosEscola(GeradorNomeDataRg geradorNomeDataRg, EscolaModal escolaModal) {
        definirValorPorId("escola-edit-nome-fantasia", gerarNomeEscolaAutomacao(geradorNomeDataRg));
        definirValorPorId("escola-edit-razao-social", "Teste de razao");
        clicarElementoPorId("escola-edit-cnpj");
        verificarElementoExistentePorId("escola-edit-cnpj", 60);
        definirValorPorId("escola-edit-cnpj", geradorNomeDataRg.geradorCnpj(false));
        selecionarTipoInstituicao(escolaModal);
        clicarElementoPorId("escola-edit-inep");
        definirValorPorId("escola-edit-inep", geradorNomeDataRg.geraCodigoInep());
        definirValorPorId("escola-edit-telefone", "51982223134");
        clicarElementoPorId("etapasEducacao");
        clicarElementoPorXpath("//li[text()='" + escolaModal.getEtapaEnsino() + "']");
        clicarElementoPorIdDuploClick("escola-edit-cnpj");
        modalidade();
        clicarElementoPorIdDuploClick("escola-edit-telefone");
        definirValorPorId("escola-edit-email", "stefany_eduarda@sicredi.com.br");
        clicarElementoPorXpath("//div[@id='escola-edit-coop']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='escola-edit-coop']/div[1]/div[1]/div[1]/div[1]/div/div/input", escolaModal.getCoop());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
    }

    private void selecionarTipoInstituicao(EscolaModal escolaModal) {
        clicarElementoPorXpath("(//div[contains(@class,'MuiInputBase-input MuiInput-input')]//div)[" + escolaModal.getTipoInstituicao() + "]");
        clicarElementoPorXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[" + escolaModal.getTipoInstituicao() + "]");
    }

    private void preencherEnderecoEscola(EscolaModal escolaModal) {
        definirValorPorId("escola-edit-cep", "91060900");
        definirValorPorId("escola-edit-endereco", "Av. Assis Brasil");
        definirValorPorId("escola-edit-numero", "3940");
        definirValorPorId("escola-edit-bairro", "São Sebastião");
        selecionarEstadoCidade(escolaModal);
    }

    private void selecionarEstadoCidade(EscolaModal escolaModal) {
        clicarElementoPorXpath("//div[@id='escola-edit-estado']/div[1]/div[1]/div[1]/div[1]");
        definirValorPorXpath("//div[@id='escola-edit-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input", escolaModal.getEstado());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
        clicarElementoPorXpath("//div[@id='escola-edit-cidade']/div[1]/div[1]/div[1]/div[1]");
        esperar(1000);
        definirValorPorXpath("//div[@id='escola-edit-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input", escolaModal.getCidade());
        clicarElementoPorXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]");
    }

    private void modalidade() {
        clicarElementoPorXpath("(//span[text()='Modalidade']/following::input)[1]");
        if (modalidade == 1) {
            clicarElementoPorXpath("//li[text()='Rural']");
        } else if (modalidade == 2) {
            clicarElementoPorXpath("//li[text()='Rural']");
            clicarElementoPorXpath("//li[text()='Quilombola']");
        } else if (modalidade == 3) {
            clicarElementoPorXpath("//li[text()='Rural']");
            clicarElementoPorXpath("//li[text()='Quilombola']");
            clicarElementoPorXpath("//li[text()='Indígena']");
            clicarElementoPorXpath("//li[text()='Especializada']");
        }
    }

    public void programasEducacionais() {
        if (programas == 1) {
            clicarElementoPorNome("uniaoFazAVida");
        } else if (programas == 2) {
            clicarElementoPorNome("cooperativaEscolar", 180);
        } else if (programas == 3) {
            clicarElementoPorNome("jornadaEducacao",90);
        } else if (programas == 4) {
            clicarElementoPorNome("cooperativaEscolar");
            clicarElementoPorNome("jornadaEducacao", 90);
        } else if (programas == 5) {
            clicarElementoPorNome("uniaoFazAVida");
            clicarElementoPorNome("cooperativaEscolar", 90);
            clicarElementoPorNome("jornadaEducacao", 90);
        }
        esperar(9000);
    }

    public void cadastroAnoPufv(String ano) {
        definirValorPorNome("anoImplementacao", ano);
        clicarElementoPorXpath("(//div[@class='custom-route-button__container-unit']//button)[2]");
    }

    private void cadastroAnoCooperativaEscolares(String ano) {
        verificarElementoVisivelPorXpath(ABA_COOPESCOLAR);
        definirValorPorNome(BUTTON_ANO_IMPLEMENTACAO, ano);
    }

    public void cadastroJornadaEducacaoFinanceira(String ano) {
        verificarElementoVisivelPorXpath("//span[text()='Jornada da Educação Financeira nas Escolas']");
        clicarElementoPorXpath("//span[text()='Jornada da Educação Financeira nas Escolas']");
        definirValorPorNome("anoImplementacao", ano);
        clicarElementoPorXpath("(//div[@class='custom-route-button__container-unit']//button)[2]");
    }

    public void cadastroCoopEscolaresJornadaPufv(String ano) {
        verificarElementoVisivelPorXpath("//span[text()='A União Faz a Vida']");
        definirValorPorNome("anoImplementacao", ano);
        verificarElementoVisivelPorXpath("//span[text()='Cooperativas Escolares']");
        clicarElementoPorXpath("//span[text()='Cooperativas Escolares']");
        definirValorPorNome("anoImplementacao", ano);
        cadastroCoopEscolarComOutrosProgramas("Cooperativa Automação" + new GeradorNomeDataRg().geradorNomes(), new GeradorDatas().dataEventoInicio());
        screenshotHelper.takeScreenshot("Cadastro de ano cooperativa escolares");
        esperar(800);
        verificarElementoVisivelPorXpath("//span[text()='Jornada da Educação Financeira nas Escolas']");
        clicarElementoPorXpath("//span[text()='Jornada da Educação Financeira nas Escolas']");
        definirValorPorNome("anoImplementacao", ano);
        screenshotHelper.takeScreenshot("Cadastro ano jornada");
        clicarElementoPorXpath("(//div[@class='custom-route-button__container-unit']//button)[2]");
        screenshotHelper.takeScreenshot("Cadastro dos anos dos programas");
    }

    public void cadastroCoopEscolaresJornada(String ano) {
        verificarElementoVisivelPorXpath("//span[text()='Cooperativas Escolares']");
        definirValorPorNome("anoImplementacao", ano);
        screenshotHelper.takeScreenshot("Cadastro de ano cooperativa escolares");
        verificarElementoVisivelPorXpath("//span[text()='Jornada de Educação Financeira nas Escolas']");
        clicarElementoPorXpath("//span[text()='Jornada de Educação Financeira nas Escolas']");
        definirValorPorNome("anoImplementacao", ano);
        screenshotHelper.takeScreenshot("Cadastro ano jornada");
        clicarElementoPorXpath("(//div[@class='custom-route-button__container-unit']//button)[2]");
        screenshotHelper.takeScreenshot("Cadastro dos anos dos programas");
    }

    public void cadastroPeriodoPufv(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar dados']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-colaboradores", periodoModal.getNumeroColaboradorEscola());
        definirValorPorId("dados-da-escola-edit-edinfantil-professor", periodoModal.getNumeroProfessorEdInfantil());
        definirValorPorId("dados-da-escola-edit-edinfantil-aluno", periodoModal.getNumeroAlunosEdInfantil());
        definirValorPorId("dados-da-escola-edit-ensfundi-professor", periodoModal.getNumeroProfessorEnFundamentalAnoInicias());
        definirValorPorId("dados-da-escola-edit-ensfundi-aluno", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIProf", periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIAlun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorId("dados-da-escola-edit-ensmedio-professor", periodoModal.getNumeroProfessorEnMedio());
        definirValorPorId("dados-da-escola-edit-ensmedio-aluno", periodoModal.getNumeroAlunosEnMedio());
        definirValorPorId("dados-da-escola-edit-especializada-professor", periodoModal.getNumeroProfessorEscolaEspecializada());
        screenshotHelper.takeScreenshot("Finalização do cadastro");
        clicarElementoPorId("dados-da-escola-edit-save");
    }
    public void cadastroPeriodoPufvCamposObrigatorio(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar dados']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-colaboradores", periodoModal.getNumeroColaboradorEscola());
        definirValorPorId("dados-da-escola-edit-edinfantil-professor", periodoModal.getNumeroProfessorEdInfantil());
        definirValorPorId("dados-da-escola-edit-edinfantil-aluno", periodoModal.getNumeroAlunosEdInfantil());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[1]");
        definirValorPorId("dados-da-escola-edit-ensfundi-professor", periodoModal.getNumeroProfessorEnFundamentalAnoInicias());
        definirValorPorId("dados-da-escola-edit-ensfundi-aluno", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[2]");
        definirValorPorNome("ensFundIIProf", periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIAlun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[3]");
        definirValorPorId("dados-da-escola-edit-ensmedio-professor", periodoModal.getNumeroProfessorEnMedio());
        definirValorPorId("dados-da-escola-edit-ensmedio-aluno", periodoModal.getNumeroAlunosEnMedio());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[4]");
        definirValorPorId("dados-da-escola-edit-especializada-professor", periodoModal.getNumeroProfessorEscolaEspecializada());
        screenshotHelper.takeScreenshot("Finalização do cadastro");
    }

    public void filtroTipoInstitucao() {
        clicarElementoPorXpath("//input[@placeholder='Todas as instituições']");
        clicarElementoPorXpath("//input[@placeholder='Pesquisar']");
        definirValorPorXpath("//input[@placeholder='Pesquisar']", "Municipal");
        clicarElementoPorXpath("(//li[@role='menuitem'])[2]");
    }

    public void validarEscolasFiltradas() {
        verificarElementoExistentePorXpath("(//p[text()='Castro Alves'])[2]");
        verificarElementoExistentePorXpath("(//p[text()='Escolinha De Futebol'])[2]");
    }

    public void filtroFiltroEtapaEnsino() {
        clicarElementoPorXpath("//input[@placeholder='Todas as etapas da educação']");
        clicarElementoPorXpath("//input[@placeholder='Pesquisar']");
        definirValorPorXpath("//input[@placeholder='Pesquisar']", "Ensino Fundamental");
        clicarElementoPorXpath("(//li[@role='menuitem'])[3]");
    }

    public void cadastroCoopEscolarComOutrosProgramas(String nomeCooperativaEscolar, String dataFuncacao) {
        clicarElementoPorXpath("//p[text()='Cadastrar cooperativas escolares']/following-sibling::button");
        definirValorPorNome("nomeCooperativaEscolar", nomeCooperativaEscolar);
        exibirElementoPorId("dataFundacao_automacao_id");
        definirValorPorId("dataFundacao_automacao_id", dataFuncacao);
        esperar(9000);
        enviarArquivo("//input[@class='1']", "src/test/resources/teste.pdf");
        enviarArquivo("//input[@class='2']", "src/test/resources/teste.pdf");
        screenshotHelper.takeScreenshot("Informações da coop e documentos");
        clicarElementoPorXpath("//button[@type='submit']");
        screenshotHelper.takeScreenshot("Cadastro da coop escolar");
    }

    public void cadastroAnoCoopEscolar(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar dados']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-colaboradores", periodoModal.getNumeroProfessorOrientadores());
        definirValorPorNome("ensFundIIProf", periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIAlun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorId("dados-da-escola-edit-ensmedio-professor", periodoModal.getNumeroProfessorEnMedio());
        definirValorPorId("dados-da-escola-edit-ensmedio-aluno", periodoModal.getNumeroAlunosEnMedio());
        definirValorPorId("dados-da-escola-edit-especializada-professor", periodoModal.getNumeroProfessorEscolaEspecializada());
        definirValorPorId("dados-da-escola-edit-especializada-aluno", periodoModal.getNumeroAlunosEscolaEspecializada());
        screenshotHelper.takeScreenshot("Finalização do cadastro");
        clicarElementoPorId("dados-da-escola-edit-save");
    }
    public void cadastroAnoCoopEscolarCamposObrigatorio(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar dados']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-colaboradores", periodoModal.getNumeroProfessorOrientadores());
        definirValorPorNome("ensFundIIProf", periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIAlun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[1]");
        definirValorPorId("dados-da-escola-edit-ensmedio-professor", periodoModal.getNumeroProfessorEnMedio());
        definirValorPorId("dados-da-escola-edit-ensmedio-aluno", periodoModal.getNumeroAlunosEnMedio());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[2]");
        definirValorPorId("dados-da-escola-edit-especializada-professor", periodoModal.getNumeroProfessorEscolaEspecializada());
        definirValorPorId("dados-da-escola-edit-especializada-aluno", periodoModal.getNumeroAlunosEscolaEspecializada());
        verificarElementoVisivelPorXpath("(//div[text()='É necessário informar o número de alunos para cada etapa da educação que possua professores.'])[3]");
        screenshotHelper.takeScreenshot("Finalização do cadastro");

    }

    public void cadastroPeriodoJornadaEducacao(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar dados']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-colaboradores", periodoModal.getNumeroColaboradorEscola());
        definirValorPorId("dados-da-escola-edit-ensfundi-professor", periodoModal.getNumeroProfessorEnFundamentalAnoInicias());
        definirValorPorId("dados-da-escola-edit-ensfundi-aluno",periodoModal.getNumeroAlunosEnFundamentalAnosInicias());
        definirValorPorId("dados-da-escola-edit-ensfundii-professor",periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorId("dados-da-escola-edit-ensfundii-aluno",periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorId("dados-da-escola-edit-especializada-professor",periodoModal.getNumeroProfessorEscolaEspecializada());
        definirValorPorId("dados-da-escola-edit-especializada-aluno", periodoModal.getNumeroAlunosEscolaEspecializada());
        screenshotHelper.takeScreenshot("Finalização do cadastro");
        clicarElementoPorId("dados-da-escola-edit-save");
    }

    public void cadastroAnoCoopEscolarComJornada(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar Ano']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        verificarElementoVisivelPorXpath("(//span[text()='Cooperativas Escolares'])[1]");
        clicarElementoPorXpath("(//span[text()='Cooperativas Escolares'])[1]");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-orientadores", periodoModal.getNumeroProfessorOrientadores());
        definirValorPorNome("ensFundIIProf", periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIAlun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorId("dados-da-escola-edit-ensmedio-professor", periodoModal.getNumeroProfessorEnMedio());
        definirValorPorId("dados-da-escola-edit-ensmedio-aluno", periodoModal.getNumeroAlunosEnMedio());
        definirValorPorId("dados-da-escola-edit-especializada-professor", periodoModal.getNumeroProfessorEscolaEspecializada());
        definirValorPorId("dados-da-escola-edit-especializada-aluno", periodoModal.getNumeroAlunosEscolaEspecializada());
        definirValorPorId("dados-da-escola-edit-professornaoespecializado", periodoModal.getNumeroProfessorNaoEspecializada());
        definirValorPorId("dados-da-escola-edit-alunonaoespecializado", periodoModal.getNumeroAlunoNaoEspecializada());
        screenshotHelper.takeScreenshot("Finalização do cadastro");
        clicarElementoPorId("dados-da-escola-edit-save");
    }

    public void cadastroAnoJornadaComCoop(PeriodoModal periodoModal) {
        clicarElementoPorXpath("//span[text()='Cadastrar Ano']");
        screenshotHelper.takeScreenshot("tela de cadastro");
        verificarElementoVisivelPorXpath("(//span[text()='Jornada de Educação Financeira nas Escolas'])[1]");
        clicarElementoPorXpath("(//span[text()='Jornada de Educação Financeira nas Escolas'])[1]");
        esperar(900);
        clicarElementoPorXpath("//div[@id='dados-da-escola-edit-periodo']/div[1]/div[1]/div[1]/div[1]");
        clicarElementoPorXpath("(//div[@role='menuitem'])[1]");
        definirValorPorId("dados-da-escola-edit-colaboradores", periodoModal.getNumeroColaboradorEscola());
        definirValorPorId("dados-da-escola-edit-ensfundiprof", periodoModal.getNumeroProfessorEnFundamentalAnoInicias());
        definirValorPorId("dados-da-escola-edit-ensfundialun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIProf", periodoModal.getNumeroProfessorEnFundamentalAnosFinais());
        definirValorPorNome("ensFundIIAlun", periodoModal.getNumeroAlunosEnFundamentalAnosFinais());
        definirValorPorId("dados-da-escola-edit-especializadaprof", periodoModal.getNumeroProfessorEscolaEspecializada());
        definirValorPorId("dados-da-escola-edit-especializadaalun", periodoModal.getNumeroAlunosEscolaEspecializada());
        screenshotHelper.takeScreenshot("Finalização do cadastro");
        clicarElementoPorId("dados-da-escola-edit-save");
    }

    public void botaoTransicaoCadastroJornadaEducacao() {
        verificarElementoVisivelPorXpath("(//span[text()='Jornada de Educação Financeira nas Escolas'])[1]");
        clicarElementoPorXpath("(//span[text()='Jornada de Educação Financeira nas Escolas'])[1]");
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
        $(byId(id)).shouldBe(Condition.exist, Duration.ofSeconds(timeoutSeconds));
    }

    private void verificarElementoExistentePorXpath(String xpath) {
        $(byXpath(xpath)).shouldBe(Condition.exist);
    }

    private void esperar(int milliseconds) {
        sleep(milliseconds);
    }
}

