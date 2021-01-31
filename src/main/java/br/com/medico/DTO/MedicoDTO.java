package br.com.medico.DTO;



import java.util.List;


import br.com.medico.model.Endereco;
import br.com.medico.model.Especialidades;
import lombok.Data;

@Data
public class MedicoDTO {

	private Long id;
	private String nome;
	private String crm;
	private String telefoneFixo;
	private String telefoneCelular;	
	private List<Especialidades> especialidades;
	private Endereco endereco;
}
