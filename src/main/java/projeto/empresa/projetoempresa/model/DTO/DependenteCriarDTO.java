package projeto.empresa.projetoempresa.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependenteCriarDTO {
    private String nome;
    private int idFuncionario;
}
