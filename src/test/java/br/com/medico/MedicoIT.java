package br.com.medico;


import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.medico.DTO.MedicoDTO;
import br.com.medico.model.AccountState;
import br.com.medico.model.Endereco;
import br.com.medico.model.Especialidades;
import br.com.medico.model.Medico;
import br.com.medico.repository.EspecialidadesRepository;
import br.com.medico.repository.MedicoRepository;
import br.com.medico.request.MedicoRequest;
import br.com.medico.service.EspecialidadesService;
import br.com.medico.service.MedicoService;
import br.com.medico.util.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class MedicoIT {

	@Autowired
	private MedicoService medicoService;
	
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private EspecialidadesRepository especialidadesRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	
	@Before
	public void setUp() {
		
		databaseCleaner.clearTables();
		preparaDados();
	}
	
	private void preparaDados() {
		
		Medico medico = new Medico();
		
		Endereco endereco = new Endereco();
		Especialidades especialidade01 = new Especialidades();
		Especialidades especialidade02 = new Especialidades();
		Especialidades especialidade03 = new Especialidades();
		Especialidades especialidade04 = new Especialidades();
		Especialidades especialidade05 = new Especialidades();
		Especialidades especialidade06 = new Especialidades();
		Especialidades especialidade07 = new Especialidades();
		Especialidades especialidade08 = new Especialidades();
		
		especialidade01.setId(1L);
		especialidade01.setEspecialidades("ALERGOLOGIA");
		
		especialidade02.setId(2L);
		especialidade02.setEspecialidades("ANGIOLOGIA");
		
		especialidade03.setId(2L);
		especialidade03.setEspecialidades("BUCO MAXILO");
		
		especialidade04.setId(2L);
		especialidade04.setEspecialidades("CARDIOLOGIA CLÍNICA");
		
		especialidade05.setId(2L);
		especialidade05.setEspecialidades("CARDIOLOGIA INFANTIL");
		
		especialidade06.setId(2L);
		especialidade06.setEspecialidades("CIRURGIA CABEÇA E PESCOÇO");
		
		especialidade07.setId(2L);
		especialidade07.setEspecialidades("CIRURGIA CARDÍACA");
		
		especialidade08.setId(2L);
		especialidade08.setEspecialidades("CIRURGIA DE TÓRAX");
		
		especialidadesRepository.save(especialidade01);
		especialidadesRepository.save(especialidade02);
		especialidadesRepository.save(especialidade03);
		especialidadesRepository.save(especialidade04);
		especialidadesRepository.save(especialidade05);
		especialidadesRepository.save(especialidade06);
		especialidadesRepository.save(especialidade07);
		especialidadesRepository.save(especialidade08);
		
		
		ArrayList<Especialidades> especialidades = new ArrayList<>();
		
		especialidades.add(especialidade01);
		especialidades.add(especialidade02);

		
		endereco.setCep("04421-220");
		endereco.setBairro("bairro");
		endereco.setComplemento("complemento");
		endereco.setLocalidade("São Paulo");
		endereco.setLogradouro("Rua maria");
		endereco.setUf("SP");
		
		
		medico.setNome("Lucas");
		medico.setCrm("12.345.75");
		medico.setTelefoneFixo("11 5623-5623");
		medico.setTelefoneCelular("11 96565-1234");
		medico.setEspecialidades(especialidades );
		medico.setEndereco(endereco);
		medico.setState(AccountState.ACTIVE);

		medicoRepository.save(medico);					

	}
	
	@Test
	public void deverAtribuirId_QuandoCadastrarMedicoComDadosCorretos() {
		 
		MedicoRequest medicoRequest = new MedicoRequest();
		
		
		Endereco endereco = new Endereco();
		Especialidades especialidade01 = new Especialidades();
		Especialidades especialidade02 = new Especialidades();
		
		especialidade01.setId(1L);
		especialidade01.setEspecialidades("BUCO MAXILO");
		
		especialidade02.setId(2L);
		especialidade02.setEspecialidades("CARDIOLOGIA CLÍNICA");
		
		
		ArrayList<Especialidades> especialidades = new ArrayList<>();
		
		especialidades.add(especialidade01);
		especialidades.add(especialidade02);

		
		endereco.setCep("04421-220");
		endereco.setBairro("bairro");
		endereco.setComplemento("complemento");
		endereco.setLocalidade("São Paulo");
		endereco.setLogradouro("Rua maria");
		endereco.setUf("SP");
		
		
		medicoRequest.setNome("Pedro");
		medicoRequest.setCrm("12.345.75");
		medicoRequest.setTelefoneFixo("11 5623-5623");
		medicoRequest.setTelefoneCelular("11 96565-1234");
		medicoRequest.setEspecialidades(especialidades );
		medicoRequest.setEndereco(endereco);
					
		MedicoDTO medico = new MedicoDTO();
		medico = medicoService.salvar(medicoRequest);
		
		Assertions.assertThat(medico).isNotNull();
		Assertions.assertThat(medico.getId()).isNotNull();
		Assertions.assertThat(medico.getCrm()).isNotNull();
		Assertions.assertThat(medico.getTelefoneFixo()).isNotNull();
		Assertions.assertThat(medico.getTelefoneFixo()).isNotNull();
		Assertions.assertThat(medico.getEspecialidades()).isNotNull();
		Assertions.assertThat(medico.getEndereco()).isNotNull();
		
	}
	
	@Test(expected = WebClientResponseException.class)
	public void deveFalhar_QuandoCadastrarComDadosIncorretos() {
		
		MedicoRequest medicoRequest = new MedicoRequest();
		
		Endereco endereco = new Endereco();
		Especialidades especialidade01 = new Especialidades();
		Especialidades especialidade02 = new Especialidades();
		
		especialidade01.setId(1L);
		especialidade01.setEspecialidades("ALERGOLOGIA");
		
		especialidade02.setId(2L);
		especialidade02.setEspecialidades("ANGIOLOGIA");
		
		
		ArrayList<Especialidades> especialidades = new ArrayList<>();
		
		especialidades.add(especialidade01);
		especialidades.add(especialidade02);

		
		endereco.setCep("04421-22a");
		endereco.setBairro("bairro");
		endereco.setComplemento("complemento");
		endereco.setLocalidade("São Paulo");
		endereco.setLogradouro("Rua maria");
		endereco.setUf("SP");
		
		
		medicoRequest.setNome("Maria");
		medicoRequest.setCrm("12.3A5.5");
		medicoRequest.setTelefoneFixo("11 5623-5623126");
		medicoRequest.setTelefoneCelular("11 96-1234");
		medicoRequest.setEspecialidades(especialidades );
		medicoRequest.setEndereco(endereco);
					
		MedicoDTO medico = new MedicoDTO();
		medico = medicoService.salvar(medicoRequest);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void deveFalhar_QuandoExcluirMedicoInexistente() {
		medicoService.delete(31L);
	}
	
	@Test
	public void deveAtualizar_QuandoAlteraDados() {
		
		Medico medico = medicoRepository.findById(1L).get();
		
		medico.setNome("João");
		
		medicoService.atualizar(medico);
	}
	
	@Test
	public void deveFalhar_QuandoTentarAtualizarComDadosIncorretos() {
		
		Medico medico = medicoRepository.findById(1L).get();
		
		medico.setTelefoneCelular("11 5623-56235623");
		
		medicoService.atualizar(medico);
	}
	
	@Test
	public void deveFazerSoftDelete_QuandoPassaIdValido() {
		
		medicoService.delete(1L);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void deveFalhar_QuandoPassaIdInvalido() {
		
		medicoService.delete(31L);
	}
	
	@Test
	public void deveBuscarListaOuUmMedico_QuandoPassaParamValido() {
		
		medicoService.filtroDinamico(1L, "", "", "", "", "", "", "", "", "", "");
	}
	
	@Test
	public void deveFalhar_QuandoPassaParamInvalido() {
		
		medicoService.filtroDinamico(31L, "", "", "", "", "", "", "", "", "", "");
	}
	
	@Test
	public void deveBuscarMedicoPorEspecialidade_QuandoPassaIdDeUmaEspecialidade() {
		
		medicoService.medicoEspecialidade(3L);
	}
	
	@Test
	public void deveFalhar_QuandoPassaIdDeUmaEspecialidadeInvalido() {
		
		medicoService.medicoEspecialidade(33L);
	}		
	
	
}
