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

import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioEstado;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.modelo.Estado;
import br.com.giovannileite.estoque.modelo.Pai;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroEstado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco
	
	private List<Estado> estadosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Pai> paisesLista;//Lista contendo as informacoes que serão mostradas na tela
	
	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	
	private FormRelatorioEstado formRelEstado;//form relatorio
	
	private JPanel contentPane;
	
	//jtf
	private JTextField jtfCodigo;
	private JTextField jftfNome;
	private JTable JTable;
	private JTextField jtfProcurar;
	private JTextField jftfUf;
	
	//ComboBox
	private	JComboBox jcbPais = new JComboBox();
	
	//Botoes
	private JButton jbtAnterior = new JButton("");
	private JButton jbtProximo = new JButton("");
	private JButton jbtUltimo = new JButton("");
	private JButton jbtNovoRegistro = new JButton("");
	private JButton jbtSalvar = new JButton("");
	private JButton jbtAtualizar = new JButton("");
	private JButton jbtExcluir = new JButton("");
	private JButton jbtCancelar = new JButton("");
	private JButton jbtImprimir = new JButton("");
	private JButton jbtPrimeiro = new JButton("");
	private JButton jbtProcurar = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroEstado frame = new FormCadastroEstado();
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
	public FormCadastroEstado() {
		
	}
	
	public FormCadastroEstado(Usuario usuario) {
		
		
		setResizable(false);
		setTitle("Manuten\u00E7\u00E3o do cadastro de estados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 529, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de estados");
		jblTitulo.setBounds(65, 16, 376, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(65, 52, 376, 2);
		
		JLabel jblCodigo = new JLabel("C\u00F3digo:");
		jblCodigo.setBounds(10, 102, 41, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblNome = new JLabel("*Nome:");
		jblNome.setBounds(69, 102, 58, 14);
		jblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setToolTipText("C\u00F3digo");
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setBounds(10, 120, 49, 20);
		jtfCodigo.setEditable(false);
		jtfCodigo.setColumns(10);
		
		jftfNome = new JFormattedTextField(controle.setMascara("*************************"));//limita numero de caracteres
		jftfNome.setBounds(69, 120, 279, 20);
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
		
		JLabel jblPais = new JLabel("*Pa\u00EDs:");
		jblPais.setBounds(400, 102, 72, 14);
		jblPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(30, 184, 465, 174);
		
		
		jbtPrimeiro.setEnabled(false);
		jbtPrimeiro.setBounds(10, 144, 37, 29);
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
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
		
		
		
		jbtAnterior.setEnabled(false);
		jbtAnterior.setBounds(50, 144, 37, 29);
		jbtAnterior.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
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
		
		jbtProximo.setEnabled(false);
		jbtProximo.setBounds(90, 144, 37, 29);
		jbtProximo.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
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
		
		jbtUltimo.setEnabled(false);
		jbtUltimo.setBounds(130, 144, 37, 29);
		jbtUltimo.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = estadosLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurar.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurar.setToolTipText("Procurar");
		jbtProcurar.setBounds(220, 73, 24, 24);
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtNovoRegistro.setBounds(180, 144, 37, 29);
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30.png")));
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				apagaJtf();//seta " " em textfields e comboBox especificos
				
				atualizaNovo();//desabilita botoes e textfields

				atualizar = 0;//indica que ira criar um novo registro
			}
		});
		
		
		jbtSalvar.setBounds(220, 144, 37, 29);
		jbtSalvar.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.setEnabled(false);
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					//verifica se informacoes do formulario nao estao em branco
					if(jftfNome.getText().trim().equals("") 
							|| jftfUf.getText().trim().equals("")
							|| (jcbPais.getItemCount() == 0)
							|| (String.valueOf(jcbPais.getSelectedItem()).trim().equals(""))
					  )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios.","Atenção",WIDTH);
					}
					else
					{
						if(estadosLista.size() == 0)//se nao existem registros
						{
							salvar();//salva o registro
						}
						else
						{
							for (int p = 0; p < estadosLista.size(); p++) //percorre a lista de estados
							{
								if (jftfNome.getText().trim().equals(estadosLista.get(p).getNome().trim()))//se o nome ja existe
								{	
									//se esta atualizando e o codigo do registro e o mesmo
									if(atualizar == 1 && jtfCodigo.getText().trim().equals(String.valueOf(estadosLista.get(p).getCodigo()).trim()))
									{
										salvar();//salva o registro
										break;
									}
									
									JOptionPane.showMessageDialog(null,"O Nome já existe.","Atenção",WIDTH);
									break;
								}
								else if(p == (estadosLista.size() - 1))//se n encontrou nenhum nome igual
								{
									salvar();//salva o registro
									break;
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
		
		
		jbtAtualizar.setBounds(260, 144, 37, 29);
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//desabilita botoes e textfields
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		
		jbtExcluir.setBounds(300, 144, 37, 29);
		jbtExcluir.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
		jbtExcluir.setToolTipText("Excluir");
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
						controle.excluir(4,codigo);//exclui registro
						JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
						
						if(estadosLista.size() == 1)//se era o unico registro da lista
						{
							pegarRegistro();//desabilita botoes e textfields
						}
						else
						{
							for (int a = 0; a < estadosLista.size(); a++) 
							{
								if(a == (estadosLista.size() - 1) & codigo == estadosLista.get(a).getCodigo())//se o excluido era o ultimo registro
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
		
		
		jbtCancelar.setBounds(340, 144, 37, 29);
		jbtCancelar.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
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
					
					desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
					
					apagaJtf();//seta " " em textfields e comboBox especificos
					
					jtfProcurar.setEditable(false);
					
					jtfBlock();//desabilita textfiels especificos
					

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
		
		
		jbtImprimir.setBounds(394, 144, 37, 29);
		jbtImprimir.setIcon(new ImageIcon(FormCadastroEstado.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelEstado == null)
				{
					formRelEstado = new FormRelatorioEstado();//cria o frame
				}
				formRelEstado.setVisible(true);//mostra o frame
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
				"C\u00F3digo", "Nome", "UF", "Pa\u00EDs"
			}
		));
		JTable.getColumnModel().getColumn(1).setPreferredWidth(188);
		JTable.getColumnModel().getColumn(2).setPreferredWidth(36);
		JTable.getColumnModel().getColumn(3).setPreferredWidth(124);
		scrollPane.setViewportView(JTable);
		
		
		
		jcbPais.setEnabled(false);
		jcbPais.setToolTipText("Campo obrigat\u00F3rio");
		jcbPais.setBounds(400, 120, 117, 20);
		
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblProcurar.setBounds(10, 59, 58, 14);
		
		
		jtfProcurar = new JTextField();
		jtfProcurar.setToolTipText("Insira o c\u00F3digo ou nome");
		jtfProcurar.setColumns(10);
		jtfProcurar.setBounds(10, 75, 207, 20);
		jtfProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		JLabel jblUf = new JLabel("*UF:");
		jblUf.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblUf.setBounds(354, 102, 46, 14);
		
		
		jftfUf = new JFormattedTextField(controle.setMascara("**"));//limita numero de caracteres
		jftfUf.setHorizontalAlignment(SwingConstants.CENTER);
		jftfUf.setToolTipText("Campo obrigat\u00F3rio");
		jftfUf.setEditable(false);
		jftfUf.setBounds(354, 120, 41, 20);
		jftfUf.setColumns(10);
		jftfUf.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfUf.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfUf.setCaretPosition(0);//inicia na primeira posicao
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
		contentPane.add(jblCodigo);
		contentPane.add(jblNome);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfNome);
		contentPane.add(jblPais);
		contentPane.add(scrollPane);
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
		contentPane.add(jftfUf);
		contentPane.add(jblUf);
		contentPane.add(jtfProcurar);
		contentPane.add(jblProcurar);
		contentPane.add(jcbPais);
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
			apagaJtf();//seta " " em textfields e comboBox especificos
			
			estadosLista = controle.consultarTodosEstado("codigo");//preenche a lista com todos os registros
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(estadosLista.get(registroAtual).getCodigo()));
			jftfNome.setText(String.valueOf(estadosLista.get(registroAtual).getNome()));
			jftfUf.setText(String.valueOf(estadosLista.get(registroAtual).getUf()));
			jcbPais.setSelectedItem(String.valueOf(estadosLista.get(registroAtual).getPais()).trim());
			
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
			jbtProcurar.setEnabled(false);
			
			desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
			
			apagaJtf();//seta " " em textfields e comboBox especificos
			
			jtfProcurar.setEditable(false);
			
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
		if(registroAtual == estadosLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < estadosLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {estadosLista.get(i).getCodigo(),
										estadosLista.get(i).getNome(),
										estadosLista.get(i).getUf(),
										estadosLista.get(i).getPais()});
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
			paisesLista = controle.consultarTodosPai("codigo");//consulta e preenche a lista com todos os registros de paises
			jcbPais.removeAllItems();
			jcbPais.addItem("");
			for(int i = 0;i < paisesLista.size();i++)
			{
				jcbPais.addItem(paisesLista.get(i).getNome().trim());//preenche o cb de paises
			}
			
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar lista de países","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < estadosLista.size();i++)//percorre toda lista
			{
				if(digitado.equals(String.valueOf(estadosLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (estadosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < estadosLista.size();i++)//percorre toda lista
			{
				int tamanhoNome = String.valueOf(estadosLista.get(i).getNome()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(estadosLista.get(i).getNome()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(estadosLista.get(i).getNome()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (estadosLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (estadosLista.size() - 1))
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
		String uf = jftfUf.getText();
		String pais = String.valueOf(jcbPais.getSelectedItem());
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			Estado estado = new Estado(nome,pais,uf);
			controle.salvar(estado);//salva o novo registro
			JOptionPane.showMessageDialog(null,"Registro salvo no banco de dados com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
			Estado estado = new Estado(codigo,nome,pais,uf);
			controle.atualizar(estado);//atualiza o registro
			JOptionPane.showMessageDialog(null,"Registro alterado com sucesso.","Notificação",WIDTH);
		}
		
		salvaCancela();//habilita e desabilita botoes e Textfields e muda variaveis
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
		jftfUf.setText("");
		
		jcbPais.setSelectedItem("");
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

		jtfCodigo.requestFocus();
				
		jtfProcurar.setEditable(true);
		
		jtfBlock();//desabilita texfields especificos
		
		atualizar = 3;//deixa habilitado o mouseclicked da tabela
	}
	
	public void atualizaNovo()//funcao compartilhada por atualizar e novo
	{
		jbtSalvar.setEnabled(true);
		jbtCancelar.setEnabled(true);
		jbtNovoRegistro.setEnabled(false);
		jbtAtualizar.setEnabled(false);
		jbtExcluir.setEnabled(false);
		jbtImprimir.setEnabled(false);
		jbtProcurar.setEnabled(false);
		
		desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
		
		jftfNome.setEditable(true);
		jftfUf.setEditable(true);
		jtfProcurar.setEditable(false);
		
		jcbPais.setEnabled(true);
	}
	
	public void jtfBlock()//desabilita texfields especificos
	{
		jftfNome.setEditable(false);
		jftfUf.setEditable(false);	

		jcbPais.setEnabled(false);
	}
}
