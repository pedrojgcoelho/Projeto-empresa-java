package projeto.empresa.projetoempresa.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.empresa.projetoempresa.model.Dependente;
import projeto.empresa.projetoempresa.model.Funcionario;
import projeto.empresa.projetoempresa.model.DAO.DependenteDAO;
import projeto.empresa.projetoempresa.model.DAO.FuncionarioDAO;
import projeto.empresa.projetoempresa.model.DTO.DependenteAtualiazaDTO;
import projeto.empresa.projetoempresa.model.DTO.DependenteCriarDTO;
import projeto.empresa.projetoempresa.model.DTO.DependenteViewDTO;

@Service
public class DependenteService {
    @Autowired
    DependenteDAO dependenteDAO;

    @Autowired
    FuncionarioDAO funcionarioDAO;

    public void criaDependente(DependenteCriarDTO dependenteCriarDTO) throws Exception {

        if (dependenteCriarDTO.getNome().isEmpty() || dependenteCriarDTO.getNome().isBlank()) {
            throw new Exception("Nome do dependente não pode estar vazio");
        }

        Optional<Funcionario> funcionario = funcionarioDAO.findById(dependenteCriarDTO.getIdFuncionario());
        if (!funcionario.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Dependente dependente = new Dependente();
        dependente.setNome(dependenteCriarDTO.getNome());
        dependente.setDependente(dependente.get());
        dependenteDAO.save(dependente);
    }

    public void alterarDependente(DependenteAtualiazaDTO dependenteAtualiazaDTO) throws Exception {
        if (dependenteAtualiazaDTO.getNome().isEmpty() || dependenteAtualiazaDTO.getNome().isBlank()) {
            throw new Exception("Nome do dependente não pode estar vazio");
        }

        Optional<Funcionario> funcionario = funcionarioDAO.findById(dependenteAtualiazaDTO.getIdFuncionario());
        if (!funcionario.isPresent()) {
            throw new Exception("Funcionario não encontrado");
        }

        Dependente dependente = dependenteDAO.findById(dependenteAtualiazaDTO.getId()).get();
        dependente.setNome(dependenteAtualiazaDTO.getNome());
        dependente.setFuncionario(funcionario.get());
        dependenteDAO.save(dependente);
    }

    public void apagarDependente(int id) throws Exception {
        Optional<Dependente> dependente = dependenteDAO.findById(id);
        if (!dependente.isPresent()) {
            throw new Exception("Dependente não encontrado");
        }

        dependenteDAO.delete(dependente.get());
    }

    public DependenteViewDTO buscarDependente(int id) throws Exception {
        Optional<Dependente> dependnete = dependenteDAO.findById(id);
        if (!dependnete.isPresent()) {
            throw new Exception("Dependente não encontrado");
        }

        DependenteViewDTO dependenteView = new DependenteViewDTO();
        dependenteView.setId(dependnete.get().getId());
        dependenteView.setNome(dependnete.get().getNome());
        dependenteView.setFuncionario(dependnete.get().getFuncionario());
        return dependenteView;
    }

    public List<DependenteViewDTO> listarDependente() {
        List<Dependente> depentendes = dependenteDAO.findAll();
        List<DependenteViewDTO> dependentesView = new ArrayList<DependenteViewDTO>();

        for (Dependente dependente : depentendes) {
            DependenteViewDTO dependenteView = new DependenteViewDTO();
            dependenteView.setId(dependente.getId());
            dependenteView.setNome(dependente.getNome());
            dependenteView.setFuncionario(dependente.getFuncionario());
            dependentesView.add(dependenteView);
        }

        return dependentesView;
    }
}
