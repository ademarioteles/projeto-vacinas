package com.vacinas.ap1;

import com.vacinas.ap1.controller.VacinaController;
import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.exceptions.VacinaNotFoundException;
import com.vacinas.ap1.exceptions.VacinaNotInsertExeption;
import com.vacinas.ap1.repository.VacinaRepository;
import com.vacinas.ap1.service.ServiceVacinaImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class VacinaServiceTests {

	@Mock
	VacinaRepository vacinaRespository;
	@Mock
	Vacina vacin;
	@Mock
	List<Vacina> vacinas;
	@InjectMocks
	ServiceVacinaImpl vacinaServiceInject;

	@BeforeEach
	void carregarVacina(){
		vacinas = new ArrayList<>();
		vacin = new Vacina();
		vacin.setId("1");
		vacin.setNome("pfizer");
		vacin.setFabricante("johnson");
		vacin.setLote("d5f6f6fa");
		vacin.setData_validade("1991-01-21");
		vacin.setNumero_de_doses(21);
		vacin.setIntervalo_doses(36);
		vacinas.add(vacin);
		when(vacinaRespository.findAll()).thenReturn(vacinas);
	}
	@Test
	void obterTodosService(){
		Assertions.assertEquals(vacinas, vacinaServiceInject.obterTodos());
	}
	@Test
	void obterPorIdService(){
		Assertions.assertEquals(vacin, vacinaServiceInject.obterPorId("1"));
	}
	@Test
	void existeVacinaService(){
		Assertions.assertEquals(true, vacinaServiceInject.existeVacina(vacin));
	}

}
