package com.vacinas.ap1.controller;

        import com.vacinas.ap1.entity.Mensagem;
        import com.vacinas.ap1.entity.Vacina;
        import com.vacinas.ap1.exceptions.VacinaNotFoundException;
        import com.vacinas.ap1.exceptions.VacinaNotInsertExeption;
        import com.vacinas.ap1.service.ServiceVacina;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.List;

@RestController
@Validated
public class VacinaController {
    @Autowired
    private ServiceVacina serviceVacina;

    //Método para adicionar vacina

    @PostMapping("/vacinas/cadastrar")
    public ResponseEntity inserir(@RequestBody  @Valid Vacina novaVacina) {
        if(serviceVacina.existeVacina(novaVacina)){
            throw new VacinaNotInsertExeption("Vacina existente na base!");
        }
        serviceVacina.inserir(novaVacina);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(novaVacina);
    }

    //Método para listar as vacinas
    @GetMapping("/vacinas")
    public ResponseEntity<List<Vacina>> obterTodos() {
        List<Vacina> vacinas = serviceVacina.obterTodos();
        if (vacinas.isEmpty()) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(vacinas);
    }
    //Metodo para pegar vacinas por id
    @GetMapping("/vacinas/{id}")
    public ResponseEntity<Vacina> obterPorId(@PathVariable String id) {
        if (serviceVacina.obterPorId(id) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterPorId(id));
    }
    //Metodo para editar vacina pelo seu body
    @PutMapping("/vacinas/editar")
    public ResponseEntity<Vacina> editarVacina(@RequestBody  @Valid Vacina vacinaEditada){
        if (serviceVacina.obterPorId(vacinaEditada.getId()) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        serviceVacina.editar(vacinaEditada);
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterPorId(vacinaEditada.getId()));
    }
    //Metodo que edita a vacina por id e requer um body
    @PutMapping("/vacinas/{id}/editar")
    public ResponseEntity<Vacina> editarVacinaPorId(@PathVariable String id, @RequestBody  @Valid Vacina vacinaEditada){
        if (serviceVacina.obterPorId(id) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        vacinaEditada.setId(id);
        serviceVacina.editar(vacinaEditada);
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceVacina.obterPorId(id));
    }

    //Metodo que exclui vacinas por id
    @DeleteMapping("/vacinas/{id}/excluir")
    public ResponseEntity<Mensagem> deletarPorId(@PathVariable String id) {
        if (serviceVacina.obterPorId(id) == null) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        serviceVacina.deletarPorId(id);
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Mensagem("Vacina excluída com sucesso!"));
    }
    //Metodo que exclui todos os b
    @DeleteMapping("/vacinas/excluir")
    public ResponseEntity<Mensagem> deletarTodos() {
        if (serviceVacina.obterTodos().isEmpty()) {
            throw new VacinaNotFoundException("Vacina(s) não encontrada(s)!");
        }
        serviceVacina.deletarTodos();
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Mensagem("Vacina excluída com sucesso!"));
    }
}



