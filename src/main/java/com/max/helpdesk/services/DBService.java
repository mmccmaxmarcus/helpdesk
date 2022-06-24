package com.max.helpdesk.services;

import com.max.helpdesk.domain.Chamado;
import com.max.helpdesk.domain.Cliente;
import com.max.helpdesk.domain.Tecnico;
import com.max.helpdesk.enums.Perfil;
import com.max.helpdesk.enums.Prioridade;
import com.max.helpdesk.enums.Status;
import com.max.helpdesk.repository.ChamadoRepository;
import com.max.helpdesk.repository.ClienteRepository;
import com.max.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Max Marcus", "044.254.403-07", "mmccmaxmarcus@gmail.com", encoder.encode("123"));
        tec1.addPerfis(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Maria Joana", "821.392.550-54", "maria@maria", encoder.encode("123"));

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamada 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(chamado1));
    }
}
