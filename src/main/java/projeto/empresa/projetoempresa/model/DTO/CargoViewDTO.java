package projeto.empresa.projetoempresa.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoViewDTO {
    private int id;
    private String nome;
    private double salario;
}
