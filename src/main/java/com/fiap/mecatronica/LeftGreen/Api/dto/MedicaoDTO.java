package com.fiap.mecatronica.LeftGreen.Api.dto;

import java.time.LocalDateTime;

import com.fiap.mecatronica.LeftGreen.Api.model.Medicao;
import com.fiap.mecatronica.LeftGreen.Api.model.StatusMedicao;

public class MedicaoDTO {
    
    private Long id;
    private Long areaId;
    private String areaCodigo;
    private Double alturaVegetacao;
    private Double densidade;
    private Double temperatura;
    private Double umidade;
    private String tipoVegetacao;
    private Double inclinacaoTerreno;
    private LocalDateTime dataColeta;
    private String sensorId;
    private String observacoes;
    private StatusMedicao status;
    private String statusDescricao;

    public MedicaoDTO() {
    }

    public MedicaoDTO(Medicao medicao) {
        this.id = medicao.getId();
        this.areaId = medicao.getArea().getId();
        this.areaCodigo = medicao.getArea().getCodigo();
        this.alturaVegetacao = medicao.getAlturaVegetacao();
        this.densidade = medicao.getDensidade();
        this.temperatura = medicao.getTemperatura();
        this.umidade = medicao.getUmidade();
        this.tipoVegetacao = medicao.getTipoVegetacao();
        this.inclinacaoTerreno = medicao.getInclinacaoTerreno();
        this.dataColeta = medicao.getDataColeta();
        this.sensorId = medicao.getSensorId();
        this.observacoes = medicao.getObservacoes();
        
        this.status = medicao.getStatus();
        this.statusDescricao = medicao.getStatus() != null ? medicao.getStatus().getDescricao() : null;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAreaId() { return areaId; }
    public void setAreaId(Long areaId) { this.areaId = areaId; }

    public String getAreaCodigo() { return areaCodigo; }
    public void setAreaCodigo(String areaCodigo) { this.areaCodigo = areaCodigo; }

    public Double getAlturaVegetacao() { return alturaVegetacao; }
    public void setAlturaVegetacao(Double alturaVegetacao) { this.alturaVegetacao = alturaVegetacao; }

    public Double getDensidade() { return densidade; }
    public void setDensidade(Double densidade) { this.densidade = densidade; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Double getUmidade() { return umidade; }
    public void setUmidade(Double umidade) { this.umidade = umidade; }

    public String getTipoVegetacao() { return tipoVegetacao; }
    public void setTipoVegetacao(String tipoVegetacao) { this.tipoVegetacao = tipoVegetacao; }

    public Double getInclinacaoTerreno() { return inclinacaoTerreno; }
    public void setInclinacaoTerreno(Double inclinacaoTerreno) { this.inclinacaoTerreno = inclinacaoTerreno; }

    public LocalDateTime getDataColeta() { return dataColeta; }
    public void setDataColeta(LocalDateTime dataColeta) { this.dataColeta = dataColeta; }

    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public StatusMedicao getStatus() { return status; }
    public void setStatus(StatusMedicao status) { this.status = status; }

    public String getStatusDescricao() { return statusDescricao; }
    public void setStatusDescricao(String statusDescricao) { this.statusDescricao = statusDescricao; }
}