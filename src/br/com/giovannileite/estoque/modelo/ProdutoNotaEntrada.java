package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the produtoNotaEntrada database table.
 * 
 */
@Entity
@Table(name="produtoNotaEntrada")
@NamedQuery(name="ProdutoNotaEntrada.findAll", query="SELECT p FROM ProdutoNotaEntrada p")
public class ProdutoNotaEntrada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(nullable=false, length=255)
	private String codigoNotaEntrada;

	@Column(nullable=false, length=255)
	private String codigoProduto;

	@Column(nullable=false, length=255)
	private String nomeProduto;

	@Column(nullable=false, length=255)
	private String precoTotal;

	@Column(nullable=false, length=255)
	private String precoUnitario;

	@Column(nullable=false, length=255)
	private String quantidade;

	public ProdutoNotaEntrada() {
	}
	
	public ProdutoNotaEntrada(int codigo)
	{
		this.codigo = codigo;
	}

	public ProdutoNotaEntrada(String codigoProduto, String nomeProduto, String precoTotal, String precoUnitario,
			String quantidade) {
		super();
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoTotal = precoTotal;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public ProdutoNotaEntrada(String codigoNotaEntrada, String codigoProduto, String nomeProduto, String precoTotal,
			String precoUnitario, String quantidade) {
		super();
		this.codigoNotaEntrada = codigoNotaEntrada;
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoTotal = precoTotal;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public ProdutoNotaEntrada(int codigo, String codigoNotaEntrada, String codigoProduto, String nomeProduto,
			String precoTotal, String precoUnitario, String quantidade) {
		super();
		this.codigo = codigo;
		this.codigoNotaEntrada = codigoNotaEntrada;
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoTotal = precoTotal;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCodigoNotaEntrada() {
		return this.codigoNotaEntrada;
	}

	public void setCodigoNotaEntrada(String codigoNotaEntrada) {
		this.codigoNotaEntrada = codigoNotaEntrada;
	}

	public String getCodigoProduto() {
		return this.codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return this.nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getPrecoTotal() {
		return this.precoTotal;
	}

	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getPrecoUnitario() {
		return this.precoUnitario;
	}

	public void setPrecoUnitario(String precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ProdutoNotaEntrada [codigo=" + codigo + ", codigoNotaEntrada=" + codigoNotaEntrada + ", codigoProduto="
				+ codigoProduto + ", nomeProduto=" + nomeProduto + ", precoTotal=" + precoTotal + ", precoUnitario="
				+ precoUnitario + ", quantidade=" + quantidade + "]";
	}

}