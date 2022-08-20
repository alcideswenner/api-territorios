package com.alcideswenner.apiterritorios.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;


@RestController
@RequestMapping(value = "/designacoes")
public class DesignacaoController {

    @Autowired
    private MapaRepository mapaRepository;

    @GetMapping(value = "/find-mapas")
    public List<MapaDTO> listaMapas() {
        return mapaRepository.findAll().stream().map(e -> new MapaDTO(e)).toList();
    }
}
