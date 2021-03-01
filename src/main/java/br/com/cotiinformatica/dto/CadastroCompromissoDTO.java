package br.com.cotiinformatica.dto;

import br.com.cotiinformatica.enums.PrioridadeCompromisso;
import br.com.cotiinformatica.enums.TipoCompromisso;

public class CadastroCompromissoDTO {

	private String nome;
	private String dataCompromisso;
	private String horaCompromisso;
	private String descricao;
	private TipoCompromisso tipo;
	private PrioridadeCompromisso prioridade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataCompromisso() {
		return dataCompromisso;
	}

	public void setDataCompromisso(String dataCompromisso) {
		this.dataCompromisso = dataCompromisso;
	}

	public String getHoraCompromisso() {
		return horaCompromisso;
	}

	public void setHoraCompromisso(String horaCompromisso) {
		this.horaCompromisso = horaCompromisso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoCompromisso getTipo() {
		return tipo;
	}

	public void setTipo(TipoCompromisso tipo) {
		this.tipo = tipo;
	}

	public PrioridadeCompromisso getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeCompromisso prioridade) {
		this.prioridade = prioridade;
	}

}
