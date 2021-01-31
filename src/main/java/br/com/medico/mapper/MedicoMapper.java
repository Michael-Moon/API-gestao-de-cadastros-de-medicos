package br.com.medico.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.medico.DTO.MedicoDTO;
import br.com.medico.model.Medico;
import br.com.medico.request.MedicoRequest;

@Component
public class MedicoMapper {

	 @Autowired
	 private ModelMapper modelMapper;
	 
	 public Medico requestToModel(MedicoRequest medicoRequest) {
		 return modelMapper.map(medicoRequest, Medico.class);
	 }
	 
	 public MedicoDTO modelToDTO(Medico medico) {
		 return modelMapper.map(medico, MedicoDTO.class);
	 }
}
