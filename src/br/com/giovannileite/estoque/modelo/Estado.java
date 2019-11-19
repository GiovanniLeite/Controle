package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name="estado")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
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

	public Estado() {
	}
	
	public Estado(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public Estado(String nome,String pais,String uf) 
	{
		this.nome = nome;
		this.pais = pais;
		this.uf = uf;
	}
	
	public Estado(int codigo,String nome,String pais,String uf) 
	{
		this.codigo = codigo;
		this.nome = nome;
		this.pais = pais;
		this.uf = uf;
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

	@Override
	public String toString() {
		return "Estado [codigo=" + codigo + ", nome=" + nome + ", pais=" + pais + ", uf=" + uf + "]";
	}

}