package br.com.cotiinformatica.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Compromisso;

public interface ICompromissoRepository extends CrudRepository<Compromisso, Integer> {
	
	/*
	 * JPQL Java Persistence Query Language
	 */

	@Query("select c from Compromisso c where c.usuario.idUsuario = :idUsuario and c.dataCompromisso between :dataMin and :dataMax")
	List<Compromisso> find(
			@Param("idUsuario") Integer idUsuario,
			@Param("dataMin") Date dataMin,
			@Param("dataMax") Date dataMax);
}

