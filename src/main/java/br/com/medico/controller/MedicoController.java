package br.com.medico.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.medico.DTO.EspecialidadesDTO;
import br.com.medico.DTO.MedicoDTO;

import br.com.medico.model.Medico;

import br.com.medico.repository.MedicoRepository;
import br.com.medico.request.MedicoRequest;
import br.com.medico.service.EspecialidadesService;
import br.com.medico.service.MedicoService;


@CrossOrigin
@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private EspecialidadesService especialidadesService;
	
	@Autowired
	private MedicoRepository medicoRepository;	

	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody  MedicoRequest medicoRequest) {	
				
		try {	
			this.limiteEspecialidades(medicoRequest);
			
			MedicoDTO medicoDTO = medicoService.salvar(medicoRequest);	
			
			return ResponseEntity.status(HttpStatus.CREATED).body(medicoDTO);
		
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}	
	
	@PutMapping("/{id}")
	public void atualizar(@RequestBody Medico medicoAtualizado, @PathVariable Long id) {
	
		Medico medico = medicoRepository.findById(id).get();
		
	
		
		medico.setNome(medicoAtualizado.getNome());
		medico.setCrm(medicoAtualizado.getCrm());
		medico.setTelefoneFixo(medicoAtualizado.getTelefoneFixo());
		medico.setTelefoneCelular(medicoAtualizado.getTelefoneCelular());
		
		medico.setEspecialidades(medicoAtualizado.getEspecialidades());

		medico.setEndereco(medicoAtualizado.getEndereco());
		
		
		medicoService.atualizar(medico);
	}
	@PutMapping("/{id}/delete")
	private void  delete(@PathVariable Long id) {
				
		medicoService.delete(id);
		
	}
	
	@GetMapping("/filtro")
	public List<MedicoDTO> buscarMedico(
			@PathParam(value = "id") Long id,
			@PathParam(value = "crm") String crm,
			@PathParam(value = "nome") String nome,
			@PathParam(value = "telefoneFixo") String telefoneFixo,
			@PathParam(value = "telefoneCelular") String telefoneCelular,
			@PathParam(value = "logradouro") String logradouro,
			@PathParam(value = "complemento") String complemento,
			@PathParam(value = "cep") String cep,
			@PathParam(value = "bairro") String bairro,
			@PathParam(value = "localidade") String localidade,
			@PathParam(value = "uf") String uf
			){
		
		return medicoService.filtroDinamico(id,crm,nome,telefoneFixo,telefoneCelular,logradouro,
				complemento, cep,bairro,localidade,uf);
	}
	
	@GetMapping("/medicoEspecialidade")
	public List<MedicoDTO> medicoEspecialidade(@PathParam(value = "especialidade") Long especialidade){
		
		return medicoService.medicoEspecialidade(especialidade);
	}
	
	@GetMapping("/{id}")
	public MedicoDTO buscar(@PathVariable Long id) {
		
		return medicoService.buscarMedico(id);
	}
	
	@GetMapping
	public List<MedicoDTO> listarMedicos() {
		
		return medicoService.listarMedicos(); 
	}	
	
	
	public String limiteEspecialidades(MedicoRequest medicoRequest) throws Exception {
		
		if (medicoRequest.getEspecialidades().size() > 2)
			throw new Exception ("Bad request limite 2 especialidades");
		
		return "error";
	}
}
