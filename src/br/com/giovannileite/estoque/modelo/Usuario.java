package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(length=255)
	private String funcionario;

	@Column(length=255)
	private String senha;

	@Column(length=255)
	private String usuario;
	
	@Column(length=255)
	private String nivel;
	
	@Column(length=255)
	private String look;

	public Usuario() {
	}
	
	public Usuario(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public Usuario(String funcionario,String senha,String usuario,String nivel,String look) 
	{
		this.funcionario = funcionario;
		this.senha = senha;
		this.usuario = usuario;
		this.nivel = nivel;
		this.look = look;
	}
	
	public Usuario(int codigo,String funcionario,String senha,String usuario,String nivel,String look) 
	{
		this.codigo = codigo;
		this.funcionario = funcionario;
		this.senha = senha;
		this.usuario = usuario;
		this.nivel = nivel;
		this.look = look;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getLook() {
		return look;
	}

	public void setLook(String look) {
		this.look = look;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", funcionario=" + funcionario + ", senha=" + senha + ", usuario="
				+ usuario + ", nivel=" + nivel + ", look=" + look + "]";
	}
}