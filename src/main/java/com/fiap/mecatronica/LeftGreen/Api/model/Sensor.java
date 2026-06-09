package com.fiap.mecatronica.LeftGreen.Api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensores")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    private String tipo;           // ex: "ALTURA", "DENSIDADE", "CLIMA"
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaMonitoramento area;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Sensor() {
        this.criadoEm = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public AreaMonitoramento getArea() { return area; }
    public void setArea(AreaMonitoramento area) { this.area = area; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}