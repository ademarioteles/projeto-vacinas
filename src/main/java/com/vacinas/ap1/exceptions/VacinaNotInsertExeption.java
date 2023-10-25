package com.vacinas.ap1.exceptions;

import java.io.Serial;

public class VacinaNotInsertExeption extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public VacinaNotInsertExeption(String mensagem){
        super(mensagem);
    }
}
