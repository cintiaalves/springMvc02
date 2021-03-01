package br.com.cotiinformatica.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

	/*
	 * JPQL - Java Persistence Query Language Sintaxe do JPA para escrever consultas
	 * em banco de dados
	 */

	@Query("select u from Usuario u where u.email =:email")
	List<Usuario> findByEmail(@Param("email") String email);

	@Query("select u from Usuario u where u.email =:email and u.senha =:senha")
	List<Usuario> findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

}



