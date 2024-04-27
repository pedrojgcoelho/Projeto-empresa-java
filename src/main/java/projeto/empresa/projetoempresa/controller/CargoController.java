package projeto.empresa.projetoempresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.empresa.projetoempresa.model.DTO.CargoAtualizaDTO;
import projeto.empresa.projetoempresa.model.DTO.CargoCriarDTO;
import projeto.empresa.projetoempresa.service.CargoService;

@RestController
@RequestMapping("/funcao")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @PostMapping("/")
    public ResponseEntity<?> criaCargo(@RequestBody(required = true) CargoCriarDTO cargoCriarDTO) {
        try {
            cargoService.criaCargo(cargoCriarDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> alteraCargo(@RequestBody(required = true) CargoAtualizaDTO cargoAtualizaDTO) {
        try {
            cargoService.alteraCargo(cargoAtualizaDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarCargo(@PathVariable(required = true) int id) {
        try {
            cargoService.apagarCargo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idCargo}")
    public ResponseEntity<?> buscaCargo(@PathVariable(required = true) int idCargo) {
        try {
            return ResponseEntity.ok(cargoService.buscaCargo(idCargo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listaCargo() {
        try {
            return ResponseEntity.ok(cargoService.listaCargo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
