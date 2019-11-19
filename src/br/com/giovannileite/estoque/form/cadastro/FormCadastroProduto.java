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
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import JNumberField.JNumberField;
import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioProduto;
import br.com.giovannileite.estoque.modelo.Fornecedor;
import br.com.giovannileite.estoque.modelo.Produto;
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
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco
	
	private String infoFoto = "";//variavel que guarda informacao sobre imagem
	private String infoFotoNova = "";//variavel que guarda informacao sobre imagem
	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private boolean iniciaComboFornecedor = false;
	
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	
	private List<Produto> produtosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Fornecedor> fornecedoresLista;//Lista contendo as informacoes que serão mostradas na tela

	private FormRelatorioProduto formRelProduto;//form relatorio
	
	private JPanel contentPane;
	
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
			
			
	//JComboBox
	private JComboBox jcbFornecedor = new JComboBox();
	
	//Label foto
	private JLabel jblFoto = new JLabel("");
	
	//jtf
	private JTextField jtfProcurar;
	private JTextField jtfCodigo;
	private JTextField jftfNome;
	private JTextField jftfMarca;
	private JTextField jftfQuantidade;
	private JTextField jftfObs;
	private JTable JTable;
	private JTextField jtfCodigoFornecedor;
	private JTextField jtfPrecoCompra;
	private JTextField jtfPrecoVenda;
	private JFormattedTextField jftfDataCadastro;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroProduto frame = new FormCadastroProduto();
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
	public FormCadastroProduto() {
		
	}
	
	public FormCadastroProduto(Usuario usuario) {
		setTitle("Manuten\u00E7\u00E3o do cadastro de produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		this.setLocationRelativeTo(null);
		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de produtos");
		jblTitulo.setBounds(153, 5, 380, 25);
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
		
		jftfNome = new JFormattedTextField(controle.setMascara("***************************"));//limita o numero de caracteres
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
		
		JLabel jblNome = new JLabel("*Nome do Produto:");
		jblNome.setBounds(109, 85, 110, 14);
		jblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblMarca = new JLabel("Marca:");
		jblMarca.setBounds(325, 85, 43, 14);
		jblMarca.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfMarca = new JFormattedTextField(controle.setMascara("************"));//limita o numero de caracteres
		jftfMarca.setBounds(321, 103, 92, 20);
		jftfMarca.setToolTipText("Insira a marca");
		jftfMarca.setHorizontalAlignment(SwingConstants.CENTER);
		jftfMarca.setEditable(false);
		jftfMarca.setColumns(10);
		jftfMarca.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfMarca.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfMarca.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblFornecedor = new JLabel("*Fornecedor:");
		jblFornecedor.setBounds(392, 127, 83, 14);
		jblFornecedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblQuantidade = new JLabel("*Quantidade:");
		jblQuantidade.setBounds(35, 127, 76, 14);
		jblQuantidade.setForeground(SystemColor.menuText);
		jblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfQuantidade = new JFormattedTextField(controle.setMascara("*********"));//limita o numero de caracteres
		jftfQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		jftfQuantidade.setBounds(35, 145, 73, 20);
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
		
		JLabel jblPrecoCompra = new JLabel("*Pre\u00E7o de Compra:");
		jblPrecoCompra.setToolTipText("");
		jblPrecoCompra.setBounds(115, 127, 110, 14);
		jblPrecoCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblPrecoVenda = new JLabel("*Pre\u00E7o de Venda:");
		jblPrecoVenda.setBounds(223, 127, 103, 14);
		jblPrecoVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblObs = new JLabel("Observa\u00E7\u00E3o:");
		jblObs.setBounds(35, 166, 70, 14);
		jblObs.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfObs = new JFormattedTextField(controle.setMascara("********************************************************************"));//limita o numero de caracteres
		jftfObs.setBounds(35, 180, 497, 20);
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
		
		JLabel jblDataCadastro = new JLabel("*Data de Cadastro:");
		jblDataCadastro.setBounds(420, 85, 120, 14);
		jblDataCadastro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jftfDataCadastro = new JFormattedTextField(controle.setMascara("##/##/####"));//limita os caracteres
		jftfDataCadastro.setToolTipText("Insira a data de cadastro");
		jftfDataCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		jftfDataCadastro.setEditable(false);
		jftfDataCadastro.setBounds(420, 103, 113, 20);
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
		
		jtfPrecoCompra = new JNumberField();
		jtfPrecoCompra.setToolTipText("Campo obrigat\u00F3rio");
		jtfPrecoCompra.setHorizontalAlignment(SwingConstants.CENTER);
		jtfPrecoCompra.setEditable(false);
		jtfPrecoCompra.setBounds(117, 145, 95, 20);
		
		jtfPrecoVenda = new JNumberField();
		jtfPrecoVenda.setToolTipText("Campo obrigat\u00F3rio");
		jtfPrecoVenda.setHorizontalAlignment(SwingConstants.CENTER);
		jtfPrecoVenda.setEditable(false);
		jtfPrecoVenda.setBounds(225, 145, 95, 20);
		
		jbtProcurar.setEnabled(false);
		jbtProcurar.setBounds(542, 192, 92, 23);
		jbtProcurar.setToolTipText("Procurar foto");
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setaImagem(1);//procura e seta foto
			}
		});
		
		
		jbtRemover.setEnabled(false);
		jbtRemover.setBounds(542, 217, 92, 23);
		jbtRemover.setToolTipText("Remover Foto");
		jbtRemover.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setaImagem(2);//remove foto
			}
		});
		
		jbtPrimeiro.setBounds(35, 211, 37, 29);
		jbtPrimeiro.setToolTipText("Primeiro");
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
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
		
		jbtAnterior.setBounds(74, 211, 37, 29);
		jbtAnterior.setToolTipText("Anterior");
		jbtAnterior.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
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
		
		jbtProximo.setBounds(113, 211, 37, 29);
		jbtProximo.setToolTipText("Pr\u00F3ximo");
		jbtProximo.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
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
		
		jbtUltimo.setBounds(153, 211, 37, 29);
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setEnabled(false);
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = produtosLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurarRegistro.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurarRegistro.setToolTipText("Procurar");
		jbtProcurarRegistro.setBounds(289, 52, 24, 24);
		jbtProcurarRegistro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtNovoRegistro.setBounds(207, 211, 37, 29);
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30 (1).png")));
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				apagaJtf();//seta "" em textfields e comboBox especificos
				
				atualizaNovo();//desabilita botoes,textfields e muda uma variavel 
				
				atualizar = 0;//indica que ira criar um novo registro
				
				setaImagem(2);//remove foto
			}
		});
		
		jbtSalvar.setEnabled(false);
		jbtSalvar.setBounds(247, 211, 37, 29);
		jbtSalvar.setToolTipText("Gravar");
		jbtSalvar.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	//verifica se informacoes do formulario nao estao em branco
					if(jftfNome.getText().trim().equals("") 
							|| (jftfDataCadastro.getText().trim().length() < 10)
							|| jftfQuantidade.getText().trim().equals("") 
							|| jtfCodigoFornecedor.getText().trim().equals("") 
							|| (jcbFornecedor.getItemCount() == 0) 
							|| (String.valueOf(jcbFornecedor.getSelectedItem()).trim().equals("")
							|| jtfPrecoCompra.getText().length() > 11
							|| jtfPrecoVenda.getText().length() > 11
							|| jtfPrecoCompra.getText().trim().equals("R$ 0.00")
							|| jtfPrecoVenda.getText().trim().equals("R$ 0.00"))
					  )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios ou ultrapassam o número máximo de caracteres.","Atenção",WIDTH);
					}
					else
					{
						Long.valueOf(jftfQuantidade.getText().trim());//verifica se foi digitado um numero
						
						if(produtosLista.size() == 0)//se n existe nenhum registo
						{
							salvar();//salva o registro
						}
						else
						{
							for (int p = 0; p < produtosLista.size(); p++) //percorre a lista de paises
							{
								if (jftfNome.getText().trim().equals(produtosLista.get(p).getNome().trim()))//se o nome ja existe
								{
									//se esta atualizando e o codigo do registro e o mesmo
									if(atualizar == 1 && jtfCodigo.getText().trim().equals(String.valueOf(produtosLista.get(p).getCodigo()).trim()))
									{
										salvar();//salva o registro
										break;
									}
									JOptionPane.showMessageDialog(null,"O Nome já existe.","Atenção",WIDTH);
									break;
								}
								else if(p == (produtosLista.size() - 1))//se n encontrou nenhum nome igual
								{
									salvar();//salva o registro
									break;
								}
							}
						}
					}
				}
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(null,"Digite apenas números no campo 'Quantidade' ","Atenção",WIDTH);
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao salvar/atualizar o registro.","Atenção",WIDTH);
				}
			}
		});
		
		jbtAtualizar.setBounds(287, 211, 37, 29);
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//habilita e desabilita botoes e Textfields e muda uma variavel 
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		jbtExcluir.setBounds(327, 211, 37, 29);
		jbtExcluir.setToolTipText("Excluir");
		jbtExcluir.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
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
						controle.excluir(10,codigo);//exclui registro
						JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
						
						if(produtosLista.size() == 1)//se era o unico registro da lista
						{
							pegarRegistro();//desabilita botoes e textfields
						}
						else
						{
							for (int a = 0; a < produtosLista.size(); a++) 
							{
								if(a == (produtosLista.size() - 1) & codigo == produtosLista.get(a).getCodigo())//se o excluido era o ultimo registro
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
		jbtCancelar.setBounds(367, 211, 37, 29);
		jbtCancelar.setToolTipText("Cancelar");
		jbtCancelar.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
		jbtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if(semRegistro.equals("1"))
				{
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

					jtfProcurar.setEditable(false);
					
					jtfBlock();//desabilita textfiels especificos
					
					setaImagem(2);//remove foto
					
					DefaultTableModel model = (DefaultTableModel) JTable.getModel();//pega o modelo da tabela de registros do form
					model.setNumRows(0);//limpa a tabela de registros do form
					
					atualizar = 3;//deixa disponivel o mouseclicked da tabela de registros do form
				}
				else
				{
					salvaCancela();//desabilita e habilita botoes e texfiels e muda variaveis
					
					pegarRegistro();//mostra informacoes do registro na tela
					
					verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(35, 245, 603, 141);
		
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
				"C\u00F3digo", "Nome", "Pre\u00E7o de Compra", "Pre\u00E7o de Venda"
			}
		));
		JTable.getColumnModel().getColumn(1).setPreferredWidth(145);
		JTable.getColumnModel().getColumn(2).setPreferredWidth(93);
		JTable.getColumnModel().getColumn(3).setPreferredWidth(87);
		scrollPane.setViewportView(JTable);
		
		jbtImprimir.setIcon(new ImageIcon(FormCadastroProduto.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.setBounds(433, 211, 37, 29);
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelProduto == null)
				{
					formRelProduto = new FormRelatorioProduto();//cria o frame
				}
				formRelProduto.setVisible(true);//mostra o frame
			}
		});
		
		JLabel jblFotoTitulo = new JLabel("Foto:");
		jblFotoTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblFotoTitulo.setBounds(542, 57, 37, 14);
		jblFoto.setToolTipText("Foto");
		
		jblFoto.setBounds(541, 72, 92, 115);
		
		jcbFornecedor.setToolTipText("Campo obrigat\u00F3rio");
		jcbFornecedor.setEnabled(false);
		jcbFornecedor.setBounds(392, 145, 140, 20);
		jcbFornecedor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaComboFornecedor == true)//se esta permitido iniciar
				{
					if(jcbFornecedor.getSelectedItem().equals(""))
					{
						jtfCodigoFornecedor.setText("");
					}
					else
					{	
						//seta o codigo do fornecedor no textfield utilizando o comboBox
						for (int p = 0; p < fornecedoresLista.size(); p++) 
						{
							if (String.valueOf(jcbFornecedor.getSelectedItem()).trim().equals(fornecedoresLista.get(p).getNome().trim()))
							{
								jtfCodigoFornecedor.setText(String.valueOf(fornecedoresLista.get(p).getCodigo()));
							}
						}
					}
				}
			}
		});
		
		JLabel jblCodigoFornecedor = new JLabel("Cod. Forn:");
		jblCodigoFornecedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCodigoFornecedor.setBounds(330, 127, 60, 14);
		
		jtfCodigoFornecedor = new JFormattedTextField(controle.setMascara("******"));//limita o numero de caracteres
		jtfCodigoFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigoFornecedor.setToolTipText("Insira o c\u00F3digo do fornecedor");
		jtfCodigoFornecedor.setEditable(false);
		jtfCodigoFornecedor.setBounds(327, 145, 60, 20);
		jtfCodigoFornecedor.setColumns(10);
		jtfCodigoFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(jtfCodigoFornecedor.getText().trim().equals(""))
				{
					jcbFornecedor.setSelectedItem("");
				}
				else
				{	
					//seta o nome do fornecedor no comboBox usando o codigo do textfield
					for (int p = 0; p < fornecedoresLista.size(); p++) 
					{
						if (jtfCodigoFornecedor.getText().trim().equals(String.valueOf(fornecedoresLista.get(p).getCodigo()).trim()))
						{
							jcbFornecedor.setSelectedItem(String.valueOf(fornecedoresLista.get(p).getNome()).trim());
						}
					}
				}
			}
		});
		jtfCodigoFornecedor.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jtfCodigoFornecedor.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jtfCodigoFornecedor.setCaretPosition(0);//inicia na primeira posicao
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
		contentPane.add(jblMarca);
		contentPane.add(jblFornecedor);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfNome);
		contentPane.add(jftfMarca);
		contentPane.add(jblQuantidade);
		contentPane.add(jblPrecoCompra);
		contentPane.add(jblPrecoVenda);
		contentPane.add(jftfQuantidade);
		contentPane.add(jblDataCadastro);
		contentPane.add(jblObs);
		contentPane.add(jftfObs);
		contentPane.add(jbtProcurar);
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
		contentPane.add(jblFotoTitulo);
		contentPane.add(jblFoto);
		contentPane.add(jcbFornecedor);
		contentPane.add(jblCodigoFornecedor);
		contentPane.add(jtfCodigoFornecedor);
		contentPane.add(jftfDataCadastro);
		contentPane.add(jtfPrecoCompra);
		contentPane.add(jtfPrecoVenda);
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
			
			produtosLista = controle.consultarTodosProduto("codigo");//preenche a lista com todos os registros
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(produtosLista.get(registroAtual).getCodigo()));
			jftfNome.setText(String.valueOf(produtosLista.get(registroAtual).getNome()));
			jftfMarca.setText(String.valueOf(produtosLista.get(registroAtual).getMarca()));
			jftfDataCadastro.setText(String.valueOf(produtosLista.get(registroAtual).getDataCadastro()));
			jftfQuantidade.setText(String.valueOf(produtosLista.get(registroAtual).getQuantidade().trim()));
			jtfPrecoCompra.setText(String.valueOf(produtosLista.get(registroAtual).getPrecoCompra()));
			jtfPrecoVenda.setText(String.valueOf(produtosLista.get(registroAtual).getPrecoVenda()));
			jtfCodigoFornecedor.setText(String.valueOf(produtosLista.get(registroAtual).getCodigoFornecedor()));
			jcbFornecedor.setSelectedItem(String.valueOf(produtosLista.get(registroAtual).getNomeFornecedor()).trim());
			jftfObs.setText(String.valueOf(produtosLista.get(registroAtual).getObs()));
			infoFoto = produtosLista.get(registroAtual).getFoto();

			setaImagem(3);//seta imagem do registro
			
			verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Não existem registros.","Atenção",WIDTH);
			
			apagaJtf();//seta "" em textfields e comboBox especificos
			
			jtfProcurar.setEditable(false);
			
			jcbFornecedor.setEnabled(false);

			setaImagem(2);//remove imagem
			
			jbtSalvar.setEnabled(false);
			jbtAtualizar.setEnabled(false);
			jbtExcluir.setEnabled(false);
			jbtCancelar.setEnabled(false);
			jbtImprimir.setEnabled(false);
			jbtProcurarRegistro.setEnabled(false);
			jbtProcurar.setEnabled(false);
			jbtRemover.setEnabled(false);
			
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
		if(registroAtual == produtosLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < produtosLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {produtosLista.get(i).getCodigo(),
										produtosLista.get(i).getNome(),
										produtosLista.get(i).getPrecoCompra(),
										produtosLista.get(i).getPrecoVenda()});
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
			jcbFornecedor.removeAllItems();
			jcbFornecedor.addItem("");
			fornecedoresLista = controle.consultarTodosFornecedor("codigo");//consulta e preenche a lista com todos os registros de fornecedores
			for(int i = 0;i < fornecedoresLista.size();i++)
			{
				jcbFornecedor.addItem(fornecedoresLista.get(i).getNome().trim());//preenche o cb de fornecedores
			}
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar lista de fornecedores","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < produtosLista.size();i++)//percorre toda lista
			{
				if(digitado.equals(String.valueOf(produtosLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (produtosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < produtosLista.size();i++)//percorre toda lista
			{
				int tamanhoNome = String.valueOf(produtosLista.get(i).getNome()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(produtosLista.get(i).getNome()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(produtosLista.get(i).getNome()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (produtosLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (produtosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
				}
			}
		}
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
		jftfMarca.setText("");
		jftfDataCadastro.setValue(null);//limpa os dados do texfield
		jftfQuantidade.setText("");
		jtfPrecoCompra.setText("");
		jtfPrecoVenda.setText("");;
		jtfCodigoFornecedor.setText("");
		jftfObs.setText("");
		
		jcbFornecedor.setSelectedItem("");
	}
	
	public void salvaCancela()//funcao compartilhada por salvar e cancelar
	{
		iniciaComboFornecedor = false;//actionPerformed do jcbFornecedor n deve funcionar

		jbtNovoRegistro.setEnabled(true);
		jbtAtualizar.setEnabled(true);
		jbtExcluir.setEnabled(true);
		jbtImprimir.setEnabled(true);
		jbtProcurarRegistro.setEnabled(true);
		jbtSalvar.setEnabled(false);
		jbtCancelar.setEnabled(false);
		jbtProcurar.setEnabled(false);
		jbtRemover.setEnabled(false);
		
		jtfProcurar.setEditable(true);

		jtfCodigo.requestFocus();
		
		jtfBlock();//desabilita texfields especificos
		
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
		jftfMarca.setEditable(true);
		jftfDataCadastro.setEditable(true);
		jftfQuantidade.setEditable(true);
		jtfPrecoCompra.setEditable(true);
		jtfPrecoVenda.setEditable(true);
		jftfObs.setEditable(true);
		jtfCodigoFornecedor.setEditable(true);
		jtfProcurar.setEditable(false);

		jcbFornecedor.setEnabled(true);

		iniciaComboFornecedor = true;//actionPerformed do jcbFornecedor n deve funcionar
	}
	
	public void jtfBlock()
	{
		jftfNome.setEditable(false);
		jftfMarca.setEditable(false);
		jftfDataCadastro.setEditable(false);
		jftfQuantidade.setEditable(false);
		jtfPrecoCompra.setEditable(false);
		jtfPrecoVenda.setEditable(false);
		jtfCodigoFornecedor.setEditable(false);
		jftfObs.setEditable(false);

		jcbFornecedor.setEnabled(false);
	}
	
	public void salvar()
	{
		//pega informacoes do formulario
		String nome = jftfNome.getText();
		String marca = jftfMarca.getText();
		String dataCadastro = jftfDataCadastro.getText();
		String quantidade = jftfQuantidade.getText().trim();
		String precoCompra = jtfPrecoCompra.getText();
		String precoVenda = jtfPrecoVenda.getText();
		String codigoFornecedor = jtfCodigoFornecedor.getText();
		String nomeFornecedor = String.valueOf(jcbFornecedor.getSelectedItem());
		String obs = jftfObs.getText();
		String foto = infoFotoNova;
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			Produto produto = new Produto(codigoFornecedor, dataCadastro, marca, 
					nome, nomeFornecedor, obs, precoCompra, precoVenda, quantidade, foto);
			controle.salvar(produto);//salva o novo registro
			JOptionPane.showMessageDialog(null,"Registro salvo no banco de dados com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
			Produto produto = new Produto(codigo, codigoFornecedor, dataCadastro, marca, 
					nome, nomeFornecedor, obs, precoCompra, precoVenda, quantidade, foto);
			controle.atualizar(produto);//atualiza o registro
			JOptionPane.showMessageDialog(null,"Registro alterado com sucesso.","Notificação",WIDTH);
		}
		
		salvaCancela();//habilita e desabilita botoes e Textfields e muda variaveis
		pegarRegistro();//mostra as informacoes do registro na tela
		pegarRegistroTabela();//mostra informacoes dos registros na tabela de registros do form
		selecionaLinha();//seleciona linha correspondente ao registro na tabela de registros do form
		
		semRegistro = "2";//indica que existem registros cadastrados
	}
	
}
