package br.com.gabriel_pereira.educational.controller;

import br.com.gabriel_pereira.educational.dto.MatriculaDto;
import br.com.gabriel_pereira.educational.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<MatriculaDto> createMatricula(@RequestBody @Valid MatriculaDto matriculaDto) {
        matriculaService.createdMatricula(matriculaDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<MatriculaDto>> findAllMatriculas() {
        Set<MatriculaDto> matriculas = matriculaService.findAllMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> findMatriculaById(@PathVariable Integer id) {
        MatriculaDto matricula = matriculaService.findById(id);
        return ResponseEntity.ok(matricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDto> updateMatricula(@PathVariable Integer id, @RequestBody @Valid MatriculaDto matriculaDto) {
        matriculaService.updateMatricula(id, matriculaDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Integer id) {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
