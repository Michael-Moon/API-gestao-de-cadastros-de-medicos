package br.com.medico;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.medico.model.Especialidades;
import br.com.medico.repository.EspecialidadesRepository;
import br.com.medico.service.EspecialidadesService;
import br.com.medico.util.DatabaseCleaner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class EspecialidadesIT {
	
	@Autowired
	private EspecialidadesService especialidadesService;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private EspecialidadesRepository especialidadesRepository;
	
	@Before
	public void setUp() {
		
		databaseCleaner.clearTables();
		preparaDados();
	}
	
	private void preparaDados() {
		
		Especialidades especialidade = new Especialidades();
		especialidade.setId(1L);
		especialidade.setEspecialidades("CIRURGIA ");
		
		especialidadesRepository.save(especialidade);
	}
	
	@Test
	public void deveAtribuirId_QuandoCadastrarEspecialidade() {
		
		Especialidades especialidade = new Especialidades();
		especialidade.setId(1L);
		especialidade.setEspecialidades("CIRURGIA ");
		
		especialidade = especialidadesService.salvarEspecialidades(especialidade);
		
		Assertions.assertThat(especialidade).isNotNull();
		Assertions.assertThat(especialidade.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarTodasEspecialidades_GetAll() {
		especialidadesService.especialidades();
	}

}
