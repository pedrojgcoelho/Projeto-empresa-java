package projeto.empresa.projetoempresa.model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.empresa.projetoempresa.model.Funcionario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioViewDTO {
    private int id;
    private double valor;
    private LocalDateTime diaPagamento;
    private Funcionario funcionario;
 
}
