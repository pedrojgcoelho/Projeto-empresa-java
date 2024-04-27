package projeto.empresa.projetoempresa.model.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioCriarDTO {
    private double valor;
    private LocalDateTime diaPagamento;
    private int idfuncionario;
}
