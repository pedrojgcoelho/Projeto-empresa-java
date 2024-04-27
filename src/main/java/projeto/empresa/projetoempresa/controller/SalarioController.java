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

import projeto.empresa.projetoempresa.model.DTO.SalarioAtualizaDTO;
import projeto.empresa.projetoempresa.service.SalarioService;



@RestController
@RequestMapping("/salario")
public class SalarioController {
    @Autowired
    private SalarioService salarioService;

    @PostMapping("/pagaFuncionario/{idFuncionario}")
    public ResponseEntity<?> salvaSalarioFuncionario(@PathVariable(required = true) int idFuncionario) {
        try {
            SalarioService.salvaSalarioFuncionario(idFuncionario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizaSalarioFuncionario")
    public ResponseEntity<?> atualizaSalarioFuncionario(
            @RequestBody(required = true) SalarioAtualizaDTO salarioAtualizaDTO) {
        try {
            salarioService.atualizaSalario(salarioAtualizaDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> apagarSalario(@PathVariable(required = true) int id) {
        try {
            salarioService.apagarSalario(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<?> listaSalariosFuncionario(@PathVariable(required = true) int idFuncionario) {
        try {
            return ResponseEntity.ok(salarioService.buscaSalario(idFuncionario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listaSalarios() {
        try {
            return ResponseEntity.ok(salarioService.listaSalarios());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
