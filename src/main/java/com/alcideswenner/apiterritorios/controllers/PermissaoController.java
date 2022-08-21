package com.alcideswenner.apiterritorios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alcideswenner.apiterritorios.services.PermissaoService;

@RestController
@RequestMapping(value = "/permissoes")
public class PermissaoController {
    @Autowired
    private PermissaoService permissaoService;

    @GetMapping
    public ResponseEntity<?> listaPermissoes() {
        return permissaoService.listaPermissoes().isPresent()
                ? ResponseEntity.ok().body(permissaoService.listaPermissoes().get())
                : ResponseEntity.notFound().build();
    }
}
