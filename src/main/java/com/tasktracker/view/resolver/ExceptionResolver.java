package com.tasktracker.view.resolver;

import com.tasktracker.model.dto.response.exception.AbstractApiException;
import com.tasktracker.model.dto.response.exception.InvalidJwtException;
import com.tasktracker.model.dto.response.exception.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.security.SignatureException;


@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<?> handle(AbstractApiException cause, WebRequest request) {
        return cause.asResponse();
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handle(SignatureException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(io.jsonwebtoken.SignatureException.class)
    public ResponseEntity<?> handle(io.jsonwebtoken.SignatureException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handle(MalformedJwtException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handle(ExpiredJwtException cause, WebRequest request) {
        return new InvalidJwtException().asResponse();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handle(UsernameNotFoundException cause, WebRequest request) {
        return new NotFoundException().asResponse();
    }
}
