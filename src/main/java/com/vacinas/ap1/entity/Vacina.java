package com.vacinas.ap1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {

    @NotEmpty(message = "O nome da vacina não foi informado!")
    private String nome;

    @NotEmpty(message = "O fabricante da vacina não foi informado!")
    private String fabricante;

    @NotEmpty(message = "O lote da vacina não foi informado!")
    private String lote;

    @NotEmpty(message = "A data da validade da vacina não foi informado!")
    private String data_validade;

    @NotEmpty(message = "O quantidade de doses da vacina não foi informado!")
    private Integer numero_de_doses;

    private Integer intervalo_doses;

}
