package com.fiap.mecatronica.LeftGreen.Api.model;

public enum StatusVegetacao {
    NORMAL("Normal - Vegetação controlada"),
    ATENCAO("Atenção - Monitoramento recomendado"),
    URGENTE("Urgente - Intervenção necessária");

    private final String descricao;

    StatusVegetacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
