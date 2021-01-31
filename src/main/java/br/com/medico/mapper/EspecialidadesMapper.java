package br.com.medico.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.medico.DTO.EspecialidadesDTO;
import br.com.medico.model.Especialidades;

@Component
public class EspecialidadesMapper {

	@Autowired
	 private ModelMapper modelMapper;
	
	public EspecialidadesDTO modelToDTO(Especialidades especialidades) {
		
		return modelMapper.map(especialidades, EspecialidadesDTO.class);
	}
	 
}
