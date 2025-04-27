package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import lombok.Setter;
import utils.GeradorNomeDataRg;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

@Getter
@Setter
public class AssessorPedagógicoPage {
    GeradorNomeDataRg geradorNomeDataRg = new GeradorNomeDataRg();
    String empresa;
    String formacaoAcademica;
    String coop;
    String nomeAssessor= "Assessor Automação ";
    String programaEducacionaisCooperativaEscolares;
    String programaEducacionaisPufv;
    String programaEducacionaisJornadaDeEducacaoFinanceira;
    Integer quantidadePrograma;

    public String nomeAssessor() {
        setNomeAssessor(nomeAssessor + geradorNomeDataRg.geradorNomes());
        return getNomeAssessor();
    }

    public void acessoTelaAssessor() {
        open(DEV+"assessores");
    }

    public void botaoCadastrarAssessor() {
        $(byXpath("//span[text()='Cadastrar Assessor Pedagógico']")).shouldBe(Condition.exist, Duration.ofSeconds(120)).click();
    }

    public void campoCadastroAssessor() {
        $(byId("assessor-edit-modal-nome")).click();
        $(byId("assessor-edit-modal-nome")).val(nomeAssessor());
        $(byId("assessor-edit-modal-celular")).click();
        $(byId("assessor-edit-modal-celular")).val("51987465374");
        $(byId("assessor-edit-modal-email")).click();
        $(byId("assessor-edit-modal-email")).val("stefany_eduarda@terceiros.sicredi.com.br");
        $(byId("assessor-edit-modal-data-nascimento")).click();
        $(byId("assessor-edit-modal-data-nascimento")).setValue(geradorNomeDataRg.dataNascimento());
        $(byId("assessor-edit-modal-cpf")).click();
        $(byId("assessor-edit-modal-cpf")).val(geradorNomeDataRg.geraCPF());
        programaEducacionais();

        $(byXpath("//div[@id='assessor-edit-modal-cooperativas']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='assessor-edit-modal-cooperativas']/../div/div/div/div/div/div/div/input")).val(getCoop());
        $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
        $(byId("assessor-edit-modal-data-nascimento")).click();
        $(byXpath("//div[@id='assessor-edit-modal-cooperativas-gestor']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='assessor-edit-modal-cooperativas-gestor']/div[1]/div[1]/div[1]/div[1]/div/div/input")).val(coop);
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();
        $(byId("assessor-edit-modal-data-nascimento")).click();
        $(byXpath("//div[@id='assessor-edit-modal-formacaoacademica']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='assessor-edit-modal-formacaoacademica']/div[1]/div[1]/div[1]/div[1]/div/div/input")).val(getFormacaoAcademica());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).doubleClick();
        $(byId("assessor-edit-modal-data-nascimento")).click();
        $(byId("assessor-edit-modal-qualificacao")).click();

        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");

        $(byId("assessor-edit-modal-qualificacao")).setValue("Pedagógia");

        $(byXpath("//div[@id='assessor-edit-modal-idempresa']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='assessor-edit-modal-idempresa']/div[1]/div[1]/div[1]/div[1]/div/div/input")).val(getEmpresa());
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).doubleClick();
        $(byXpath("//span[text()='Cadastrar']")).click();

    }
    public void programaEducacionais(){
        if (quantidadePrograma==1){
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div[1]/div[1]/div[1]/div[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div/div/div/div/div/div/input")).val(getProgramaEducacionaisCooperativaEscolares());
            $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
            $(byId("assessor-edit-modal-cpf")).click();
        } else if (quantidadePrograma==2) {
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div[1]/div[1]/div[1]/div[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div/div/div/div/div/div/input")).val(getProgramaEducacionaisPufv());
            $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div[1]/div[1]/div[1]/div[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div/div/div/div/div/div/input")).val(getProgramaEducacionaisCooperativaEscolares());
            $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
            $(byId("assessor-edit-modal-cpf")).click();

        }else if(quantidadePrograma==3){
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div[1]/div[1]/div[1]/div[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div/div/div/div/div/div/input")).val(getProgramaEducacionaisCooperativaEscolares());
            $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div/div/div/div/div/div/input")).val(getProgramaEducacionaisPufv());
            $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
            $(byXpath("//div[@id='assessor-edit-modal-programaseducacionais']/div/div/div/div/div/div/input")).val(getProgramaEducacionaisJornadaDeEducacaoFinanceira());
            $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
            $(byId("assessor-edit-modal-cpf")).click();
        }
    }

    public void filtroSituacaoAtivado(String situacao) {
        $(byXpath("//input[@placeholder='Todas as situações']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(situacao);
        $(byText(situacao)).click();

    }

    public void filtroSituacaoInativado(String situacao) {
        $(byXpath("//input[@placeholder='Todas as situações']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(situacao);
        $(byText(situacao)).click();

    }

    public void filtroEstado(String estado) {
        $(byXpath("//input[@placeholder='Pesquise por nome do assessor pedagógico e cpf...']")).doubleClick();
        $(byXpath("(//label[text()='Estados']/following::input)[1]")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(estado).click();
        $(byText(estado)).click();
    }

    public void filtroCidade(String cidade) {
        $(byXpath("//input[@placeholder='Pesquise por nome do assessor pedagógico e cpf...']")).doubleClick();
        $(byXpath("(//label[text()='Cidades']/following::input)[1]")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(cidade).click();
        $(byText(cidade)).click();
    }

    public void filtroEscola(String escola) {
        $(byXpath("//input[@placeholder='Pesquise por nome do assessor pedagógico e cpf...']")).doubleClick();
        $(byXpath("//label[text()='Escola']/following::input")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(escola).click();
        $(byText(escola)).click();
    }

    public void filtroFormacaoAcademica(String formacao) {
        $(byXpath("(//label[text()='Formação acadêmica']/following::input)[1]")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(formacao).click();
        $(byText(formacao)).click();
    }

    public void validarTodasSituacoes() {
        $(byXpath("//input[@placeholder='Pesquise por nome do assessor pedagógico e cpf...']")).doubleClick();
        $(byXpath("//input[@placeholder='Todas as situações']")).click();
        $(byText("Ativado")).shouldBe(Condition.visible);
        $(byText("Inativado")).shouldBe(Condition.visible);
    }

    public void validacaoAssessoreAtivos() {
   $(byXpath("(//p[@class='assessor-item-label assessor-item-label-name'])[2]")).shouldBe(Condition.exist);
    }

    public void validacaoAssessoresInativos() {
        $(byText("Assessor P Abelão")).exists();
        $(byText("Assessora Suellen")).exists();
    }

    public void validacaoAssessorEstado() {
        $(byXpath("(//p[@class='assessor-item-label assessor-item-label-name'])[2]")).shouldBe(Condition.exist);
    }

    public void validacaoAssessorEstadoCidadeEscola() {
        $(byText("Prof Selina")).shouldBe(Condition.exist);
        $(byText("Teste Disseste")).shouldBe(Condition.exist);
    }

    public void validacaoAssessorFormacaoAcademica() {
        $(byXpath("(//p[@class='assessor-item-label assessor-item-label-name'])[2]")).shouldBe(Condition.exist);

    }


    public void camposRemovidoCadastro() {
        //$(byId("assessor-edit-modal-mae")).click();
        // $(byId("assessor-edit-modal-mae")).val("mae da silva");
        //$(byId("assessor-edit-modal-rg")).click();
        // $(byId("assessor-edit-modal-rg")).val(geradorNomeDataRg.geraRG());

    }

}


