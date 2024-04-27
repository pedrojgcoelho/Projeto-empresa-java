package projeto.empresa.projetoempresa.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioViewDTO {
    private int id;
    private String nome;
    private String cargo;
    private double salarioBrutoMensal;
    private double salarioBrutoAnual;
    private String status;
}
