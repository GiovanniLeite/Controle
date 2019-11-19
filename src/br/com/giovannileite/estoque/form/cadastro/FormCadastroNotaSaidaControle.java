package br.com.giovannileite.estoque.form.cadastro;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import JNumberField.JNumberField;
import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.modelo.Cliente;
import br.com.giovannileite.estoque.modelo.NotaEntradaControle;
import br.com.giovannileite.estoque.modelo.NotaSaidaControle;
import br.com.giovannileite.estoque.modelo.Produto;
import br.com.giovannileite.estoque.modelo.ProdutoNotaEntrada;
import br.com.giovannileite.estoque.modelo.ProdutoNotaSaida;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroNotaSaidaControle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco

	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private boolean iniciaCombo = false;//variavel q habilita ou n o actionPerformed do jcbProduto e jcbCliente
	
	private int codigoExcluir = 0; //orienta exclusao da nota
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	private int linha = 0;//indica qual linha da tabela de notas esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	private int quantidadeProdutoAdicionado = 0;//quantidade de produtos adicionados durante a atualizacao
	
	private List<NotaSaidaControle> notasLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<ProdutoNotaSaida> produtosNotaLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Produto> produtosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Cliente> clienteLista;//Lista contendo as informacoes que serão mostradas na tela
	
	private List<ProdutoNotaSaida> excluirTabelaProdutoNota = new ArrayList<>();//itens que serao excluidos
	private List<ProdutoNotaSaida> salvarTabelaProdutoNota = new ArrayList<>();//itens que serao salvos
	
	private List<ProdutoNotaSaida> auxiliarTabelaProdutoNota = new ArrayList<>();//auxiliar nos produtos adicionados a nota
	private List<Produto> auxiliarAtualizarProduto = new ArrayList<>();//atualiza tabela de cadastro de produtos
	
	private FormRelatorioNotaSaida formRelNotaSaida;//form relatorio
	
	private JPanel contentPane;

	private JTable JTableItens;
	private JTable JTable;
	
	//Botoes
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
	private JButton jbtRemover = new JButton("");
	private JButton jbtAdicionar = new JButton("");
	private JButton jbtProcurar = new JButton("");
			
	//JComboBox
	private JComboBox jcbCliente = new JComboBox();
	private JComboBox jcbProduto = new JComboBox();		
	
	//jtf
	private JFormattedTextField jftfDataVenda;
	private JFormattedTextField jftfCpf;
	private JTextField jftfNumeroNotaFiscal;
	private JTextField jtfProcurar;
	private JTextField jtfCodigoCliente;
	private JTextField jtfCodigoProduto;
	private JTextField jftfQuantidade;
	private JTextField jtfCodigo;
	private JTextField jtfTotalNota;
	private JTextField jtfPrecoUnitario;
	private JTextField jtfPrecoTotal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroNotaSaidaControle frame = new FormCadastroNotaSaidaControle();
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
	
	public FormCadastroNotaSaidaControle() {
		
	}
	
	public FormCadastroNotaSaidaControle(Usuario usuario) {
		
		
		setResizable(false);
		setTitle("Manuten\u00E7\u00E3o do cadastro de notas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o de venda de mercadorias");
		jblTitulo.setBounds(88, 16, 410, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(85, 52, 410, 2);
		
		JLabel jblNumeroNota = new JLabel("*N\u00FAmero da Nota Fiscal:");
		jblNumeroNota.setBounds(69, 90, 141, 14);
		jblNumeroNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblTotalNota = new JLabel("*Total da Nota:");
		jblTotalNota.setBounds(336, 90, 92, 14);
		jblTotalNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfNumeroNotaFiscal = new JFormattedTextField(controle.setMascara("******************"));//limita o numero de caracteres
		jftfNumeroNotaFiscal.setBounds(69, 106, 141, 20);
		jftfNumeroNotaFiscal.setToolTipText("Campo obrigat\u00F3rio");
		jftfNumeroNotaFiscal.setEditable(false);
		jftfNumeroNotaFiscal.setColumns(10);
		jftfNumeroNotaFiscal.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfNumeroNotaFiscal.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfNumeroNotaFiscal.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblDataVenda = new JLabel("*Data da Venda:");
		jblDataVenda.setBounds(220, 90, 107, 14);
		jblDataVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane scrollPaneItens = new JScrollPane();
		scrollPaneItens.setToolTipText("Tabela de itens da nota");
		scrollPaneItens.setBounds(10, 223, 560, 75);
		
		JTableItens = new JTable();
		JTableItens.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num. Nota", "Cod. Produto", "Nome do Produto", "Quantidade", "Pre\u00E7o Unit\u00E1rio", "Pre\u00E7o Total"
			}
		));
		JTableItens.getColumnModel().getColumn(2).setPreferredWidth(94);
		JTableItens.getColumnModel().getColumn(4).setPreferredWidth(81);
		scrollPaneItens.setViewportView(JTableItens);
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setBounds(10, 65, 58, 14);
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfProcurar = new JTextField();
		jtfProcurar.setBounds(67, 62, 130, 20);
		jtfProcurar.setToolTipText("Insira o c\u00F3digo ou o n\u00FAmero da nota");
		jtfProcurar.setColumns(10);
		jtfProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou numero da nota
			}
		});
		
		JLabel jblCliente = new JLabel("*Cliente:");
		jblCliente.setBounds(10, 130, 77, 14);
		jblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigoCliente = new JFormattedTextField(controle.setMascara("******"));//limita o numero de caracteres
		jtfCodigoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigoCliente.setBounds(10, 146, 46, 20);
		jtfCodigoCliente.setToolTipText("C\u00F3digo do cliente");
		jtfCodigoCliente.setEditable(false);
		jtfCodigoCliente.setColumns(10);
		jtfCodigoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(jtfCodigoCliente.getText().trim().equals(""))
				{
					jcbCliente.setSelectedItem("");
					jftfCpf.setValue(null);//limpa os dados do texfield
				}
				else
				{	//seta o nome e o cpf do cliente
					for (int p = 0; p < clienteLista.size(); p++) 
					{
						if (jtfCodigoCliente.getText().trim().equals(String.valueOf(clienteLista.get(p).getCodigo()).trim()))
						{
							jtfCodigoCliente.setText("");
							jftfCpf.setValue(null);//limpa os dados do texfield
							
							jcbCliente.setSelectedItem(String.valueOf(clienteLista.get(p).getNome()).trim());
							jftfCpf.setText(String.valueOf(clienteLista.get(p).getCpf()));
						}
					}
				}
			}
		});
		jtfCodigoCliente.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jtfCodigoCliente.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jtfCodigoCliente.setCaretPosition(0);//inicia na primeira posicao
			}
		});

		
		jcbCliente.setBounds(59, 146, 296, 20);
		jcbCliente.setEnabled(false);
		jcbCliente.setToolTipText("Campo obrigat\u00F3rio");
		jcbCliente.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaCombo == true)//se esta permitido iniciar
				{
					String fornecedor = String.valueOf(jcbCliente.getSelectedItem()).trim();

					if(jcbCliente.getSelectedItem().equals(""))
					{
						jtfCodigoCliente.setText("");
						jftfCpf.setValue(null);//limpa os dados do texfield
					}
					else
					{	//seta o codigo e o cpf do cliente
						for(int i = 0;i < clienteLista.size();i++)
						{
							if(fornecedor.equals(clienteLista.get(i).getNome().trim()))
							{
								jtfCodigoCliente.setText("");
								jftfCpf.setValue(null);//limpa os dados do texfield
								
								jtfCodigoCliente.setText(String.valueOf(clienteLista.get(i).getCodigo()));
								jftfCpf.setText(String.valueOf(clienteLista.get(i).getCpf()));
							}
						}
					}
				}
			}
		});
		
		jtfCodigoProduto = new JFormattedTextField(controle.setMascara("******"));//limita o numero de caracteres
		jtfCodigoProduto.setEditable(false);
		jtfCodigoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigoProduto.setBounds(10, 192, 46, 20);
		jtfCodigoProduto.setToolTipText("C\u00F3digo do produto");
		jtfCodigoProduto.setColumns(10);
		jtfCodigoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(jtfCodigoProduto.getText().trim().equals(""))
				{
					apagaCampos();//seta "" em textfields e comboBox especificos
				}
				else
				{	//seta o nome e preco unitario do produto
					for (int p = 0; p < produtosLista.size(); p++) 
					{
						if (jtfCodigoProduto.getText().trim().equals(String.valueOf(produtosLista.get(p).getCodigo()).trim()))
						{
							jftfQuantidade.setText("");
							jtfPrecoTotal.setText("");
							jtfPrecoUnitario.setText("");
							jtfCodigoProduto.setText("");
							
							jcbProduto.setSelectedItem(String.valueOf(produtosLista.get(p).getNome()).trim());
							jtfPrecoUnitario.setText(String.valueOf(produtosLista.get(p).getPrecoCompra()));
						}
					}
				}
			}
		});
		jtfCodigoProduto.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jtfCodigoProduto.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jtfCodigoProduto.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblNomeProduto = new JLabel("*Produto:");
		jblNomeProduto.setBounds(10, 177, 58, 14);
		jblNomeProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		jcbProduto.setBounds(59, 192, 211, 20);
		jcbProduto.setEnabled(false);
		jcbProduto.setToolTipText("Campo obrigat\u00F3rio");
		jcbProduto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaCombo == true)
				{
					String produto = String.valueOf(jcbProduto.getSelectedItem()).trim();
					
					if(jcbProduto.getSelectedItem().equals(""))
					{
						apagaCampos();//seta "" em textfields e comboBox especificos
					}
					else
					{	//seta o codigo e preco unitario do produto
						for(int i = 0;i < produtosLista.size();i++)
						{
							if(produto.equals(produtosLista.get(i).getNome().trim()))
							{
								jftfQuantidade.setText("");
								jtfPrecoTotal.setText("");
								jtfPrecoUnitario.setText("");
								jtfCodigoProduto.setText("");
								
								jtfCodigoProduto.setText(String.valueOf(produtosLista.get(i).getCodigo()));
								jtfPrecoUnitario.setText(String.valueOf(produtosLista.get(i).getPrecoCompra()));
							}
						}
					}
				}
			}
		});
		
		JLabel jblQuantidade = new JLabel("*Quantidade:");
		jblQuantidade.setBounds(273, 177, 68, 14);
		jblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		jftfQuantidade = new JFormattedTextField(controle.setMascara("********"));//limita o numero de caracteres
		jftfQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		jftfQuantidade.setBounds(273, 192, 68, 20);
		jftfQuantidade.setToolTipText("Campo obrigat\u00F3rio");
		jftfQuantidade.setEditable(false);
		jftfQuantidade.setColumns(10);
		jftfQuantidade.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfQuantidade.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfQuantidade.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		//funcao que deve ser repensada
		/*
		jftfQuantidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	//calcula quantidade de produtos * seu preco unitario e seta no preco total
				int quantidade = Integer.parseInt(jftfQuantidade.getText().trim());
				double valor = Integer.parseInt(jtfPrecoUnitario.getText().trim().substring(3));
				double resultado = quantidade * valor;
				jtfPrecoTotal.setText(String.valueOf(resultado));
			}
		});
		*/
		JLabel jblPrecoUnitario = new JLabel("*Pre\u00E7o Unit\u00E1ro:");
		jblPrecoUnitario.setBounds(344, 177, 77, 14);
		jblPrecoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel jblPrecoTotal = new JLabel("*Pre\u00E7o Total:");
		jblPrecoTotal.setBounds(427, 177, 68, 14);
		
		JLabel jblCpf = new JLabel("CPF:");
		jblCpf.setBounds(358, 130, 37, 14);
		jblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblCodigo = new JLabel("C\u00F3digo:");
		jblCodigo.setBounds(10, 90, 46, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setToolTipText("C\u00F3digo da Nota");
		jtfCodigo.setBounds(10, 106, 46, 20);
		jtfCodigo.setEditable(false);
		jtfCodigo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(10, 345, 560, 126);
		
		jftfDataVenda = new JFormattedTextField(controle.setMascara("##/##/####"));//limita os caracteres
		jftfDataVenda.setToolTipText("Campo obrigat\u00F3rio");
		jftfDataVenda.setHorizontalAlignment(SwingConstants.CENTER);
		jftfDataVenda.setEditable(false);
		jftfDataVenda.setBounds(223, 106, 100, 20);
		jftfDataVenda.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				if(jftfDataVenda.getText().trim().equals("/  /") | jftfDataVenda.getText().trim().length() < 10)
				{
					jftfDataVenda.setValue(null);//limpa os dados do texfield
				}
				jftfDataVenda.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfDataVenda.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		jtfTotalNota = new JNumberField();
		jtfTotalNota.setToolTipText("Campo obrigat\u00F3rio");
		jtfTotalNota.setHorizontalAlignment(SwingConstants.CENTER);
		jtfTotalNota.setEditable(false);
		jtfTotalNota.setBounds(336, 106, 100, 20);
		
		jftfCpf = new JFormattedTextField(controle.setMascara("###.###.###-##"));//limita os caracteres
		jftfCpf.setToolTipText("Insira o CPF do cliente");
		jftfCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jftfCpf.setEditable(false);
		jftfCpf.setBounds(358, 146, 130, 20);
		
		jtfPrecoUnitario = new JNumberField();
		jtfPrecoUnitario.setEditable(false);
		jtfPrecoUnitario.setToolTipText("Campo obrigat\u00F3rio");
		jtfPrecoUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		jtfPrecoUnitario.setBounds(344, 192, 80, 20);

		jtfPrecoTotal = new JNumberField();
		jtfPrecoTotal.setEditable(false);
		jtfPrecoTotal.setToolTipText("Campo obrigat\u00F3rio");
		jtfPrecoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jtfPrecoTotal.setBounds(427, 192, 80, 20);

		jbtPrimeiro.setBounds(20, 309, 37, 29);
		jbtPrimeiro.setEnabled(false);
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
		jbtPrimeiro.setToolTipText("Primeiro");
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
		
		
		jbtAnterior.setBounds(60, 309, 37, 29);
		jbtAnterior.setEnabled(false);
		jbtAnterior.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
		jbtAnterior.setToolTipText("Anterior");
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
		
		jbtProximo.setBounds(100, 309, 37, 29);
		jbtProximo.setEnabled(false);
		jbtProximo.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
		jbtProximo.setToolTipText("Pr\u00F3ximo");
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
		
		jbtUltimo.setBounds(140, 309, 37, 29);
		jbtUltimo.setEnabled(false);
		jbtUltimo.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = notasLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurar.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurar.setToolTipText("Procurar");
		jbtProcurar.setBounds(200, 60, 24, 24);
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou numero da nota
			}
		});
		
		jbtNovoRegistro.setBounds(249, 309, 37, 29);
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30 (1).png")));
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				apagaJtf();//seta "" em textfields e comboBox especificos
				
				atualizaNovo();//desabilita botoes,textfields e muda uma variavel
				
				clearList();//limpa lista de registros
				
				atualizar = 0;//indica que ira criar um novo registro

				DefaultTableModel modelo = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
				modelo.setNumRows(0);//apaga as linhas da tabela de itens
			}
		});
		
		jbtSalvar.setBounds(289, 309, 37, 29);
		jbtSalvar.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.setEnabled(false);
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	//verifica se informacoes do formulario nao estao em branco
					if(jftfNumeroNotaFiscal.getText().trim().equals("") 
							|| jtfCodigoCliente.getText().trim().equals("") 
							|| (jftfDataVenda.getText().trim().length() < 10)
							|| jtfTotalNota.getText().equals("R$ 0.00") 
							|| (jtfTotalNota.getText().trim().length() > 15)
							|| (jcbCliente.getItemCount() == 0) 
							|| (String.valueOf(jcbCliente.getSelectedItem()).trim().equals(""))
					  )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios ou total da nota excede o número de caracteres.","Atenção",WIDTH);
					}
					else
					{
						Long.valueOf(jftfNumeroNotaFiscal.getText().trim());
						
						DefaultTableModel model = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
						
						if(model.getRowCount() > 0)//verifica se existem itens na nota fiscal
						{
							if(notasLista.size() == 0)//se n existe nenhum registo
							{
								salvar();//salva o registro
							}
							else
							{
								for (int p = 0; p < notasLista.size(); p++) //percorre a lista de registros
								{
									//se esta criando um novo registro e o numero da nota do registro e o mesmo
									if (jftfNumeroNotaFiscal.getText().trim().equals(notasLista.get(p).getNumeroNota().trim()))//se o nome ja existe
									{
										//se esta atualizando e o codigo do registro e o mesmo
										if(atualizar == 1 && jtfCodigo.getText().trim().equals(String.valueOf(notasLista.get(p).getCodigo()).trim()))
										{
											salvar();//salva o registro
											break;
										}
										JOptionPane.showMessageDialog(null,"Número de Nota já cadastrado.","Atenção",WIDTH);
										break;
									}
									else if(p == (notasLista.size() - 1))//se n encontrou nenhum numero de nota igual
									{
										salvar();
										break;
									}
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"A tabela de itens não pode ficar vazia","Atenção",WIDTH);
						}
					}
				}
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(null,"Digite apenas números no campo 'Número da Nota Fiscal' ","Atenção",WIDTH);
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao salvar o registro.","Atenção",WIDTH);
				}
			}
		});
		
		jbtAtualizar.setBounds(329, 309, 37, 29);
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//habilita e desabilita botoes e textfields e muda uma variavel 
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		jbtExcluir.setBounds(369, 309, 37, 29);
		jbtExcluir.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
		jbtExcluir.setToolTipText("Excluir");
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
						codigoExcluir = Integer.parseInt(jtfCodigo.getText());//pega o codigo do registro que sera excluido
						
						DefaultTableModel model = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela

						if(model.getRowCount() > 0)//se o numero de linhas da tabela de itens for maior que zero
						{
							for(int i = 0; i < auxiliarTabelaProdutoNota.size();i++)//percorre a lista de produtos da nota
							{
								int codProduto = Integer.parseInt(auxiliarTabelaProdutoNota.get(i).getCodigoProduto());//pega o codigo do produto atrelado a essa nota
								int quantidade = Integer.parseInt(auxiliarTabelaProdutoNota.get(i).getQuantidade());//pega a quantidade de unidades daquele produto atrelados a essa nota
								
								try
								{
									Produto produtoEstoque = controle.consultarProduto(codProduto);//busca o produto no banco
									int atualizaQuantidade = Integer.parseInt(produtoEstoque.getQuantidade().trim());//pega a quantidade de unidades daquele produto no banco
									atualizaQuantidade += quantidade;//diminui a quantidade de unidades do produto
									produtoEstoque.setQuantidade(String.valueOf(atualizaQuantidade));
									
									auxiliarAtualizarProduto.add(produtoEstoque);//add a lista de produtos a serem atualizados no estoque
								}
								catch (NullPointerException error)//produto foi apagado 
								{
									
								}
							}
							
							controle.excluirNotaSaida(codigoExcluir,auxiliarTabelaProdutoNota,auxiliarAtualizarProduto);//exclui a nota seus itens e atualiza a quantidade de produtos
							JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
							auxiliarExcluir();//desabilita ou habilita botoes e textfields dependendo da condicao da exclusao 
						}
						else//se nao existem linhas na tabela de itens
						{
							controle.excluir(8,codigoExcluir);//exclui registro 
							JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
							
							auxiliarExcluir();//desabilita ou habilita botoes e textfields dependendo da condicao da exclusao 
						}
					}
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Não foi possivel excluir o registro do banco de dados.","Atenção",WIDTH);
				}
			}
		});
		
		jbtCancelar.setBounds(409, 309, 37, 29);
		jbtCancelar.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
		jbtCancelar.setToolTipText("Cancelar");
		jbtCancelar.setEnabled(false);
		jbtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(semRegistro.equals("1"))//se nao existem registros
				{
					jbtNovoRegistro.setEnabled(true);
					jbtSalvar.setEnabled(false);
					jbtAtualizar.setEnabled(false);
					jbtExcluir.setEnabled(false);
					jbtCancelar.setEnabled(false);
					jbtImprimir.setEnabled(false);
					jbtProcurar.setEnabled(false);
					jbtAdicionar.setEnabled(false);
					jbtRemover.setEnabled(false);
					
					desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"

					apagaJtf();//seta "" em textfields e comboBox especificos
					
					jtfBlock();//desabilita textfiels especificos
					
					jtfProcurar.setEditable(false);

					jcbBlock();//desabilita comboBox especificos
					
					clearList();//limpa as listas de registros
					
					DefaultTableModel model = (DefaultTableModel) JTable.getModel();//pega o modelo da tabela de registros do form
					model.setNumRows(0);//limpa a tabela de registros do form
					
					DefaultTableModel model2 = (DefaultTableModel) JTableItens.getModel();//pega o modelo da tabela de registros do form
					model2.setNumRows(0);//limpa a tabela de registros do form
					
					atualizar = 3;//deixa disponivel o mouseclicked da tabela de registros do form
					
					quantidadeProdutoAdicionado = 0;//reseta variavel que auxilia botao remover
				}
				else
				{
					iniciaCombo = false;

					salvaCancela();//desabilita e habilita botoes e texfiels e muda variaveis
					
					pegarRegistro();//mostra informacoes do registro na tela
					
					verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
				}
			}
		});
		
		jbtImprimir.setBounds(511, 309, 37, 29);
		jbtImprimir.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelNotaSaida == null)
				{
					formRelNotaSaida = new FormRelatorioNotaSaida();//cria o frame
				}
				formRelNotaSaida.setVisible(true);//mostra o frame
			}
		});
		
		
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
		JTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "N\u00FAmero da Nota", "Data da Venda", "Total da Nota"
			}
		));
		JTable.getColumnModel().getColumn(1).setPreferredWidth(97);
		JTable.getColumnModel().getColumn(2).setPreferredWidth(91);
		scrollPane.setViewportView(JTable);
		
		jbtAdicionar.setEnabled(false);
		jbtAdicionar.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-plus-24.png")));
		jbtAdicionar.setToolTipText("Adicionar Item");
		jbtAdicionar.setBounds(510, 187, 30, 29);
		jbtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//verifica se campos nao estao vazios ou excedem o numero de caracteres
				if(jtfCodigoProduto.getText().trim().equals("") 
						|| jftfQuantidade.getText().trim().equals("") 
						|| jtfPrecoUnitario.getText().equals("R$ 0.00")
						|| (jtfPrecoUnitario.getText().trim().length() > 12)
						|| jtfPrecoTotal.getText().equals("R$ 0.00")
						|| (jtfPrecoTotal.getText().trim().length() > 12)
						|| (jcbProduto.getItemCount() == 0) 
						|| (String.valueOf(jcbProduto.getSelectedItem()).trim().equals(""))
				  )
				{
					JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios ou excedem o número de caracteres.","Atenção",WIDTH);
				}
				else
				{
					try
					{
						Long.parseLong(jftfQuantidade.getText().trim());
						
						String codProduto = jtfCodigoProduto.getText().trim();

						DefaultTableModel model = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
						
						if(model.getRowCount() > 0)//se o numero de linhas da tabela de itens for maior que zero
						{
							
							for(int i = 0;i < model.getRowCount();i++)//percorre as linhas da tabela
							{
								if(String.valueOf(codProduto).equals(String.valueOf(JTableItens.getValueAt(i,1))))
								{
									JOptionPane.showMessageDialog(null,"Adicione produtos diferentes","Atenção",WIDTH);
									break;
								}	
								else if(i == (model.getRowCount() - 1))//se nenhuma dela tinha produtos repetidos
								{
									auxiliaBtAdd();//add linhas a tabela,apaga alguns campos e preenche a lista de itens da nota
									break;
								}
							}
						}
						else//se n existem linhas na tabela
						{
							auxiliaBtAdd();//add linhas a tabela,apaga alguns campos e preenche a lista de itens da nota
						}
					}
					catch (NumberFormatException ex) 
					{
						JOptionPane.showMessageDialog(null,"Digite apenas números no campo 'Quantidade' ","Atenção",WIDTH);
					}
				}
			}
		});
		
		
		jbtRemover.setEnabled(false);
		jbtRemover.setIcon(new ImageIcon(FormCadastroNotaSaidaControle.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-32 (1).png")));
		jbtRemover.setToolTipText("Remover Item");
		jbtRemover.setBounds(541, 187, 30, 29);
		jbtRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				Object[] options = {"Sim","Não"};
				String pergunta = "Tem certeza de que quer remover este item da tabela?";
				int confirmarExcluir = JOptionPane.showOptionDialog(null,pergunta,"Exclusão",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(confirmarExcluir == JOptionPane.YES_NO_OPTION)//inicia caso a resposta seja sim
				{
					DefaultTableModel model = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
					
					if(atualizar == 1)//se a nota esta sendo atualizada
					{
						
						if(quantidadeProdutoAdicionado <= 0)//se produtos nao foram adicionados durante a atualizacao
						{
							int linha = model.getRowCount() -1;//pega o numero da ultima linha da tabela
							
							String codProduto = String.valueOf(model.getValueAt(linha, 1));
							String quant = String.valueOf(model.getValueAt(linha, 3));
							
							int codiProduto = Integer.valueOf(codProduto);
							int quanti = Integer.valueOf(quant);
							
							try
							{
								Produto produtoEstoque = controle.consultarProduto(codiProduto);//busca o produto no banco
								
								int atualizaQuantidade = Integer.parseInt(produtoEstoque.getQuantidade().trim());//pega a quantidade de unidades daquele produto no banco
									
								atualizaQuantidade += quanti;//aumenta a quantidade de unidades do produto
								
								/*
								 *poderia trocar o codigo abaixo por "produtoEstoque.setQuantidade(String.valueOf(atualizaQuantidade));" mas alguma coisa faz com que se eu fizer isso
								 *quando qualquer operacao como salvar/remover/atualizar for feita no banco ela atualize a quantidade de produtos da nota  mesmo que eu nao os mande para ser atualizados
								 * no metodo da classe controle que faz isso no momento que clico para salvar
								 */
								
								//cria um objeto com a quantidade atualizada
								String codigoFornecedor = produtoEstoque.getCodigoFornecedor();
								String dataCadastro = produtoEstoque.getDataCadastro();
								String marca = produtoEstoque.getMarca();
								String nome = produtoEstoque.getNome();
								String nomeFornecedor = produtoEstoque.getNomeFornecedor();
								String obs = produtoEstoque.getObs();
								String precoCompra = produtoEstoque.getPrecoCompra();
								String precoVenda = produtoEstoque.getPrecoVenda();
								String quantidade = String.valueOf(atualizaQuantidade);
								String foto = produtoEstoque.getFoto();
								
								Produto produtoEstoqu = new Produto(codiProduto,codigoFornecedor,dataCadastro,marca,nome,nomeFornecedor,obs,precoCompra,precoVenda,quantidade,foto);
								
								//lista de produtos da tabela produtos
								auxiliarAtualizarProduto.add(produtoEstoqu);//add a lista de produtos a serem atualizados no estoque
								
								//lista de produtos da tabela atrelada a nota fiscal
								excluirTabelaProdutoNota.add(auxiliarTabelaProdutoNota.get(auxiliarTabelaProdutoNota.size() -1));//produtos que serao excluidos da nota caso seja uma atualizacao
								auxiliarTabelaProdutoNota.remove(auxiliarTabelaProdutoNota.get(auxiliarTabelaProdutoNota.size() -1));//remove da lista de produtos que ja estavam na nota
								model.removeRow(model.getRowCount()-1);//remove a ultima linha da tabela de itens
							}
							catch (NullPointerException error) //produto foi removido da tabela de produtos pela interface "Cadastro de Produtos"
							{
								//entao apenas a tabela de itens atrelados a nota fiscal sera atualizada e nao a tabela de produtos que teria a quantidade deste produto modificada
								excluirTabelaProdutoNota.add(auxiliarTabelaProdutoNota.get(auxiliarTabelaProdutoNota.size() -1));//produtos que serao excluidos da nota caso seja uma atualizacao
								auxiliarTabelaProdutoNota.remove(auxiliarTabelaProdutoNota.get(auxiliarTabelaProdutoNota.size() -1));//remove da lista de produtos que ja estavam na nota
								model.removeRow(model.getRowCount()-1);//remove a ultima linha da tabela de itens
							}
						}
						else if (quantidadeProdutoAdicionado >= 1)//se produtos foram adicionados durante a atualizacao
						{
							auxiliarAtualizarProduto.remove(auxiliarAtualizarProduto.size() -1);//remove o produto da lista onde seria atualizado
							quantidadeProdutoAdicionado --;
						}
					}
					else if(atualizar == 0 || atualizar == 3)//se for uma nota nova que esta sendo criada
					{
						auxiliarAtualizarProduto.remove(auxiliarAtualizarProduto.size() -1);//remove o produto da lista onde seria atualizado
					}
				
					if(salvarTabelaProdutoNota.size() > 0)//se existem produtos a serem salvos na nota
					{
						salvarTabelaProdutoNota.remove(salvarTabelaProdutoNota.size() -1);//produtos que serao salvos na nota
						
						
						//int linha = model.getRowCount() -1;//pega o numero da ultima linha da tabela
						
						//funcao que deve ser repensada
						/*
						//diminui valor da nota
						double valorNota = Integer.parseInt(jtfTotalNota.getText().trim().substring(3));
						double valorProduto = Integer.parseInt(String.valueOf(model.getValueAt(linha, 5)));
						double resultado = valorNota - valorProduto;
						jtfTotalNota.setText(String.valueOf(resultado));
						*/
						
						model.removeRow(model.getRowCount()-1);//remove a ultima linha da tabela de itens
					}
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
		contentPane.add(jtfProcurar);
		contentPane.add(jblProcurar);
		contentPane.add(jblCodigo);
		contentPane.add(jblNumeroNota);
		contentPane.add(jblDataVenda);
		contentPane.add(jblTotalNota);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfNumeroNotaFiscal);
		contentPane.add(jblCliente);
		contentPane.add(jblCpf);
		contentPane.add(jtfCodigoCliente);
		contentPane.add(jcbCliente);
		contentPane.add(jtfCodigoProduto);
		contentPane.add(jblNomeProduto);
		contentPane.add(jcbProduto);
		contentPane.add(jblQuantidade);
		contentPane.add(jftfQuantidade);
		contentPane.add(jblPrecoUnitario);
		contentPane.add(jblPrecoTotal);
		contentPane.add(scrollPaneItens);
		contentPane.add(jbtPrimeiro);
		contentPane.add(jbtAnterior);
		contentPane.add(jbtProximo);
		contentPane.add(jbtUltimo);
		contentPane.add(jbtNovoRegistro);
		contentPane.add(jbtSalvar);
		contentPane.add(jbtAtualizar);
		contentPane.add(jbtExcluir);
		contentPane.add(jbtCancelar);
		contentPane.add(jbtImprimir);
		contentPane.add(scrollPane);
		contentPane.add(jbtAdicionar);
		contentPane.add(jbtRemover);
		contentPane.add(jftfDataVenda);
		contentPane.add(jtfTotalNota);
		contentPane.add(jftfCpf);
		contentPane.add(jtfPrecoUnitario);
		contentPane.add(jtfPrecoTotal);
		contentPane.add(jbtProcurar);
		
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

	public void pegarRegistro()//coloca as informações na tela
	{
		try
		{
			quantidadeProdutoAdicionado = 0;//reseta variavel que auxilia botao remover
			
			apagaJtf();//seta "" em textfields e comboBox especificos
			
			clearList();//limpa as listas de registros
			
			notasLista = controle.consultarTodosNotaSaidaControle("codigo");//preenche a lista com todos os registros de notas
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(notasLista.get(registroAtual).getCodigo()));
			jftfNumeroNotaFiscal.setText(String.valueOf(notasLista.get(registroAtual).getNumeroNota()));
			jftfDataVenda.setText(String.valueOf(notasLista.get(registroAtual).getDataVenda()));
			jtfCodigoCliente.setText(String.valueOf(notasLista.get(registroAtual).getCodigoCliente()));
			jcbCliente.setSelectedItem(String.valueOf(notasLista.get(registroAtual).getNomeCliente()).trim());
			jftfCpf.setText(String.valueOf(notasLista.get(registroAtual).getCpf()));
			jtfTotalNota.setText(String.valueOf(notasLista.get(registroAtual).getTotalNota()));
			
			DefaultTableModel modelo = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
			modelo.setNumRows(0);//apaga as linhas da tabela de itens
			
			produtosNotaLista = controle.consultarTodosProdutoNotaSaida();//preenche a lista com todos os registros de produtos da nota
			
			for (int i = 0; i < produtosNotaLista.size(); i++)
			{
				//se o numero da nota no produto e o msm numero da nota que estamos utilizando
				if(notasLista.get(registroAtual).getNumeroNota().trim().equals(String.valueOf(produtosNotaLista.get(i).getCodigoNotaSaida()).trim()) )
				{
					auxiliarTabelaProdutoNota.add(produtosNotaLista.get(i));//lista de produtos da nota que ira ser alterada de acordo com as operacoes feitas na nota
					
					//Adiciona as linhas na tabela de itens
					modelo.addRow(new Object [] {produtosNotaLista.get(i).getCodigoNotaSaida(),
												 produtosNotaLista.get(i).getCodigoProduto(),
							 					 produtosNotaLista.get(i).getNomeProduto(),
												 produtosNotaLista.get(i).getQuantidade(),
												 produtosNotaLista.get(i).getPrecoUnitario(),
												 produtosNotaLista.get(i).getPrecoTotal()});
				}
			}
			
			verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Não existem registros.","Atenção",WIDTH);
			
			apagaJtf();//seta "" em textfields e comboBox especificos

			jtfProcurar.setEditable(false);

			jcbBlock();//seta "" em comboBox especificos

			jbtSalvar.setEnabled(false);
			jbtAtualizar.setEnabled(false);
			jbtExcluir.setEnabled(false);
			jbtCancelar.setEnabled(false);
			jbtImprimir.setEnabled(false);
			jbtProcurar.setEnabled(false);
			jbtAdicionar.setEnabled(false);
			jbtRemover.setEnabled(false);
			
			desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
			
			DefaultTableModel modelo = (DefaultTableModel) JTable.getModel();//Cria um objeto que pega o modelo da tabela
			modelo.setNumRows(0);//apaga as linhas da tabela de registros
			
			DefaultTableModel modelo2 = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
			modelo2.setNumRows(0);//apaga as linhas da tabela de itens
			
			semRegistro = "1";//indica que n existem registros
			
			quantidadeProdutoAdicionado = 0;//reseta variavel que auxilia botao remover
			
			clearList();//limpa as listas de registros
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
		if(registroAtual == notasLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < notasLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {notasLista.get(i).getCodigo(),
										notasLista.get(i).getNumeroNota(),
										notasLista.get(i).getDataVenda(),
										notasLista.get(i).getTotalNota()});
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
			jcbProduto.removeAllItems();
			produtosLista = controle.consultarTodosProduto("codigo");//consulta e preenche a lista com todos os registros de produtos
			jcbProduto.addItem("");
			for(int i = 0;i < produtosLista.size();i++)
			{
				jcbProduto.addItem(produtosLista.get(i).getNome().trim());//preenche o cb de produtos
			}
			
			jcbCliente.removeAllItems();
			clienteLista = controle.consultarTodosCliente("codigo");//consulta e preenche a lista com todos os registros de fornecedores
			jcbCliente.addItem("");
			for(int i = 0;i < clienteLista.size();i++)
			{
				jcbCliente.addItem(clienteLista.get(i).getNome().trim());//preenche o cb de fornecedores
			}
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar lista de produtos e/ou clientes","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou numero de nota que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//verifica se e um numero
				
			for(int i = 0;i < notasLista.size();i++)//percorre a lista de notas
			{
				if(digitado.equals(String.valueOf(notasLista.get(i).getCodigo())))//tenta encontrar a nota por codigo
				{
					registroAtual = i;
					linha = i;
					pegarRegistro();
					selecionaLinha();
					break;
				}
				else if(digitado.equals(String.valueOf(notasLista.get(i).getNumeroNota())))//tenta encontrar a nota por numero de nota
				{
					registroAtual = i;
					linha = i;
					pegarRegistro();
					selecionaLinha();
					break;
				}
				else if(i == (notasLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código ou Número de nota não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex) 
		{
			JOptionPane.showMessageDialog(null,"Digite apenas números.","Atenção",WIDTH);
		}
	}
	
	public void salvar()
	{
		//pega informacoes do formulario
		String numeroNota = jftfNumeroNotaFiscal.getText().trim();
		String dataVenda = jftfDataVenda.getText();
		String totalNota = jtfTotalNota.getText();
		String codigoCliente = jtfCodigoCliente.getText();
		String nomeCliente = String.valueOf(jcbCliente.getSelectedItem());
		String cpf = jftfCpf.getText();
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			NotaSaidaControle notaSaida = new NotaSaidaControle(cpf, 
					codigoCliente, dataVenda, nomeCliente, numeroNota, totalNota);
			
			for(int i = 0; i < salvarTabelaProdutoNota.size();i++)//seta o numero da nota fiscal nos produtos
			{
				salvarTabelaProdutoNota.get(i).setCodigoNotaSaida(numeroNota);
			}
			
			controle.salvarNotaSaida(notaSaida, salvarTabelaProdutoNota,auxiliarAtualizarProduto);//salva o novo registro
			
			JOptionPane.showMessageDialog(null,"Registro salvo com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText().trim());//pega o codigo
			NotaSaidaControle notaEntrada = new NotaSaidaControle(codigo,cpf, 
					codigoCliente, dataVenda, nomeCliente, numeroNota, totalNota);

			for(int i = 0; i < salvarTabelaProdutoNota.size();i++)
			{
				salvarTabelaProdutoNota.get(i).setCodigoNotaSaida(numeroNota);
			}
			
			for(int i = 0; i < auxiliarTabelaProdutoNota.size();i++)
			{
				auxiliarTabelaProdutoNota.get(i).setCodigoNotaSaida(numeroNota);
			}

			//atualiza a o registro
			controle.atualizarNotaSaida(notaEntrada,salvarTabelaProdutoNota,excluirTabelaProdutoNota,auxiliarAtualizarProduto);

			JOptionPane.showMessageDialog(null,"Registro alterado com sucesso.","Notificação",WIDTH);

		}
		
		salvaCancela();//habilita e desabilita botoes e Textfields e muda variaveis
		pegarRegistro();//mostra as informacoes do registro na tela
		pegarRegistroTabela();//mostra informacoes dos registros na tabela de registros do form
		selecionaLinha();//seleciona linha correspondente ao registro na tabela de registros do form
		
		semRegistro = "2";//indica que existem registros cadastrados
	}
	
	public void auxiliarExcluir()
	{
		if(notasLista.size() == 1)//se era o unico registro da lista
		{
			pegarRegistro();//desabilita botoes e textfields
		}
		else
		{
			for (int a = 0; a < notasLista.size(); a++) 
			{
				if(a == (notasLista.size() - 1) & codigoExcluir == notasLista.get(a).getCodigo())//se o excluido era o ultimo registro
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

	public void apagaJtf()//seta "" em Texfields e ComboBox especificos
	{
		jtfCodigo.requestFocus();
		
		jtfCodigo.setText("");
		jftfNumeroNotaFiscal.setText("");
		jftfDataVenda.setValue(null);//limpa os dados do texfield
		jtfTotalNota.setText("");
		jftfCpf.setValue(null);//limpa os dados do texfield
		jtfCodigoCliente.setText("");
		jcbCliente.setSelectedItem("");
		
		apagaCampos();//seta "" em Texfields e ComboBox especificos
	}
	
	public void apagaCampos()//seta "" em Texfields e ComboBox especificos
	{
		jftfQuantidade.setText("");
		jtfPrecoTotal.setText("");
		jtfPrecoUnitario.setText("");
		jtfCodigoProduto.setText("");
		jcbProduto.setSelectedItem("");
	}
	
	public void clearList()//limpa as listas de registros
	{
		auxiliarTabelaProdutoNota.clear();
		auxiliarAtualizarProduto.clear();
		
		excluirTabelaProdutoNota.clear();
		salvarTabelaProdutoNota.clear();
	}
	
	public void desabilitaSeta()//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
	{
		jbtPrimeiro.setEnabled(false);
		jbtAnterior.setEnabled(false);
		jbtProximo.setEnabled(false);
		jbtUltimo.setEnabled(false);
	}
	
	public void jtfBlock()//desabilita textfields especificos
	{
		jftfNumeroNotaFiscal.setEditable(false);
		jftfDataVenda.setEditable(false);
		jtfTotalNota.setEditable(false);
		jtfCodigoCliente.setEditable(false);
		jftfCpf.setEditable(false);
		jtfCodigoProduto.setEditable(false);
		jftfQuantidade.setEditable(false);
		jtfPrecoUnitario.setEditable(false);
		jtfPrecoTotal.setEditable(false);
	}
	
	public void jcbBlock()//desabilita comboBox especificos
	{
		jcbCliente.setEnabled(false);
		jcbProduto.setEnabled(false);
	}
	
	public void salvaCancela()//funcao compartilhada por salvar e cancelar
	{
		jbtNovoRegistro.setEnabled(true);
		jbtAtualizar.setEnabled(true);
		jbtExcluir.setEnabled(true);
		jbtImprimir.setEnabled(true);
		jbtProcurar.setEnabled(true);
		jbtSalvar.setEnabled(false);
		jbtCancelar.setEnabled(false);
		jbtAdicionar.setEnabled(false);
		jbtRemover.setEnabled(false);
				
		jtfCodigo.requestFocus();
		
		jtfBlock();//desabilita texfields especifico
		
		jtfProcurar.setEditable(true);

		jcbBlock();//desabilita comboBox especificos
		
		atualizar = 3;//deixa habilitado o mouseclicked da tabela
	}
	
	public void atualizaNovo()//funcao compartilhada por atualizar e novo
	{
		jbtSalvar.setEnabled(true);
		jbtCancelar.setEnabled(true);
		jbtAdicionar.setEnabled(true);
		jbtRemover.setEnabled(true);
		jbtNovoRegistro.setEnabled(false);
		jbtAtualizar.setEnabled(false);
		jbtExcluir.setEnabled(false);
		jbtImprimir.setEnabled(false);
		jbtProcurar.setEnabled(false);
		
		desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
		
		jtfCodigo.requestFocus();
		
		jftfNumeroNotaFiscal.setEditable(true);
		jftfDataVenda.setEditable(true);
		jtfTotalNota.setEditable(true);
		jftfQuantidade.setEditable(true);
		jtfCodigoProduto.setEditable(true);
		jtfCodigoCliente.setEditable(true);
		jtfPrecoTotal.setEditable(true);
		jtfPrecoUnitario.setEditable(true);
		jtfProcurar.setEditable(false);

		jcbCliente.setEnabled(true);
		jcbProduto.setEnabled(true);
		
		iniciaCombo = true;//permite que funcione o actionPerformed de jcbProduto e jcbFornecedor
	}
	
	public void auxiliaBtAdd()
	{
		//pega informacoes do formulario
		String codProduto = jtfCodigoProduto.getText().trim();
		String prod = String.valueOf(jcbProduto.getSelectedItem());
		String quant = jftfQuantidade.getText().trim();
		String precUnitario = jtfPrecoUnitario.getText();
		String precTotal = jtfPrecoTotal.getText();

		DefaultTableModel model = (DefaultTableModel) JTableItens.getModel();//Cria um objeto que pega o modelo da tabela
		
		//verifica se existem produtos suficientes em estoque para serem vendidos
		int codiProduto = Integer.valueOf(codProduto);
		Produto produtoEstoque = controle.consultarProduto(codiProduto);
		int atualizaQuantidade = Integer.parseInt(produtoEstoque.getQuantidade().trim());
		
		if(atualizaQuantidade < Integer.parseInt(quant))
		{
			JOptionPane.showMessageDialog(null,"Não existem tantos produtos em estoque","Atenção",WIDTH);
		}
		else
		{
			ProdutoNotaSaida produto = new ProdutoNotaSaida(codProduto,prod,precTotal,precUnitario,quant);
			salvarTabelaProdutoNota.add(produto);//adiciona o produto a lista que sera assoaciada a nota fiscal
			
			atualizaQuantidade -= Integer.parseInt(quant);
			
			/*
			 *poderia trocar o codigo abaixo por "produtoEstoque.setQuantidade(String.valueOf(atualizaQuantidade));" mas alguma coisa faz com que se eu fizer isso
			 *quando qualquer operacao como salvar/remover/atualizar for feita no banco ela atualize a quantidade de produtos da nota  mesmo que eu nao os mande para ser atualizados
			 * no metodo da classe controle que faz isso no momento que clico para salvar
			 */
			
			//cria um objeto com a quantidade atualizada
			String codigoFornecedor = produtoEstoque.getCodigoFornecedor();
			String dataCadastro = produtoEstoque.getDataCadastro();
			String marca = produtoEstoque.getMarca();
			String nome = produtoEstoque.getNome();
			String nomeFornecedor = produtoEstoque.getNomeFornecedor();
			String obs = produtoEstoque.getObs();
			String precoCompra = produtoEstoque.getPrecoCompra();
			String precoVenda = produtoEstoque.getPrecoVenda();
			String quantidade = String.valueOf(atualizaQuantidade);
			String foto = produtoEstoque.getFoto();
			
			Produto produtoEstoqu = new Produto(codiProduto,codigoFornecedor,dataCadastro,marca,nome,nomeFornecedor,obs,precoCompra,precoVenda,quantidade,foto);
			
			auxiliarAtualizarProduto.add(produtoEstoqu);//adiciona o produto com a quantidade atualizada a lista de itens que serao atualizados no estoque
			
			quantidadeProdutoAdicionado ++;
			
			//funcao que deve ser repensada
			/*
			//aumenta valor da nota
			double valorNota = Integer.parseInt(jtfTotalNota.getText().trim().substring(3));
			double valorProduto = Integer.parseInt(jtfPrecoTotal.getText().trim().substring(3));
			double resultado = valorNota + valorProduto;
			jtfTotalNota.setText(String.valueOf(resultado));
			*/

			model.addRow(new Object [] {0,codProduto,prod,quant,precUnitario,precTotal});//adiciona a linha na tabela de itens
		
			apagaCampos();//seta "" em Texfields e ComboBox especificos
		}
	}
	
}
