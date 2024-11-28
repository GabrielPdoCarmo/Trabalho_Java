package br.com.gabriel_pereira.educational.controller;

import br.com.gabriel_pereira.educational.dto.DisciplinaDto;
import br.com.gabriel_pereira.educational.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<DisciplinaDto> createDisciplina(@RequestBody @Valid DisciplinaDto disciplinaDto) {
        disciplinaService.createDisciplina(disciplinaDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<DisciplinaDto>> findAllDisciplinas() {
        Set<DisciplinaDto> disciplinas = disciplinaService.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> findDisciplinaById(@PathVariable Integer id) {
        DisciplinaDto disciplina= disciplinaService.findById(id);
        return ResponseEntity.ok(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDto> updateDisciplina(@PathVariable Integer id, @RequestBody @Valid DisciplinaDto disciplinaDto) {
        disciplinaService.updateDisciplina(disciplinaDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Integer id) {
        disciplinaService.deleteDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
