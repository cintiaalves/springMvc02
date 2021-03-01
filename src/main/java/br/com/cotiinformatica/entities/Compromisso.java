package br.com.cotiinformatica.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cotiinformatica.enums.PrioridadeCompromisso;
import br.com.cotiinformatica.enums.TipoCompromisso;

@Entity
@Table
public class Compromisso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idCompromisso;
	
	@Column
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dataCompromisso;
	
	@Temporal(TemporalType.TIME)
	@Column
	private Date horaCompromisso;
	
	@Column
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column
	private TipoCompromisso tipo;
	
	@Enumerated(EnumType.STRING)
	@Column
	private PrioridadeCompromisso prioridade;
	
	/*
	 * Cardinalidades -> OneToOne, OneToMany, ManyToOne, ManyToMany
	 */
	@ManyToOne //Muitos compromissos para 1 Usuário
	@JoinColumn(name = "idusuario") //Mapeamento do campo FOREIGN KEY
	private Usuario usuario; // relacionamento

	public Compromisso() {
		// TODO Auto-generated constructor stub
	}

	public Compromisso(Integer idCompromisso, String nome, Date dataCompromisso, Date horaCompromisso, String descricao,
			TipoCompromisso tipo, PrioridadeCompromisso prioridade, Usuario usuario) {
		super();
		this.idCompromisso = idCompromisso;
		this.nome = nome;
		this.dataCompromisso = dataCompromisso;
		this.horaCompromisso = horaCompromisso;
		this.descricao = descricao;
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.usuario = usuario;
	}

	public Integer getIdCompromisso() {
		return idCompromisso;
	}

	public void setIdCompromisso(Integer idCompromisso) {
		this.idCompromisso = idCompromisso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCompromisso() {
		return dataCompromisso;
	}

	public void setDataCompromisso(Date dataCompromisso) {
		this.dataCompromisso = dataCompromisso;
	}

	public Date getHoraCompromisso() {
		return horaCompromisso;
	}

	public void setHoraCompromisso(Date horaCompromisso) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Compromisso [idCompromisso=" + idCompromisso + ", nome=" + nome + ", dataCompromisso=" + dataCompromisso
				+ ", horaCompromisso=" + horaCompromisso + ", descricao=" + descricao + ", tipo=" + tipo
				+ ", prioridade=" + prioridade + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Compromisso) {
			Compromisso compromisso = (Compromisso) obj;
			return this.idCompromisso.equals(compromisso.getIdCompromisso());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {	
		return this.idCompromisso.hashCode();
	}
}



