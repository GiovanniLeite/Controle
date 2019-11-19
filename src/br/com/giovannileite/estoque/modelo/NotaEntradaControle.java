package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notaEntradaControle database table.
 * 
 */
@Entity
@Table(name="notaEntradaControle")
@NamedQuery(name="NotaEntradaControle.findAll", query="SELECT n FROM NotaEntradaControle n")
public class NotaEntradaControle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(length=255)
	private String cnpj;

	@Column(nullable=false, length=255)
	private String codigoFornecedor;

	@Column(nullable=false, length=255)
	private String dataCompra;

	@Column(nullable=false, length=255)
	private String nomeFornecedor;

	@Column(nullable=false, length=255)
	private String numeroNota;

	@Column(nullable=false, length=255)
	private String totalNota;

	public NotaEntradaControle() {
	}

	public NotaEntradaControle(int codigo) 
	{
		this.codigo = codigo;
	}
	
	public NotaEntradaControle(String cnpj, String codigoFornecedor, String dataCompra, String nomeFornecedor,
			String numeroNota, String totalNota) {
		super();
		this.cnpj = cnpj;
		this.codigoFornecedor = codigoFornecedor;
		this.dataCompra = dataCompra;
		this.nomeFornecedor = nomeFornecedor;
		this.numeroNota = numeroNota;
		this.totalNota = totalNota;
	}

	public NotaEntradaControle(int codigo, String cnpj, String codigoFornecedor, String dataCompra,
			String nomeFornecedor, String numeroNota, String totalNota) {
		super();
		this.codigo = codigo;
		this.cnpj = cnpj;
		this.codigoFornecedor = codigoFornecedor;
		this.dataCompra = dataCompra;
		this.nomeFornecedor = nomeFornecedor;
		this.numeroNota = numeroNota;
		this.totalNota = totalNota;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigoFornecedor() {
		return this.codigoFornecedor;
	}

	public void setCodigoFornecedor(String codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}

	public String getDataCompra() {
		return this.dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getNomeFornecedor() {
		return this.nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNumeroNota() {
		return this.numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getTotalNota() {
		return this.totalNota;
	}

	public void setTotalNota(String totalNota) {
		this.totalNota = totalNota;
	}

	@Override
	public String toString() {
		return "NotaEntradaControle [codigo=" + codigo + ", cnpj=" + cnpj + ", codigoFornecedor=" + codigoFornecedor
				+ ", dataCompra=" + dataCompra + ", nomeFornecedor=" + nomeFornecedor + ", numeroNota=" + numeroNota
				+ ", totalNota=" + totalNota + "]";
	}

}