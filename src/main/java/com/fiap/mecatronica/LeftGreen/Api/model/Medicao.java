package com.fiap.mecatronica.LeftGreen.Api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicoes")
public class Medicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private AreaMonitoramento area;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @Column(nullable = false)
    private Double alturaVegetacao;

    @Column(nullable = false)
    private Double densidade;

    @Column(nullable = false)
    private Double temperatura;

    @Column(nullable = false)
    private Double umidade;

    private String tipoVegetacao;

    private Double inclinacaoTerreno;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @Column(name = "sensor_id_str")
    private String sensorId;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusMedicao status;

    public Medicao() {
        this.dataColeta = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AreaMonitoramento getArea() { return area; }
    public void setArea(AreaMonitoramento area) { this.area = area; }

    public Sensor getSensor() { return sensor; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }

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
}