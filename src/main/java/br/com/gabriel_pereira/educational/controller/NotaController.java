package br.com.gabriel_pereira.educational.controller;

import br.com.gabriel_pereira.educational.dto.NotaDto;
import br.com.gabriel_pereira.educational.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/notas")
public class NotaController {

    private final NotaService NotaService;

    public NotaController(NotaService NotaService) {
        this.NotaService = NotaService;
    }

    @PostMapping
    public ResponseEntity<NotaDto> createNota(@RequestBody @Valid NotaDto notaDto) {
        NotaService.createdNota(notaDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Set<NotaDto>> findAllNotas() {
        Set<NotaDto> notas = NotaService.findAllMatriculas();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDto> findNotaById(@PathVariable Integer id) {
        NotaDto nota = NotaService.findById(id);
        return ResponseEntity.ok(nota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDto> updateNota(@PathVariable Integer id, @RequestBody @Valid NotaDto NotaDto) {
        NotaService.updateNota(id, NotaDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Integer id) {
        NotaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
