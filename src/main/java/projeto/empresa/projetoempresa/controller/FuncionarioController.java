package projeto.empresa.projetoempresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.empresa.projetoempresa.model.DTO.FuncionarioCriarDTO;
import projeto.empresa.projetoempresa.service.FuncionarioService;

@RestController
@RequestMapping("/Funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/")
    public ResponseEntity<?> criaFuncionario(@RequestBody(required = true) FuncionarioCriarDTO funcionarioCriarDTO) {
        try {
            funcionarioService.criarFuncionario(funcionarioCriarDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> listaFuncionario() {
        try {
            return ResponseEntity.ok(funcionarioService.listaFuncionario());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/alocaFeriasEPagaTercoSalario/{idFuncionario}")
    public ResponseEntity<?> alocaFerias(@PathVariable(required = true) int idFuncionario) {
        try {
            funcionarioService.registraFeriasFuncionario(idFuncionario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/voltaFerias/{idFuncionario}")
    public ResponseEntity<?> voltaFerias(@PathVariable(required = true) int idFuncionario) {
        try {
            funcionarioService.registraFeriasFuncionario(idFuncionario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/pagaFuncionario/{idFuncionario}")
    public ResponseEntity<?> pagaEmpregado(@PathVariable(required = true) int idFuncionario) {
        try {
            funcionarioService.salvaSalarioFuncionario(idFuncionario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
