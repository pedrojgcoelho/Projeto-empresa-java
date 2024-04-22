package projeto.empresa.projetoempresa.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.empresa.projetoempresa.model.Cargo;

public interface CargoDAO extends JpaRepository<Cargo, Integer> {
    
}
