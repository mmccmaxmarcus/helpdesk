package com.max.helpdesk.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Prioridade toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (Prioridade prioridade: Prioridade.values()) {
            if (codigo.equals(prioridade.getCodigo())) {
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválido");
    }
}
