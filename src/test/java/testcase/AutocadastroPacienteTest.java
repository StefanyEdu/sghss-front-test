package testcase;

import dto.PacienteDTO;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PacientePage;
import setup.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static constans.Perfil.*;
import static dataProvider.DataProvider.cadastroPaciente;

public class AutocadastroPacienteTest extends BaseTest {
    LoginPage login = new LoginPage();
    PacientePage pacientePage = new PacientePage();
    PacienteDTO pacienteDTO;

    @Test
    @Feature("Realiza autocadastro paciente")
    public void validarAutoCadastroPacienteComSucessoTest(){
        open("");
        login.login(PACIENTE,SENHA_USUARIO_EXTERNO);
        pacienteDTO=cadastroPaciente();
        pacientePage.cadastroPacientePorPaciente(pacienteDTO);

    }

}
