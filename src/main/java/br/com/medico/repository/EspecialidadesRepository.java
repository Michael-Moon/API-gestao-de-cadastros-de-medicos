package br.com.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.medico.model.Especialidades;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Long> {

}
