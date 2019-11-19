package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notaSaidaControle database table.
 * 
 */
@Entity
@Table(name="notaSaidaControle")
@NamedQuery(name="NotaSaidaControle.findAll", query="SELECT n FROM NotaSaidaControle n")
public class NotaSaidaControle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(length=255)
	private String cpf;

	@Column(nullable=false, length=255)
	private String codigoCliente;

	@Column(nullable=false, length=255)
	private String dataVenda;

	@Column(nullable=false, length=255)
	private String nomeCliente;

	@Column(nullable=false, length=255)
	private String numeroNota;

	@Column(nullable=false, length=255)
	private String totalNota;

	public NotaSaidaControle() {
	}
	
	public NotaSaidaControle(int codigo) 
	{
		this.codigo = codigo;
	}

	public NotaSaidaControle(String cpf, String codigoCliente, String dataVenda, String nomeCliente, String numeroNota,
			String totalNota) {
		super();
		this.cpf = cpf;
		this.codigoCliente = codigoCliente;
		this.dataVenda = dataVenda;
		this.nomeCliente = nomeCliente;
		this.numeroNota = numeroNota;
		this.totalNota = totalNota;
	}

	public NotaSaidaControle(int codigo, String cpf, String codigoCliente, String dataVenda, String nomeCliente,
			String numeroNota, String totalNota) {
		super();
		this.codigo = codigo;
		this.cpf = cpf;
		this.codigoCliente = codigoCliente;
		this.dataVenda = dataVenda;
		this.nomeCliente = nomeCliente;
		this.numeroNota = numeroNota;
		this.totalNota = totalNota;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCnpj(String cpf) {
		this.cpf= cpf;
	}

	public String getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDataVenda() {
		return this.dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
		return "NotaSaidaControle [codigo=" + codigo + ", cpf=" + cpf + ", codigoCliente=" + codigoCliente
				+ ", dataVenda=" + dataVenda + ", nomeCliente=" + nomeCliente + ", numeroNota=" + numeroNota
				+ ", totalNota=" + totalNota + "]";
	}

}