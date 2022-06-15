package com.max.helpdesk.resources;

import com.max.helpdesk.domain.Tecnico;
import com.max.helpdesk.domain.dto.TecnicoDto;
import com.max.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok(new TecnicoDto(tecnico));
    }
    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll() {
        return ResponseEntity.ok(tecnicoService.findAll());
    }
    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto tecnicoDto) {
        Tecnico tecnico = tecnicoService.create(tecnicoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDto tecnicoDto) {
        Tecnico tecnico = tecnicoService.update(id, tecnicoDto);
        return ResponseEntity.ok().body(new TecnicoDto(tecnico));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
