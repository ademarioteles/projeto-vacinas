package com.vacinas.ap1.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {


    @NotEmpty(message = "O nome da vacina não foi informado!")
    private String nome;

    @NotEmpty(message = "O fabricante não foi informado!")
    private String fabricante;

    @NotEmpty(message = "O lote não foi informado!")
    private String lote;

    @NotEmpty(message = "A data de validade não foi informada!")
    private String data_validade;

    @NotNull(message = "O número de doses não foi informado!")
    private Integer numero_de_doses;

    private Integer intervalo_doses;

}
