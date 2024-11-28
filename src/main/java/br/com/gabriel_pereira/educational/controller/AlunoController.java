package br.com.gabriel_pereira.educational.controller;

import br.com.gabriel_pereira.educational.dto.AlunoDto;
import br.com.gabriel_pereira.educational.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDto> createAluno(@RequestBody @Valid AlunoDto alunoDto) {
        alunoService.createAluno(alunoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> findAllAlunos() {
        List<AlunoDto> alunos = alunoService.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> findAlunoById(@PathVariable Integer id) {
        AlunoDto aluno = alunoService.findById(id);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> updateAluno(@PathVariable Integer id, @RequestBody @Valid AlunoDto alunoDto) {
        alunoService.updateAluno(alunoDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Integer id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }
}
