package com.vacinas.ap1.exceptions;

import com.vacinas.ap1.entity.Mensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MediaTypeNotSupportedStatusException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GenericHandlerException extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericHandlerException.class);

    @ExceptionHandler(LoteRepetidoException.class)
    protected ResponseEntity handleException(LoteRepetidoException e) {
        Mensagem mensagem = new Mensagem("O lote informado já encontra-se cadastrado em nossa base de dados!");
        LOGGER.info("Tratamentação de exceção LoteRepetidoException: " + mensagem);
        return new ResponseEntity(mensagem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VacinaNotFoundException.class)
    protected ResponseEntity handleException(VacinaNotFoundException e) {
        Mensagem mensagem = new Mensagem("Vacina(s) não encontrada(s)");
        LOGGER.info("Tratamentação de exceção VacinaNotFoundException: " + mensagem);
        return new ResponseEntity(mensagem, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Mensagem> message = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            message.add(new Mensagem(error.getDefaultMessage()));
            LOGGER.info("Tratamento de Exceção MethodArgumentNotValidException: " + error.getDefaultMessage());
        }
        return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request);
    }
}