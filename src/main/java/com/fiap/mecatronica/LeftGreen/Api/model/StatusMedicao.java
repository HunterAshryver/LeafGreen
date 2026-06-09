package com.fiap.mecatronica.LeftGreen.Api.model;

public enum StatusMedicao {
    NORMAL("Normal - Dentro dos limites"),
    ALERTA("Alerta - Monitoramento recomendado"),
    CRITICO("Crítico - Intervenção urgente");

    private final String descricao;

    StatusMedicao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}