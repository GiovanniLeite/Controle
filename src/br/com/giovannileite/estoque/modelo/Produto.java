package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(name="produto")
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(nullable=false, length=255)
	private String codigoFornecedor;

	@Column(nullable=false, length=255)
	private String dataCadastro;

	@Column(length=255)
	private String marca;

	@Column(nullable=false, length=255)
	private String nome;

	@Column(nullable=false, length=255)
	private String nomeFornecedor;

	@Column(length=255)
	private String obs;

	@Column(nullable=false, length=255)
	private String precoCompra;

	@Column(nullable=false, length=255)
	private String precoVenda;

	@Column(nullable=false, length=255)
	private String quantidade;
	
	@Column(length=255)
	private String foto;

	public Produto() {
	}
	
	public Produto(int codigo) 
	{
		this.codigo = codigo;
	}
	

	public Produto(int codigo, String codigoFornecedor, String dataCadastro, String marca, String nome,
			String nomeFornecedor, String obs, String precoCompra, String precoVenda, String quantidade, String foto) {
		super();
		this.codigo = codigo;
		this.codigoFornecedor = codigoFornecedor;
		this.dataCadastro = dataCadastro;
		this.marca = marca;
		this.nome = nome;
		this.nomeFornecedor = nomeFornecedor;
		this.obs = obs;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.foto = foto;
	}

	
	
	public Produto(String codigoFornecedor, String dataCadastro, String marca, String nome, String nomeFornecedor,
			String obs, String precoCompra, String precoVenda, String quantidade, String foto) {
		super();
		this.codigoFornecedor = codigoFornecedor;
		this.dataCadastro = dataCadastro;
		this.marca = marca;
		this.nome = nome;
		this.nomeFornecedor = nomeFornecedor;
		this.obs = obs;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.foto = foto;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

	public String getCodigoFornecedor() {
		return this.codigoFornecedor;
	}

	public void setCodigoFornecedor(String codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}

	public String getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFornecedor() {
		return this.nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPrecoCompra() {
		return this.precoCompra;
	}

	public void setPrecoCompra(String precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getPrecoVenda() {
		return this.precoVenda;
	}

	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", codigoFornecedor=" + codigoFornecedor + ", dataCadastro=" + dataCadastro
				+ ", marca=" + marca + ", nome=" + nome + ", nomeFornecedor=" + nomeFornecedor + ", obs=" + obs
				+ ", precoCompra=" + precoCompra + ", precoVenda=" + precoVenda + ", quantidade=" + quantidade
				+ ", foto=" + foto + "]";
	}
	
}