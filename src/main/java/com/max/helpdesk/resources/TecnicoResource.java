package com.max.helpdesk.resources;

import com.max.helpdesk.domain.Tecnico;
import com.max.helpdesk.domain.dto.TecnicoDto;
import com.max.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
}
