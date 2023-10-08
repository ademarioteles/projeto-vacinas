package com.vacinas.ap1.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {
    //Fabricante
    private String fabricante;

    //Lote
    private String lote;

    //Data da validade
    private String data_validade;

    //Número de doses
    private Integer total_de_doses;

    //Intervalo minimo entre doses em dias
    private Integer intervalo_entre_doses;

}
