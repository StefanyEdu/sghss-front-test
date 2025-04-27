package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FiltroPage {
    public void campoSituacaoProjeto() {
            $(byXpath("(//div[contains(@class,'MuiInputBase-input MuiInput-input')]//div)[1]")).shouldBe(Condition.visible).click();
            $(byText("TODAS AS SITUAÇÕES")).exists();

    }
   public void campoCooperativa(){
        $(byXpath("//input[@placeholder='Selecione uma ou mais cooperativas']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).setValue("União Metropolitana");
        $(byXpath("(//li[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
    }
    public void campoEstado(){

    }



}
