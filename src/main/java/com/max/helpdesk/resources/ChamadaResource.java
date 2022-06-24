package com.max.helpdesk.resources;

import com.max.helpdesk.domain.Chamado;
import com.max.helpdesk.domain.dto.ChamadoDto;
import com.max.helpdesk.services.ChamadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadaResource {

    @Autowired
    private ChamadaService chamadaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ChamadoDto(chamadaService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll() {
        List<Chamado> chamadoList = chamadaService.findAll();
        return ResponseEntity.ok(chamadoList.stream().map(chamado -> new ChamadoDto(chamado)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto chamadoDto) {
        Chamado chamado = chamadaService.create(chamadoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ChamadoDto> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDto chamadoDto) {
        Chamado chamado = chamadaService.update(id, chamadoDto);
        return ResponseEntity.ok().body(new ChamadoDto(chamado));
    }

}
