package com.vacinas.ap1.exceptions;

import java.io.Serial;

public class VacinasRetornoVazioException extends RuntimeException  {
    @Serial
    private static final long serialVersionUID = 1L;

    public VacinasRetornoVazioException(String mensagem) {
        super(mensagem);
    }
}
