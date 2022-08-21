package com.alcideswenner.apiterritorios.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alcideswenner.apiterritorios.dto.PermissaoDTO;
import com.alcideswenner.apiterritorios.repositories.PermissaoRepository;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Optional<List<PermissaoDTO>> listaPermissoes() {
        return Optional.of(permissaoRepository.findAll().stream().distinct().map(e -> new PermissaoDTO(e)).toList());
    }
}
