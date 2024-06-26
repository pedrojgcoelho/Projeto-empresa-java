package projeto.empresa.projetoempresa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private double valor;
    private LocalDateTime diaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
}
