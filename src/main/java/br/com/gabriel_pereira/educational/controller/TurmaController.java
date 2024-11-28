package br.com.gabriel_pereira.educational.controller;

import br.com.gabriel_pereira.educational.dto.TurmaDto;
import br.com.gabriel_pereira.educational.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<TurmaDto> createTurma(@RequestBody @Valid TurmaDto turmaDto) {
        turmaService.createTurma(turmaDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<TurmaDto>> findAllClasss(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester,
            @RequestParam(name = "curso_id", required = false) Integer courseId
    ) {
        Set<TurmaDto> turmaDto = turmaService.searchTurma(year, semester, courseId);
        return ResponseEntity.ok(turmaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> findTurmaById(@PathVariable Integer id) {
        TurmaDto turmaDto = turmaService.findById(id);
        return ResponseEntity.ok(turmaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDto> updateTurma(@PathVariable Integer id, @RequestBody @Valid TurmaDto turmaDto) {
        turmaService.updateTurma(turmaDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Integer id) {
        turmaService.deleteTurma(id);
        return ResponseEntity.noContent().build();
    }
}
