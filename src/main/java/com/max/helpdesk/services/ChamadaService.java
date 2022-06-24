package com.max.helpdesk.services;

import com.max.helpdesk.domain.Chamado;
import com.max.helpdesk.domain.Cliente;
import com.max.helpdesk.domain.Tecnico;
import com.max.helpdesk.domain.dto.ChamadoDto;
import com.max.helpdesk.enums.Prioridade;
import com.max.helpdesk.enums.Status;
import com.max.helpdesk.repository.ChamadoRepository;
import com.max.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadaService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDto chamadoDto) {
        return chamadoRepository.save(newChamado(chamadoDto));
    }

    private Chamado newChamado(ChamadoDto chamadoDto) {
        Chamado chamado = new Chamado();
        Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDto.getCliente());

        if (chamadoDto.getId() != null) {
            chamado.setId(chamadoDto.getId());
        }

        if (chamadoDto.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDto.getStatus()));
        chamado.setTitulo(chamadoDto.getTitulo());
        chamado.setObservacoes(chamadoDto.getObservacoes());
        chamado.setDataAbertura(chamadoDto.getDataAbertura());
        chamado.setDataFechamento(chamadoDto.getDataFechamento());

        return chamado;
    }

    public Chamado update(Integer id, ChamadoDto chamadoDto) {
        chamadoDto.setId(id);
        Chamado chamado = findById(id);
        chamado = newChamado(chamadoDto);
        return chamadoRepository.save(chamado);
    }
}
