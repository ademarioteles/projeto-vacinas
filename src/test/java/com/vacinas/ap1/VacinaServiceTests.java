package com.vacinas.ap1;
import com.vacinas.ap1.entity.Vacina;
import com.vacinas.ap1.exceptions.VacinaNotFoundException;
import com.vacinas.ap1.repository.VacinaRepository;
import com.vacinas.ap1.service.ServiceVacinaImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
	void obterPorIdServiceError(){
		Assertions.assertThrows(VacinaNotFoundException.class,()->vacinaServiceInject.obterPorId("2"));
	}
	@Test
	void existeVacinaService(){
		Assertions.assertEquals(true, vacinaServiceInject.existeVacina(vacin));
	}
	@Test
	void compareEditeTest(){
		Vacina vacinaEditar = new Vacina();
		vacinaEditar.setNome("adalberto");
		vacinaEditar.setFabricante("Pfezer");
		vacinaEditar = vacinaServiceInject.compareEdite(vacin,vacinaEditar);
		Assertions.assertEquals(vacinaEditar.getNome(),vacin.getNome());
		Assertions.assertEquals(vacinaEditar.getFabricante(),vacin.getFabricante());
		Assertions.assertEquals(vacinaEditar.getData_validade(),vacin.getData_validade());
		Assertions.assertEquals(vacinaEditar.getNumero_de_doses(),vacin.getNumero_de_doses());
	}

	@Test
	void deletarPorIdError(){
		Assertions.assertThrows(VacinaNotFoundException.class,()->vacinaServiceInject.deletarPorId("2"));
	}
	@Test
	void EditarPorIdError(){
		Assertions.assertThrows(VacinaNotFoundException.class,()->vacinaServiceInject.editarPorId("2", new Vacina()));
	}
	@Test
	void EditarError(){
		Assertions.assertThrows(VacinaNotFoundException.class,()->vacinaServiceInject.editar(new Vacina()));
	}
	@Test
	void deletarParcialmenteError(){
		Assertions.assertThrows(VacinaNotFoundException.class,()->vacinaServiceInject.editarParcial(new Vacina()));
	}

}
