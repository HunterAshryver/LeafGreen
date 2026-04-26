package com.fiap.mecatronica.LeftGreen.Api.dto;

import java.time.LocalDateTime;

import com.fiap.mecatronica.LeftGreen.Api.model.AreaMonitoramento;
import com.fiap.mecatronica.LeftGreen.Api.model.StatusVegetacao;

public class AreaMonitoramentoDTO {
    
    private Long id;
    private String codigo;
    private String rodovia;
    private Double kmInicial;
    private Double kmFinal;
    private String localizacao;
    private StatusVegetacao status;
    private String statusDescricao;
    private String tipoTerreno;
    private Double densidade;
    private Double alturaMedia;
    private Double complexidade;
    private LocalDateTime ultimaMedicao;
    private LocalDateTime proximaIntervencao;
    private Long totalMedicoes;

    public AreaMonitoramentoDTO() {
    }

    public AreaMonitoramentoDTO(AreaMonitoramento area) {
        this.id = area.getId();
        this.codigo = area.getCodigo();
        this.rodovia = area.getRodovia();
        this.kmInicial = area.getKmInicial();
        this.kmFinal = area.getKmFinal();
        this.localizacao = area.getLocalizacao();
        this.status = area.getStatus();
        this.statusDescricao = area.getStatus().getDescricao();
        this.tipoTerreno = area.getTipoTerreno();
        this.densidade = area.getDensidade();
        this.alturaMedia = area.getAlturaMedia();
        this.complexidade = area.getComplexidade();
        this.ultimaMedicao = area.getUltimaMedicao();
        this.proximaIntervencao = area.getProximaIntervencao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRodovia() {
        return rodovia;
    }

    public void setRodovia(String rodovia) {
        this.rodovia = rodovia;
    }

    public Double getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Double kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Double getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Double kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public StatusVegetacao getStatus() {
        return status;
    }

    public void setStatus(StatusVegetacao status) {
        this.status = status;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public Double getDensidade() {
        return densidade;
    }

    public void setDensidade(Double densidade) {
        this.densidade = densidade;
    }

    public Double getAlturaMedia() {
        return alturaMedia;
    }

    public void setAlturaMedia(Double alturaMedia) {
        this.alturaMedia = alturaMedia;
    }

    public Double getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(Double complexidade) {
        this.complexidade = complexidade;
    }

    public LocalDateTime getUltimaMedicao() {
        return ultimaMedicao;
    }

    public void setUltimaMedicao(LocalDateTime ultimaMedicao) {
        this.ultimaMedicao = ultimaMedicao;
    }

    public LocalDateTime getProximaIntervencao() {
        return proximaIntervencao;
    }

    public void setProximaIntervencao(LocalDateTime proximaIntervencao) {
        this.proximaIntervencao = proximaIntervencao;
    }

    public Long getTotalMedicoes() {
        return totalMedicoes;
    }

    public void setTotalMedicoes(Long totalMedicoes) {
        this.totalMedicoes = totalMedicoes;
    }
}
