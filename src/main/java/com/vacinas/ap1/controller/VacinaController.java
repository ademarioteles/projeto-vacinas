package com.vacinas.ap1.controller;

import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.service.ServiceVacina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VacinaController {
    @Autowired
    ServiceVacina servicevacina;

    @GetMapping("/vacinas")
    public ResponseEntity<List<Vacina>> obterTodos(){
        return ResponseEntity.ok(servicevacina.obterTodos());
    }
    @GetMapping("/vacinas/{vacinas}")
    public void inserir(@PathVariable String vacinas){
        Vacina vacine = new Vacina();
        vacine.setFabricante(vacinas);
        servicevacina.inserir(vacine);
    }
}
