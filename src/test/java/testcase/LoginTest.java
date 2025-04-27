package testcase;
import io.qameta.allure.Feature;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static constans.Perfil.ADMIN;

public class LoginTest extends BaseTest {
    @Test
    @Feature("Realiza Login")
    public void acessarTelaLogin(){
        open("");
        LoginPage login = new LoginPage();
        login.digitarPerfil(ADMIN);
        login.digitarSenha("teste123");
        login.clicarEntrar();

    }


}
