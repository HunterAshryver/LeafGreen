package com.fiap.mecatronica.LeftGreen.Api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fiap.mecatronica.LeftGreen.Api.model.AreaMonitoramento;
import com.fiap.mecatronica.LeftGreen.Api.repository.AreaMonitoramentoRepository;
import com.fiap.mecatronica.LeftGreen.Api.service.MedicaoService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AreaMonitoramentoRepository areaRepository;

    @Autowired
    private MedicaoService medicaoService;

    @Override
    public void run(String... args) {
        if (areaRepository.count() > 0) {
            return;
        }

        System.out.println("Inicializando banco de dados com dados de exemplo...");

        criarAreaExemplo("SP-280-KM-45", "SP-280 - Rodovia Castello Branco", 45.0, 47.0, "Pista Norte", "Plano");
        criarAreaExemplo("SP-280-KM-50", "SP-280 - Rodovia Castello Branco", 50.0, 52.0, "Pista Sul", "Inclinado");
        criarAreaExemplo("SP-348-KM-20", "SP-348 - Rodovia dos Bandeirantes", 20.0, 22.0, "Pista Norte", "Misto");
        criarAreaExemplo("SP-330-KM-100", "SP-330 - Rodovia Anhanguera", 100.0, 102.0, "Pista Sul", "Plano");
        criarAreaExemplo("SP-070-KM-35", "SP-070 - Rodovia Ayrton Senna", 35.0, 37.0, "Pista Norte", "Inclinado");
        criarAreaExemplo("SP-021-KM-15", "SP-021 - Rodoanel Mário Covas", 15.0, 17.0, "Pista Leste", "Misto");

        System.out.println("Áreas de monitoramento criadas com sucesso.");

        System.out.println("Simulando coleta inicial de dados dos sensores...");
        medicaoService.simularColetaTodasAreas();
        
        System.out.println("Inicialização concluída. Sistema pronto para uso.");
    }

    private void criarAreaExemplo(String codigo, String rodovia, Double kmInicial, 
                                 Double kmFinal, String localizacao, String tipoTerreno) {
        AreaMonitoramento area = new AreaMonitoramento(
            codigo, rodovia, kmInicial, kmFinal, localizacao, tipoTerreno
        );
        
        if (tipoTerreno.equals("Plano")) {
            area.setComplexidade(20.0);
        } else if (tipoTerreno.equals("Inclinado")) {
            area.setComplexidade(65.0);
        } else {
            area.setComplexidade(45.0);
        }
        
        areaRepository.save(area);
    }
}
