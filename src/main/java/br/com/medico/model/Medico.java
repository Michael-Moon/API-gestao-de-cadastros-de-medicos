package br.com.medico.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE medico SET state = 'DELETED WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state <> 'DELETED'")
@NamedQuery(name =  "Medico.FindByName", query = "SELECT m FROM Medico m WHERE m.nome like :nome")
@Table(name="medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=120)
	private String nome;	
	
	@Column	
	private String crm;
	
	@Column	
	private String telefoneFixo;
	
	@Column	
	private String telefoneCelular;
	
	@Column
	@Enumerated(EnumType.STRING)
	private AccountState state;	
	
	@ManyToMany
	@JoinTable(name = "grupo_especialidade",
	joinColumns = @JoinColumn(name = "medico_id"),
			inverseJoinColumns = @JoinColumn(name = "especialidades_id"))
	private List<Especialidades> especialidades = new ArrayList<>();

	
	@Embedded
	private Endereco endereco;
	
	@PreRemove
	public void deleteUser() {
		System.out.println("Set state to DELETED");
		this.state = AccountState.DELETED;
	}
}
