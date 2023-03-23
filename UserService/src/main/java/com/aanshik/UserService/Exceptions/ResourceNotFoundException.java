package com.aanshik.UserService.Exceptions;

public class ResourceNotFoundException extends RuntimeException{




    public ResourceNotFoundException(String resource, String value) {
        super(resource+" not found with id = "+value);
    }
}
