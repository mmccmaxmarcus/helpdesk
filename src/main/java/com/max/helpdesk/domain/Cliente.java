package com.max.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.max.helpdesk.domain.dto.ClienteDto;
import com.max.helpdesk.domain.dto.TecnicoDto;
import com.max.helpdesk.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa implements Serializable {
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(ClienteDto clienteDto) {
        this.id = clienteDto.getId();
        this.nome = clienteDto.getNome();
        this.cpf = clienteDto.getCpf();
        this.email = clienteDto.getEmail();
        this.senha = clienteDto.getSenha();
        this.perfis = clienteDto.getPerfis().stream().map(perfil -> perfil.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = clienteDto.getDataCriacao();
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
