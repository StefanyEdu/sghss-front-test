package pages;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import lombok.Setter;
import utils.GeradorDatas;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Getter
@Setter
public class AgendaAssessorPage {
    String tipoAgenda;
    String assuntoAgenda;
    String coop;
    String assessor;
    String estado;
    String cidade;
    String escola;

    public void acessoAgenda() {
        $(byXpath("(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')])[1]")).click();
        $(byXpath("//span[text()='Agenda dos Assessores Pedagógicos']")).click();
    }

    public void botaoCadastroAgenda() {
        $(byXpath("//span[text()='Cadastrar Agenda']")).click();
    }

    public void cadastroAgenda(GeradorDatas geradorDatas, String recorrencia,String semana, String dia,String intervalo ) {
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-tipo-agenda']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-tipo-agenda']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(getTipoAgenda());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).doubleClick();

        $(byId("evento-assessor-pedagogico-edit-modal-assunto")).shouldBe(Condition.exist).click();
        $(byId("evento-assessor-pedagogico-edit-modal-assunto"))
                .val(getAssuntoAgenda());

        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-cooperativa']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-cooperativa']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(getCoop());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).click();
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-assessores-pedagogicos']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-assessores-pedagogicos']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(getAssessor());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).doubleClick();
        $(byId("evento-assessor-pedagogico-edit-modal-data")).click();
        $(byId("evento-assessor-pedagogico-edit-modal-data")).val(geradorDatas.dataEventoInicio());
        //nova funcionadade de recorrencia dia 24/10/2023
        $(byId("seleciona-tipo")).click();
        botaorecorrencia(recorrencia,semana,dia);
        $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial")).click();
        $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial")).val(geradorDatas.horaEventoInicio());
        $(byId("evento-assessor-pedagogico-edit-modal-hora-final")).click();
        $(byId("evento-assessor-pedagogico-edit-modal-hora-final")).val(geradorDatas.horaFim());
        //intervalo
        botaoIntervaloAgenda(geradorDatas,intervalo);

    }
    public void cadastroAgendaBloqueioPessoal(GeradorDatas geradorDatas, String recorrencia,String semana, String dia,String intervalo ) {
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-tipo-agenda']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-tipo-agenda']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(getTipoAgenda());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).doubleClick();

        $(byId("evento-assessor-pedagogico-edit-modal-assunto")).shouldBe(Condition.exist).click();
        $(byId("evento-assessor-pedagogico-edit-modal-assunto"))
                .val(getAssuntoAgenda());
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-assessores-pedagogicos']/div[1]/div[1]/div[1]/div[1]")).click();
        $(byXpath("//div[@id='evento-assessor-pedagogico-edit-modal-assessores-pedagogicos']/div[1]/div[1]/div[1]/div[1]/div/div/input"))
                .val(getAssessor());
        $(byXpath("(//div[contains(@class,'MuiButtonBase-root MuiListItem-root')])[1]")).doubleClick();
        $(byId("evento-assessor-pedagogico-edit-modal-data")).click();
        $(byId("evento-assessor-pedagogico-edit-modal-data")).val(geradorDatas.dataEventoInicio());
        $(byId("seleciona-tipo")).click();
        botaorecorrencia(recorrencia,semana,dia);
        $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial")).click();
        $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial")).val(geradorDatas.horaEventoInicio());
        $(byId("evento-assessor-pedagogico-edit-modal-hora-final")).click();
        $(byId("evento-assessor-pedagogico-edit-modal-hora-final")).val(geradorDatas.horaFim());
        botaoIntervaloAgenda(geradorDatas,intervalo);
    }
    public void vinculoEscola(){
        $(byXpath("//span[text()='Adicionar escolas']")).click();
        //estado
        $(byXpath("(//label[text()='Estados']/following::input)[1]")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(getEstado());
        $(byXpath("//li[text()='"+getEstado()+"']")).click();
        // cidade
        $(byXpath("(//label[text()='Cidades']/following::input)[1]"))
                .shouldBe(Condition.exist, Duration.ofSeconds(120)).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(getCidade());
        $(byXpath("//li[text()='"+getCidade()+"']")).click();
        $(byXpath("(//label[text()='Cidades']/following::input)[1]")).doubleClick();

        $(byId("assessor-filter-empresas")).click();
        $(byXpath("//input[@placeholder='Pesquisar']"))
                .shouldBe(Condition.exist, Duration.ofSeconds(60))
                .click();
        $(byXpath("//input[@placeholder='Pesquisar']")).val(getEscola());
        $(byXpath("//li[text()='"+getEscola()+"']")).click();
        $(byXpath("(//label[text()='Cidades']/following::input)[1]")).doubleClick();
        $(byXpath("//span[text()='Selecionar escola']")).click();

        $(byId("evento-assessor-pedagogico-edit-modal-save")).click();
    }

    public void botaorecorrencia(String recorrencia,String semana, String dia) {
        if (recorrencia == "Mensal") {
            $(byXpath("//li[text()='Mensal']")).click();
            $(byId("week-of-month")).click();
            $(byXpath("//li[text()='"+semana+"']")).click();
            $(byId("days-of-week")).click();
            $(byXpath("//li[text()='"+dia+"']")).click();
            $(byId("save")).click();

        } else if (recorrencia == "Diária ou Semanal") {
            $(byXpath("//li[text()='Diária ou Semanal']")).click();
            $(byXpath("//span[text()='Salvar']")).click();

        }else{
            $(byXpath("//li[text()='Não repetir']")).click();
        }
    }

    public void botaoIntervaloAgenda(GeradorDatas geradorDatas,String agendaComIntervalo) {
        if(agendaComIntervalo=="Sim") {

            $(byXpath("(//input[@data-indeterminate='false'])[2]")).click();
            $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial-intervalo")).click();
            $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial-intervalo")).val(geradorDatas.horaInicioInter());
            $(byId("evento-assessor-pedagogico-edit-modal-hora-final-intervalo")).click();
            $(byId("evento-assessor-pedagogico-edit-modal-hora-final-intervalo")).val(geradorDatas.horaFimInter());
        }
        else if (agendaComIntervalo=="Sim com falha"){
                $(byXpath("(//input[@data-indeterminate='false'])[2]")).click();
                $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial-intervalo")).click();
                $(byId("evento-assessor-pedagogico-edit-modal-hora-inicial-intervalo")).val(geradorDatas.horaFimInter());
                $(byId("evento-assessor-pedagogico-edit-modal-hora-final-intervalo")).click();
                $(byId("evento-assessor-pedagogico-edit-modal-hora-final-intervalo")).val(geradorDatas.horaFimInter());
                $(byText("A Hora Final é inferior ou igual a Hora Inicial.")).shouldBe(Condition.visible);
        }

    }

    public void botaoCadastrarBloqueio(){
        $(byXpath("//span[text()='Cadastrar']")).click();

    }
        public void botaoCadastrar(){
            $(byXpath("//span[text()='Cadastrar']")).click();
            $(byXpath("//span[text()='Cadastrar agenda']")).click();

        }
    public void botaoCadastrarAgenda(){
        $(byXpath("//span[text()='Cadastrar agenda']")).click();

    }




    }

