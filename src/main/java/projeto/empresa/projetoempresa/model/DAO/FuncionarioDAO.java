package projeto.empresa.projetoempresa.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.empresa.projetoempresa.model.EStatusFuncionario;
import projeto.empresa.projetoempresa.model.Funcionario;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findAllByStatus(EStatusFuncionario status);   
}
