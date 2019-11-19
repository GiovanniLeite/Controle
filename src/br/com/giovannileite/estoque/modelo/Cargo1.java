package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cargo1 database table.
 * 
 */
@Entity
@Table(name="cargo1")
@NamedQuery(name="Cargo1.findAll", query="SELECT c FROM Cargo1 c")
public class Cargo1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(nullable=false, length=255)
	private String nome;

	@Column(nullable=false, length=255)
	private String salario;

	public Cargo1() {
	}
	
	public Cargo1(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public Cargo1(String nome,String salario) 
	{
		this.nome = nome;
		this.salario = salario;
	}
	
	public Cargo1(int codigo,String nome,String salario) 
	{
		this.codigo = codigo;
		this.nome = nome;
		this.salario = salario;
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

	public String getSalario() {
		return this.salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Cargo1 [codigo=" + codigo + ", nome=" + nome + ", salario=" + salario + "]";
	}

}