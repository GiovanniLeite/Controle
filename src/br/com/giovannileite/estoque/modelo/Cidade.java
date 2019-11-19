package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@Table(name="cidade")
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(nullable=false, length=255)
	private String nome;

	@Column(nullable=false, length=255)
	private String pais;

	@Column(nullable=false, length=255)
	private String uf;
	
	@Column(nullable=false, length=255)
	private String nomeEstado;

	public Cidade() {
	}
	
	public Cidade(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public Cidade (String nome,String pais,String uf,String nomeEstado) 
	{
		this.nome = nome;
		this.pais = pais;
		this.uf = uf;
		this.nomeEstado = nomeEstado;
	}
	
	public Cidade (int codigo,String nome,String pais,String uf,String nomeEstado) 
	{
		this.codigo = codigo;
		this.nome = nome;
		this.pais = pais;
		this.uf = uf;
		this.nomeEstado = nomeEstado;
	}
	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
	
	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	@Override
	public String toString() {
		return "Cidade [codigo=" + codigo + ", nome=" + nome + ", pais=" + pais + ", uf=" + uf + ", nomeEstado="
				+ nomeEstado + "]";
	}

	

}