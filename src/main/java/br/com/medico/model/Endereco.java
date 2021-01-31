package br.com.medico.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	@Column(name = "endereco_logradouro")
	private String logradouro;
	
	@Column
	private String complemento;
	
	
	@Column
	@NotNull
	@Pattern(regexp="[0-9]{5}-[0-9]{3}")
	private String cep;
	
	@Column
	private String bairro;
	
	@Column
	private String localidade;
	
	@Column
	private String uf;
}
