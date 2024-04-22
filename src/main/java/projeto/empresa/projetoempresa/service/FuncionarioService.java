package projeto.empresa.projetoempresa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.empresa.projetoempresa.model.Cargo;
import projeto.empresa.projetoempresa.model.EStatusFuncionario;
import projeto.empresa.projetoempresa.model.Funcionario;
import projeto.empresa.projetoempresa.model.DAO.FuncionarioDAO;
import projeto.empresa.projetoempresa.model.DTO.FuncionarioCriarDTO;
import projeto.empresa.projetoempresa.model.DTO.FuncionarioViewDTO;
import projeto.empresa.projetoempresa.model.DAO.CargoDAO;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioDAO funcionarioDAO;

    public void criarFuncionario(FuncionarioCriarDTO funcionarioCriarDTO) throws Exception{

        if (funcionarioCriarDTO.getNome().isEmpty() || funcionarioCriarDTO.getNome().isBlank()) {
            throw new Exception("Nome do empregado não pode ser vazio");
        }

        Optional<Cargo> cargo = cargoDAO.findById(funcionarioCriarDTO.getIdFuncao());
        if (!cargo.isPresent()) {
            throw new Exception("Cargo não encontrado");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioCriarDTO.getNome());
        funcionario.setStatus(EStatusFuncionario.ATIVO);
        funcionario.setStatus(EStatusFuncionario.ATIVO);
        funcionario.setCargo(cargo.get());
        funcionarioDAO.save(funcionario);
    }
    public List<FuncionarioViewDTO> listaFuncionario() {
        List<Funcionario> funcionarios = funcionarioDAO.findAll();
        List<FuncionarioViewDTO> funcionarioView = new ArrayList<FuncionarioViewDTO>();

        for (Funcionario funcionario : funcionarios) {
            FuncionarioViewDTO funcionarioView = new FuncionarioViewDTO();
            funcionarioView.setId(funcionario.getId());
            funcionarioView.setNome(funcionario.getNome());
            funcionarioView.setStatus(funcionario.getStatus().toString());
            funcionarioView.setCargo(funcionario.getCargo().getNome());
            funcionarioView.add(funcionarioView);
        }

        return funcionarioView;
    }
}
