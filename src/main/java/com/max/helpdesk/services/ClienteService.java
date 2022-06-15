package com.max.helpdesk.services;

import com.max.helpdesk.domain.Cliente;
import com.max.helpdesk.domain.Pessoa;
import com.max.helpdesk.domain.dto.ClienteDto;
import com.max.helpdesk.repository.ClienteRepository;
import com.max.helpdesk.repository.PessoaRepository;
import com.max.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.max.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
    }

    public List<ClienteDto> findAll() {
        return clienteRepository.findAll().stream().map(c -> new ClienteDto(c)).collect(Collectors.toList());
    }

    public Cliente create(ClienteDto clienteDto) {
        clienteDto.setId(null);
        validaPorCpfOuEmail(clienteDto);
        Cliente cliente = new Cliente(clienteDto);
        return clienteRepository.save(cliente);
    }

    private void validaPorCpfOuEmail(ClienteDto clienteDto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDto.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != clienteDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        pessoa = pessoaRepository.findByEmail(clienteDto.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != clienteDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }

    public Cliente update(Integer id, ClienteDto clienteDto) {
        clienteDto.setId(id);
        Cliente cliente = findById(id);
        validaPorCpfOuEmail(clienteDto);
        cliente = new Cliente(clienteDto);
        return pessoaRepository.save(cliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if (cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordem de serviço e não pode ser deletado");
        }
        clienteRepository.deleteById(id);
    }
}
