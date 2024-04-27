package projeto.empresa.projetoempresa.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.empresa.projetoempresa.model.Salario;

public interface SalarioDAO extends JpaRepository<Salario, Integer> {

    static void save(double salario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
