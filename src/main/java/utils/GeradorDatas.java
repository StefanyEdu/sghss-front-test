package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeradorDatas {
   // public static final String DATA_FILTRO= "dd MM yyyy";
    public static final String DATA_SEM_BARRA = "ddMMyyyy";
    public static final String HORA = "HH:mm";
    public String dataInicioInscricaoPadrao(){
        LocalDateTime dataInicio = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataInicio.format(formatter);
        return dataFormatada;

    }

    public String dataEventoInicio() {
        LocalDate dataInicio = LocalDate.now();
        String dataInicioFormatado = dataInicio.format(formatacaoDatas());
        return dataInicioFormatado;
    }

    public String dataInicioContratoNaoIniciado() {
        LocalDate dataInicio = LocalDate.now();
        String dataInicioFormatado = dataInicio.plusMonths(1).format(formatacaoDatas());
        return dataInicioFormatado;
    }
    public String dataFimContratoNaoIniciado() {
        LocalDate dataInicio = LocalDate.now();
        String dataInicioFormatado = dataInicio.plusMonths(13).format(formatacaoDatas());
        return dataInicioFormatado;
    }

    public String dataEventFim() {
        String dataFimFormatado = calculoDataFim().format(formatacaoDatas());
        return dataFimFormatado;
    }
    public String horaEventoInicio() {
        String horaInicioEvento = horaInicio().format(horaformatada());
        return horaInicioEvento;
    }

    public String horaFim() {
        String horaFimEvento = horaEventoFim().format(horaformatada());
        return horaFimEvento;
    }
    public String horaInicioInter() {
        String horaFimIntervalo = horaInicioIntervalo().format(horaformatada());
        return horaFimIntervalo;
    }
    public String horaFimInter() {
        String horaFimIntervalo = horaFimIntervalo().format(horaformatada());
        return horaFimIntervalo;
    }
    public Integer anoFormatado(){
        LocalDateTime anoEscola=LocalDateTime.now();
        return anoEscola.getYear();
    }

    private LocalDate calculoDataFim() {
        LocalDate dataInicio = LocalDate.now();
        return dataInicio.plusMonths(12);

    }
    private DateTimeFormatter formatacaoDatas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_SEM_BARRA);
        return formatter;
    }

    private LocalDateTime horaInicio() {
        LocalDateTime dataInicio = LocalDateTime.now();
        return dataInicio.plusMinutes(15);

    }
    private LocalDateTime horaInicioIntervalo() {
        LocalDateTime dataInicio = LocalDateTime.now();
        return dataInicio.plusHours(2);

    }
    private LocalDateTime horaFimIntervalo() {
        LocalDateTime dataInicio = LocalDateTime.now();
        return dataInicio.plusHours(3);

    }

    private LocalDateTime horaEventoFim() {
        LocalDateTime dataInicio = LocalDateTime.now();
        return dataInicio.plusHours(5);
    }
    private DateTimeFormatter horaformatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HORA);
        return formatter;
    }


}
