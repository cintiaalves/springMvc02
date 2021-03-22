package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dto.CadastroCompromissoDTO;
import br.com.cotiinformatica.dto.ConsultaCompromissoDTO;
import br.com.cotiinformatica.dto.EdicaoCompromissoDTO;
import br.com.cotiinformatica.entities.Compromisso;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.PrioridadeCompromisso;
import br.com.cotiinformatica.enums.TipoCompromisso;
import br.com.cotiinformatica.services.CompromissoService;
import br.com.cotiinformatica.services.UsuarioService;
import br.com.cotiinformatica.utils.DateUtil;

@Controller
public class CompromissoController {

	/*
	 * Injeção de dependência Inicialização automatica feita pelo spring
	 */

	@Autowired
	private CompromissoService compromissoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/cadastro-compromisso")
	public ModelAndView cadastroCompromisso() {
		ModelAndView modelAndView = new ModelAndView("agenda/cadastro-compromisso");

		// criando um objeto da classe funcionario..
		modelAndView.addObject("compromisso", new CadastroCompromissoDTO());

		// enviando os dados dos ENUMs
		modelAndView.addObject("tipos", TipoCompromisso.values());
		modelAndView.addObject("prioridades", PrioridadeCompromisso.values());

		return modelAndView;
	}

	@RequestMapping(value = "cadastrarCompromisso", method = RequestMethod.POST)
	public ModelAndView cadastrarCompromisso(CadastroCompromissoDTO dto, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("agenda/cadastro-compromisso");

		try {

			// obter o usuario autenticado na sessão..
			Usuario auth = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			Usuario usuario = usuarioService.findById(auth.getIdUsuario());

			Compromisso compromisso = new Compromisso();

			compromisso.setNome(dto.getNome());
			compromisso.setDataCompromisso(DateUtil.convertDate(dto.getDataCompromisso()));
			compromisso.setHoraCompromisso(DateUtil.convertTime(dto.getHoraCompromisso()));
			compromisso.setDescricao(dto.getDescricao());
			compromisso.setPrioridade(dto.getPrioridade());
			compromisso.setTipo(dto.getTipo());
			compromisso.setUsuario(usuario); // relacionando o usuario do compromisso..

			// gravar no banco de dados
			compromissoService.createOrUpdate(compromisso);

			modelAndView.addObject("mensagem_sucesso", "Compromisso cadastrado com sucesso");
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}

		// criando um objeto da classe funcionario..
		modelAndView.addObject("compromisso", new Compromisso());

		// enviando os dados dos ENUMs
		modelAndView.addObject("tipos", TipoCompromisso.values());
		modelAndView.addObject("prioridades", PrioridadeCompromisso.values());

		return modelAndView;
	}

	@RequestMapping("/consulta-compromisso")
	public ModelAndView consultaCompromisso() {
		ModelAndView modelAndView = new ModelAndView("agenda/consulta-compromisso");
		
		//enviando um objeto da classe ConsultaCompromissoDTO
		modelAndView.addObject("consulta", new ConsultaCompromissoDTO());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "consultarCompromissos", method = RequestMethod.POST)
	public ModelAndView consultarCompromissos(ConsultaCompromissoDTO dto, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("agenda/consulta-compromisso");

		try {
			
			Date dataInicio = DateUtil.convertDate(dto.getDataInicio());
			Date dataFim = DateUtil.convertDate(dto.getDataFim());
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//realizando a consulta e armazenar o seu resultado no modelAndView
			modelAndView.addObject("listagem_compromissos", compromissoService.find(usuario.getIdUsuario(), dataInicio, dataFim));
	
		}
		
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("consulta", dto);
		return modelAndView;
	
	
	}
	
	@RequestMapping("/exclusao-compromisso")
	public ModelAndView exclusaoCompromisso(Integer id, HttpServletRequest request) {
		//"agenda/edicao-compromisso" é aonde vai ser direcionado depois da exclusao.
		ModelAndView modelAndView = new ModelAndView("agenda/consulta-compromisso");
		
		try {
			
			//obter o usuário autenticado na aplicação.
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//buscar o compromisso no banco de dados (atraves do id)...
			Compromisso compromisso = compromissoService.findById(id);
			
			//verificando se o compromisso foi encontrado e se é um compromisso do usuário autenticado.
			if(compromisso != null && compromisso.getUsuario().equals(usuario)) {
				
				//excluindo o compromisso
				compromissoService.delete(compromisso);
				
				
			}
			else {
				throw new Exception("Compromisso inválido para exclusão");
			}
		}
		
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("consulta", new ConsultaCompromissoDTO());
		return modelAndView;
	}

	@RequestMapping("/edicao-compromisso")
	public ModelAndView edicaoCompromisso(Integer id, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("agenda/edicao-compromisso");
		
		try {
			
			//resgatar o usuario autenticado..
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//consultar o compromisso atraves do id..
			Compromisso compromisso = compromissoService.findById(id);
			
			//verificar se o compromisso foi encontrado e se pertence ao usuario que esta autenticado
			if(compromisso != null && compromisso.getUsuario().equals(usuario)) {
				
				//exibir na página de edição os enums..
				modelAndView.addObject("tipos", TipoCompromisso.values());
				modelAndView.addObject("prioridades", PrioridadeCompromisso.values());
				
				//carregando todos os dados do compromisso no DTO..
				EdicaoCompromissoDTO dto = new EdicaoCompromissoDTO();
				dto.setIdCompromisso(compromisso.getIdCompromisso());
				dto.setNome(compromisso.getNome());
				dto.setDataCompromisso(new SimpleDateFormat("yyy-MM-dd").format(compromisso.getDataCompromisso()));
				dto.setHoraCompromisso(new SimpleDateFormat("HH:mm").format(compromisso.getHoraCompromisso()));
				dto.setDescricao(compromisso.getDescricao());
				dto.setTipo(compromisso.getTipo());
				dto.setPrioridade(compromisso.getPrioridade());
				
				modelAndView.addObject("compromisso", dto);
				
			}
			
			else {
				throw new Exception("Compromisso inválido para edição.");
			}
		}
		catch (Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}

	@RequestMapping("/relatorio-compromisso")
	public ModelAndView relatorioCompromisso() {
		ModelAndView modelAndView = new ModelAndView("agenda/relatorio-compromisso");
		return modelAndView;
	}
}
