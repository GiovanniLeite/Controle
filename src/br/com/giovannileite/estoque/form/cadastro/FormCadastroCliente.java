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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.Session;

import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCliente;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.modelo.Cargo1;
import br.com.giovannileite.estoque.modelo.Cidade;
import br.com.giovannileite.estoque.modelo.Cliente;
import br.com.giovannileite.estoque.modelo.Estado;
import br.com.giovannileite.estoque.modelo.Pai;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

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
public class FormCadastroCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco
	
	private boolean iniciaComboEstado = false;//variavel q habilita ou n o actionPerformed o jcbPais
	private boolean iniciaComboCidade = false;//variavel q habilita ou n o actionPerformed o jcbEstado
	
	private String infoFoto = "";//variavel que guarda informacao sobre imagem
	private String infoFotoNova = "";//variavel que guarda informacao sobre imagem
	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	
	private List<Cidade> cidadesLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Estado> estadosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Pai> paisesLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Cliente> clientesLista;//Lista contendo as informacoes que serão mostradas na tela
		
	private FormRelatorioCliente formRelCliente;//form relatorio
	
	private JPanel contentPane;

	private JTable JTable;
	
	//jbl
	private JLabel jblFoto = new JLabel("");

	//JComboBox
	private JComboBox jcbPais = new JComboBox();
	private JComboBox jcbEstado = new JComboBox();
	private JComboBox jcbCidade = new JComboBox();
	
	//jtf
	private JTextField jftfEmissorRg;
	private JTextField jtfProcurar;
	private JTextField jtfCodigo;
	private JTextField jftfNome;
	private JTextField jftfEmail;
	private JTextField jftfObs;
	private JTextField jftfEndereco;
	private JTextField jftfNumero;
	private JTextField jftfBairro;
	private JFormattedTextField jftfRg;
	private JFormattedTextField jftfTelefone;
	private JFormattedTextField jftfCep;
	private JFormattedTextField jftfCelular;
	private JFormattedTextField jftfDataCadastro;
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
					FormCadastroCliente frame = new FormCadastroCliente();
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
	public FormCadastroCliente() {
		
	}
	
	public FormCadastroCliente(Usuario usuario) {
		setTitle("Manuten\u00E7\u00E3o do cadastro de clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		this.setLocationRelativeTo(null);
		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de clientes");
		jblTitulo.setBounds(165, 5, 366, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(107, 41, 490, 2);
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setBounds(35, 57, 52, 14);
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfProcurar = new JTextField();
		jtfProcurar.setBounds(91, 54, 195, 20);
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
		jblCodigo.setBounds(35, 85, 41, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setBounds(35, 103, 68, 20);
		jtfCodigo.setToolTipText("C\u00F3digo");
		jtfCodigo.setEditable(false);
		jtfCodigo.setColumns(10);
		
		jftfNome = new JFormattedTextField(controle.setMascara("******************************"));//limita o numero de caracteres
		jftfNome.setBounds(109, 103, 206, 20);
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
		
		JLabel jblNome = new JLabel("*Nome do Cliente:");
		jblNome.setBounds(109, 85, 101, 14);
		jblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblRG = new JLabel("RG:");
		jblRG.setBounds(247, 131, 19, 14);
		jblRG.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfRg = new JFormattedTextField(controle.setMascara("##.###.###-*"));//limita o numero de caracteres
		jftfRg.setBounds(247, 145, 92, 20);
		jftfRg.setToolTipText("Insira o RG");
		jftfRg.setHorizontalAlignment(SwingConstants.CENTER);
		jftfRg.setEditable(false);
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
		
		jftfCpf = new JFormattedTextField(controle.setMascara("###.###.###-##"));//limita os caracteres
		jftfCpf.setBounds(423, 145, 110, 20);
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
		jblCpf.setBounds(423, 131, 23, 14);
		jblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblEmail = new JLabel("Email:");
		jblEmail.setBounds(35, 131, 33, 14);
		jblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfEmail = new JFormattedTextField(controle.setMascara("******************************"));//limita numero de caracteres
		jftfEmail.setBounds(35, 145, 206, 20);
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
		jblTelefone.setBounds(321, 85, 52, 14);
		jblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblObs = new JLabel("Observa\u00E7\u00E3o:");
		jblObs.setBounds(161, 169, 70, 14);
		jblObs.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfObs = new JFormattedTextField(controle.setMascara("**************************************************"));//limita numero de caracteres
		jftfObs.setBounds(160, 187, 255, 20);
		jftfObs.setToolTipText("Insira a observa\u00E7\u00E3o");
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
		jblEndereco.setBounds(35, 253, 55, 14);
		jblEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfEndereco = new JFormattedTextField(controle.setMascara("********************************"));//limita numero de caracteres
		jftfEndereco.setBounds(35, 271, 230, 20);
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
		jblNumero.setBounds(271, 253, 47, 14);
		jblNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfNumero = new JFormattedTextField(controle.setMascara("**********"));//limita numero de caracteres
		jftfNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jftfNumero.setBounds(271, 271, 86, 20);
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
		jblBairro.setBounds(363, 253, 37, 14);
		jblBairro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfBairro = new JFormattedTextField(controle.setMascara("************************"));//limita numero de caracteres
		jftfBairro.setBounds(363, 271, 171, 20);
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
		jblCelular.setBounds(423, 85, 46, 14);
		jblCelular.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblDataCadastro = new JLabel("*Data de Cadastro:");
		jblDataCadastro.setBounds(35, 169, 120, 14);
		jblDataCadastro.setFont(new Font("Tahoma", Font.BOLD, 11));
		jbtProcurar.setEnabled(false);
		
		jftfEmissorRg = new JFormattedTextField(controle.setMascara("**********"));//limita numero de caracteres
		jftfEmissorRg.setHorizontalAlignment(SwingConstants.CENTER);
		jftfEmissorRg.setEditable(false);
		jftfEmissorRg.setToolTipText("Insira o \u00F3rg\u00E3o emissor");
		jftfEmissorRg.setBounds(344, 145, 75, 20);
		jftfEmissorRg.setColumns(10);
		jftfEmissorRg.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfEmissorRg.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfEmissorRg.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblEmissorRg = new JLabel("Emissor:");
		jblEmissorRg.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblEmissorRg.setBounds(344, 131, 63, 14);
		
		jftfDataCadastro = new JFormattedTextField(controle.setMascara("##/##/####"));//limita os caracteres
		jftfDataCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		jftfDataCadastro.setEditable(false);
		jftfDataCadastro.setToolTipText("Campo obrigat\u00F3rio");
		jftfDataCadastro.setBounds(35, 187, 120, 20);
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
		
		jftfCelular = new JFormattedTextField(controle.setMascara("(##)#####-####"));//limita os caracteres
		jftfCelular.setHorizontalAlignment(SwingConstants.CENTER);
		jftfCelular.setToolTipText("Insira o celular");
		jftfCelular.setEditable(false);
		jftfCelular.setBounds(423, 103, 110, 20);
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
		
		jftfCep = new JFormattedTextField(controle.setMascara("#####-###"));//limita os caracteres
		jftfCep.setHorizontalAlignment(SwingConstants.CENTER);
		jftfCep.setEditable(false);
		jftfCep.setToolTipText("Insira o cep");
		jftfCep.setBounds(423, 187, 110, 20);
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
		
		
		jftfTelefone = new JFormattedTextField(controle.setMascara("(##)####-####"));//limita os caracteres
		jftfTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		jftfTelefone.setEditable(false);
		jftfTelefone.setToolTipText("Insira o telefone");
		jftfTelefone.setBounds(321, 103, 98, 20);
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
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(35, 328, 603, 141);
		
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
		JTable.setToolTipText("Notas fiscais do cliente");
		JTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Celular", "Telefone"
			}
		));
		JTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		JTable.getColumnModel().getColumn(1).setPreferredWidth(137);
		JTable.getColumnModel().getColumn(2).setPreferredWidth(98);
		JTable.getColumnModel().getColumn(3).setPreferredWidth(95);
		scrollPane.setViewportView(JTable);
		
		
		jbtImprimir.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.setBounds(433, 295, 37, 29);
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelCliente == null)
				{
					formRelCliente = new FormRelatorioCliente();//cria o frame
				}
				formRelCliente.setVisible(true);//mostra o frame
			}
		});
		
		JLabel jblCep = new JLabel("CEP:");
		jblCep.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCep.setBounds(423, 169, 46, 14);
		
		JLabel jblpais = new JLabel("Pa\u00EDs:");
		jblpais.setToolTipText("Campo obrigat\u00F3rio");
		jblpais.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblpais.setBounds(35, 211, 46, 14);
		
		JLabel jblEstado = new JLabel("Estado:");
		jblEstado.setToolTipText("Campo obrigat\u00F3rio");
		jblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblEstado.setBounds(209, 211, 72, 14);
		
		JLabel jblCidade = new JLabel("Cidade:");
		jblCidade.setToolTipText("Campo obrigat\u00F3rio");
		jblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCidade.setBounds(384, 211, 55, 14);
		
		
		
		jcbPais.setToolTipText("Selecione o pa\u00EDs");
		jcbPais.setEnabled(false);
		jcbPais.setBounds(35, 229, 150, 20);
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
		jcbEstado.setBounds(209, 229, 150, 20);
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
		jcbCidade.setBounds(384, 229, 150, 20);
		
		JLabel jblFotoTitulo = new JLabel("Foto:");
		jblFotoTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblFotoTitulo.setBounds(542, 85, 46, 14);
		jblFoto.setToolTipText("Foto");
		
		
		jblFoto.setBounds(543, 103, 92, 115);
		
		jbtProcurar.setBounds(542, 222, 92, 23);
		jbtProcurar.setToolTipText("Procurar foto");
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setaImagem(1);//procura e seta foto
			}
		});
		
		
		jbtRemover.setEnabled(false);
		jbtRemover.setBounds(542, 250, 92, 23);
		jbtRemover.setToolTipText("Remover foto");
		jbtRemover.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setaImagem(2);//remove foto
			}
		});
		
		
		jbtPrimeiro.setBounds(35, 295, 37, 29);
		jbtPrimeiro.setToolTipText("Primeiro");
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
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
		
		
		jbtAnterior.setBounds(74, 295, 37, 29);
		jbtAnterior.setToolTipText("Anterior");
		jbtAnterior.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
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
		
		
		jbtProximo.setBounds(113, 295, 37, 29);
		jbtProximo.setToolTipText("Pr\u00F3ximo");
		jbtProximo.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
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
		
		
		jbtUltimo.setBounds(153, 295, 37, 29);
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setEnabled(false);
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = clientesLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurarRegistro.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurarRegistro.setToolTipText("Procurar");
		jbtProcurarRegistro.setBounds(289, 52, 24, 24);
		jbtProcurarRegistro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtNovoRegistro.setBounds(207, 295, 37, 29);
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30.png")));
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				apagaJtf();//seta "" em textfields e comboBox especificos
				
				atualizaNovo();//desabilita botoes,textfields e muda uma variavel 

				setaImagem(2);//remove foto
				
				atualizar = 0;//indica que ira criar um novo registro
			}
		});
		
		
		
		jbtSalvar.setEnabled(false);
		jbtSalvar.setBounds(247, 295, 37, 29);
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				try
				{
					//verifica se informacoes do formulario nao estao em branco
					if(jftfNome.getText().trim().equals("") 
							|| (jftfDataCadastro.getText().trim().length() < 10)
					  )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios.","Atenção",WIDTH);
					}
					else
					{
						if(clientesLista.size() == 0)//se nao existem registros
						{
							salvar();//salva o registro
						}
						else
						{
							
							int contAtualiza = 0;//contador do atualizar
							String codigoAtualiza = "";//codigo repetido
							
							if(atualizar == 1)//se esta atualizando um registro
							{
								for (int p = 0; p < clientesLista.size(); p++)//percorre a lista de clientes
								{
									//verifica se nome,rg ou cpf ja existem em algum registro
									if (jftfNome.getText().trim().equals(clientesLista.get(p).getNome().trim())
											|| (jftfRg.getText().trim().equals(clientesLista.get(p).getRg().trim()) && !jftfRg.getText().equals("  .   .   - "))
								   		    || (jftfCpf.getText().trim().equals(clientesLista.get(p).getCpf().trim()) && !jftfCpf.getText().equals("   .   .   -  ")))
									{
											contAtualiza ++;
											codigoAtualiza = String.valueOf(clientesLista.get(p).getCodigo()).trim();
									}
									if(p == (clientesLista.size() - 1))//se chegou ao ultimo registro da lista
									{
										//se n encotrou  nome,rg ou cpf repetidos ou se encontrou 1 repetido e este tem o msm codigo do que estamos atualizando
										if( (contAtualiza == 0) 
												|| (contAtualiza == 1 && codigoAtualiza.equals(jtfCodigo.getText().trim())) )
										{
											salvar();//salva o registro
											break;
										}
										JOptionPane.showMessageDialog(null,"Nome,RG ou CPF já existem em outro cadastro.","Atenção",WIDTH);
										break;
									}
								}
							}
							else//se esta criando um novo registro
							{
								for (int p = 0; p < clientesLista.size(); p++)//percorre a lista de clientes
								{
									//verifica se nome,rg ou cpf ja existem em algum registro
									if (jftfNome.getText().trim().equals(clientesLista.get(p).getNome().trim())
											|| (jftfRg.getText().trim().equals(clientesLista.get(p).getRg().trim()) && !jftfRg.getText().equals("  .   .   - "))
								   		    || (jftfCpf.getText().trim().equals(clientesLista.get(p).getCpf().trim()) && !jftfCpf.getText().equals("   .   .   -  ")))
									{
										JOptionPane.showMessageDialog(null,"Nome,RG ou CPF já existem em outro cadastro.","Atenção",WIDTH);
										break;
									}
									else if(p == (clientesLista.size() - 1))//se n encontrou nenhum nome,rg ou cpf igual
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
		
		
		jbtAtualizar.setBounds(287, 295, 37, 29);
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//habilita e desabilita botoes e textfields e muda uma variavel 
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		
		jbtExcluir.setBounds(327, 295, 37, 29);
		jbtExcluir.setToolTipText("Excluir");
		jbtExcluir.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
		jbtExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					Object[] options = {"Sim","Não"};
					String pergunta = "Tem certeza de que quer excluir este registro?";
					int confirmarExcluir = JOptionPane.showOptionDialog(null,pergunta,"Exclusão",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(confirmarExcluir == JOptionPane.YES_NO_OPTION)//inicia caso a resposta seja sim
					{
						int codigo = Integer.parseInt(jtfCodigo.getText());
						controle.excluir(3,codigo);//exclui registro 
						JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
						
						if(clientesLista.size() == 1)//se era o unico registro da lista
						{
							pegarRegistro();//desabilita botoes e textfields
						}
						else
						{
							for (int a = 0; a < clientesLista.size(); a++) 
							{
								if(a == (clientesLista.size() - 1) & codigo == clientesLista.get(a).getCodigo())//se o excluido era o ultimo registro
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
		
		
		
		jbtCancelar.setEnabled(false);
		jbtCancelar.setBounds(367, 295, 37, 29);
		jbtCancelar.setToolTipText("Cancelar");
		jbtCancelar.setIcon(new ImageIcon(FormCadastroCliente.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
		jbtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if(semRegistro.equals("1"))//se nao existem registros
				{
					iniciaComboEstado = false;//actionPerformed do jcbPais n deve funcionar
					iniciaComboCidade = false;//actionPerformed do jcbEstado n deve funcionar
					
					atualizaCb();//atualiza comboBox
					
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
					
					setaImagem(2);//remove foto
					
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
		contentPane.add(jblTitulo);
		contentPane.add(separator);
		contentPane.add(jblProcurar);
		contentPane.add(jtfProcurar);
		contentPane.add(jblCodigo);
		contentPane.add(jblNome);
		contentPane.add(jblRG);
		contentPane.add(jblCpf);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfNome);
		contentPane.add(jftfRg);
		contentPane.add(jftfCpf);
		contentPane.add(jblEmail);
		contentPane.add(jblTelefone);
		contentPane.add(jblCelular);
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
		contentPane.add(jblpais);
		contentPane.add(jblEstado);
		contentPane.add(jblCidade);
		contentPane.add(jcbPais);
		contentPane.add(jcbEstado);
		contentPane.add(jcbCidade);
		contentPane.add(jblFotoTitulo);
		contentPane.add(jblFoto);
		contentPane.add(jftfTelefone);
		contentPane.add(jftfCep);
		contentPane.add(jftfCelular);
		contentPane.add(jftfDataCadastro);
		contentPane.add(jblEmissorRg);
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

						//seta a imagem 
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
						//seta a imagem 
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
			
			clientesLista = controle.consultarTodosCliente("codigo");//preenche a lista com todos os registros
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(clientesLista.get(registroAtual).getCodigo()));
			jftfNome.setText(String.valueOf(clientesLista.get(registroAtual).getNome()));
			jftfEmail.setText(String.valueOf(clientesLista.get(registroAtual).getEmail()));
			jftfRg.setText(String.valueOf(clientesLista.get(registroAtual).getRg()));
			jftfEmissorRg.setText(String.valueOf(clientesLista.get(registroAtual).getEmissorRg()));
			jcbPais.setSelectedItem(String.valueOf(clientesLista.get(registroAtual).getPais()).trim());
			jcbEstado.setSelectedItem(String.valueOf(clientesLista.get(registroAtual).getEstado()).trim());
			jcbCidade.setSelectedItem(String.valueOf(clientesLista.get(registroAtual).getCidade()).trim());
			jftfEndereco.setText(String.valueOf(clientesLista.get(registroAtual).getEndereco()));
			jftfNumero.setText(String.valueOf(clientesLista.get(registroAtual).getNumero()));
			jftfBairro.setText(String.valueOf(clientesLista.get(registroAtual).getBairro()));
			jftfObs.setText(String.valueOf(clientesLista.get(registroAtual).getObs()));
			jftfCep.setText(String.valueOf(clientesLista.get(registroAtual).getCep()));
			jftfTelefone.setText(String.valueOf(clientesLista.get(registroAtual).getTelefone()));
			jftfDataCadastro.setText(String.valueOf(clientesLista.get(registroAtual).getDataCadastro()));
			jftfCelular.setText(String.valueOf(clientesLista.get(registroAtual).getCelular()));
			jftfCpf.setText(String.valueOf(clientesLista.get(registroAtual).getCpf()));
			infoFoto = clientesLista.get(registroAtual).getFoto();
			
			setaImagem(3);//seta imagem do registro
			
			verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Não existem registros.","Atenção",WIDTH);
			
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
			
			jtfProcurar.setEditable(false);
			
			jcbBlock();//seta "" em comboBox especificos
			
			setaImagem(2);//remove imagem
			
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
		if(registroAtual == clientesLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < clientesLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {clientesLista.get(i).getCodigo(),
										clientesLista.get(i).getNome(),
										clientesLista.get(i).getCelular(),
										clientesLista.get(i).getTelefone()});
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
			jcbPais.addItem("");
			paisesLista = controle.consultarTodosPai("codigo");//consulta e preenche a lista com todos os registros de paises
			for(int i = 0;i < paisesLista.size();i++)
			{
				jcbPais.addItem(paisesLista.get(i).getNome().trim());//preenche o cb de paises
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
			
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar País,Estado ou Cidade","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < clientesLista.size();i++)//percorre toda lista
			{
				if(digitado.equals(String.valueOf(clientesLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (clientesLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < clientesLista.size();i++)//percorre toda lista
			{
				int tamanhoNome = String.valueOf(clientesLista.get(i).getNome()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(clientesLista.get(i).getNome()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(clientesLista.get(i).getNome()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (clientesLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (clientesLista.size() - 1))
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
		String emissor = jftfEmissorRg.getText();
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
		String foto = infoFotoNova;
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			Cliente cliente = new Cliente(nome, rg, cpf, emissor, 
											email, telefone, celular, dataCadastro, 
											obs, cep, pais, estado, cidade, endereco,
											numero, bairro,foto);
			controle.salvar(cliente);//salva o novo registro
			JOptionPane.showMessageDialog(null,"Registro salvo no banco de dados com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
			Cliente cliente = new Cliente(codigo,nome, rg, cpf, emissor, 
											email, telefone, celular, dataCadastro, 
											obs, cep, pais, estado, cidade, endereco,
											numero, bairro,foto);
			controle.atualizar(cliente);//atualiza o registro
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
		jftfEmail.setText("");
		jftfRg.setValue(null);//limpa os dados do texfield
		jftfEmissorRg.setText("");
		jftfEndereco.setText("");
		jftfNumero.setText("");
		jftfBairro.setText("");
		jftfObs.setText("");
		jftfCep.setValue(null);//limpa os dados do texfield
		jftfTelefone.setValue(null);//limpa os dados do texfield
		jftfDataCadastro.setValue(null);//limpa os dados do texfield
		jftfCelular.setValue(null);//limpa os dados do texfield
		jftfCpf.setValue(null);//limpa os dados do texfield
		
		jcbPais.setSelectedItem("");
		jcbEstado.setSelectedItem("");
		jcbCidade.setSelectedItem("");
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
				
		jtfCodigo.requestFocus();
				
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

		jtfCodigo.requestFocus();
		
		jftfNome.setEditable(true);
		jftfRg.setEditable(true);
		jftfEmissorRg.setEditable(true);
		jftfCpf.setEditable(true);
		jftfTelefone.setEditable(true);
		jftfCelular.setEditable(true);
		jftfDataCadastro.setEditable(true);
		jftfCep.setEditable(true);
		jftfEmail.setEditable(true);
		jftfObs.setEditable(true);
		jftfEndereco.setEditable(true);
		jftfNumero.setEditable(true);
		jftfBairro.setEditable(true);
		jtfProcurar.setEditable(false);
		
		jcbPais.setEnabled(true);
		
		iniciaComboEstado = true;//actionPerformed do jcbPais deve funcionar
	}
	
	public void jcbBlock()//desabilita comboBox especificos
	{
		jcbPais.setEnabled(false);
		jcbCidade.setEnabled(false);
		jcbEstado.setEnabled(false);
	}
	
	public void jtfBlock()//desabilita texfields especificos
	{
		jftfNome.setEditable(false);
		jftfRg.setEditable(false);
		jftfCpf.setEditable(false);
		jftfEmissorRg.setEditable(false);
		jftfEmail.setEditable(false);
		jftfCelular.setEditable(false);
		jftfTelefone.setEditable(false);
		jftfDataCadastro.setEditable(false);
		jftfObs.setEditable(false);
		jftfCep.setEditable(false);
		jftfEndereco.setEditable(false);
		jftfNumero.setEditable(false);
		jftfBairro.setEditable(false);
	}
}
