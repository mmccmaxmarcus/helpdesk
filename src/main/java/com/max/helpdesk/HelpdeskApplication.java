package com.max.helpdesk;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null,"Max Marcus", "044.254.403-07", "mmccmaxmarcus@gmail.com", "123");
		tec1.addPerfis(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Maria Joana", "000.111.222-33", "maria@maria", "123");

		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamada 01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
	}
}
