package com.vacinas.ap1.controller;

        import com.vacinas.ap1.entity.Mensagem;
        import com.vacinas.ap1.entity.Vacina;
        import com.vacinas.ap1.exceptions.VacinaNotFoundException;
        import com.vacinas.ap1.exceptions.VacinaNotInsertExeption;
        import com.vacinas.ap1.service.ServiceVacina;
        import com.vacinas.ap1.service.ServiceVacinaImpl;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.List;
        import java.util.Map;

@RestController
@Validated
public class VacinaController {
    @Autowired
    private ServiceVacina serviceVacina;

    //Método para adicionar vacina

    @PostMapping("/vacinas")
    public ResponseEntity<Vacina> inserir(@RequestBody  @Valid Vacina novaVacina) {
        serviceVacina.inserir(novaVacina);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(novaVacina);
    }
    @PostMapping("/vacinas/inject")//Responsavel por injectar valores de vacinas de forma automatica
    public ResponseEntity<List<Vacina>> inject(){
        serviceVacina.inject();
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(serviceVacina.obterTodos());
    }

    //Método para listar as vacinas
    @GetMapping("/vacinas")
    public ResponseEntity<List<Vacina>> obterTodos() {
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterTodos());
    }
    //Metodo para pegar vacinas por id
    @GetMapping("/vacinas/{id}")
    public ResponseEntity<Vacina> obterPorId(@PathVariable String id) {
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterPorId(id));
    }
    //Metodo para editar vacina pelo seu body
    @PutMapping("/vacinas")
    public ResponseEntity<Vacina> editarVacina(@RequestBody  @Valid Vacina vacinaEditada){
        serviceVacina.editar(vacinaEditada);
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterPorId(vacinaEditada.getId()));
    }
    //Metodo que edita a vacina por id e requer um body
    @PutMapping("/vacinas/{id}")
    public ResponseEntity<Vacina> editarVacinaPorId(@PathVariable String id, @RequestBody  @Valid Vacina vacinaEditada){
        serviceVacina.editarPorId(id,vacinaEditada);
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterPorId(id));
    }

    //Metodo para edição parcial por id e requer um body
    @PatchMapping("/vacinas/{id}")
    public ResponseEntity<Vacina> atualizarParcialPorId(@PathVariable String id, @RequestBody Vacina vacinaEditada) {
        serviceVacina.editarParcialPorId(id,vacinaEditada);
        return ResponseEntity.ok(vacinaEditada);
    }

    @PatchMapping("/vacinas")
    public ResponseEntity<Vacina> atualizarParcialmenteVacina(@RequestBody Vacina vacinaEditada) {
        serviceVacina.editar(vacinaEditada);

        return ResponseEntity.ok(vacinaEditada);
    }


    //Metodo que exclui vacinas por id
    @DeleteMapping("/vacinas/{id}")
    public ResponseEntity<Mensagem> deletarPorId(@PathVariable String id) {
        serviceVacina.deletarPorId(id);
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Mensagem("Vacina excluída com sucesso!"));
    }
    //Metodo que exclui todas as vacinas
    @DeleteMapping("/vacinas")
    public ResponseEntity<Mensagem> deletarTodos() {
        serviceVacina.deletarTodos();
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Mensagem("Vacinas excluídas com sucesso!"));
    }

    @GetMapping("/sanhok")
    public ResponseEntity testeSanhok() {
        return ResponseEntity.status(200)
                .contentType(MediaType.TEXT_PLAIN)
                .body("API de Gerenciamento de Vacinação desenvolvida pela equipe Sanhok para atender aos requisitos do projeto 'Programação Web 2 - Oficial 2'");
    }
}



