package com.alcideswenner.apiterritorios.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceTerritorioExceptionHandler {

    @ExceptionHandler(value = TerritorioException.class)
    public ResponseEntity<StandardErrorException> resourceExceptionHandlerTerritorio(
            TerritorioException e,
            HttpServletRequest request) {
        return ResponseEntity
                .status(e.getStandardErrorException().getStatus())
                .body(e.getStandardErrorException());
    }

    @ExceptionHandler(value = UserAuthenticationIsRealException.class)
    public ResponseEntity<String> resourceExceptionHandlerUserAuthenticationIsReal(
            UserAuthenticationIsRealException e,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }
}
