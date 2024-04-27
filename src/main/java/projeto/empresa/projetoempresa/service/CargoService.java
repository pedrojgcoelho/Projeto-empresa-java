package projeto.empresa.projetoempresa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.empresa.projetoempresa.model.Cargo;
import projeto.empresa.projetoempresa.model.DAO.CargoDAO;
import projeto.empresa.projetoempresa.model.DTO.CargoAtualizaDTO;
import projeto.empresa.projetoempresa.model.DTO.CargoCriarDTO;
import projeto.empresa.projetoempresa.model.DTO.CargoViewDTO;

@Service
public class CargoService {
    @Autowired
    CargoDAO cargoDAO;

    public void criaCargo(CargoCriarDTO cargoCriarDTO) throws Exception {

        if (cargoCriarDTO.getNome().isEmpty() || cargoCriarDTO.getNome().isBlank()) {
            throw new Exception("O nome do cargo não pode estar vazio");
        }

        if (cargoCriarDTO.getSalario() <= 0) {
            throw new Exception("O Sálario do cargo deve ser maior que zero");
        }

        Cargo cargo = new Cargo();
        cargo.setNome(cargoCriarDTO.getNome());
        cargo.setSalario(cargoCriarDTO.getSalario());
        cargoDAO.save(cargo);
    }

    public void alteraCargo(CargoAtualizaDTO cargoAtualizaDTO) throws Exception {
        if (cargoAtualizaDTO.getNome().isEmpty() || cargoAtualizaDTO.getNome().isBlank()) {
            throw new Exception("O nome do cargo não pode estar vazio");
        }

        if (cargoAtualizaDTO.getSalario() <= 0) {
            throw new Exception("O Sálario do cargo deve ser maior que zero");
        }

        Cargo cargo = cargoDAO.findById(cargoAtualizaDTO.getId()).get();
        cargo.setNome(cargoAtualizaDTO.getNome());
        cargo.setSalario(cargoAtualizaDTO.getSalario());
        cargoDAO.save(cargo);
    }

    public void apagarCargo(int id) throws Exception {
        if (!cargoDAO.existsById(id)) {
            throw new Exception("Cargo não encontrado");
        }

        cargoDAO.deleteById(id);
    }

    public CargoViewDTO buscaCargo(int id) throws Exception {
        Optional<Cargo> cargo = cargoDAO.findById(id);
        if (!cargo.isPresent()) {
            throw new Exception("Cargo não encontrado");
        }

        CargoViewDTO cargoView = new CargoViewDTO();
        cargoView.setId(cargo.get().getId());
        cargoView.setNome(cargo.get().getNome());
        cargoView.setSalario(cargo.get().getSalario());
        return cargoView;
    }

    public List<CargoViewDTO> listaCargo() {
        List<Cargo> cargos = cargoDAO.findAll();
        List<CargoViewDTO> cargosView = new ArrayList<CargoViewDTO>();

        for (Cargo cargo : cargos) {
            CargoViewDTO cargoView = new CargoViewDTO();
            cargoView.setId(cargo.getId());
            cargoView.setNome(cargo.getNome());
            cargoView.setSalario(cargo.getSalario());
            cargosView.add(cargoView);
        }

        return cargosView;
    }
}
