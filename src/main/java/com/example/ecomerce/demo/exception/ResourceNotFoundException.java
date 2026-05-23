package com.example.ecomerce.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " não encontrado com ID: " + id);
    }
}
