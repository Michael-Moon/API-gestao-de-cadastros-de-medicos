package br.com.medico.request;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.medico.model.Endereco;
import br.com.medico.model.Especialidades;
import lombok.Data;

@Data
public class MedicoRequest {

	private Long id;
	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	@Pattern(regexp="[0-9]{2}.[0-9]{3}.[0-9]{2}")
	private String crm;
	@NotBlank
	@NotNull
	@Pattern(regexp="[0-9]{2}[- .][0-9]{4}-[0-9]{4}")
	private String telefoneFixo;
	@NotBlank
	@NotNull
	@Pattern(regexp="[0-9]{2}[- .][0-9]{5}-[0-9]{4}")
	private String telefoneCelular;

	private List<Especialidades> especialidades;
	
	private Endereco endereco;
}
