package com.vacinas.ap1.service;

import com.vacinas.ap1.controller.VacinaController;
import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.exceptions.VacinaNotFoundException;
import com.vacinas.ap1.exceptions.VacinaNotInsertExeption;
import com.vacinas.ap1.repository.VacinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ServiceVacinaImpl implements ServiceVacina {
    private static final Logger LOGGER = LoggerFactory.getLogger(VacinaController.class);

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
        throw new VacinaNotFoundException("Vacina existente na base!");
    }

    @Override
    public void inserir(Vacina vacina) {
        if(this.existeVacina(vacina)){
            throw new VacinaNotInsertExeption("Vacina existente na base!");
        }
        else if(this.loteExistente(vacina)){
            throw new VacinaNotInsertExeption("Lote informado existente na base!");
        }else if (this.nomeVacinaExistente(vacina)) {
            throw new VacinaNotInsertExeption("O nome da vacina informado existente na base!");
        } else if (this.dataNoFuturo(vacina.getData_validade())) {
            throw new VacinaNotInsertExeption("A data de validade deve ser no futuro!");
        }

        vacina.setLote(vacina.getLote().toUpperCase());
        vacina.setNome(vacina.getNome().toUpperCase());
        vacina.setFabricante(vacina.getFabricante().toUpperCase());
        vacinaRepository.insert(vacina);
        LOGGER.info("Vacina com id " + vacina.getId() +" foi inserida com sucesso!");
    }

    @Override
    public void editarParcial(Vacina vacina) {
        Vacina vacinaEncontrado = this.obterPorId(vacina.getId());

        if (vacina == null) {
            throw new VacinaNotFoundException("Vacina não encontrado!");
        } else if (this.loteExistente(vacina)) {
        throw new VacinaNotInsertExeption("Lote informado existente na base!");
        } else if (vacina.getNumero_de_doses() <= 0) {
        throw new VacinaNotInsertExeption("O número de doses deve ser maior que zero.");
        } else if (vacina.getIntervalo_doses() < 0) {
        throw new VacinaNotInsertExeption("O intervalo de doses deve ser positivo.");
        } else if (this.nomeVacinaExistente(vacina)) {
        throw new VacinaNotInsertExeption("O nome da vacina informado existente na base!");
        } else if (this.dataNoFuturo(vacina.getData_validade())) {
        throw new VacinaNotInsertExeption("A data de validade deve ser no futuro!");
        }

        vacina.setId(id);
        vacina.setLote(vacina.getLote().toUpperCase());
        vacina.setNome(vacina.getNome().toUpperCase());
        vacina.setFabricante(vacina.getFabricante().toUpperCase());
        vacina = this.compareEdite(vacina, vacinaEncontrado);
        if (!vacina.equals(vacinaEncontrado)) {
            vacinaRepository.save(vacina);
            LOGGER.info("Paciente com id " + vacina.getId() + " editado parcialmente com sucesso!");

        }
    }

    @Override
    public void editarParcialPorId(String id, Vacina vacina) {
        Vacina vacinaEncontrada = this.obterPorId(id);
        if (id == null || vacina == null || vacinaEncontrada == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)");
        } else if (this.loteExistente(vacina)) {
            throw new VacinaNotInsertExeption("Lote informado existente na base!");
        } else if (vacina.getNumero_de_doses() <= 0) {
            throw new VacinaNotInsertExeption("O número de doses deve ser maior que zero.");
        } else if (vacina.getIntervalo_doses() < 0) {
            throw new VacinaNotInsertExeption("O intervalo de doses deve ser positivo.");
        } else if (this.nomeVacinaExistente(vacina)) {
            throw new VacinaNotInsertExeption("O nome da vacina informado existente na base!");
        } else if (this.dataNoFuturo(vacina.getData_validade())) {
            throw new VacinaNotInsertExeption("A data de validade deve ser no futuro!");
        }
        vacina.setId(id);
        vacina.setLote(vacina.getLote().toUpperCase());
        vacina.setNome(vacina.getNome().toUpperCase());
        vacina.setFabricante(vacina.getFabricante().toUpperCase());
        vacina = this.compareEdite(vacina, vacinaEncontrada);
        vacinaRepository.save(vacina);
        LOGGER.info("Vacina com id " + id + " foi editada por completo!");
    }

    @Override
    public void editar(Vacina vacina) {
        if (vacina.getId() == null || this.obterPorId(vacina.getId()) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)");
        }
        vacinaRepository.save(vacina);
        LOGGER.info("Vacina com id "+ vacina.getId() +" foi editada por completo!");
    }
    @Override
    public void editarPorId(String id, Vacina vacina) {
        if (vacina.getId() == null || this.obterPorId(vacina.getId()) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)");
        }
        vacina.setId(id);
        this.editar(vacina);
    }


    @Override
    public boolean existeVacina(Vacina vacina) {
        for (Vacina vacin : obterTodos()) {//Verificação ignorando o Id
            if (vacin.equals(vacina)) {
                LOGGER.info("Vacina com id " + vacin.getId() +" existente na base!");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean loteExistente(Vacina vacina) {
        String loteAtual = vacina.getLote().toUpperCase();  // Converter para maiúsculas para tornar a comparação case-insensitive
        return obterTodos().stream()
                .anyMatch(v -> v.getLote().toUpperCase().equals(loteAtual));
    }

    @Override
    public boolean nomeVacinaExistente(Vacina vacina) {
        String nomeAtual = vacina.getNome().toUpperCase();
        return obterTodos().stream()
                .anyMatch(v -> v.getNome().toUpperCase().equals(nomeAtual));
    }

    @Override
    public boolean dataNoFuturo(String dataValidadeString) {
        try {
            // Converter a String para LocalDate
            LocalDate dataValidade = LocalDate.parse(dataValidadeString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Comparar com a data atual
            return dataValidade.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            // Adicionar log para registrar a exceção
            LOGGER.error("Erro ao converter a data de validade: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void deletarPorId(String id) {
        if (this.obterPorId(id) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        vacinaRepository.deleteById(id);
        LOGGER.info("Vacina com id " + id+" existente na base!");

    }

    @Override
    public void deletarTodos() {
        if (this.obterTodos().isEmpty()) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        vacinaRepository.deleteAll();
        LOGGER.info("Todas as vacinas foram deletadas!");
    }

    @Override
    public Vacina compareEdite(Vacina vacinaU, Vacina vacinaD) {//Preenche atributos vazios com objetos já existente no banco
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
    public void inject() {
        Vacina vacinaUm = new Vacina("65582566c691757a205e3302","Moderna","Moderna","M456B","2023-06-30",3,16);
        Vacina vacinaDois = new Vacina("65582566c691757a205e3303","Johnson & Johnson","Janssen","J789C","2023-11-15",4,17);
        Vacina vacinaTres = new Vacina("65582566c691757a205e3304","Novavax","Novavax","N555H","2023-03-31",2,21);
        Vacina vacinaQuatro = new Vacina("65582566c691757a205e3305","CureVac","CureVac","C666I","2023-12-15",2,28);
        Vacina vacinaCinco = new Vacina("65582566c691757a205e3306","Pfizer-BioNTech","Pfizer","P123A","2023-12-31",2,21);
        List<Vacina> vacinaInjectadas = new ArrayList<>(Arrays.asList(vacinaUm, vacinaDois, vacinaTres, vacinaQuatro, vacinaCinco));
        vacinaRepository.saveAll(vacinaInjectadas);
    }


}
