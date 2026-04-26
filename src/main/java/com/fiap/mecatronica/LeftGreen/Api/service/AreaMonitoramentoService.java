package com.fiap.mecatronica.LeftGreen.Api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.LeftGreen.Api.dto.AreaMonitoramentoDTO;
import com.fiap.mecatronica.LeftGreen.Api.model.AreaMonitoramento;
import com.fiap.mecatronica.LeftGreen.Api.model.StatusVegetacao;
import com.fiap.mecatronica.LeftGreen.Api.repository.AreaMonitoramentoRepository;
import com.fiap.mecatronica.LeftGreen.Api.repository.MedicaoRepository;

@Service
public class AreaMonitoramentoService {

    @Autowired
    private AreaMonitoramentoRepository areaRepository;

    @Autowired
    private MedicaoRepository medicaoRepository;

    public List<AreaMonitoramentoDTO> listarTodas() {
        return areaRepository.findAllByOrderByStatusDesc()
                .stream()
                .map(area -> {
                    AreaMonitoramentoDTO dto = new AreaMonitoramentoDTO(area);
                    dto.setTotalMedicoes((long) medicaoRepository.findByAreaIdOrderByDataColetaDesc(area.getId()).size());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public AreaMonitoramentoDTO buscarPorId(Long id) {
        AreaMonitoramento area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        AreaMonitoramentoDTO dto = new AreaMonitoramentoDTO(area);
        dto.setTotalMedicoes((long) medicaoRepository.findByAreaIdOrderByDataColetaDesc(area.getId()).size());
        return dto;
    }

    public AreaMonitoramentoDTO buscarPorCodigo(String codigo) {
        AreaMonitoramento area = areaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        AreaMonitoramentoDTO dto = new AreaMonitoramentoDTO(area);
        dto.setTotalMedicoes((long) medicaoRepository.findByAreaIdOrderByDataColetaDesc(area.getId()).size());
        return dto;
    }

    public List<AreaMonitoramentoDTO> listarPorStatus(StatusVegetacao status) {
        return areaRepository.findByStatus(status)
                .stream()
                .map(area -> {
                    AreaMonitoramentoDTO dto = new AreaMonitoramentoDTO(area);
                    dto.setTotalMedicoes((long) medicaoRepository.findByAreaIdOrderByDataColetaDesc(area.getId()).size());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public AreaMonitoramentoDTO criar(AreaMonitoramento area) {
        return new AreaMonitoramentoDTO(areaRepository.save(area));
    }

    public AreaMonitoramentoDTO atualizar(Long id, AreaMonitoramento areaAtualizada) {
        AreaMonitoramento area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        
        area.setCodigo(areaAtualizada.getCodigo());
        area.setRodovia(areaAtualizada.getRodovia());
        area.setKmInicial(areaAtualizada.getKmInicial());
        area.setKmFinal(areaAtualizada.getKmFinal());
        area.setLocalizacao(areaAtualizada.getLocalizacao());
        area.setTipoTerreno(areaAtualizada.getTipoTerreno());
        area.setDensidade(areaAtualizada.getDensidade());
        area.setAlturaMedia(areaAtualizada.getAlturaMedia());
        area.setComplexidade(areaAtualizada.getComplexidade());
        area.setStatus(areaAtualizada.getStatus());
        area.setAtualizadoEm(LocalDateTime.now());
        
        return new AreaMonitoramentoDTO(areaRepository.save(area));
    }

    public void deletar(Long id) {
        areaRepository.deleteById(id);
    }

    public void atualizarStatusArea(Long areaId, Double densidade, Double altura) {
        AreaMonitoramento area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        
        if (densidade > 70 || altura > 1.5) {
            area.setStatus(StatusVegetacao.URGENTE);
            area.setProximaIntervencao(LocalDateTime.now().plusDays(7));
        } else if (densidade > 50 || altura > 1.0) {
            area.setStatus(StatusVegetacao.ATENCAO);
            area.setProximaIntervencao(LocalDateTime.now().plusDays(15));
        } else {
            area.setStatus(StatusVegetacao.NORMAL);
            area.setProximaIntervencao(LocalDateTime.now().plusDays(30));
        }
        
        area.setDensidade(densidade);
        area.setAlturaMedia(altura);
        area.setUltimaMedicao(LocalDateTime.now());
        
        areaRepository.save(area);
    }
}
