package com.max.helpdesk.services;

import com.max.helpdesk.domain.Tecnico;
import com.max.helpdesk.domain.dto.TecnicoDto;
import com.max.helpdesk.repository.TecnicoRepository;
import com.max.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
    }

    public List<TecnicoDto> findAll() {
        return tecnicoRepository.findAll().stream().map(t -> new TecnicoDto(t)).collect(Collectors.toList());
    }

}
