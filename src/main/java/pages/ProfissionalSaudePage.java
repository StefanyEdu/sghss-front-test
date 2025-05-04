package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import dto.ProfissionalSaudeDTO;
import utils.ScreenshotHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfissionalSaudePage {

    ScreenshotHelper screenshotHelper = new ScreenshotHelper();

    public  void cadastroProfissionalMedico(ProfissionalSaudeDTO profissionalSaudeDTO){
            acessoProfissionalSaude();
            cadastroMedico(profissionalSaudeDTO);
    }



    private void acessoProfissionalSaude(){
        open("/profissionalsaude");
        screenshotHelper.takeScreenshot("Acesso ao cadastro profissional");
    }

    private void cadastroMedico(ProfissionalSaudeDTO profissionalSaudeDTO){
        clicarElementoPorId("cadastrarProfissional");
        clicarElementoPorId("nomeProfissional");
        definirValorPorId("nomeProfissional",profissionalSaudeDTO.getNomeProfissional());
        clicarElementoPorId("cpfCnpjProfissional");
        definirValorPorId("cpfCnpjProfissional",profissionalSaudeDTO.getCpfCnpjProfissionalSaude());
        clicarElementoPorId("especialidade");
        definirValorPorId("especialidade",profissionalSaudeDTO.getEspecialidade());
        clicarElementoPorId("crm");
        definirValorPorId("crm",profissionalSaudeDTO.getCRM());
        clicarElementoPorId("telefone");
        definirValorPorId("telefone",profissionalSaudeDTO.getTelefone());
        clicarElementoPorId("endereco");
        definirValorPorId("endereco",profissionalSaudeDTO.getEndereco());
        clicarElementoPorId("cidade");
        definirValorPorId("cidade",profissionalSaudeDTO.getCidade());
        clicarElementoPorId("email");
        definirValorPorId("email",profissionalSaudeDTO.getEmail());
        verificarElementoExistentePorId("Profissional Saude cadastrado",90);

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
