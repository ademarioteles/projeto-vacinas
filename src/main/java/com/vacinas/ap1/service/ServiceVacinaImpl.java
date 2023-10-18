package com.vacinas.ap1.service;

import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.exceptions.LoteRepetidoException;
import com.vacinas.ap1.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVacinaImpl implements ServiceVacina {
    @Autowired
    VacinaRepository vacinaRepository;

    @Override
    public List<Vacina> obterTodos() {
        return vacinaRepository.findAll();
    }

    @Override
    public Vacina obterPorId(String id) {
        for (Vacina vacin : obterTodos()) {
            if (vacin.getId().equals(id)) {
                return vacin;
            }
        }
        return null;
    }

    @Override
    public void inserir(Vacina vacina) {
        vacinaRepository.insert(vacina);
    }

    @Override
    public boolean encontrarVacina(Vacina vacina) {
        for (Vacina vacin : obterTodos()) {
            if (vacina.getLote().equals(vacin.getLote())) {
                return true;
            }
        }
        return false;
    }

}