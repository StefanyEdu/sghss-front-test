package pages;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import lombok.Setter;
import utils.GeradorNomeDataRg;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Getter
@Setter
public class CoordenadorLocalPage {
    String estado;
    String cidade;
    String coop;
    String nomeCoordenador;

    public void acessoTelaCoordenador() {
        $(byXpath("(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')])[1]")).click();
        $(byXpath("//span[text()='Coordenadores Locais']")).click();
    }

    public void botaoCadastroLocal() {
        $(byXpath("//span[text()='Cadastrar Coordenador Local']")).click();
    }

    public void cadastroCoordenadorLocal(GeradorNomeDataRg geradorNomeDataRg) {
        String cpf = geradorNomeDataRg.geraCPF();
        setNomeCoordenador("Coordenador Local" + " " + geradorNomeDataRg.geradorNomes());
        $(byId("coordenador-edit-modal-nome")).click();
        $(byId("coordenador-edit-modal-nome")).val(getNomeCoordenador());
        $(byId("coordenador-edit-modal-email")).click();
        $(byId("coordenador-edit-modal-email")).val("stefany_eduarda@terceiros.sicredi.com.br");
        $(byId("coordenador-edit-modal-data-nascimento")).click();
        $(byId("coordenador-edit-modal-data-nascimento")).val("11112022");
        $(byId("coordenador-edit-modal-celular")).click();
        $(byId("coordenador-edit-modal-celular")).val("51987654738");
        $(byId("coordenador-edit-modal-cpf")).click();
        $(byId("coordenador-edit-modal-cpf")).val(cpf);
        System.out.println(cpf);
        $(byXpath("//div[@id='cooperativa-id']/div[1]/div[1]/div[1]/div[1]")).click();

        $(byXpath("//div[@id='cooperativa-id']/div[1]/div[1]/div[1]/div[1]/div/div/input")).val(coop);
        //ver com kauan como podemos melhorar o componente de coop,estado e cidade
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();


        $(byXpath("//div[@id='coordenador-edit-modal-estado']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='coordenador-edit-modal-estado']/div[1]/div[1]/div[1]/div[1]/div/div/input")).val(estado);
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();

        $(byXpath("//div[@id='coordenador-edit-modal-cidade']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='coordenador-edit-modal-cidade']/div[1]/div[1]/div[1]/div[1]/div/div/input")).val(cidade);
        $(byXpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root')]")).click();

    }

    public void botaoCadastrar() {
        $(byXpath("//span[text()='Cadastrar']")).click();
    }

    public void filtroSituacaoAtivado(String situacao) {
        $(byXpath("//input[@placeholder='Pesquise por nome do coordenador local e cpf...']")).doubleClick();
        $(byXpath("//input[@placeholder='Pesquise por nome do coordenador local e cpf...']")).val(getNomeCoordenador());
        $(byId("icon-search-contratos")).click();
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
        $(byXpath("//input[@placeholder='Pesquise por nome do coordenador local e cpf...']")).doubleClick();
        $(byXpath("(//label[text()='Estados']/following::input)[1]")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(estado).click();
        $(byText(estado)).click();
    }

    public void filtroCidade(String cidade) {
        $(byXpath("//input[@placeholder='Pesquise por nome do coordenador local e cpf...']")).doubleClick();
        $(byXpath("(//label[text()='Cidades']/following::input)[1]")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(cidade).click();
        $(byText(cidade)).click();
    }

    public void filtroEscola(String escola) {
        $(byXpath("//input[@placeholder='Pesquise por nome do coordenador local e cpf...']")).doubleClick();
        $(byXpath("//label[text()='Escola']/following::input")).shouldBe(Condition.exist, Duration.ofSeconds(60)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(escola).click();
        $(byText(escola)).click();
    }

    public void validarTodasSituacoes() {
        $(byXpath("//input[@placeholder='Pesquise por nome do coordenador local e cpf...']")).doubleClick();
        $(byXpath("//input[@placeholder='Todas as situações']")).click();
        $(byText("Ativado")).shouldBe(Condition.visible);
        $(byText("Inativado")).shouldBe(Condition.visible);
    }

    public void validacaoCoordenadorAtivo(String nomeCoordenador) {
        $(byXpath("(//p[@class='coordenador-item-label coordenador-item-label-name'])[4]")).shouldBe(Condition.exist);
    }

    public void validacaoCoordenadorInativos() {
        $(byText("Abelão Cara Você")).exists();
        $(byText("Coordenador Automação")).shouldBe(Condition.exist);
    }

    public void validacaoCoordenadorEstado() {
        $(byXpath("(//p[@class='coordenador-item-label coordenador-item-label-name'])[4]")).shouldBe(Condition.exist);

    }

    public void validacaoCoordenadorEstadoCidadeEscola() {
        $(byXpath("(//p[text()='Coordenador Local Do Leandro'])[2]")).shouldBe(Condition.exist);
    }
}
