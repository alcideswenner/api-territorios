package com.alcideswenner.apiterritorios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alcideswenner.apiterritorios.dto.PermissaoDTO;
import com.alcideswenner.apiterritorios.repositories.PermissaoRepository;

@RestController
@RequestMapping(value = "/permissoes")
public class PermissaoController {
    @Autowired
    private PermissaoRepository permissaoRepository;

    @GetMapping
    public List<PermissaoDTO> teste(){
        return permissaoRepository.findAll().stream().map(e-> new PermissaoDTO(e)).toList();
    }
}
