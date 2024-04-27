package projeto.empresa.projetoempresa.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.empresa.projetoempresa.model.Dependente;

public interface DependenteDAO extends JpaRepository<Dependente, Integer> {
    
}
