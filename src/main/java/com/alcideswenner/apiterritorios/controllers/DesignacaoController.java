package com.alcideswenner.apiterritorios.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.dto.RankingDTO;
import com.alcideswenner.apiterritorios.entities.Designacao;
import com.alcideswenner.apiterritorios.exceptions.DesignacaoNotFoundException;
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
    public ResponseEntity<?> listaMapas(
            @RequestParam(value = "userAtual", required = false) Long idUser,
            @RequestParam(value = "bairro", required = false) String nome) {
        Optional<List<MapaDTO>> listaOpt = mapaService.listaMapas(idUser, nome);
        return listaOpt.isPresent()
                ? ResponseEntity.ok().body(listaOpt.get())
                : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyAuthority('SYSTEM','ADMIN', 'USER')")
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

    @PreAuthorize("hasAnyAuthority('SYSTEM','ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> concluirDesignacao(@PathVariable("id") Long id) {
        Optional<Boolean> optDesignacao = Optional.empty();

        try {
            optDesignacao = designacaoService.concluirDesignacao(id);
        } catch (DesignacaoNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return optDesignacao.get().booleanValue()
                ? ResponseEntity.ok("Designação Concluída")
                : ResponseEntity.badRequest().body("Designação já concluída");
    }

    @GetMapping(value = "/ranking-bairro-mais-trabalhados")
    public ResponseEntity<?> retornaRankingBairroMaisTrabalhadoPorAnoCorrente() {
        Optional<List<RankingDTO>> lista = mapaService
                .rankingBairros();
        return lista.isPresent()
                ? ResponseEntity.ok(lista.get())
                : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "historico-designacao")
    public ResponseEntity<?> findAllHistoricoDesignacao() {
        return designacaoService.findAllHistoricoDesignacao().isPresent()
                ? ResponseEntity.ok().body(designacaoService.findAllHistoricoDesignacao().get())
                : ResponseEntity.notFound().build();
    }

}
