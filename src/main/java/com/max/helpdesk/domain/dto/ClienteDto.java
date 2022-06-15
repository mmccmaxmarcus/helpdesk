package com.max.helpdesk.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.max.helpdesk.domain.Cliente;
import com.max.helpdesk.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDto implements Serializable {
    private Integer id;
    @NotNull(message = "O campo NOME é requerido")
    private String nome;
    @NotNull(message = "O campo CPF é requerido")
    private String cpf;
    @NotNull(message = "O campo EMAIL é requerido")
    private String email;
    @NotNull(message = "O campo SENHA é requerido")
    private String senha;
    private Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.perfis = cliente.getPerfis().stream().map(perfil -> perfil.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = cliente.getDataCriacao();
    }

    public ClienteDto() {
        // setPerfis(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void setPerfis(Set<Integer> perfis) {
        this.perfis.addAll(perfis);
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
