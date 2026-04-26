package com.fiap.mecatronica.LeftGreen.Api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.mecatronica.LeftGreen.Api.dto.MedicaoDTO;
import com.fiap.mecatronica.LeftGreen.Api.model.Medicao;
import com.fiap.mecatronica.LeftGreen.Api.service.MedicaoService;

@RestController
@RequestMapping("/api/medicoes")
@CrossOrigin(origins = "*")
public class MedicaoController {

    @Autowired
    private MedicaoService medicaoService;

    @GetMapping
    public ResponseEntity<List<MedicaoDTO>> listarTodas() {
        List<MedicaoDTO> medicoes = medicaoService.listarTodas();
        return ResponseEntity.ok(medicoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicaoDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(medicaoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<List<MedicaoDTO>> listarPorArea(@PathVariable Long areaId) {
        return ResponseEntity.ok(medicaoService.listarPorArea(areaId));
    }

    @PostMapping("/area/{areaId}")
    public ResponseEntity<MedicaoDTO> registrarMedicao(
            @PathVariable Long areaId,
            @RequestBody Medicao medicao) {
        try {
            MedicaoDTO medicaoSalva = medicaoService.registrarMedicao(areaId, medicao);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicaoSalva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/simular/{areaId}")
    public ResponseEntity<MedicaoDTO> simularColetaDados(@PathVariable Long areaId) {
        try {
            MedicaoDTO medicao = medicaoService.simularColetaDados(areaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/simular-todas")
    public ResponseEntity<List<MedicaoDTO>> simularColetaTodasAreas() {
        return ResponseEntity.ok(medicaoService.simularColetaTodasAreas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            medicaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
