package projeto.empresa.projetoempresa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import projeto.empresa.projetoempresa.model.Cargo;
import projeto.empresa.projetoempresa.model.EStatusFuncionario;
import projeto.empresa.projetoempresa.model.Funcionario;
import projeto.empresa.projetoempresa.model.Salario;
import projeto.empresa.projetoempresa.model.DAO.FuncionarioDAO;
import projeto.empresa.projetoempresa.model.DAO.SalarioDAO;
import projeto.empresa.projetoempresa.model.DTO.FuncionarioCriarDTO;
import projeto.empresa.projetoempresa.model.DTO.FuncionarioViewDTO;
import projeto.empresa.projetoempresa.model.DAO.CargoDAO;

@Service
public class FuncionarioService {

    private static final CrudRepository<Funcionario, Integer> salarioDAO = null;

    @Autowired
    FuncionarioDAO funcionarioDAO;

    @Autowired
    CargoDAO cargoDAO;

    public void criarFuncionario(FuncionarioCriarDTO funcionarioCriarDTO) throws Exception{

        if (funcionarioCriarDTO.getNome().isEmpty() || funcionarioCriarDTO.getNome().isBlank()) {
            throw new Exception("Nome do empregado não pode ser vazio");
        }

        Optional<Cargo> cargo = cargoDAO.findById(funcionarioCriarDTO.getIdCargo());
        if (!cargo.isPresent()) {
            throw new Exception("Cargo não encontrado");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioCriarDTO.getNome());
        funcionario.setStatus(EStatusFuncionario.ATIVO);
        funcionario.setCargo(cargo.get());
        funcionarioDAO.save(funcionario);
    }
    public List<FuncionarioViewDTO> listaFuncionario(Optional<EStatusFuncionario> status) {
        List<Funcionario> funcionarios = status.isPresent() ? funcionarioDAO.findAllByStatus(status.get())
        : funcionarioDAO.findAll();
        List<FuncionarioViewDTO> funcionariosView = new ArrayList<FuncionarioViewDTO>();

        for (Funcionario funcionario : funcionarios) {
            FuncionarioViewDTO funcionarioView = new FuncionarioViewDTO();
            funcionarioView.setId(funcionario.getId());
            funcionarioView.setNome(funcionario.getNome());
            funcionarioView.setStatus(funcionario.getStatus().toString());
            funcionarioView.setCargo(funcionario.getCargo().getNome());
            funcionariosView.add(funcionarioView);
        }

        return funcionariosView;
    }
    public void voltaFeriasFuncionario(int idFuncionario) throws Exception {
        Optional<Funcionario> funcionario = funcionarioDAO.findById(idFuncionario);
        if (!Funcionario.isPresent()) {
            throw new Exception("Funcionario não encontrado");
        }

        funcionario.get().setStatus(EStatusFuncionario.ATIVO);
        funcionarioDAO.save(funcionario.get());
    }

    public void registraFeriasFuncionario(int idFuncionario) throws Exception {
        Optional<Funcionario> funcionario = funcionarioDAO.findById(idFuncionario);
        if (!funcionario.isPresent()) {
            throw new Exception("Funcionario não encontrado");
        }

        Cargo CargoFuncionario = funcionario.get().getCargo();

        double tercoFerias = CargoFuncionario.getSalario() / 3;

        Salario salario = new Salario();
        salario.setFuncionario(funcionario.get());
        salario.setDiaPagamento(java.time.LocalDateTime.now());
        salario.setValor(tercoFerias);

        funcionario.get().setStatus(EStatusFuncionario.FERIAS);

        funcionarioDAO.save(funcionario.get());
        salarioDAO.save(salario);
    }

    public void salvaSalarioFuncionario(int idFuncionario) throws Exception {
        Optional<Funcionario> funcionario = funcionarioDAO.findById(idFuncionario);
        if (!funcionario.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Cargo CargoFuncionario = funcionario.get().getCargo();

        double salario = CargoFuncionario.getSalario();

        salario = salario * 0.87; 

        if (funcionario.get().isZerohora()) { 
            salario = salario * 1.05;
        }

        if (funcionario.get().isVale()) { 
            salario = salario * 0.94;
        }

        int numDependentes = funcionario.get().getDependentes().size();
        if (numDependentes > 0 && numDependentes <= 3) { 
            int bonusDependentes = numDependentes * 50;
            salario += bonusDependentes;
        }

        if (numDependentes > 3) { 
            int bonusDependentes = 3 * 50;
            salario += bonusDependentes;
        }

        Salario pagamento = new Salario();
        pagamento.setFuncionario(funcionario.get());
        pagamento.setDiaPagamento(java.time.LocalDateTime.now());
        pagamento.setValor(salario);

        SalarioDAO.save(salario);
    }
}
