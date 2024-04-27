package projeto.empresa.projetoempresa.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.empresa.projetoempresa.model.Funcionario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependenteViewDTO {
    private int id;
    private String nome;
    private Funcionario funcionario;
}
