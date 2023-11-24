package com.vacinas.ap1.exceptions;

import java.io.Serial;

public class VacinaInvalidParameterException extends RuntimeException  {
    @Serial
    private static final long serialVersionUID = 1L;

    public VacinaInvalidParameterException(String mensagem) {
        super(mensagem);
    }
}
