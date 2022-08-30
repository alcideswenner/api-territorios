package com.alcideswenner.apiterritorios.exceptions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerritorioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private StandardErrorException standardErrorException;

    public TerritorioException(StandardErrorException e) {
        super(e.getMessage());
        standardErrorException = e;
        log.error(standardErrorException.getMessage());
    }
}
