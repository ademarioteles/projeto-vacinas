package com.vacinas.ap1.service;

import com.vacinas.ap1.entity.Vacina;
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
        for (Vacina vacin : obterTodos()) {//Como o id  é uma string não é possível utilizar o FindById
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
    public boolean existeVacina(Vacina vacina) {
        for (Vacina vacin : obterTodos()) {//Verificação ignorando o Id
            if (vacin.getNome().equals(vacina.getNome()) &&
            vacin.getFabricante().equals(vacina.getFabricante()) &&
            vacin.getLote().equals(vacina.getLote()) &&
            vacin.getData_validade().equals(vacina.getData_validade()) &&
            vacin.getNumero_de_doses().equals(vacina.getNumero_de_doses())&&
            vacin.getIntervalo_doses().equals(vacina.getIntervalo_doses())
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deletarPorId(String id) {
        vacinaRepository.deleteById(id);
    }

    @Override
    public void deletarTodos() {
        vacinaRepository.deleteAll();
    }

    @Override
    public void editar(Vacina vacina) {
        vacinaRepository.save(vacina);
    }


}