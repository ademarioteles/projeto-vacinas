package com.vacinas.ap1.controller;

        import com.vacinas.ap1.entity.Vacina;
        import com.vacinas.ap1.service.ServiceVacina;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@Validated
public class VacinaController {
    @Autowired
    private ServiceVacina serviceVacina;

    //Método para listar as vacinas
    @GetMapping("/vacinas")
    public ResponseEntity<List<Vacina>> obterTodos() {
        List<Vacina> vacinas = serviceVacina.obterTodos();
        return ResponseEntity.ok(vacinas);
    }

    //Método para adicionar vacina
    @PostMapping("/vacinas/cadastrar")
    public ResponseEntity<?> inserir(@RequestBody Vacina novaVacina) {
        serviceVacina.inserir(novaVacina);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
