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

import projeto.empresa.projetoempresa.model.DTO.DependenteAtualiazaDTO;
import projeto.empresa.projetoempresa.model.DTO.DependenteCriarDTO;
import projeto.empresa.projetoempresa.service.DependenteService;

@RestController
@RequestMapping("/dependente")
public class DependenteController {
    @Autowired
    private DependenteService dependenteService;

    @PostMapping("/")
    public ResponseEntity<?> criaDependente(@RequestBody(required = true) DependenteCriarDTO dependenteCriarDTO) {
        try {
            dependenteService.criaDependente(dependenteCriarDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> alterarDependente(@RequestBody(required = true) DependenteAtualiazaDTO dependenteAtualiazaDTO) {
        try {
            dependenteService.alterarDependente(dependenteAtualiazaDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarDependente(@PathVariable(required = true) int id) {
        try {
            dependenteService.apagarDependente(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idDependente}")
    public ResponseEntity<?> buscarDependente(@PathVariable(required = true) int idDependente) {
        try {
            return ResponseEntity.ok(dependenteService.buscarDependente(idDependente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarDependente() {
        try {
            return ResponseEntity.ok(dependenteService.listarDependente());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

