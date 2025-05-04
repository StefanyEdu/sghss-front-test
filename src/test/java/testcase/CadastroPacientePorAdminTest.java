package testcase;

import dto.PacienteDTO;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PacientePage;
import setup.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static constans.Perfil.ADMIN;
import static constans.Perfil.SENHA_ADMIN;
import static dataProvider.DataProvider.*;

public class CadastroPacientePorAdminTest extends BaseTest {
    LoginPage login = new LoginPage();
    PacientePage pacientePage = new PacientePage();
    PacienteDTO pacienteDTO;

    @Test
    @Feature("Realiza cadastro paciente")
    public void validarCadastroPacienteComSucessoTest(){
        open("");
        login.login(ADMIN,SENHA_ADMIN);
        pacienteDTO=cadastroPaciente();
        pacientePage.cadastroPacientePorAdmin(pacienteDTO);

    }
}
