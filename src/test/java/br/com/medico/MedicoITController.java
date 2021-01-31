package br.com.medico;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.medico.model.Endereco;
import br.com.medico.model.Especialidades;
import br.com.medico.request.MedicoRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicoITController {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/medico";	
	}
	
	
	@Test
	public void deveRetornarStatus200_QuandoCadastrarMedico() {
		
		MedicoRequest medicoRequest = new MedicoRequest();
		Especialidades especialidades = new Especialidades();
		Endereco endereco = new Endereco();
		
		especialidades.setId(1L);
		especialidades.setEspecialidades("ALERGOLOGIA");
		
		ArrayList<Especialidades> espe = new ArrayList<>();
		
		espe.add(especialidades);
		
		endereco.setCep("04421-220");
		
		medicoRequest.setCrm("12.123.12");
		medicoRequest.setNome("Paulo");
		medicoRequest.setTelefoneFixo("11 5623-5623");
		medicoRequest.setTelefoneCelular("11 95623-5623");
		medicoRequest.setEspecialidades(espe);
		medicoRequest.setEndereco(endereco);
		
		RestAssured.given()			
			.body(medicoRequest)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus200_QuandoAtaulizarCadastrarMedico() {
		
		MedicoRequest medicoRequest = new MedicoRequest();
		Especialidades especialidades = new Especialidades();
		Endereco endereco = new Endereco();
		
		especialidades.setId(1L);
		especialidades.setEspecialidades("ALERGOLOGIA");
		
		ArrayList<Especialidades> espe = new ArrayList<>();
		
		espe.add(especialidades);
		
		endereco.setCep("04421-220");
		
		medicoRequest.setCrm("12.123.12");
		medicoRequest.setNome("Maria");
		medicoRequest.setTelefoneFixo("11 5623-5623");
		medicoRequest.setTelefoneCelular("11 95623-5623");
		medicoRequest.setEspecialidades(espe);
		medicoRequest.setEndereco(endereco);
		
		RestAssured.given()	
			.pathParam("id", 1)
			.body(medicoRequest)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarMedicoPeloDadosPessoais() {
				
		RestAssured.given()	
			.pathParam("nome", "Lucas")
			.accept(ContentType.JSON)			
		.when()
			.get("/filtro?nome={nome}")			
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarMedicoPeloIdEspecialidades() {
				
		RestAssured.given()	
			.pathParam("id", 1)
			.accept(ContentType.JSON)			
		.when()
			.get("/medicoEspecialidade?especialidade={id}")			
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarMedicoPeloId() {
				
		RestAssured.given()		
			.pathParam("id", 1)
			.accept(ContentType.JSON)			
		.when()
			.get("/{id}")			
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarTodosMedicos() {
				
		RestAssured.given()			
			.accept(ContentType.JSON)			
		.when()
			.get()
		.then()
			.statusCode(200);
	}
	

}
