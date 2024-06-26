package projeto.empresa.projetoempresa.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private EStatusFuncionario status;
    private boolean zerohora;
    private boolean vale;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Dependente>  dependentes;

    public static boolean isPresent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPresent'");
    }
}
