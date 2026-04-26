package com.fiap.mecatronica.LeftGreen.Api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mecatronica.LeftGreen.Api.model.AreaMonitoramento;
import com.fiap.mecatronica.LeftGreen.Api.model.StatusVegetacao;

@Repository
public interface AreaMonitoramentoRepository extends JpaRepository<AreaMonitoramento, Long> {
    
    Optional<AreaMonitoramento> findByCodigo(String codigo);
    
    List<AreaMonitoramento> findByStatus(StatusVegetacao status);
    
    List<AreaMonitoramento> findByRodoviaContainingIgnoreCase(String rodovia);
    
    List<AreaMonitoramento> findByStatusOrderByAtualizadoEmDesc(StatusVegetacao status);
    
    List<AreaMonitoramento> findAllByOrderByStatusDesc();
}
