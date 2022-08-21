package com.alcideswenner.apiterritorios.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.entities.Designacao;
import com.alcideswenner.apiterritorios.services.DesignacaoService;
import com.alcideswenner.apiterritorios.services.MapaService;

@RestController
@RequestMapping(value = "/designacoes")
public class DesignacaoController {

    @Autowired
    private MapaService mapaService;

    @Autowired
    private DesignacaoService designacaoService;

    @GetMapping(value = "/find-mapas")
    public ResponseEntity<?> listaMapas() {
        return mapaService.listaMapas().isPresent()
                ? ResponseEntity.ok().body(mapaService.listaMapas().get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> saveDesignacao(@RequestBody Designacao designacao) {
        Optional<MapaDTO> mapaOpt = mapaService.findMapaByID(designacao.getMapa().getId());

        if (mapaOpt.isPresent() && mapaOpt.get().getStatus() == true) {
            return ResponseEntity.badRequest().body("Mapa não disponível!");
        } else if (!mapaOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Mapa não existe!");
        }
        return designacaoService.saveDesignacao(designacao).isPresent()
                ? ResponseEntity.ok().body(designacaoService.saveDesignacao(designacao).get())
                : ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return designacaoService.findAll().isPresent()
                ? ResponseEntity.ok().body(designacaoService.findAll().get())
                : ResponseEntity.notFound().build();
    }

}
