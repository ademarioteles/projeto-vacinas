package com.vacinas.ap1.service;

import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVacina {
    @Autowired
    VacinaRepository vacinaRepository;
    public List<Vacina> obterTodos(){
        return vacinaRepository.findAll();
    }
    public void inserir(Vacina vacina){
        vacinaRepository.insert(vacina);
    }
}
