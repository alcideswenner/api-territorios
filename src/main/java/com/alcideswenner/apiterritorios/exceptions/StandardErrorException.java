package com.alcideswenner.apiterritorios.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.http.HttpStatus;

public class StandardErrorException implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;
    
    public StandardErrorException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public StandardErrorException() {

    }

    public String getTimestamp() {
        Timestamp instant= Timestamp.from(Instant.now());  
        return instant.toString();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
