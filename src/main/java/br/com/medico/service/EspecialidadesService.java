package br.com.medico.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.medico.DTO.EspecialidadesDTO;
import br.com.medico.mapper.EspecialidadesMapper;
import br.com.medico.model.Especialidades;
import br.com.medico.repository.EspecialidadesRepository;

@Service
public class EspecialidadesService {

	@Autowired
	private EspecialidadesRepository especialidadesRepository;
	
	@Autowired
	private EspecialidadesMapper especialidadesMapper;
	
	@Transactional
	public Especialidades salvarEspecialidades(Especialidades especialidades) {
		
		return especialidadesRepository.save(especialidades);
	}
	
	public List<EspecialidadesDTO> especialidades(){
		
		return especialidadesRepository.findAll()
				.stream()
				.map(especialidades -> especialidadesMapper.modelToDTO(especialidades))
				.collect(Collectors.toList());
	}
}
