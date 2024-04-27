package projeto.empresa.projetoempresa.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.empresa.projetoempresa.model.EStatusFuncionario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioAtualizaDTO {
    private int id;
    private String nome;
    private int idCargo;
    private EStatusFuncionario status;
}
