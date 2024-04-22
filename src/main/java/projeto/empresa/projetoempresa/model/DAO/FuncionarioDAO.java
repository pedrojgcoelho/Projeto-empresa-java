package projeto.empresa.projetoempresa.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.empresa.projetoempresa.model.Funcionario;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {
    
}
