package com.vacinas.ap1.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Vacina {

    private String nome;

    private String fabricante;

    private String lote;

    private String data_validade;

    private Integer numero_de_doses;

    private Integer intervalo_doses;

}
