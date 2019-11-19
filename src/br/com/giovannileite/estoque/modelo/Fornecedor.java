package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@Table(name="fornecedor")
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(length=255)
	private String bairro;

	@Column(length=255)
	private String cep;

	@Column(length=255)
	private String cidade;

	@Column(length=255)
	private String cnpj;

	@Column(nullable=false, length=255)
	private String dataCadastro;

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
	private String responsavel;

	@Column(length=255)
	private String telefone1;

	@Column(length=255)
	private String telefone2;
	
	@Column(length=255)
	private String foto;

	public Fornecedor() {
	}
	
	public Fornecedor(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public Fornecedor(int codigo,String nome,String telefone1,String telefone2,
						String dataCadastro,String responsavel,String cnpj,String pais,String estado,
							String cidade,String obs,String cep,String endereco,String numero,
								String bairro,String foto) 
	{
		this.codigo = codigo;
		this.nome = nome;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.dataCadastro = dataCadastro;
		this.responsavel = responsavel;
		this.cnpj = cnpj;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.obs = obs;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.foto = foto;
	}
	
	public Fornecedor(String nome,String telefone1,String telefone2,
			String dataCadastro,String responsavel,String cnpj,String pais,String estado,
				String cidade,String obs,String cep,String endereco,String numero,
					String bairro,String foto) 
	{
		this.nome = nome;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.dataCadastro = dataCadastro;
		this.responsavel = responsavel;
		this.cnpj = cnpj;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.obs = obs;
		this.cep = cep;
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

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getResponsavel() {
		return this.responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTelefone1() {
		return this.telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return this.telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	
	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + ", cnpj="
				+ cnpj + ", dataCadastro=" + dataCadastro + ", endereco=" + endereco + ", estado=" + estado + ", nome="
				+ nome + ", numero=" + numero + ", obs=" + obs + ", pais=" + pais + ", responsavel=" + responsavel
				+ ", telefone1=" + telefone1 + ", telefone2=" + telefone2 + ", foto=" + foto + "]";
	}

}