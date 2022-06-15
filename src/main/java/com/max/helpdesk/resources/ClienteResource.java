package com.max.helpdesk.resources;

import com.max.helpdesk.domain.Cliente;
import com.max.helpdesk.domain.dto.ClienteDto;
import com.max.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(new ClienteDto(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteService.create(clienteDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteService.update(id, clienteDto);
        return ResponseEntity.ok().body(new ClienteDto(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
