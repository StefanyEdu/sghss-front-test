package testcase;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static constans.Perfil.ADMIN;
import static constans.Perfil.SENHA_ADMIN;

public class LoginAdminTest extends BaseTest {
    LoginPage login = new LoginPage();
    @Test
    @Feature("Realiza Login")
    public void acessarTelaLogin(){
        open("");
        login.login(ADMIN,SENHA_ADMIN);


    }


}
