package br.com.cotiinformatica.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Compromisso;
import br.com.cotiinformatica.interfaces.ICompromissoRepository;

@Service //classe de serviço do spring
@Transactional //capaz de executar transações em BD
public class CompromissoService {

	/*
	 * Injeção de dependência do repositorio que será utilizado.
	 * O Spring inicializa o repositorio
	 */
	
	@Autowired
	private ICompromissoRepository compromissoRepository;
	
	//método para gravar ou atualizar um compromisso
	public void createOrUpdate(Compromisso compromisso) throws Exception {
		compromissoRepository.save(compromisso);
	}
	
	//método para excluir um compromisso
	public void delete(Compromisso compromisso) throws Exception {
		compromissoRepository.delete(compromisso);
	}
	
	//método para consultar todos os compromissos
	public List<Compromisso> findAll() throws Exception {
		return (List<Compromisso>) compromissoRepository.findAll();
	}
	
	//método para retornar 1 unico compromisso atraves do id
	public Compromisso findById(Integer id) throws Exception {
		return compromissoRepository.findById(id).get();
	}
	
	//método para executar a consulta de compromissos por periodo de data e usuário
	public List<Compromisso> find(Integer idUsuario, Date dataMin, Date dataMax) throws Exception {
		return compromissoRepository.find(idUsuario, dataMin, dataMax);
	}
	
}











