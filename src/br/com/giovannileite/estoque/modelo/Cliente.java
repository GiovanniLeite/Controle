package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(length=255)
	private String bairro;

	@Column(length=255)
	private String celular;

	@Column(length=255)
	private String cep;

	@Column(length=255)
	private String cidade;

	@Column(length=255)
	private String cpf;

	@Column(nullable=false, length=255)
	private String dataCadastro;

	@Column(length=255)
	private String email;

	@Column(length=255)
	private String endereco;

	@Column(length=255)
	private String estado;

	@Column(nullable=false, length=255)
	private String nome;

	@Column(length=255)
	private String numero;

	@Column(length=255)
	private String obs;

	@Column(length=255)
	private String pais;

	@Column(length=255)
	private String rg;

	@Column(length=255)
	private String emissorRg;

	@Column(length=255)
	private String telefone;
	
	@Column(length=255)
	private String foto;

	public Cliente() {
	}
	
	public Cliente(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public Cliente(String nome,String rg,String cpf,String emissorRg,
					String email,String telefone,String celular,
						String dataCadastro,String obs,String cep,
							String pais,String estado,String cidade,
								String endereco,String numero,String bairro,String foto) 
	{
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.emissorRg = emissorRg;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.dataCadastro = dataCadastro;
		this.obs = obs;
		this.cep = cep;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.foto = foto;
	}
	
	public Cliente(int codigo,String nome,String rg,String cpf,String emissorRg,
			String email,String telefone,String celular,
				String dataCadastro,String obs,String cep,
					String pais,String estado,String cidade,
						String endereco,String numero,String bairro,String foto) 
	{
		this.codigo = codigo;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.emissorRg = emissorRg;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.dataCadastro = dataCadastro;
		this.obs = obs;
		this.cep = cep;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.foto = foto;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmissorRg() {
		return this.emissorRg;
	}

	public void setEmissorRg(String emissorRg) {
		this.emissorRg = emissorRg;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", bairro=" + bairro + ", celular=" + celular + ", cep=" + cep
				+ ", cidade=" + cidade + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro + ", email=" + email
				+ ", endereco=" + endereco + ", estado=" + estado + ", nome=" + nome + ", numero=" + numero + ", obs="
				+ obs + ", pais=" + pais + ", rg=" + rg + ", emissorRg=" + emissorRg + ", telefone=" + telefone
				+ ", foto=" + foto + "]";
	}

	

	

}