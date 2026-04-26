package com.fiap.mecatronica.LeftGreen.Api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "areas_monitoramento")
public class AreaMonitoramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String rodovia;

    @Column(nullable = false)
    private Double kmInicial;

    @Column(nullable = false)
    private Double kmFinal;

    @Column(nullable = false)
    private String localizacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVegetacao status;

    private String tipoTerreno;

    private Double densidade;

    private Double alturaMedia;

    private Double complexidade;

    @Column(name = "ultima_medicao")
    private LocalDateTime ultimaMedicao;

    @Column(name = "proxima_intervencao")
    private LocalDateTime proximaIntervencao;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    public AreaMonitoramento() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
        this.status = StatusVegetacao.NORMAL;
    }

    public AreaMonitoramento(String codigo, String rodovia, Double kmInicial, Double kmFinal, 
                           String localizacao, String tipoTerreno) {
        this();
        this.codigo = codigo;
        this.rodovia = rodovia;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
        this.localizacao = localizacao;
        this.tipoTerreno = tipoTerreno;
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
        this.atualizadoEm = LocalDateTime.now();
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

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
