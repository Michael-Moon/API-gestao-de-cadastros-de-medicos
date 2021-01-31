package br.com.medico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.medico.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	@Query("from Medico m where 0=0 and" 
			+"(:id is null or m.id = :id) and"
			+"(:crm is null or m.crm = :crm) and"
			+"(:nome is null or m.nome = :nome) and"
			+"(:telefoneFixo is null or m.telefoneFixo = :telefoneFixo) and"
			+"(:telefoneCelular is null or m.telefoneCelular = :telefoneCelular) and"
			+"(:logradouro is null or m.endereco.logradouro like :logradouro) and"
			+"(:complemento is null or m.endereco.complemento like :complemento) and"
			+"(:cep is null or m.endereco.cep like :cep) and"
			+"(:bairro is null or m.endereco.bairro like :bairro) and"
			+"(:localidade is null or m.endereco.localidade like :localidade) and"
			+"(:uf is null or m.endereco.uf like :uf)")			

	List<Medico> filtroDinamico(
			@Param("id") Long id,
			@Param("crm") String crm,
			@Param("nome") String nome,
			@Param("telefoneFixo") String telefoneFixo,
			@Param("telefoneCelular") String telefoneCelular,
			@Param("logradouro") String logradouro,
			@Param("complemento") String complemento,
			@Param("cep") String cep,
			@Param("bairro") String bairro,
			@Param("localidade") String localidade,
			@Param("uf") String uf			
			);
	
	@Query(value = "select * from grupo_especialidade as g "
			+ "inner join Medico as m on m.id = g.medico_id"
			+ " where g.especialidades_id = :especialidade",nativeQuery = true)
	List<Medico> listaEspecialidade(@Param("especialidade")Long especialidade);	
	

}
