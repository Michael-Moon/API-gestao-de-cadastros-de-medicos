package br.com.medico.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.medico.DTO.EspecialidadesDTO;
import br.com.medico.DTO.MedicoDTO;
import br.com.medico.mapper.EspecialidadesMapper;
import br.com.medico.mapper.MedicoMapper;
import br.com.medico.model.AccountState;
import br.com.medico.model.Endereco;
import br.com.medico.model.Medico;
import br.com.medico.repository.EspecialidadesRepository;
import br.com.medico.repository.MedicoRepository;
import br.com.medico.request.MedicoRequest;
import reactor.core.publisher.Mono;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private EspecialidadesRepository especialidadesRepository;
	
	@Autowired
	private MedicoMapper medicoMapper;	
	
	
	@Autowired
	private WebClient webCliente;
	
	@Transactional
	public MedicoDTO salvar (MedicoRequest medicoRequest) {
				
		
		Mono<Endereco> monoEndereco = this.webCliente
		.method(HttpMethod.GET)
		.uri("{codigo}/json/", medicoRequest.getEndereco().getCep())
		.retrieve()
		.bodyToMono(Endereco.class);	
		
		
		Medico medico = medicoMapper.requestToModel(medicoRequest);		
		
		medico.setState(AccountState.ACTIVE);
		
		medico.setEndereco( monoEndereco.block());	

		
	
		return medicoMapper.modelToDTO(medicoRepository.save(medico));
	}
	
	@Transactional
	public void atualizar (Medico medico) {
		
		Mono<Endereco> monoEndereco = this.webCliente
				.method(HttpMethod.GET)
				.uri("{codigo}/json/", medico.getEndereco().getCep())
				.retrieve()
				.bodyToMono(Endereco.class);
		
		medico.setEndereco( monoEndereco.block());
		
		medicoRepository.save(medico);
	}
	
	@Transactional
	public void delete(Long id) {
		
			Medico medico = medicoRepository.findById(id).get();
			medico.setState(AccountState.DELETED);		
	}
	
	public MedicoDTO buscarMedico(Long id) {	
		
		MedicoDTO medicoDTO = medicoMapper.modelToDTO(medicoRepository.findById(id).get());
		
		return medicoDTO;
				
				
	}
	
	public List<MedicoDTO> filtroDinamico(Long id,String crm,String nome,String telefoneFixo,
			String telefoneCelular, String logradouro, String complemento, String cep, String bairro,
			String localidade,String uf){
		
		return medicoRepository.filtroDinamico(id,crm,nome,telefoneFixo,telefoneCelular,logradouro,
				complemento,cep,bairro,localidade,uf)
				.stream()
				.map(medico -> medicoMapper.modelToDTO(medico))
				.collect(Collectors.toList());
	}
	
	public List<MedicoDTO> medicoEspecialidade(Long id){
		return medicoRepository.listaEspecialidade(id)
				.stream()
				.map(medico -> medicoMapper.modelToDTO(medico))
				.collect(Collectors.toList());
	}
	
	public List<MedicoDTO> listarMedicos(){
		return medicoRepository.findAll()
				.stream()
				.map(medico -> medicoMapper.modelToDTO(medico))
				.collect(Collectors.toList());
	}	
	
}
