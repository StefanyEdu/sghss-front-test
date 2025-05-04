package testcase;

import dto.PacienteDTO;
import dto.ProfissionalSaudeDTO;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfissionalSaudePage;
import setup.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static constans.Perfil.ADMIN;
import static constans.Perfil.SENHA_ADMIN;
import static dataProvider.DataProvider.cadastroMedico;

public class CadastroProfissionalMedicoTest extends BaseTest {
    LoginPage login = new LoginPage();
    ProfissionalSaudePage profissionalSaudePage = new ProfissionalSaudePage();
    ProfissionalSaudeDTO profissionalSaudeDTO;
    @Test
    @Feature("Realiza cadastro de medico")
    public void validarCadastroMedicoComSucessoTest(){
        open("");
        login.login(ADMIN,SENHA_ADMIN);
        profissionalSaudeDTO= cadastroMedico();
        profissionalSaudePage.cadastroProfissionalMedico(profissionalSaudeDTO);

    }
}
