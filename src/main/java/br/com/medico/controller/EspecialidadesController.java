package br.com.medico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.medico.DTO.EspecialidadesDTO;
import br.com.medico.model.Especialidades;
import br.com.medico.service.EspecialidadesService;

@CrossOrigin
@RestController
@RequestMapping("/especialidades")
public class EspecialidadesController {
	
	@Autowired
	private EspecialidadesService especialidadesService;
	
	@PostMapping
	public void salvar(@RequestBody Especialidades especialidades) {
		
		especialidadesService.salvarEspecialidades(especialidades);
	}
	
	@GetMapping
	public List<EspecialidadesDTO> listaEspecialidades(){
		
		return especialidadesService.especialidades();
	}

}
