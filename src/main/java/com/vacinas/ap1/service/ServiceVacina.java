package com.vacinas.ap1.service;

import com.vacinas.ap1.entity.Vacina;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ServiceVacina {

    List<Vacina> obterTodos();
    Vacina obterPorId(String id);
    void inserir(Vacina vacina);
    boolean existeVacina(Vacina vacina);
    void deletarPorId(String id);
    void deletarTodos();
    Vacina compareEdite(Vacina vacinaU, Vacina vacinaD);
    void editar(Vacina vacina);
    void editarPorId(String id, Vacina vacina);
    void editarParcialPorId(String id, Vacina vacina);
    void editarParcial(Vacina vacina);
}