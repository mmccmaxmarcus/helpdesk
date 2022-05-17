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
public class HelpdeskApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}
}
