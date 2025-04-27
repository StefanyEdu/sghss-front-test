package pages;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import lombok.Setter;
import utils.GeradorNomeDataRg;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

@Getter
@Setter
public class ProfessorPage {

    String estado;
    String cidade;
    String escola;
    String nomeProfessor = "Professor Automação";

    public void caminhoTelaProfessor() {
        open(DEV+"professores");
    }

    public void botaoCadastro() {
        $(byXpath("//span[text()='Cadastrar Professor']")).click();
    }
    public void botaoCadastrarAutoCadastro() {
        $(byXpath("//button[@label='Cadastrar']")).click();
    }

    public String nomeProfessor(GeradorNomeDataRg geradorNomeDataRg) {
        setNomeProfessor(nomeProfessor+geradorNomeDataRg.geradorNomes());
        return getNomeProfessor();

    }

    public void cadastroProfessor(GeradorNomeDataRg geradorNomeDataRg) {
        $(byId("professor-edit-modal-nome")).click();
        $(byId("professor-edit-modal-nome")).val(nomeProfessor(geradorNomeDataRg));
        $(byId("professor-edit-modal-email")).click();
        $(byId("professor-edit-modal-email")).val("stefany_eduarda@terceiros.sicredi.com.br");
        $(byId("professor-edit-modal-data-nascimento")).click();
        $(byId("professor-edit-modal-data-nascimento")).val("11112022");
        $(byId("professor-edit-modal-celular")).click();
        $(byId("professor-edit-modal-celular")).val("51987847382");
        $(byId("professor-edit-modal-cpf")).click();
        $(byId("professor-edit-modal-cpf")).val(geradorNomeDataRg.geraCPF());


    }

    public void vinculoEscolaProfessor() {
        $(byXpath("(//button[@label='Adicionar escolas']//span)[1]")).click();
        $(byXpath("//div[@id='professor-edit-modal-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='professor-edit-modal-find-school-modal-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(estado);
        $(byXpath("(//div[@class='new-auto-complete-field__menu-list css-11unzgr']//div)[1]")).doubleClick();

        $(byXpath("//div[@id='professor-edit-modal-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='professor-edit-modal-find-school-modal-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(cidade);
        $(byXpath("(//div[@class='new-auto-complete-field__menu-list css-11unzgr']//div)[1]")).doubleClick();
        $(byXpath("//div[@id='professor-edit-modal-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='professor-edit-modal-find-school-modal-escola']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(escola);
        $(byXpath("(//div[@class='new-auto-complete-field__menu-list css-11unzgr']//div)[1]")).doubleClick();
        $(byXpath("//span[text()='Selecionar escola']")).click();
    }

    public void vinculoProfessorGestor() {
        $(byXpath("//div[@id='professor-edit-modal-escolas-gestor']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='professor-edit-modal-escolas-gestor']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(escola);
        $(byXpath("(//div[contains(@class,'new-auto-complete-field__menu-list new-auto-complete-field__menu-list--is-multi')]//div)[1]")).click();
        $(byId("professor-edit-modal-celular")).click();
    }

    public void botãoCadastro() {
        $(byXpath("//span[text()='Cadastrar']")).click();
    }

    public void botaoAutoCadastro() {
        $(byXpath("//span[text()[normalize-space()='Cadastre-se']]")).shouldBe(Condition.exist, Duration.ofSeconds(90)).click();
    }
}
