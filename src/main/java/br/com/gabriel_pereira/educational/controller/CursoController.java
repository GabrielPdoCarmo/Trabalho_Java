package br.com.gabriel_pereira.educational.controller;

import br.com.gabriel_pereira.educational.dto.CursoDto;
import br.com.gabriel_pereira.educational.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<CursoDto> createCurso(@RequestBody @Valid CursoDto professorDto) {
        cursoService.createCurso(professorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> findAllCursos() {
        List<CursoDto> students = cursoService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> findCursoById(@PathVariable Integer id) {
        CursoDto student = cursoService.findById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDto> updateCurso(@PathVariable Integer id, @RequestBody @Valid CursoDto professorDto) {
        cursoService.updateCurso(professorDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
