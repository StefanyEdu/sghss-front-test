package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaudeDTO {
    private String nomeProfissional;
    private String cpfCnpjProfissionalSaude;
    private String especialidade;
    private String CRM;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String email;
}
