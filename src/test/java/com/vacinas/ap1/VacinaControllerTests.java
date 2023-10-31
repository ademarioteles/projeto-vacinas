package com.vacinas.ap1;

import com.vacinas.ap1.controller.VacinaController;
import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.exceptions.VacinaNotFoundException;
import com.vacinas.ap1.exceptions.VacinaNotInsertExeption;
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
class VacinaControllerTests {
	@Autowired
	VacinaController vacinaController;
	@Mock
	ServiceVacinaImpl vacinaServiceImpl;
	@Mock
	Vacina vacin;
	@Mock
	List<Vacina> vacinas;
	@InjectMocks
	VacinaController vacinaControllerInject;

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
		when(vacinaServiceImpl.obterTodos()).thenReturn(vacinas);
		when(vacinaServiceImpl.obterPorId(vacin.getId())).thenReturn(vacin);

	}
	@Test
	void obterTodosController(){
		Assertions.assertEquals(ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(vacinas), vacinaControllerInject.obterTodos());

	}
	@Test
	void obterPorIdController(){
		Assertions.assertEquals( ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(vacin), vacinaControllerInject.obterPorId(vacin.getId()));

	}
	@Test
	void insercaoVacinaController(){//Retorna umaa exceção por meio validation, caso algum dos campos não forem preenchidos
		vacin.setNome("");
		vacin.setFabricante("");
		Assertions.assertThrows(ConstraintViolationException.class, () -> vacinaController.inserir(vacin));
	}
	@Test
	void insercaoDataValidadeController(){//Retornara uma excecao caso o formato da data não esta igual a  1991-01-21
		vacin.setData_validade("1991/01/21");
		Assertions.assertThrows(ConstraintViolationException.class, () -> vacinaController.inserir(vacin));
	}
	@Test
	void vacinaExistenteController(){
		when(vacinaServiceImpl.existeVacina(vacin)).thenReturn(true);// Atribui que a vacina já existe na base e impedir o cadastro
		Assertions.assertThrows(VacinaNotInsertExeption.class, () -> vacinaControllerInject.inserir(vacin));//Nesse momento de inserção o usuario já existe
	}
	@Test
	void existeUsuarioController(){//Retorna uma exceção personalizada ao buscar uma vacina não existente
		Assertions.assertThrows(VacinaNotFoundException.class, () -> vacinaControllerInject.obterPorId("abcde123456"));
	}
	@Test
	void editarVacinaController(){//Retornará uma exceção caso não encontre nenhuma vacina
		Vacina novaVacina = new Vacina();//vacina esta com as informações vazias
		Assertions.assertThrows(VacinaNotFoundException.class, () -> vacinaControllerInject.editarVacina(novaVacina));

	}
	@Test
	void editarVacinaPorIdController(){//Retornará uma exceção caso o id não for encontrado
		Vacina novaVacina = new Vacina("2","pfizer","johnson","d5f6f6fa","1991-01-21",21,36);
		Assertions.assertThrows(VacinaNotFoundException.class, () -> vacinaControllerInject.editarVacinaPorId(novaVacina.getId(),novaVacina));
	}
	
}
