package br.com.giovannileite.estoque.form.cadastro;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import JNumberField.JNumberField;
import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioFuncionario;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.modelo.Cargo1;
import br.com.giovannileite.estoque.modelo.Cidade;
import br.com.giovannileite.estoque.modelo.Estado;
import br.com.giovannileite.estoque.modelo.Funcionario;
import br.com.giovannileite.estoque.modelo.Pai;
import br.com.giovannileite.estoque.modelo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco

	private String infoFoto = "";//variavel que guarda informacao sobre imagem
	private String infoFotoNova = "";//variavel que guarda informacao sobre imagem
	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	
	private boolean iniciaComboEstado = false;//variavel q habilita ou n o actionPerformed o jcbPais
	private boolean iniciaComboCidade = false;//variavel q habilita ou n o actionPerformed o jcbEstado
	
	private List<Cidade> cidadesLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Estado> estadosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Pai> paisesLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Funcionario> funcionariosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Cargo1> cargosLista;//Lista contendo as informacoes que serão mostradas na tela

	private FormRelatorioFuncionario formRelFuncionario;//form relatorio
	
	private JPanel contentPane;
	
	private JTable JTable;
	
	//Label
	private JLabel jblFoto = new JLabel("");
	
	//JComboBox
	private JComboBox jcbSituacao = new JComboBox();
	private JComboBox jcbCargo = new JComboBox();
	private JComboBox jcbPais = new JComboBox();
	private JComboBox jcbEstado = new JComboBox();
	private JComboBox jcbCidade = new JComboBox();
	private JComboBox jcbNacionalidade = new JComboBox();
	
	//jftf
	private JTextField jtfVt;
	private JTextField jtfVr;
	private JTextField jtfVa;
	private JTextField jtfSalario;
	private JTextField jftfEmissorRg;
	private JTextField jtfProcurar;
	private JTextField jtfCodigo;
	private JTextField jftfNome;
	private JTextField jftfSexo;
	private JTextField jftfEmail;
	private JTextField jftfObs;
	private JTextField jftfEndereco;
	private JTextField jftfNumero;
	private JTextField jftfBairro;
	private JTextField jftfEstadoCivil;
	private JFormattedTextField jftfRg;
	private JFormattedTextField jftfCep;
	private JFormattedTextField jftfDataCadastro;
	private JFormattedTextField jftfTelefone;
	private JFormattedTextField jftfCelular;
	private JFormattedTextField jftfDataNascimento;
	private JFormattedTextField jftfCpf;
	
	//Botoes
	private JButton jbtProcurar = new JButton("Procurar");
	private JButton jbtRemover = new JButton("Remover");
	private JButton jbtPrimeiro = new JButton("");
	private JButton jbtAnterior = new JButton("");
	private JButton jbtProximo = new JButton("");
	private JButton jbtUltimo = new JButton("");
	private JButton jbtNovoRegistro = new JButton("");
	private JButton jbtSalvar = new JButton("");
	private JButton jbtAtualizar = new JButton("");
	private JButton jbtExcluir = new JButton("");
	private JButton jbtCancelar = new JButton("");
	private JButton jbtImprimir = new JButton("");
	private JButton jbtProcurarRegistro = new JButton("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroFuncionario frame = new FormCadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCadastroFuncionario() {
		
	}
	
	public FormCadastroFuncionario(Usuario usuario) {
		setTitle("Manuten\u00E7\u00E3o do cadastro de Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		this.setLocationRelativeTo(null);
		JLabel jbtTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de Funcion\u00E1rios");
		jbtTitulo.setBounds(150, 5, 420, 25);
		jbtTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(107, 41, 490, 2);
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setBounds(34, 57, 52, 14);
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfProcurar = new JTextField();
		jtfProcurar.setBounds(90, 54, 195, 20);
		jtfProcurar.setToolTipText("Insira o c\u00F3digo ou nome");
		jtfProcurar.setColumns(10);
		jtfProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		JLabel jblCodigo = new JLabel("C\u00F3digo:");
		jblCodigo.setBounds(34, 85, 41, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setBounds(34, 103, 68, 20);
		jtfCodigo.setToolTipText("C\u00F3digo");
		jtfCodigo.setEditable(false);
		jtfCodigo.setColumns(10);
		
		jftfNome = new JFormattedTextField(controle.setMascara("******************************************"));//limita numero de caracteres
		jftfNome.setBounds(108, 103, 235, 20);
		jftfNome.setToolTipText("Campo obrigat\u00F3rio");
		jftfNome.setEditable(false);
		jftfNome.setColumns(10);
		jftfNome.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfNome.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfNome.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblNome = new JLabel("*Nome do funcion\u00E1rio:");
		jblNome.setBounds(108, 85, 140, 14);
		jblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblRG = new JLabel("RG:");
		jblRG.setBounds(350, 85, 31, 14);
		jblRG.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfCpf = new JFormattedTextField(controle.setMascara("###.###.###-##"));//limita os caracteres
		jftfCpf.setBounds(540, 103, 92, 20);
		jftfCpf.setToolTipText("Insira o CPF");
		jftfCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jftfCpf.setEditable(false);
		jftfCpf.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfCpf.getText().trim().equals(".   .   -") | jftfCpf.getText().trim().length() < 14)
				{
					jftfCpf.setValue(null);//limpa os dados do texfield
				}
				jftfCpf.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfCpf.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblCpf = new JLabel("CPF:");
		jblCpf.setBounds(540, 85, 41, 14);
		jblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblSexo = new JLabel("Sexo:");
		jblSexo.setBounds(540, 338, 41, 14);
		jblSexo.setForeground(SystemColor.menuText);
		jblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfSexo = new JFormattedTextField(controle.setMascara("***********"));//limita numero de caracteres
		jftfSexo.setHorizontalAlignment(SwingConstants.CENTER);
		jftfSexo.setBounds(540, 356, 68, 20);
		jftfSexo.setToolTipText("Insira o sexo");
		jftfSexo.setEditable(false);
		jftfSexo.setColumns(10);
		jftfSexo.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfSexo.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfSexo.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblEmail = new JLabel("Email:");
		jblEmail.setBounds(34, 127, 33, 14);
		jblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfEmail = new JFormattedTextField(controle.setMascara("*******************************************************"));//limita numero de caracteres
		jftfEmail.setBounds(34, 145, 391, 20);
		jftfEmail.setToolTipText("Insira o email");
		jftfEmail.setEditable(false);
		jftfEmail.setColumns(10);
		jftfEmail.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfEmail.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfEmail.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblTelefone = new JLabel("Telefone:");
		jblTelefone.setBounds(434, 127, 52, 14);
		jblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblObs = new JLabel("Observa\u00E7\u00E3o:");
		jblObs.setBounds(160, 169, 70, 14);
		jblObs.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfObs = new JFormattedTextField(controle.setMascara("************************************************************"));//limita numero de caracteres
		jftfObs.setBounds(160, 187, 352, 20);
		jftfObs.setToolTipText("Insira observa\u00E7\u00E3o");
		jftfObs.setEditable(false);
		jftfObs.setColumns(10);
		jftfObs.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfObs.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfObs.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblEndereco = new JLabel("Endere\u00E7o:");
		jblEndereco.setBounds(34, 253, 68, 14);
		jblEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfEndereco = new JFormattedTextField(controle.setMascara("**************************************"));//limita numero de caracteres
		jftfEndereco.setBounds(34, 271, 280, 20);
		jftfEndereco.setToolTipText("Insira o endere\u00E7o");
		jftfEndereco.setEditable(false);
		jftfEndereco.setColumns(10);
		jftfEndereco.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfEndereco.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfEndereco.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblNumero = new JLabel("N\u00FAmero:");
		jblNumero.setBounds(320, 253, 64, 14);
		jblNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfNumero = new JFormattedTextField(controle.setMascara("**********"));//limita numero de caracteres
		jftfNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jftfNumero.setBounds(320, 271, 86, 20);
		jftfNumero.setToolTipText("Insira o n\u00FAmero");
		jftfNumero.setEditable(false);
		jftfNumero.setColumns(10);
		jftfNumero.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfNumero.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfNumero.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblBairro = new JLabel("Bairro:");
		jblBairro.setBounds(412, 253, 51, 14);
		jblBairro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfBairro = new JFormattedTextField(controle.setMascara("************************************"));//limita numero de caracteres
		jftfBairro.setBounds(412, 271, 220, 20);
		jftfBairro.setToolTipText("Insira o bairro");
		jftfBairro.setEditable(false);
		jftfBairro.setColumns(10);
		jftfBairro.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfBairro.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfBairro.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblCelular = new JLabel("Celular:");
		jblCelular.setBounds(533, 127, 64, 14);
		jblCelular.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblDataCadastro = new JLabel("*Data de Cadastro:");
		jblDataCadastro.setBounds(34, 169, 120, 14);
		jblDataCadastro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblCep = new JLabel("CEP:");
		jblCep.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCep.setBounds(524, 169, 46, 14);
		
		jftfDataCadastro = new JFormattedTextField(controle.setMascara("##/##/####"));//limita os caracteres
		jftfDataCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		jftfDataCadastro.setToolTipText("Campo obrigat\u00F3rio");
		jftfDataCadastro.setEditable(false);
		jftfDataCadastro.setBounds(34, 187, 120, 20);
		jftfDataCadastro.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfDataCadastro.getText().trim().equals("/  /") | jftfDataCadastro.getText().trim().length() < 10)
				{
					jftfDataCadastro.setValue(null);//limpa os dados do texfield
				}
				jftfDataCadastro.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfDataCadastro.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		jftfTelefone = new JFormattedTextField(controle.setMascara("(##)####-####"));//limita os caracteres
		jftfTelefone.setToolTipText("Insira o Telefone");
		jftfTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		jftfTelefone.setEditable(false);
		jftfTelefone.setBounds(435, 145, 92, 20);
		jftfTelefone.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfTelefone.getText().trim().equals("(  )    -") | jftfTelefone.getText().trim().length() < 13)
				{
					jftfTelefone.setValue(null);//limpa os dados do texfield
				}
				jftfTelefone.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfTelefone.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		jftfCelular = new JFormattedTextField(controle.setMascara("(##)#####-####"));//limita os caracteres
		jftfCelular.setToolTipText("Insira o Celular");
		jftfCelular.setEditable(false);
		jftfCelular.setHorizontalAlignment(SwingConstants.CENTER);
		jftfCelular.setBounds(537, 145, 95, 20);
		jftfCelular.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfCelular.getText().trim().equals("(  )     -") | jftfCelular.getText().trim().length() < 14)
				{
					jftfCelular.setValue(null);//limpa os dados do texfield
				}
				jftfCelular.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfCelular.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		
		JLabel jblDataNascimento = new JLabel("Data de Nascimento:");
		jblDataNascimento.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblDataNascimento.setBounds(34, 295, 130, 14);
		
		jftfDataNascimento = new JFormattedTextField(controle.setMascara("##/##/####"));//limita os caracteres
		jftfDataNascimento.setToolTipText("Insira a data de nascimento");
		jftfDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		jftfDataNascimento.setEditable(false);
		jftfDataNascimento.setBounds(34, 313, 120, 20);
		jftfDataNascimento.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfDataNascimento.getText().trim().equals("/  /") | jftfDataNascimento.getText().trim().length() < 10)
				{
					jftfDataNascimento.setValue(null);//limpa os dados do texfield
				}
				jftfDataNascimento.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfDataNascimento.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblSituacao = new JLabel("Situa\u00E7\u00E3o:");
		jblSituacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblSituacao.setBounds(541, 295, 67, 14);
		
		
		jcbSituacao.setEnabled(false);
		jcbSituacao.setToolTipText("Selecione a situa\u00E7\u00E3o");
		jcbSituacao.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo", "F\u00E9rias", "Licen\u00E7a-Maternidade", "Afastado"}));
		jcbSituacao.setBounds(540, 313, 140, 20);
		
		JLabel jblCargo = new JLabel("*Cargo:");
		jblCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCargo.setBounds(267, 295, 46, 14);
		
		
		jcbCargo.setEnabled(false);
		jcbCargo.setToolTipText("Campo obrigat\u00F3rio");
		jcbCargo.setBounds(267, 313, 166, 20);
		jcbCargo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaComboEstado == true)//se esta permitido iniciar
				{
					for(int i = 0;i < cargosLista.size();i++)
					{
						if(String.valueOf(cargosLista.get(i).getNome()).trim().equals(String.valueOf(jcbCargo.getSelectedItem()).trim()))
						{
							jtfSalario.setText(cargosLista.get(i).getSalario().trim());//seta o salario correspondente ao cargo
						}
					}
				}
			}
		});
		
		JLabel jblSalario = new JLabel("Sal\u00E1rio:");
		jblSalario.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblSalario.setBounds(184, 295, 51, 14);
		
		JLabel jblEstadoCivil = new JLabel("Estado Civil:");
		jblEstadoCivil.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblEstadoCivil.setBounds(443, 295, 86, 14);
		
		
		jftfEstadoCivil = new JFormattedTextField(controle.setMascara("***********"));//limita numero de caracteres
		jftfEstadoCivil.setToolTipText("Insira o estado civil");
		jftfEstadoCivil.setEditable(false);
		jftfEstadoCivil.setBounds(446, 313, 86, 20);
		jftfEstadoCivil.setColumns(10);
		jftfEstadoCivil.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfEstadoCivil.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfEstadoCivil.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		jftfEmissorRg = new JFormattedTextField(controle.setMascara("***********"));//limita numero de caracteres
		jftfEmissorRg.setEditable(false);
		jftfEmissorRg.setHorizontalAlignment(SwingConstants.CENTER);
		jftfEmissorRg.setToolTipText("Insira o \u00F3rg\u00E3o emissor");
		jftfEmissorRg.setBounds(447, 103, 86, 20);
		jftfEmissorRg.setColumns(10);
		jftfEmissorRg.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfEmissorRg.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfEmissorRg.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblEmissor = new JLabel("Emissor:");
		jblEmissor.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblEmissor.setBounds(447, 85, 63, 14);
		
		JLabel jblVA = new JLabel("Vale Alimenta\u00E7\u00E3o:");
		jblVA.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblVA.setBounds(34, 338, 120, 14);
		
		JLabel jblVR = new JLabel("Vale Refei\u00E7\u00E3o:");
		jblVR.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblVR.setBounds(155, 338, 92, 14);
		
		JLabel jblVT = new JLabel("Vale Transporte:");
		jblVT.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblVT.setBounds(263, 338, 109, 14);
		
		JLabel jblPais = new JLabel("Pa\u00EDs:");
		jblPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblPais.setBounds(34, 211, 46, 14);
		
		JLabel jblEstado = new JLabel("Estado:");
		jblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblEstado.setBounds(209, 211, 64, 14);
		
		JLabel jblCidade = new JLabel("Cidade:");
		jblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCidade.setBounds(450, 211, 63, 14);
		
		jcbPais.setToolTipText("Selecione o pa\u00EDs");
		jcbPais.setEnabled(false);
		jcbPais.setBounds(34, 229, 166, 20);
		jcbPais.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaComboEstado == true)//se esta permitido iniciar
				{
					//preenche o jcbEstado com os nomes dos estados que existem no pais selecionado no jcbPais
					jcbEstado.removeAllItems();
					for(int i = 0;i < estadosLista.size();i++)
					{
						if(String.valueOf(estadosLista.get(i).getPais()).trim().equals(String.valueOf(jcbPais.getSelectedItem()).trim()))
						{
							jcbEstado.addItem(estadosLista.get(i).getNome().trim());
						}
					}
					jcbEstado.setEnabled(true);
					iniciaComboCidade = true;
				}
			}
		});
		
		jcbEstado.setToolTipText("Selecione o estado");
		jcbEstado.setEnabled(false);
		jcbEstado.setBounds(209, 229, 231, 20);
		jcbEstado.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaComboCidade == true)//se esta permitido iniciar
				{
					//preenche o jcbCidade com os nomes das cidades que existem no estado selecionado no jcbEstado
					jcbCidade.removeAllItems();
					for(int i = 0;i < cidadesLista.size();i++)
					{
						if(String.valueOf(cidadesLista.get(i).getNomeEstado()).trim().equals(String.valueOf(jcbEstado.getSelectedItem()).trim()))
						{
							jcbCidade.addItem(cidadesLista.get(i).getNome().trim());
						}
					}
					jcbCidade.setEnabled(true);
				}
			}
		});
		
		jcbCidade.setToolTipText("Selecione a cidade");
		jcbCidade.setEnabled(false);
		jcbCidade.setBounds(450, 229, 176, 20);
		
		JLabel jblNacionalidade = new JLabel("Nacionalidade:");
		jblNacionalidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblNacionalidade.setBounds(375, 338, 92, 14);
		
		JLabel jblFotoTitulo = new JLabel("Foto:");
		jblFotoTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblFotoTitulo.setBounds(642, 85, 46, 14);
		
		jcbNacionalidade.setToolTipText("Selecione a nacionalidade");
		jcbNacionalidade.setEnabled(false);
		jcbNacionalidade.setBounds(379, 356, 152, 20);
		jblFoto.setToolTipText("Foto");
		
		jblFoto.setBounds(642, 103, 92, 115);
		
		jftfRg = new JFormattedTextField(controle.setMascara("##.###.###-*"));//limita numero de caracteres
		jftfRg.setToolTipText("Insira o RG");
		jftfRg.setEditable(false);
		jftfRg.setBounds(350, 103, 90, 20);
		jftfRg.setColumns(10);
		jftfRg.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfRg.getText().trim().equals(".   .   -") | jftfRg.getText().trim().length() < 12)
				{
					jftfRg.setValue(null);//limpa os dados do texfield
				}
				jftfRg.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfRg.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		jftfCep = new JFormattedTextField(controle.setMascara("#####-###"));//limita os caracteres
		jftfCep.setHorizontalAlignment(SwingConstants.CENTER);
		jftfCep.setEditable(false);
		jftfCep.setToolTipText("Insira o CEP");
		jftfCep.setBounds(522, 187, 110, 20);
		jftfCep.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfCep.getText().trim().equals("-") | jftfCep.getText().trim().length() < 9)
				{
					jftfCep.setValue(null);//limpa os dados do texfield
				}
				jftfCep.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfCep.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		jtfSalario = new JNumberField();
		jtfSalario.setEditable(false);
		jtfSalario.setToolTipText("Insira o sal\u00E1rio");
		jtfSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jtfSalario.setBounds(164, 313, 95, 20);
		
		jtfVa = new JNumberField();
		jtfVa.setToolTipText("Insira o VA");
		jtfVa.setEditable(false);
		jtfVa.setHorizontalAlignment(SwingConstants.CENTER);
		jtfVa.setBounds(34, 356, 110, 20);
		
		jtfVr = new JNumberField();
		jtfVr.setHorizontalAlignment(SwingConstants.CENTER);
		jtfVr.setToolTipText("Insira o VR");
		jtfVr.setEditable(false);
		jtfVr.setBounds(155, 356, 100, 20);
		
		jtfVt = new JNumberField();
		jtfVt.setHorizontalAlignment(SwingConstants.CENTER);
		jtfVt.setToolTipText("Insira o VT");
		jtfVt.setEditable(false);
		jtfVt.setBounds(263, 356, 102, 20);
		
		jbtProcurar.setEnabled(false);
		jbtProcurar.setBounds(642, 229, 92, 23);
		jbtProcurar.setToolTipText("Procurar foto");
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setaImagem(1);//procura e seta foto
			}
		});
		
		jbtRemover.setEnabled(false);
		jbtRemover.setBounds(642, 253, 92, 23);
		jbtRemover.setToolTipText("Remover foto");
		jbtRemover.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setaImagem(2);//remove foto
			}
		});
		
		
		jbtPrimeiro.setBounds(34, 380, 37, 29);
		jbtPrimeiro.setToolTipText("Primeiro");
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
		jbtPrimeiro.setEnabled(false);
		jbtPrimeiro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = 0;//variavel indica o primeiro registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = 0;//variavel indica a primeira linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtAnterior.setBounds(74, 380, 37, 29);
		jbtAnterior.setToolTipText("Anterior");
		jbtAnterior.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
		jbtAnterior.setEnabled(false);
		jbtAnterior.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual --;//variavel indica o indice do registro anterior na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha --;//variavel indica a linha anterior da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProximo.setBounds(116, 380, 37, 29);
		jbtProximo.setToolTipText("Pr\u00F3ximo");
		jbtProximo.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
		jbtProximo.setEnabled(false);
		jbtProximo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual ++;//variavel indica o indice do proximo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha ++;//variavel indica a proxima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtUltimo.setBounds(157, 380, 37, 29);
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setEnabled(false);
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = funcionariosLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurarRegistro.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurarRegistro.setToolTipText("Procurar");
		jbtProcurarRegistro.setBounds(289, 52, 24, 24);
		jbtProcurarRegistro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtNovoRegistro.setBounds(311, 380, 37, 29);
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30 (1).png")));
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				apagaJtf();//seta " " em textfields e comboBox especificos
				
				atualizaNovo();//desabilita botoes,textfields e muda uma variavel 

				setaImagem(2);//remove foto
				
				atualizar = 0;//indica que ira criar um novo registro
			}
		});
		
		
		jbtSalvar.setEnabled(false);
		jbtSalvar.setBounds(353, 380, 37, 29);
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	//verifica se informacoes do formulario nao estao em branco
					if(jftfNome.getText().trim().equals("") 
							|| (jftfDataCadastro.getText().trim().length() < 10)
							|| (jtfSalario.getText().length() > 15)
							|| (jtfVa.getText().length() > 15)
							|| (jtfVr.getText().length() > 15)
							|| (jtfVt.getText().length() > 15)
							|| (jcbCargo.getItemCount() == 0)
							|| (String.valueOf(jcbCargo.getSelectedItem()).trim().equals(""))
					  )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios ou ultrapassam o número máximo de caracteres.","Atenção",WIDTH);
					}
					else
					{
						if(funcionariosLista.size() == 0)//se nao existem registros
						{
							salvar();//salva o registro
						}
						else
						{
							
							int contAtualiza = 0;//contador do atualizar
							String codigoAtualiza = "";//codigo repetido
							
							if(atualizar == 1)//se esta atualizando um registro
							{
								for (int p = 0; p < funcionariosLista.size(); p++)//percorre a lista de funcionarios
								{
									//verifica se nome,rg ou cpf ja existem em algum registro
									if (jftfNome.getText().trim().equals(funcionariosLista.get(p).getNome().trim())
								 			|| (jftfRg.getText().trim().equals(funcionariosLista.get(p).getRg().trim()) && !jftfRg.getText().equals("  .   .   - "))
								 			|| (jftfCpf.getText().trim().equals(funcionariosLista.get(p).getCpf().trim()) &&!jftfCpf.getText().equals("   .   .   -  ")))
									{
											contAtualiza ++;
											codigoAtualiza = String.valueOf(funcionariosLista.get(p).getCodigo()).trim();
									}
									if(p == (funcionariosLista.size() - 1))//se chegou ao ultimo registro da lista
									{
										//se n encotrou  nome,rg ou cpf repetidos ou se encontrou 1 repetido e este tem o msm codigo do que estamos atualizando
										if( (contAtualiza == 0) 
												|| (contAtualiza == 1 && codigoAtualiza.equals(jtfCodigo.getText().trim())) )
										{
											salvar();//salva o registro
											break;
										}
										JOptionPane.showMessageDialog(null,"Nome,RG e/ou CPF já existem em outro cadastro.","Atenção",WIDTH);
										break;
									}
								}
							}
							else//se esta criando um novo registro
							{
								for (int p = 0; p < funcionariosLista.size(); p++) //percorre a lista de funcionarios
								{	
									//verifica se nome,rg ou cpf ja existem em algum registro
									if (jftfNome.getText().trim().equals(funcionariosLista.get(p).getNome().trim())
								 			|| (jftfRg.getText().trim().equals(funcionariosLista.get(p).getRg().trim()) && !jftfRg.getText().equals("  .   .   - "))
								 			|| (jftfCpf.getText().trim().equals(funcionariosLista.get(p).getCpf().trim()) &&!jftfCpf.getText().equals("   .   .   -  ")) )
									{
										JOptionPane.showMessageDialog(null,"Nome,RG e/ou CPF já existem em outro cadastro.","Atenção",WIDTH);
										break;
									}
									else if(p == (funcionariosLista.size() - 1))//se n encontrou nenhum nome,rg ou cpf igual
									{
										salvar();//salva o registro
										break;
									}
								}
							}
						}
					}
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao salvar/atualizar o registro.","Atenção",WIDTH);
				}
			}
		});
		
		
		jbtAtualizar.setBounds(395, 380, 37, 29);
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//habilita e desabilita botoes e Textfields e muda uma variavel 
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		
		jbtExcluir.setBounds(437, 380, 37, 29);
		jbtExcluir.setToolTipText("Excluir");
		jbtExcluir.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
		jbtExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					
					Object[] options = {"Sim","Não"};
					String pergunta = "Tem certeza de que quer excluir este registro?";
					int confirmarExcluir = JOptionPane.showOptionDialog(null,pergunta,"Exclusão",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(confirmarExcluir == JOptionPane.YES_NO_OPTION)//inicia caso seja resposta seja sim
					{
						int codigo = Integer.parseInt(jtfCodigo.getText());
						controle.excluir(6,codigo);//exclui registro 
						JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
						
						if(funcionariosLista.size() == 1)//se era o unico registro da lista
						{
							pegarRegistro();//desabilita botoes e textfields
						}
						else
						{
							for (int a = 0; a < funcionariosLista.size(); a++) 
							{
								if(a == (funcionariosLista.size() - 1) & codigo == funcionariosLista.get(a).getCodigo())//se o excluido era o ultimo registro
								{
									registroAtual --;//indica o registro anterior ao que foi excluido
									linha--;//indica a linha na tabela correspondente ao registro anterior
									break;
								}
							}
							pegarRegistro();//mostra informacoes do registro tela
							pegarRegistroTabela();//mostra informacoes dos registros na tabela de registros do form
							selecionaLinha();//seleciona linha
						}
					}					
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Não foi possivel excluir o registro do banco de dados.","Atenção",WIDTH);
				}
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(34, 411, 694, 140);
		
		JTable = new JTable();
		JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(atualizar == 3)//se nao estiver atualizando nem criando um novo registro
				{
					int linhaClick = JTable.getSelectedRow();//pega a linha selecionada na tabela de registros do form
					registroAtual = linhaClick;//indica o registro correspondente a linha selecionada
					linha = linhaClick;//indica a linha que foi selecionada
					pegarRegistro();//mostra as informacoes do registro selecionado no form
				}
			}
		});
		JTable.setToolTipText("");
		JTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Atividade"
			}
		));
		JTable.getColumnModel().getColumn(1).setPreferredWidth(155);
		scrollPane.setViewportView(JTable);

		
		jbtImprimir.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.setBounds(560, 380, 37, 29);
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelFuncionario == null)
				{
					formRelFuncionario = new FormRelatorioFuncionario();//cria o frame
				}
				formRelFuncionario.setVisible(true);//mostra o frame
			}
		});
		
		
		jbtCancelar.setEnabled(false);
		jbtCancelar.setBounds(479, 380, 37, 29);
		jbtCancelar.setToolTipText("Cancelar");
		jbtCancelar.setIcon(new ImageIcon(FormCadastroFuncionario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
		jbtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(semRegistro.equals("1"))//se nao existem registros
				{
					iniciaComboEstado = false;//actionPerformed do jcbPais n deve funcionar
					iniciaComboCidade = false;//actionPerformed do jcbEstado n deve funcionar
					
					atualizaCb();//atualiza comboBox
					
					setaImagem(2);//remove foto
					
					jbtNovoRegistro.setEnabled(true);
					jbtSalvar.setEnabled(false);
					jbtAtualizar.setEnabled(false);
					jbtExcluir.setEnabled(false);
					jbtCancelar.setEnabled(false);
					jbtImprimir.setEnabled(false);
					jbtProcurarRegistro.setEnabled(false);
					jbtProcurar.setEnabled(false);
					jbtRemover.setEnabled(false);
					
					desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
					
					apagaJtf();//seta "" em textfields e comboBox especificos

					jtfBlock();//desabilita textfiels especificos
					
					jtfProcurar.setEditable(false);
					
					jcbBlock();//desabilita comboBox especificos
					
					DefaultTableModel model = (DefaultTableModel) JTable.getModel();//pega o modelo da tabela de registros do form
					model.setNumRows(0);//limpa a tabela de registros do form
					
					atualizar = 3;//deixa disponivel o mouseclicked da tabela de registros do form
				}
				else
				{
					atualizaCb();//atualiza comboBox
					
					salvaCancela();//desabilita e habilita botoes e texfiels e muda variaveis
					
					pegarRegistro();//mostra informacoes do registro na tela
					
					verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
				}
			}
		});
		
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent evt)
			{
				controle.sair();//encerra a conexao
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(jbtTitulo);
		contentPane.add(separator);
		contentPane.add(jblProcurar);
		contentPane.add(jtfProcurar);
		contentPane.add(jblCodigo);
		contentPane.add(jblNome);
		contentPane.add(jblRG);
		contentPane.add(jblCpf);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfNome);
		contentPane.add(jftfCpf);
		contentPane.add(jblSexo);
		contentPane.add(jblEmail);
		contentPane.add(jblTelefone);
		contentPane.add(jblCelular);
		contentPane.add(jftfSexo);
		contentPane.add(jftfEmail);
		contentPane.add(jblDataCadastro);
		contentPane.add(jblObs);
		contentPane.add(jftfObs);
		contentPane.add(jblEndereco);
		contentPane.add(jblNumero);
		contentPane.add(jblBairro);
		contentPane.add(jbtProcurar);
		contentPane.add(jftfEndereco);
		contentPane.add(jftfNumero);
		contentPane.add(jftfBairro);
		contentPane.add(jbtRemover);
		contentPane.add(jbtPrimeiro);
		contentPane.add(jbtAnterior);
		contentPane.add(jbtProximo);
		contentPane.add(jbtUltimo);
		contentPane.add(jbtNovoRegistro);
		contentPane.add(jbtSalvar);
		contentPane.add(jbtAtualizar);
		contentPane.add(jbtExcluir);
		contentPane.add(jbtCancelar);
		contentPane.add(scrollPane);
		contentPane.add(jbtImprimir);
		contentPane.add(jblCep);
		contentPane.add(jftfDataCadastro);
		contentPane.add(jftfTelefone);
		contentPane.add(jftfCelular);
		contentPane.add(jblDataNascimento);
		contentPane.add(jftfDataNascimento);
		contentPane.add(jblSituacao);
		contentPane.add(jcbSituacao);
		contentPane.add(jblCargo);
		contentPane.add(jcbCargo);
		contentPane.add(jblSalario);
		contentPane.add(jblEstadoCivil);
		contentPane.add(jftfEstadoCivil);
		contentPane.add(jblVA);
		contentPane.add(jblVR);
		contentPane.add(jblVT);
		contentPane.add(jblPais);
		contentPane.add(jblEstado);
		contentPane.add(jblCidade);
		contentPane.add(jcbPais);
		contentPane.add(jcbEstado);
		contentPane.add(jcbCidade);
		contentPane.add(jblNacionalidade);
		contentPane.add(jblFotoTitulo);
		contentPane.add(jcbNacionalidade);
		contentPane.add(jblFoto);
		contentPane.add(jftfRg);
		contentPane.add(jftfCep);
		contentPane.add(jtfSalario);
		contentPane.add(jtfVa);
		contentPane.add(jtfVr);
		contentPane.add(jtfVt);
		contentPane.add(jblEmissor);
		contentPane.add(jftfEmissorRg);
		contentPane.add(jbtProcurarRegistro);

		//colocando dados na tela
		atualizaCb();//atualiza o comboBox
		pegarRegistro();//mostra o primeiro registro na tela
		pegarRegistroTabela();//preenche a tabela de registros
		selecionaLinha();//seleciona a primeira linha
		jtfCodigo.requestFocus();
				
		if(usuario.getNivel().equals("1"))//se for um usuario de nivel 1 n podera editar os registros
		{
			jbtNovoRegistro.setEnabled(false);
			jbtAtualizar.setEnabled(false);
			jbtExcluir.setEnabled(false);
		}
	}

	public void setaImagem(int ProcRemov)//coloca a foto do registro
	{
		try
		{
			switch(ProcRemov)//recebe o numero correspondente a remover ou n
			{
				case 1://Procura e seta imagem
				{
					JFileChooser selecionar = new JFileChooser();
					selecionar.setCurrentDirectory(new File("C:\\Users"));
					selecionar.setDialogTitle("Carregar foto");
					selecionar.showOpenDialog(this);
					String foto = "" + selecionar.getSelectedFile().getAbsolutePath();//nome da imagem selecionada
					
					if(foto.length() <= 250)
					{
						//criar uma imagem
						BufferedImage imagem = new BufferedImage(92, 115, BufferedImage.TYPE_INT_RGB);
						
						//le a imagem	
						ImageIcon imagemSelecionada = new ImageIcon(foto);	
						
						//redimensiona 
						imagem.getGraphics().drawImage(imagemSelecionada.getImage(), 0, 0, 114, 
								150, 0, 0, imagemSelecionada.getIconWidth(), imagemSelecionada.getIconHeight(), null);
						//seta
						jblFoto.setIcon(new ImageIcon(imagem));  
						
						infoFotoNova = foto;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Nome da foto excede o número de caracteres","Atenção",WIDTH);
					}
					break;
				}
				case 2://remove imagem
				{
					jblFoto.setIcon(null);  
					infoFotoNova = "";
					break;
				}
				case 3://seta imagem que foi dada
				{
					if(infoFoto.trim().equals(""))//n faz nada
					{
						
					}
					else
					{
						String foto = infoFoto;
						
						//criar uma imagem
						BufferedImage imagem = new BufferedImage(92, 115, BufferedImage.TYPE_INT_RGB);
						
						//le a imagem
						ImageIcon imagemSelecionada = new ImageIcon(foto);	
						
						//redimensiona 
						imagem.getGraphics().drawImage(imagemSelecionada.getImage(), 0, 0, 114, 
								150, 0, 0, imagemSelecionada.getIconWidth(), imagemSelecionada.getIconHeight(), null);
						//seta
						jblFoto.setIcon(new ImageIcon(imagem));  
						
						infoFotoNova = foto;
					}
					break;
				}
				default:
				{
					break;
				}
			}
		}
		catch(NullPointerException erro)
		{
			JOptionPane.showMessageDialog(null,"Não selecionou foto.","Atenção",WIDTH);
		}
		catch(Exception erro)
		{
			jblFoto.setIcon(null); 
			JOptionPane.showMessageDialog(null,"Erro ao mostrar foto","Atenção",WIDTH);
		}
	}
	
	public void pegarRegistro()//coloca as informações na tela
	{
		try
		{
			apagaJtf();//seta "" em textfields e comboBox especificos
			
			setaImagem(2);//remove imagem
			
			//mostra as informacoes do registro indicado
			funcionariosLista = controle.consultarTodosFuncionario("codigo");
			jtfCodigo.setText(String.valueOf(funcionariosLista.get(registroAtual).getCodigo()));
			jftfNome.setText(String.valueOf(funcionariosLista.get(registroAtual).getNome()));
			jftfRg.setText(String.valueOf(funcionariosLista.get(registroAtual).getRg()));
			jftfEmissorRg.setText(String.valueOf(funcionariosLista.get(registroAtual).getEmissorRg()));
			jftfCpf.setText(String.valueOf(funcionariosLista.get(registroAtual).getCpf()));
			jftfSexo.setText(String.valueOf(funcionariosLista.get(registroAtual).getSexo()));
			jftfEmail.setText(String.valueOf(funcionariosLista.get(registroAtual).getEmail()));
			jftfTelefone.setText(String.valueOf(funcionariosLista.get(registroAtual).getTelefone()));
			jftfCelular.setText(String.valueOf(funcionariosLista.get(registroAtual).getCelular()));
			jftfDataCadastro.setText(String.valueOf(funcionariosLista.get(registroAtual).getDataCadastro()));
			jftfObs.setText(String.valueOf(funcionariosLista.get(registroAtual).getObs()));
			jftfCep.setText(String.valueOf(funcionariosLista.get(registroAtual).getCep()));
			jcbPais.setSelectedItem(String.valueOf(funcionariosLista.get(registroAtual).getPais()).trim());
			jcbEstado.setSelectedItem(String.valueOf(funcionariosLista.get(registroAtual).getEstado()).trim());
			jcbCidade.setSelectedItem(String.valueOf(funcionariosLista.get(registroAtual).getCidade()).trim());
			jcbSituacao.setSelectedItem(String.valueOf(funcionariosLista.get(registroAtual).getSituacao()).trim());
			jcbCargo.setSelectedItem(String.valueOf(funcionariosLista.get(registroAtual).getCargo()).trim());
			jcbNacionalidade.setSelectedItem(String.valueOf(funcionariosLista.get(registroAtual).getNacionalidade()).trim());
			jftfEndereco.setText(String.valueOf(funcionariosLista.get(registroAtual).getEndereco()));
			jftfNumero.setText(String.valueOf(funcionariosLista.get(registroAtual).getNumero()));
			jftfBairro.setText(String.valueOf(funcionariosLista.get(registroAtual).getBairro()));
			infoFoto = funcionariosLista.get(registroAtual).getFoto();
			jftfDataNascimento.setText(String.valueOf(funcionariosLista.get(registroAtual).getDataNascimento()));
			jtfSalario.setText(String.valueOf(funcionariosLista.get(registroAtual).getSalario()));
			jftfEstadoCivil.setText(String.valueOf(funcionariosLista.get(registroAtual).getEstadoCivil()));
			jtfVa.setText(String.valueOf(funcionariosLista.get(registroAtual).getVa()));
			jtfVt.setText(String.valueOf(funcionariosLista.get(registroAtual).getVt()));
			jtfVr.setText(String.valueOf(funcionariosLista.get(registroAtual).getVr()));
			
			setaImagem(3);//seta imagem do registro
			
			verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Não existem registros.","Atenção",WIDTH);
			
			apagaJtf();//seta "" em textfields e comboBox especificos

			jcbBlock();//seta "" em comboBox especificos
			
			setaImagem(2);//remove imagem
			
			jbtSalvar.setEnabled(false);
			jbtAtualizar.setEnabled(false);
			jbtExcluir.setEnabled(false);
			jbtCancelar.setEnabled(false);
			jbtImprimir.setEnabled(false);
			jbtProcurarRegistro.setEnabled(false);
			jbtProcurar.setEnabled(false);
			jbtRemover.setEnabled(false);
			
			jtfProcurar.setEditable(false);
			
			desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
			
			DefaultTableModel model = (DefaultTableModel) JTable.getModel();//pega o modelo da tabela
			model.setNumRows(0);//apaga todas as linhas da tabela

			semRegistro = "1";//indica que n existem registros
		}
	}
	
	public void verificarBotao()//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou nao
	{
		if(registroAtual == 0)//se estiver no primeiro registro do list
		{
			jbtPrimeiro.setEnabled(false);
			jbtAnterior.setEnabled(false);
		}
		else 
		{
			jbtPrimeiro.setEnabled(true);
			jbtAnterior.setEnabled(true);
		}
		if(registroAtual == funcionariosLista.size() - 1)//se estiver no ultimo registro do list
		{
			jbtProximo.setEnabled(false);
			jbtUltimo.setEnabled(false);
		}
		else 
		{
			jbtProximo.setEnabled(true);
			jbtUltimo.setEnabled(true);
		}
	}
	
	public void pegarRegistroTabela()//preenche e atualiza a tabela
	{
		DefaultTableModel model = (DefaultTableModel) JTable.getModel();//Cria um objeto que pega o modelo da tabela
		model.setNumRows(0);//apaga todas as linhas da tabela
		
		for (int i = 0; i < funcionariosLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {funcionariosLista.get(i).getCodigo(),
										funcionariosLista.get(i).getNome(),
										funcionariosLista.get(i).getSituacao()});
		}
	}
	
	public void selecionaLinha()//mostra linha selecionada na tabela
	{
		JTable.getSelectionModel().setSelectionInterval(linha, linha);
	}
	
	public void atualizaCb()//atualiza o comboBox
	{
		try
		{
			jcbPais.removeAllItems();
			jcbNacionalidade.removeAllItems();
			jcbPais.addItem("");
			jcbNacionalidade.addItem("");
			paisesLista = controle.consultarTodosPai("codigo");//consulta e preenche a lista com todos os registros de paises
			for(int i = 0;i < paisesLista.size();i++)
			{
				jcbPais.addItem(paisesLista.get(i).getNome().trim());//preenche o cb de paises
				jcbNacionalidade.addItem(paisesLista.get(i).getNome().trim());//preenche o cb de nacionalidades
			}
			
			jcbEstado.removeAllItems();
			jcbEstado.addItem("");
			estadosLista = controle.consultarTodosEstado("codigo");//consulta e preenche a lista com todos os registros de estados
			for(int i = 0;i < estadosLista.size();i++)
			{
				jcbEstado.addItem(estadosLista.get(i).getNome().trim());//preenche o cb de estados
			}
			
			jcbCidade.removeAllItems();
			jcbCidade.addItem("");
			cidadesLista = controle.consultarTodosCidade("codigo");//consulta e preenche a lista com todos os registros de cidades
			for(int i = 0;i < cidadesLista.size();i++)
			{
				jcbCidade.addItem(cidadesLista.get(i).getNome().trim());//preenche o cb de cidades
			}
			
			jcbCargo.removeAllItems();
			jcbCargo.addItem("");
			cargosLista = controle.consultarTodosCargo("codigo");//consulta e preenche a lista com todos os registros de cargos
			for(int i = 0;i < cargosLista.size();i++)
			{
				jcbCargo.addItem(cargosLista.get(i).getNome().trim());//preenche o cb de cargos
			}
			
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar Nacionalidade,País,Estado ou Cidade","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < funcionariosLista.size();i++)//percorre toda lista
			{
				if(digitado.equals(String.valueOf(funcionariosLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (funcionariosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < funcionariosLista.size();i++)//percorre toda lista
			{
				int tamanhoNome = String.valueOf(funcionariosLista.get(i).getNome()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(funcionariosLista.get(i).getNome()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(funcionariosLista.get(i).getNome()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (funcionariosLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (funcionariosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
				}
			}
		}
	}
	
	public void salvar()
	{
		//pega informacoes do formulario
		String nome = jftfNome.getText();
		String rg = jftfRg.getText();
		String cpf = jftfCpf.getText();
		String sexo = jftfSexo.getText();
		String email = jftfEmail.getText();
		String telefone = jftfTelefone.getText();
		String celular = jftfCelular.getText();
		String dataCadastro = jftfDataCadastro.getText();
		String obs = jftfObs.getText();
		String cep = jftfCep.getText();
		String pais = String.valueOf(jcbPais.getSelectedItem());
		String estado = String.valueOf(jcbEstado.getSelectedItem());
		String cidade = String.valueOf(jcbCidade.getSelectedItem());
		String endereco = jftfEndereco.getText();
		String numero = jftfNumero.getText();
		String bairro = jftfBairro.getText();
		String dataNascimento = jftfDataNascimento.getText();
		String salario = jtfSalario.getText();
		String cargo = String.valueOf(jcbCargo.getSelectedItem());
		String estadoCivil = jftfEstadoCivil.getText();
		String situacao = String.valueOf(jcbSituacao.getSelectedItem());
		String va = jtfVa.getText();
		String vr = jtfVr.getText();
		String vt = jtfVt.getText();
		String nacionalidade = String.valueOf(jcbNacionalidade.getSelectedItem());
		String foto = infoFotoNova;
		String emissorRg = jftfEmissorRg.getText();
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			Funcionario funcionario = new Funcionario(nome,rg,cpf,sexo,email,telefone,celular,dataCadastro,obs,cep,pais,
														estado,cidade,endereco,numero,bairro,dataNascimento,salario,cargo,estadoCivil,situacao,va,vr,
															vt,nacionalidade,foto,emissorRg);
			controle.salvar(funcionario);//salva o novo registro
			JOptionPane.showMessageDialog(null,"Registro salvo no banco de dados com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
			Funcionario funcionario = new Funcionario(codigo, bairro, cargo, celular, cep,
					cidade, cpf,dataCadastro, dataNascimento, email, endereco, estado,
					estadoCivil, nacionalidade, nome, numero, obs, pais, rg,
					salario, sexo, situacao, telefone, va, vr, vt,
					foto,emissorRg);
			controle.atualizar(funcionario);//atualiza o registro
			JOptionPane.showMessageDialog(null,"Registro alterado com sucesso.","Notificação",WIDTH);
		}

		salvaCancela();//habilita e desabilita botoes e Textfields e muda variaveis
		atualizaCb();//atualiza os comboBox
		pegarRegistro();//mostra as informacoes do registro na tela
		pegarRegistroTabela();//mostra informacoes dos registros na tabela de registros do form
		selecionaLinha();//seleciona linha correspondente ao registro na tabela de registros do form
		
		semRegistro = "2";//indica que existem registros cadastrados
	}
	
	public void desabilitaSeta()//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
	{
		jbtPrimeiro.setEnabled(false);
		jbtAnterior.setEnabled(false);
		jbtProximo.setEnabled(false);
		jbtUltimo.setEnabled(false);
	}
	
	public void apagaJtf()//seta "" em Texfields e ComboBox especificos
	{
		jtfCodigo.requestFocus();
		
		jtfCodigo.setText("");
		jftfNome.setText("");
		jftfRg.setValue(null);//limpa os dados do texfield
		jftfEmissorRg.setText("");
		jftfCpf.setValue(null);//limpa os dados do texfield
		jftfSexo.setText("");
		jftfEmail.setText("");
		jftfCelular.setValue(null);//limpa os dados do texfield
		jftfTelefone.setValue(null);//limpa os dados do texfield
		jftfDataCadastro.setValue(null);//limpa os dados do texfield
		jftfObs.setText("");
		jftfCep.setValue(null);//limpa os dados do texfield
		jftfEndereco.setText("");
		jftfNumero.setText("");
		jftfBairro.setText("");
		jftfDataNascimento.setValue(null);//limpa os dados do texfield
		jtfSalario.setText("");
		jftfEstadoCivil.setText("");
		jtfVa.setText("");
		jtfVr.setText("");
		jtfVt.setText("");

		jcbPais.setSelectedItem("");
		jcbEstado.setSelectedItem("");
		jcbCidade.setSelectedItem("");
		jcbNacionalidade.setSelectedItem("");
		jcbCargo.setSelectedItem("");
		jcbSituacao.setSelectedItem("Ativo");
	}
	
	public void salvaCancela()//funcao compartilhada por salvar e cancelar
	{
		iniciaComboEstado = false;//actionPerformed do jcbPais n deve funcionar
		iniciaComboCidade = false;//actionPerformed do jcbEstado n deve funcionar
		
		jbtNovoRegistro.setEnabled(true);
		jbtAtualizar.setEnabled(true);
		jbtExcluir.setEnabled(true);
		jbtImprimir.setEnabled(true);
		jbtProcurarRegistro.setEnabled(true);
		jbtSalvar.setEnabled(false);
		jbtCancelar.setEnabled(false);
		jbtProcurar.setEnabled(false);
		jbtRemover.setEnabled(false);
		
		jtfBlock();//desabilita texfields especificos
		
		jtfProcurar.setEditable(true);

		jcbBlock();//desabilita comboBox especificos
		
		atualizar = 3;//deixa habilitado o mouseclicked da tabela
	}
	
	public void atualizaNovo()//funcao compartilhada por atualizar e novo
	{
		jbtSalvar.setEnabled(true);
		jbtCancelar.setEnabled(true);
		jbtProcurar.setEnabled(true);
		jbtRemover.setEnabled(true);
		jbtNovoRegistro.setEnabled(false);
		jbtAtualizar.setEnabled(false);
		jbtExcluir.setEnabled(false);
		jbtImprimir.setEnabled(false);
		jbtProcurarRegistro.setEnabled(false);
		
		desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"

		jftfNome.setEditable(true);
		jftfRg.setEditable(true);
		jftfCpf.setEditable(true);
		jftfRg.setEditable(true);
		jftfSexo.setEditable(true);
		jftfEmail.setEditable(true);
		jftfCelular.setEditable(true);
		jftfTelefone.setEditable(true);
		jftfDataCadastro.setEditable(true);
		jftfObs.setEditable(true);
		jftfCep.setEditable(true);
		jftfEndereco.setEditable(true);
		jftfNumero.setEditable(true);
		jftfBairro.setEditable(true);
		jftfDataNascimento.setEditable(true);
		jtfSalario.setEditable(true);
		jftfEstadoCivil.setEditable(true);
		jtfVa.setEditable(true);
		jtfVr.setEditable(true);
		jtfVt.setEditable(true);
		jftfEmissorRg.setEditable(true);
		jtfProcurar.setEditable(false);

		jcbPais.setEnabled(true);
		jcbCargo.setEnabled(true);
		jcbSituacao.setEnabled(true);
		jcbNacionalidade.setEnabled(true);

		iniciaComboEstado = true;//actionPerformed do jcbPais deve funcionar
	}
	
	public void jcbBlock()//desabilita comboBox especificos
	{
		jcbPais.setEnabled(false);
		jcbCidade.setEnabled(false);
		jcbEstado.setEnabled(false);
		jcbCargo.setEnabled(false);
		jcbNacionalidade.setEnabled(false);
		jcbSituacao.setEnabled(false);
	}
	
	public void jtfBlock()//desabilita texfields especificos
	{
		jftfNome.setEditable(false);
		jftfRg.setEditable(false);
		jftfCpf.setEditable(false);
		jftfSexo.setEditable(false);
		jftfEmail.setEditable(false);
		jftfCelular.setEditable(false);
		jftfTelefone.setEditable(false);
		jftfDataCadastro.setEditable(false);
		jftfObs.setEditable(false);
		jftfCep.setEditable(false);
		jftfEndereco.setEditable(false);
		jftfNumero.setEditable(false);
		jftfBairro.setEditable(false);
		jftfDataNascimento.setEditable(false);
		jtfSalario.setEditable(false);
		jftfEstadoCivil.setEditable(false);
		jtfVa.setEditable(false);
		jtfVr.setEditable(false);
		jtfVt.setEditable(false);
		jftfEmissorRg.setEditable(false);
	}
}
