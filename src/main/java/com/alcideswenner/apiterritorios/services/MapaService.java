package com.alcideswenner.apiterritorios.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;

@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    public Optional<List<MapaDTO>> listaMapas() {
        return Optional.of(mapaRepository.findAll().stream().map(e -> new MapaDTO(e)).toList());
    }

    public Optional<MapaDTO> findMapaByID(Long id) {
        return Optional.of(mapaRepository
                .findById(id)
                .stream()
                .map(e -> new MapaDTO(e))
                .findFirst()
                .orElseGet(null));
    }
}
