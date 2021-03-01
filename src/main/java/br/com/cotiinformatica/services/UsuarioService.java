package br.com.cotiinformatica.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;

@Service // disponibilizar operações de repositorio
@Transactional // transacionar banco de dados
public class UsuarioService {

	@Autowired // injeção de dependência
	private IUsuarioRepository usuarioRepository;

	public void createOrUpdate(Usuario usuario) throws Exception {
		usuarioRepository.save(usuario);
	}

	public void delete(Usuario usuario) throws Exception {
		usuarioRepository.delete(usuario);
	}

	public List<Usuario> findAll() throws Exception {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public Usuario findById(Integer id) throws Exception {
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario findByEmail(String email) throws Exception {

		List<Usuario> lista = usuarioRepository.findByEmail(email);

		if (lista != null && lista.size() > 0)
			return lista.get(0);
		else
			return null;
	}

	public Usuario findByEmailAndSenha(String email, String senha) throws Exception {

		List<Usuario> lista = usuarioRepository.findByEmailAndSenha(email, senha);

		if (lista != null && lista.size() > 0)
			return lista.get(0);
		else
			return null;
	}
}