package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import dto.PacienteDTO;
import utils.ScreenshotHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static enums.Ambiente.HOM;

public class PacientePage {
    ScreenshotHelper screenshotHelper = new ScreenshotHelper();

    public void cadastroPacientePorPaciente(PacienteDTO pacienteDTO){
        acessoPaciente();
        cadastroPaciente(pacienteDTO);

    }

    public void cadastroPacientePorAdmin(PacienteDTO pacienteDTO){
        acessoPaciente();
        cadastroPaciente(pacienteDTO);
    }


    private  void acessoPaciente(){
        open(HOM+"/paciente");
        screenshotHelper.takeScreenshot("Acesso a pagina de paciente");

    }

    private void cadastroPaciente(PacienteDTO pacienteDTO){
        clicarElementoPorId("cadastroPaciente");
        clicarElementoPorId("nomePaciente");
        definirValorPorId("nomePaciente",pacienteDTO.getNomePaciente());
        clicarElementoPorId("cpfPaciente");
        definirValorPorId("cpfPaciente",pacienteDTO.getCpfPaciente());
        clicarElementoPorId("telefone");
        definirValorPorId("telefone",pacienteDTO.getTelefone());
        clicarElementoPorId("emailPaciente");
        definirValorPorId("emailPaciente",pacienteDTO.getEmail());
        clicarElementoPorId("endereco");
        definirValorPorId("endereco",pacienteDTO.getEndereco());
        clicarElementoPorId("bairro");
        definirValorPorId("bairro",pacienteDTO.getBairro());
        clicarElementoPorId("numero");
        definirValorPorId("numero",pacienteDTO.getNumero());
        clicarElementoPorId("alergias");
        definirValorPorId("alergias",pacienteDTO.getAlergias());
        clicarElementoPorId("convenioPaciente");
        definirValorPorId("convenioPaciente",pacienteDTO.getConvenio());
        screenshotHelper.takeScreenshot("Preencimento dos dados");
        clicarElementoPorId("buttonsalvar");
        verificarElementoExistentePorId("Paciente salvo com sucessos",90);
        screenshotHelper.takeScreenshot("Salvamento do paciente");


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
