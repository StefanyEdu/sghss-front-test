package dataProvider;

import dto.PacienteDTO;
import dto.ProfissionalSaudeDTO;
import utils.GeradorNomeDataRg;

public class DataProvider {
    private DataProvider (){

    }

    public static PacienteDTO cadastroPaciente(){
        return PacienteDTO
                .builder()
                .nomePaciente("Paciente automacao"+new GeradorNomeDataRg().geradorNomes())
                .cpfPaciente(new GeradorNomeDataRg().geraCPF())
                .telefone("5198745682")
                .email("paciente@gmai.com")
                .endereco("rua das flores")
                .bairro("jardim encantado")
                .numero("20")
                .cidade("Porto Alegre")
                .alergias("celiaca")
                .convenio("Unimed")
                .build();
    }

    public static ProfissionalSaudeDTO cadastroMedico(){
        return ProfissionalSaudeDTO
                .builder()
                .nomeProfissional("Medico automacao"+new GeradorNomeDataRg().geradorNomes())
                .cpfCnpjProfissionalSaude(new GeradorNomeDataRg().geradorCnpj(true))
                .especialidade("Gastrologista")
                .CRM(new GeradorNomeDataRg().geraCodigoCrm())
                .telefone("51987554468")
                .endereco("Rua dona luiza ,153")
                .cidade("Novo Hamburgo")
                .email("medico@shss.com.br")
                .build();
    }

}
