package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name="pais")
@NamedQuery(name="Pai.findAll", query="SELECT p FROM Pai p")
public class Pai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(nullable=false, length=255)
	private String nome;

	@Column(length=255)
	private String org;

	public Pai() 
	{
		
	}
	
	

	public Pai(int codigo, String nome, String org) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.org = org;
	}



	public Pai(String nome, String org) {
		super();
		this.nome = nome;
		this.org = org;
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

	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "Pai [codigo=" + codigo + ", nome=" + nome + ", org=" + org + "]";
	}

}