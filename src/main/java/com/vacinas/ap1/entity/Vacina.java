package com.vacinas.ap1.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacina vacina = (Vacina) o;
        return Objects.equals(nome, vacina.nome) && Objects.equals(fabricante, vacina.fabricante) && Objects.equals(lote, vacina.lote) && Objects.equals(data_validade, vacina.data_validade) && Objects.equals(numero_de_doses, vacina.numero_de_doses) && Objects.equals(intervalo_doses, vacina.intervalo_doses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, fabricante, lote, data_validade, numero_de_doses, intervalo_doses);
    }
}
