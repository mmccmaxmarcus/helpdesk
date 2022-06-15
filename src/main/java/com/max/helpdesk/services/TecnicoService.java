package com.max.helpdesk.services;

import com.max.helpdesk.domain.Pessoa;
import com.max.helpdesk.domain.Tecnico;
import com.max.helpdesk.domain.dto.TecnicoDto;
import com.max.helpdesk.repository.PessoaRepository;
import com.max.helpdesk.repository.TecnicoRepository;
import com.max.helpdesk.services.exceptions.DataIntegrityViolationException;
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
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
    }

    public List<TecnicoDto> findAll() {
        return tecnicoRepository.findAll().stream().map(t -> new TecnicoDto(t)).collect(Collectors.toList());
    }

    public Tecnico create(TecnicoDto tecnicoDto) {
        tecnicoDto.setId(null);
        validaPorCpfOuEmail(tecnicoDto);
        Tecnico tecnico = new Tecnico(tecnicoDto);
        return tecnicoRepository.save(tecnico);
    }

    private void validaPorCpfOuEmail(TecnicoDto tecnicoDto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDto.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        pessoa = pessoaRepository.findByEmail(tecnicoDto.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }

    public Tecnico update(Integer id, TecnicoDto tecnicoDto) {
        tecnicoDto.setId(id);
        Tecnico tecnico = findById(id);
        validaPorCpfOuEmail(tecnicoDto);
        tecnico = new Tecnico(tecnicoDto);
        return pessoaRepository.save(tecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (tecnico.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordem de serviço e não pode ser deletado");
        }
        tecnicoRepository.deleteById(id);
    }
}
