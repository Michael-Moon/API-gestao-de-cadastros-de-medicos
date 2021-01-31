package br.com.medico;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.medico.model.Especialidades;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EspecialidadeITController {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/especialidades";	
	}
	
	@Test
	public void deveRetornarStatus200_QuandoCadastrarUmaEspecialidade() {
		
		Especialidades especialidade = new Especialidades();
		especialidade.setEspecialidades("CIRUGIA");
		
		RestAssured.given()			
			.body(especialidade)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			//.statusCode(HttpStatus.CREATED.value());
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarTodasEspecialidades() {
				
		RestAssured.given()			
			.accept(ContentType.JSON)			
		.when()
			.get()
		.then()
			.statusCode(200);
	}
	
}
