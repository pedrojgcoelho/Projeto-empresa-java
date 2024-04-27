package projeto.empresa.projetoempresa.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.empresa.projetoempresa.model.Cargo;
import projeto.empresa.projetoempresa.model.Funcionario;
import projeto.empresa.projetoempresa.model.Salario;
import projeto.empresa.projetoempresa.model.DAO.FuncionarioDAO;
import projeto.empresa.projetoempresa.model.DAO.SalarioDAO;
import projeto.empresa.projetoempresa.model.DTO.SalarioAtualizaDTO;
import projeto.empresa.projetoempresa.model.DTO.SalarioViewDTO;

@Service
public class SalarioService {
    @Autowired
    FuncionarioDAO funcionarioDAO;

    @Autowired
    SalarioDAO salarioDAO;

    public void salvaSalarioFuncionario(int idFuncionario) throws Exception {
        Optional<Funcionario> funcionario = funcionarioDAO.findById(idFuncionario);
        if (!funcionario.isPresent()) {
            throw new Exception("Funcionario não encontrado");
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

    public void atualizaSalario(SalarioAtualizaDTO salarioAtualizaDTO) throws Exception {
        Optional<Salario> salario = salarioDAO.findById(salarioAtualizaDTO.getId());
        if (!salario.isPresent()) {
            throw new Exception("Salário não encontrado");
        }

        salario.get().setValor(salarioAtualizaDTO.getValor());
        salario.get().setDiaPagamento(salarioAtualizaDTO.getDiaPagamento());

        salarioDAO.save(salario.get());
    }

    public void apagarSalario(int id) throws Exception {
        Optional<Salario> salario = salarioDAO.findById(id);
        if (!salario.isPresent()) {
            throw new Exception("Salário não encontrado");
        }

        salarioDAO.delete(salario.get());
    }

    public SalarioViewDTO buscaSalario(int id) throws Exception {
        Optional<Salario> salario = salarioDAO.findById(id);
        if (!salario.isPresent()) {
            throw new Exception("Salário não encontrado");
        }

        SalarioViewDTO salarioView = new SalarioViewDTO();
        salarioView.setId(salario.get().getId());
        salarioView.setValor(salario.get().getValor());
        salarioView.setDiaPagamento(salario.get().getDiaPagamento());
        salarioView.setFuncionario(salario.get().getFuncionario());
        return salarioView;
    }

    public List<SalarioViewDTO> listaSalarios() {
        List<Salario> salarios = salarioDAO.findAll();
        List<SalarioViewDTO> salariosView = new ArrayList<SalarioViewDTO>();

        for (Salario salario : salarios) {
            SalarioViewDTO salarioView = new SalarioViewDTO();
            salarioView.setId(salario.getId());
            salarioView.setValor(salario.getValor());
            salarioView.setDiaPagamento(salario.getDiaPagamento());
            salarioView.setFuncionario(salario.getFuncionario());
            salariosView.add(salarioView);
        }

        return salariosView;
    }
}
