package com.vacinas.ap1.exceptions;

import java.io.Serial;

public class VacinaNotFoundException extends RuntimeException  {
    @Serial
    private static final long serialVersionUID = 1L;

    public VacinaNotFoundException(String mensagem) {
        super(mensagem);
    }
}
