package br.com.giovannileite.estoque.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@Table(name="funcionario")
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int codigo;

	@Column(length=255)
	private String bairro;

	@Column(nullable=false, length=255)
	private String cargo;

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
	private String dataNascimento;

	@Column(length=255)
	private String email;

	@Column(length=255)
	private String endereco;

	@Column(length=255)
	private String estado;

	@Column(length=255)
	private String estadoCivil;

	@Column(length=255)
	private String nacionalidade;

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
	private String salario;

	@Column(length=255)
	private String sexo;

	@Column(length=255)
	private String situacao;

	@Column(length=255)
	private String telefone;

	@Column(length=255)
	private String va;

	@Column(length=255)
	private String vr;

	@Column(length=255)
	private String vt;
	
	@Column(length=255)
	private String foto;
	
	@Column(length=255)
	private String emissorRg;

	public Funcionario() {
	}
	
	public Funcionario(int codigo) 
	{
		this.codigo = codigo;
	}
	public Funcionario(int codigo, String bairro, String cargo, String celular, String cep, String cidade, String cpf,
			String dataCadastro, String dataNascimento, String email, String endereco, String estado,
			String estadoCivil, String nacionalidade, String nome, String numero, String obs, String pais, String rg,
			String salario, String sexo, String situacao, String telefone, String va, String vr, String vt,
			String foto,String emissorRg) {
		super();
		this.codigo = codigo;
		this.bairro = bairro;
		this.cargo = cargo;
		this.celular = celular;
		this.cep = cep;
		this.cidade = cidade;
		this.cpf = cpf;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.endereco = endereco;
		this.estado = estado;
		this.estadoCivil = estadoCivil;
		this.nacionalidade = nacionalidade;
		this.nome = nome;
		this.numero = numero;
		this.obs = obs;
		this.pais = pais;
		this.rg = rg;
		this.salario = salario;
		this.sexo = sexo;
		this.situacao = situacao;
		this.telefone = telefone;
		this.va = va;
		this.vr = vr;
		this.vt = vt;
		this.foto = foto;
		this.emissorRg = emissorRg;
	}
	
	public Funcionario(String nome,String rg,String cpf,String sexo,String email,
			String telefone,String celular,String dataCadastro,String obs,String cep,String pais,
				String estado,String cidade,String endereco,String numero,String bairro,String dataNascimento,
				String salario,String cargo,String estadoCivil,String situacao,String va,String vr,
					String vt,String nacionalidade,String foto,String emissorRg) 
	{
		this.bairro = bairro;
		this.cargo = cargo;
		this.celular = celular;
		this.cep = cep;
		this.cidade = cidade;
		this.cpf = cpf;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.endereco = endereco;
		this.estado = estado;
		this.estadoCivil = estadoCivil;
		this.nacionalidade = nacionalidade;
		this.nome = nome;
		this.numero = numero;
		this.obs = obs;
		this.pais = pais;
		this.rg = rg;
		this.salario = salario;
		this.sexo = sexo;
		this.situacao = situacao;
		this.telefone = telefone;
		this.va = va;
		this.vr = vr;
		this.vt = vt;
		this.foto = foto;
		this.emissorRg = emissorRg;
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

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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

	public String getSalario() {
		return this.salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getVa() {
		return this.va;
	}

	public void setVa(String va) {
		this.va = va;
	}

	public String getVr() {
		return this.vr;
	}

	public void setVr(String vr) {
		this.vr = vr;
	}

	public String getVt() {
		return this.vt;
	}

	public void setVt(String vt) {
		this.vt = vt;
	}
	
	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEmissorRg() {
		return emissorRg;
	}

	public void setEmissorRg(String emissorRg) {
		this.emissorRg = emissorRg;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", bairro=" + bairro + ", cargo=" + cargo + ", celular=" + celular
				+ ", cep=" + cep + ", cidade=" + cidade + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro
				+ ", dataNascimento=" + dataNascimento + ", email=" + email + ", endereco=" + endereco + ", estado="
				+ estado + ", estadoCivil=" + estadoCivil + ", nacionalidade=" + nacionalidade + ", nome=" + nome
				+ ", numero=" + numero + ", obs=" + obs + ", pais=" + pais + ", rg=" + rg + ", salario=" + salario
				+ ", sexo=" + sexo + ", situacao=" + situacao + ", telefone=" + telefone + ", va=" + va + ", vr=" + vr
				+ ", vt=" + vt + ", foto=" + foto + ", emissorRg=" + emissorRg + "]";
	}

	
}