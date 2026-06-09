package com.fiap.mecatronica.LeftGreen.Api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mecatronica.LeftGreen.Api.dto.MedicaoDTO;
import com.fiap.mecatronica.LeftGreen.Api.model.AreaMonitoramento;
import com.fiap.mecatronica.LeftGreen.Api.model.Medicao;
import com.fiap.mecatronica.LeftGreen.Api.model.StatusMedicao;
import com.fiap.mecatronica.LeftGreen.Api.repository.AreaMonitoramentoRepository;
import com.fiap.mecatronica.LeftGreen.Api.repository.MedicaoRepository;

@Service
public class MedicaoService {

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private AreaMonitoramentoRepository areaRepository;

    @Autowired
    private AreaMonitoramentoService areaService;

    private final Random random = new Random();

    private StatusMedicao calcularStatus(Double altura, Double densidade) {
        if (altura == null || densidade == null) {
            return StatusMedicao.NORMAL;
        }
        if (altura > 1.5 || densidade > 70.0) {
            return StatusMedicao.CRITICO;
        } else if (altura > 1.0 || densidade > 50.0) {
            return StatusMedicao.ALERTA;
        } else {
            return StatusMedicao.NORMAL;
        }
    }

    public List<MedicaoDTO> listarTodas() {
        return medicaoRepository.findAll()
                .stream()
                .map(medicao -> {
                    try {
                        return new MedicaoDTO(medicao);
                    } catch (Exception e) {
                        // Log simples para debug
                        System.err.println("Erro ao converter medição ID " + medicao.getId() + ": " + e.getMessage());
                        return null;
                    }
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public List<MedicaoDTO> listarPorArea(Long areaId) {
        return medicaoRepository.findByAreaIdOrderByDataColetaDesc(areaId)
                .stream()
                .map(MedicaoDTO::new)
                .collect(Collectors.toList());
    }

    public MedicaoDTO buscarPorId(Long id) {
        Medicao medicao = medicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medição não encontrada"));
        return new MedicaoDTO(medicao);
    }

    public MedicaoDTO registrarMedicao(Long areaId, Medicao medicao) {
        AreaMonitoramento area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        
        medicao.setArea(area);
        medicao.setDataColeta(LocalDateTime.now());
        
        StatusMedicao statusCalculado = calcularStatus(medicao.getAlturaVegetacao(), medicao.getDensidade());
        medicao.setStatus(statusCalculado);
        
        Medicao medicaoSalva = medicaoRepository.save(medicao);
        
        areaService.atualizarStatusArea(areaId, medicao.getDensidade(), medicao.getAlturaVegetacao());
        
        return new MedicaoDTO(medicaoSalva);
    }

    public MedicaoDTO simularColetaDados(Long areaId) {
        AreaMonitoramento area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
        
        double fatorTerreno = calcularFatorTerreno(area.getTipoTerreno());
        
        Medicao medicao = new Medicao();
        medicao.setArea(area);
        medicao.setAlturaVegetacao(0.3 + (random.nextDouble() * 1.7 * fatorTerreno));
        medicao.setDensidade(20.0 + (random.nextDouble() * 70.0));
        medicao.setTemperatura(15.0 + (random.nextDouble() * 20.0));
        medicao.setUmidade(30.0 + (random.nextDouble() * 60.0));
        medicao.setInclinacaoTerreno(area.getComplexidade() != null ? area.getComplexidade() : 0.0);
        medicao.setSensorId("SENSOR-" + area.getCodigo() + "-" + System.currentTimeMillis());
        
        String[] tiposVegetacao = {"Gramínea", "Arbustiva", "Mista"};
        medicao.setTipoVegetacao(tiposVegetacao[random.nextInt(tiposVegetacao.length)]);
        
        if (medicao.getAlturaVegetacao() > 1.2) {
            medicao.setObservacoes("Vegetação alta detectada - recomendada intervenção");
        } else if (medicao.getDensidade() > 60) {
            medicao.setObservacoes("Alta densidade de vegetação - monitorar crescimento");
        } else {
            medicao.setObservacoes("Condições normais");
        }

        StatusMedicao statusCalculado = calcularStatus(medicao.getAlturaVegetacao(), medicao.getDensidade());
        medicao.setStatus(statusCalculado);
        
        Medicao medicaoSalva = medicaoRepository.save(medicao);
        
        areaService.atualizarStatusArea(areaId, medicao.getDensidade(), medicao.getAlturaVegetacao());
        
        return new MedicaoDTO(medicaoSalva);
    }

    public List<MedicaoDTO> simularColetaTodasAreas() {
        return areaRepository.findAll()
                .stream()
                .map(area -> simularColetaDados(area.getId()))
                .collect(Collectors.toList());
    }

    private double calcularFatorTerreno(String tipoTerreno) {
        if (tipoTerreno == null) return 1.0;
        switch (tipoTerreno.toLowerCase()) {
            case "inclinado": return 1.3;
            case "misto": return 1.15;
            default: return 1.0;
        }
    }

    public void deletar(Long id) {
        medicaoRepository.deleteById(id);
    }

    /**
     * Lista medições filtrando por sensorId
     */
    public List<MedicaoDTO> listarPorSensor(String sensorId) {
        if (sensorId == null || sensorId.isEmpty()) {
            return List.of();
        }
        
        return medicaoRepository.findAll()
                .stream()
                .filter(m -> m.getSensorId() != null && m.getSensorId().equals(sensorId))
                .map(MedicaoDTO::new)
                .collect(Collectors.toList());
    }
}