package com.fiap.mecatronica.LeftGreen.Api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.LeftGreen.Api.model.AreaMonitoramento;
import com.fiap.mecatronica.LeftGreen.Api.model.Medicao;

@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Long> {
    
    List<Medicao> findByAreaOrderByDataColetaDesc(AreaMonitoramento area);
    
    List<Medicao> findByAreaIdOrderByDataColetaDesc(Long areaId);
    
    List<Medicao> findByDataColetaBetween(LocalDateTime inicio, LocalDateTime fim);
    
    List<Medicao> findTop10ByAreaOrderByDataColetaDesc(AreaMonitoramento area);
}
