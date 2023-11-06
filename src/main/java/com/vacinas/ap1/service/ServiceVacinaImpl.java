package com.vacinas.ap1.service;

import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.exceptions.VacinaNotFoundException;
import com.vacinas.ap1.exceptions.VacinaNotInsertExeption;
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
        Vacina vacinaEncontrada = null;
        for (Vacina vacin : obterTodos()) {//Como o id  é uma string não é possível utilizar o FindById
            if (vacin.getId().equals(id)) {
                return vacin;
            }
        }
        if(vacinaEncontrada == null || this.obterTodos().isEmpty() || this.obterTodos() == null){
            throw  new VacinaNotFoundException("Vacina não encontrado!");
        }
        return vacinaEncontrada;
    }

    @Override
    public void inserir(Vacina vacina) {
        if(this.existeVacina(vacina)){
            throw new VacinaNotInsertExeption("Vacina existente na base!");
        }
        vacinaRepository.insert(vacina);
    }

    @Override
    public boolean existeVacina(Vacina vacina) {
        for (Vacina vacin : obterTodos()) {//Verificação ignorando o Id
            if (vacin.equals(vacina)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deletarPorId(String id) {
        if (this.obterPorId(id) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        vacinaRepository.deleteById(id);
    }

    @Override
    public void deletarTodos() {
        if (this.obterTodos().isEmpty()) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        vacinaRepository.deleteAll();
    }

    @Override
    public Vacina compareEdite(Vacina vacinaU, Vacina vacinaD) {
        if (vacinaU.getNome() == null) {
            vacinaU.setNome(vacinaD.getNome());
        }if (vacinaU.getFabricante() == null) {
            vacinaU.setFabricante(vacinaD.getFabricante());
        }  if (vacinaU.getLote() == null) {
            vacinaU.setLote(vacinaD.getLote());
        } if (vacinaU.getData_validade() == null) {
            vacinaU.setData_validade(vacinaD.getData_validade());
        } if (vacinaU.getNumero_de_doses() == null) {
            vacinaU.setNumero_de_doses(vacinaD.getNumero_de_doses());
        }
        return vacinaU;
    }

    @Override
    public void editarParcial(Vacina vacina) {
        Vacina vacinaEncontrada = this.obterPorId(vacina.getId());
        if (vacina.getId() == null || vacina == null || vacinaEncontrada == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)");
        }
        vacina.setId(vacina.getId());
        vacina = this.compareEdite(vacina,vacinaEncontrada);
        vacinaRepository.save(vacina);
    }

    @Override
    public void editarParcialPorId(String id, Vacina vacina) {
        Vacina vacinaEncontrada = this.obterPorId(id);
        if (id == null || vacina == null || vacinaEncontrada == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)");
        }
        vacina.setId(id);
        vacina = this.compareEdite(vacina,vacinaEncontrada);
        vacinaRepository.save(vacina);
    }

    @Override
    public void editar(Vacina vacina) {
        if (vacina.getId() == null || this.obterPorId(vacina.getId()) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)");
        }
        vacinaRepository.save(vacina);
    }
    @Override
    public void editarPorId(String id, Vacina vacina) {
        vacina.setId(id);
        this.editar(vacina);
    }


}