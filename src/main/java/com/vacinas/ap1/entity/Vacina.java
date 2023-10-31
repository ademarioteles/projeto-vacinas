package com.vacinas.ap1.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {

    private String id;
    @NotEmpty(message = "O nome da vacina não foi informado!")
    @NotNull(message = "O nome da vacina não foi informado!")
    private String nome;

    @NotEmpty(message = "O fabricante não foi informado!")
    @NotNull(message = "O fabricante da vacina não foi informado!")
    private String fabricante;

    @NotEmpty(message = "O lote não foi informado!")
    @NotNull(message = "O lote da vacina não foi informado!")
    private String lote;

    @NotEmpty(message = "A data de validade não foi informada!")
    @NotNull(message = "A data de validade não foi informada!")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message = "A data deve ter um formato igual a 2023-07-29")
    private String data_validade;

    @NotNull(message = "O número de doses não foi informado!")
    private Integer numero_de_doses;

    private Integer intervalo_doses;

}
