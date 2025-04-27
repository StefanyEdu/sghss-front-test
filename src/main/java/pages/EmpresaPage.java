package pages;

import com.codeborne.selenide.Condition;

import lombok.Getter;
import lombok.Setter;
import modal.ContratoCentralizadoModal;
import modal.ContratoDesecentralizadoModal;
import utils.GeradorNomeDataRg;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

@Setter
@Getter
public class EmpresaPage {
    GeradorNomeDataRg geradorNomeDataRg = new GeradorNomeDataRg();
    String nome_empresa;
    String empresa = "Empresa automação ";
    String cnpj;

    public String nomeDaEmpresa() {
        setNome_empresa(empresa + geradorNomeDataRg.geradorNomes());
        return getNome_empresa();
    }


    public void acessoEmpresas() {
        open(DEV+"empresas");
    }

    public void botaocadastroEmpresa() {
        $(byXpath("//span[text()='Cadastrar Empresa']")).click();
    }

    public void cadastroEmpresa(String estado, String cidade) {
        setCnpj(geradorNomeDataRg.geradorCnpj(false));
        $(byId("empresa-edit-modal-nome")).click();
        $(byId("empresa-edit-modal-nome")).val(nomeDaEmpresa());
        $(byId("empresa-edit-modal-cnpj-empresa")).click();
        $(byId("empresa-edit-modal-cnpj-empresa")).val(getCnpj());
        $(byId("empresa-edit-modal-cep")).click();
        $(byId("empresa-edit-modal-cep")).val("91060900");
        $(byId("empresa-edit-modal-endereco")).click();
        $(byId("empresa-edit-modal-endereco")).val("Av. Assis Brasil");
        $(byId("empresa-edit-modal-numero")).click();
        $(byId("empresa-edit-modal-numero")).val("3940");
        $(byId("empresa-edit-modal-bairro")).click();
        $(byId("empresa-edit-modal-bairro")).val("São Sebastião");
        $(byXpath("//div[@id='empresa-edit-modal-estado']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='empresa-edit-modal-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(estado);
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).doubleClick();
        $(byXpath("//div[@id='empresa-edit-modal-cidade']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='empresa-edit-modal-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .shouldBe(Condition.visible, Duration.ofSeconds(60))
                .val(cidade);
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).doubleClick();


    }

    public void botaoCadastro() {
        $(byId("empresa-edit-modal-save")).click();
    }

    public void filtroEstado(String estado) {
        $(byXpath("//input[@placeholder='Pesquise por nome da empresa, cnpj ou cidade...']")).doubleClick();
        $(byXpath("(//label[text()='Estados']/following::input)[1]")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(estado).click();
        $(byText(estado)).click();
    }

    public void filtroCidade(String cidade) {
        $(byXpath("//input[@placeholder='Pesquise por nome da empresa, cnpj ou cidade...']")).doubleClick();
        $(byXpath("(//label[text()='Cidades']/following::input)[1]")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(cidade);
        $(byText(cidade)).doubleClick();
    }

    public void filtroAssessorPedagogico(String assessor) {
        $(byXpath("//input[@placeholder='Pesquise por nome da empresa, cnpj ou cidade...']")).doubleClick();
        $(byId("assessor-filter-empresas")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(assessor);
        $(byText(assessor)).doubleClick();
    }

    public void validacaoEstado() {
        $(byXpath("(//p[text()='45.457.666/0001-01'])[2]")).shouldBe(Condition.exist);
    }

    public String validacaoCidade() {
      return  $(byXpath("((//p[@class='escola-item-label escola-item-label-name'])[2]")).innerText();
    }

    public void validacaoEmpreseVinculoAssessor() {
        $(byXpath("(//p[text()='87.676.262/0001-59'])[2]")).shouldBe(Condition.exist);

    }

    public void vinculoEmpresaAssessor(AssessorPedagógicoPage assessorPedagógicoPage, String coop, String formacao) {

        assessorPedagógicoPage.acessoTelaAssessor();
        assessorPedagógicoPage.botaoCadastrarAssessor();
        assessorPedagógicoPage.setCoop(coop);
        assessorPedagógicoPage.setFormacaoAcademica(formacao);
        assessorPedagógicoPage.setEmpresa(getNome_empresa());
        assessorPedagógicoPage.setQuantidadePrograma(1);
        assessorPedagógicoPage.setProgramaEducacionaisCooperativaEscolares("COOPERATIVAS ESCOLARES");
        assessorPedagógicoPage.campoCadastroAssessor();

    }
    public void vinculoEmpresaContratoDescentralizado(ContratoDesecentralizadoModal contratoDesecentralizadoModal){
        contratoDesecentralizadoModal.setEmpresa(getNome_empresa());
    }
    public void vinculoEmpresaContratoCentralizado(ContratoCentralizadoModal contratoCentralizadoModal){
        contratoCentralizadoModal.setEmpresa(getNome_empresa());
    }
}
