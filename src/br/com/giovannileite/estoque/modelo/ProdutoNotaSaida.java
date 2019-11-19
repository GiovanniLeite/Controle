package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the produtoNotaSaida database table.
 * 
 */
@Entity
@Table(name="produtoNotaSaida")
@NamedQuery(name="ProdutoNotaSaida.findAll", query="SELECT p FROM ProdutoNotaSaida p")
public class ProdutoNotaSaida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(nullable=false, length=255)
	private String codigoNotaSaida;

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

	public ProdutoNotaSaida() {
	}
	
	public ProdutoNotaSaida(int codigo) 
	{
		this.codigo = codigo;
	}

	public ProdutoNotaSaida(String codigoProduto, String nomeProduto, String precoTotal, String precoUnitario,
			String quantidade) {
		super();
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoTotal = precoTotal;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public ProdutoNotaSaida(int codigo, String codigoNotaSaida, String codigoProduto, String nomeProduto, String precoTotal,
			String precoUnitario, String quantidade) {
		super();
		this.codigo = codigo;
		this.codigoNotaSaida = codigoNotaSaida;
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.precoTotal = precoTotal;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public ProdutoNotaSaida(String codigoNotaSaida, String codigoProduto, String nomeProduto, String precoTotal,
			String precoUnitario, String quantidade) {
		super();
		this.codigoNotaSaida = codigoNotaSaida;
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

	public String getCodigoNotaSaida() {
		return this.codigoNotaSaida;
	}

	public void setCodigoNotaSaida(String codigoNotaSaida) {
		this.codigoNotaSaida = codigoNotaSaida;
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
		return "ProdutoNotaSaida [codigo=" + codigo + ", codigoNotaSaida=" + codigoNotaSaida + ", codigoProduto="
				+ codigoProduto + ", nomeProduto=" + nomeProduto + ", precoTotal=" + precoTotal + ", precoUnitario="
				+ precoUnitario + ", quantidade=" + quantidade + "]";
	}

}