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
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.modelo.Cargo1;
import br.com.giovannileite.estoque.modelo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import JNumberField.JNumberField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroCargo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco

	private List<Cargo1> cargosLista;//lista com todos os registros
	
	private String semRegistro = "";//orienta botoes que devem estar ativos ou nao
	
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	
	private FormRelatorioCargo formRelCargo;//form relatorio
	
	private JPanel contentPane;
	
	private JTable JTable;
	
	private JTextField jtfCodigo;
	private JTextField jftfNome;
	private JTextField jtfSalario;
	private JTextField jtfProcurar;
	
	private JButton jbtPrimeiro = new JButton("");
	private JButton jbtAnterior = new JButton("");
	private JButton jbtProximo = new JButton("");
	private JButton jbtUltimo = new JButton("");
	private JButton jbtSalvar = new JButton("");
	private JButton jbtAtualizar = new JButton("");
	private JButton jbtExcluir = new JButton("");
	private JButton jbtNovoRegistro = new JButton("");
	private JButton jbtImprimir = new JButton("");
	private JButton jbtCancelar = new JButton("");
	private JButton jbtProcurar = new JButton("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroCargo frame = new FormCadastroCargo();
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
	public FormCadastroCargo() {
		
	}
	
	public FormCadastroCargo(Usuario usuario) {
		
		
		
		setResizable(false);
		setTitle("Manuten\u00E7\u00E3o do cadastro de cargos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de cargos");
		jblTitulo.setBounds(43, 16, 358, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 52, 376, 2);
		
		JLabel jblCodigo = new JLabel("C\u00F3digo:");
		jblCodigo.setBounds(10, 109, 41, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblNome = new JLabel("*Nome:");
		jblNome.setBounds(69, 109, 58, 14);
		jblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setToolTipText("C\u00F3digo");
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setEditable(false);
		jtfCodigo.setBounds(10, 127, 49, 20);
		jtfCodigo.setColumns(10);
		
		jftfNome = new JFormattedTextField(controle.setMascara("******************************"));//limita numero de caracteres
		jftfNome.setEditable(false);
		jftfNome.setBounds(69, 127, 279, 20);
		jftfNome.setToolTipText("Campo obrigat\u00F3rio");
		jftfNome.setColumns(10);
		jftfNome.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfNome.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfNome.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblSalario = new JLabel("*Sal\u00E1rio base:");
		jblSalario.setBounds(355, 109, 85, 14);
		jblSalario.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfSalario = new JNumberField();
		jtfSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jtfSalario.setEditable(false);
		jtfSalario.setBounds(358, 127, 73, 20);
		jtfSalario.setToolTipText("Campo obrigat\u00F3rio");
		jtfSalario.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(10, 191, 420, 171);
		
		jbtPrimeiro.setEnabled(false);
		jbtPrimeiro.setBounds(10, 151, 37, 29);
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
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
		jbtAnterior.setBounds(50, 151, 37, 29);
		jbtAnterior.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
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
		jbtProximo.setBounds(90, 151, 37, 29);
		jbtProximo.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
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
		jbtUltimo.setBounds(130, 151, 37, 29);
		jbtUltimo.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = cargosLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurar.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurar.setToolTipText("Procurar");
		jbtProcurar.setBounds(220, 76, 24, 24);
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtSalvar.setEnabled(false);
		jbtSalvar.setBounds(220, 151, 37, 29);
		jbtSalvar.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try
				{	//verifica se informacoes do formulario nao estao em branco
					if(jtfSalario.getText().equals("R$ 0.00") || jftfNome.getText().trim().equals("") || jtfSalario.getText().length() > 12)
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios ou ultrapassam número máximo de caracteres.","Atenção",WIDTH);
					}
					else
					{
						if(cargosLista.size() == 0)//se n existe nenhum registo
						{
							salvar();//salva o registro
						}
						else
						{
							for (int p = 0; p < cargosLista.size(); p++) //percorre a lista de cargos
							{
								if (jftfNome.getText().trim().equals(cargosLista.get(p).getNome().trim()))//se o nome ja existe
								{
									//se esta atualizando e o codigo do registro e o mesmo
									if(atualizar == 1 && jtfCodigo.getText().trim().equals(String.valueOf(cargosLista.get(p).getCodigo()).trim()))
									{
										salvar();//salva o registro
										break;
									}
									JOptionPane.showMessageDialog(null,"O Nome já existe.","Atenção",WIDTH);
									break;
								}
								else if(p == (cargosLista.size() - 1))//se n encontrou nenhum nome igual
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
		
		
		
		jbtAtualizar.setBounds(260, 151, 37, 29);
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//habilita e desabilita botoes e Textfields

				atualizar = 1;//indica que vai atualizar um registro
			}
		});
		
		
		jbtExcluir.setBounds(300, 151, 37, 29);
		jbtExcluir.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
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
						controle.excluir(1,codigo);//exclui registro 
						JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
						
						if(cargosLista.size() == 1)//se era o unico registro da lista
						{
							pegarRegistro();//desabilita botoes e textfields
						}
						else
						{
							for (int a = 0; a < cargosLista.size(); a++) 
							{
								if(a == (cargosLista.size() - 1) & codigo == cargosLista.get(a).getCodigo())//se o excluido era o ultimo registro
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
		jbtCancelar.setBounds(340, 151, 37, 29);
		jbtCancelar.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
		jbtCancelar.setToolTipText("Cancelar");
		jbtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(semRegistro.equals("1"))//se nao existem registros
				{
					apagaJtf();//seta "" em textfields especificos
					
					jtfBlock();//desabilita textfiels especificos
					
					jtfProcurar.setEditable(false);
					
					jbtNovoRegistro.setEnabled(true);
					jbtSalvar.setEnabled(false);
					jbtAtualizar.setEnabled(false);
					jbtExcluir.setEnabled(false);
					jbtCancelar.setEnabled(false);
					jbtImprimir.setEnabled(false);
					jbtProcurar.setEnabled(false);
					
					desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
					
					DefaultTableModel model = (DefaultTableModel) JTable.getModel();//pega o modelo da tabela de registros do form
					model.setNumRows(0);//limpa a tabela de registros do form
					
					atualizar = 3;//deixa disponivel o mouseclicked da tabela de registros do form
				}
				else
				{
					salvaCancela();//habilita e desabilita botoes e Textfields
					
					pegarRegistro();//mostra informacoes do registro na tela
					
					verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
				}
			}
		});
		
		
		
		jbtNovoRegistro.setBounds(180, 151, 37, 29);
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30.png")));
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//desabilita botoes e textfields
				
				apagaJtf();//seta "" em textfields especificos

				atualizar = 0;//indica que ira criar um novo registro
			}
		});
		
		
		
		jbtImprimir.setBounds(394, 151, 37, 29);
		jbtImprimir.setIcon(new ImageIcon(FormCadastroCargo.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio");
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelCargo == null)
				{
					formRelCargo = new FormRelatorioCargo();//cria o frame
				}
				formRelCargo.setVisible(true);//mostra o frame
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
				"C\u00F3digo", "Nome", "Sal\u00E1rio base"
			}
		));
		JTable.getColumnModel().getColumn(1).setPreferredWidth(177);
		scrollPane.setViewportView(JTable);
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblProcurar.setBounds(10, 62, 58, 14);
		
		jtfProcurar = new JTextField();
		jtfProcurar.setToolTipText("Procurar por nome ou c\u00F3digo.");
		jtfProcurar.setBounds(10, 78, 207, 20);
		jtfProcurar.setColumns(10);
		jtfProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//procura o registro pelo nome ou codigo indicado
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
		contentPane.add(jblSalario);
		contentPane.add(jtfSalario);
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
		contentPane.add(jblProcurar);
		contentPane.add(jtfProcurar);
		contentPane.add(jbtProcurar);
	
		//colocando dados na tela
		pegarRegistro();//mostra o primeiro registro na tela
		pegarRegistroTabela();//preenche a tabela de registros
		selecionaLinha();//seleciona a primeira linha
		jtfCodigo.requestFocus();
		
		//Verificando permissão 
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
			apagaJtf();//seta "" em textfields especificos
			
			cargosLista = controle.consultarTodosCargo("codigo");//preenche a lista com todos os registros
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(cargosLista.get(registroAtual).getCodigo()));
			jftfNome.setText(String.valueOf(cargosLista.get(registroAtual).getNome()));
			jtfSalario.setText(String.valueOf(cargosLista.get(registroAtual).getSalario()));
			
			verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Não existem registros.","Atenção",WIDTH);
			
			apagaJtf();//seta " " em textfields especificos
			
			jtfProcurar.setEditable(false);
			
			//botoes
			jbtSalvar.setEnabled(false);
			jbtAtualizar.setEnabled(false);
			jbtExcluir.setEnabled(false);
			jbtCancelar.setEnabled(false);
			jbtImprimir.setEnabled(false);
			jbtProcurar.setEnabled(false);
			
			desabilitaSeta();//desabilita botoes "Primeiro,Anterior,Proximo,Ultimo"
			
			DefaultTableModel model = (DefaultTableModel) JTable.getModel();//pega o modelo da tabela
			model.setNumRows(0);//apaga todas as linhas da tabela

			semRegistro = "1";//indica que n existem registros
		}
	}
	
	public void verificarBotao()//verifica quais botoes devem estar habilitados ou nao
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
		if(registroAtual == cargosLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < cargosLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {cargosLista.get(i).getCodigo(),
										cargosLista.get(i).getNome(),
										cargosLista.get(i).getSalario()});
		}
	}
	
	public void selecionaLinha()//mostra linha selecionada na tabela
	{
		JTable.getSelectionModel().setSelectionInterval(linha, linha);
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < cargosLista.size();i++)//percorre toda lista
			{
				if(digitado.equals(String.valueOf(cargosLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (cargosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < cargosLista.size();i++)//percorre toda lista
			{
				int tamanhoNome = String.valueOf(cargosLista.get(i).getNome()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(cargosLista.get(i).getNome()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(cargosLista.get(i).getNome()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (cargosLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (cargosLista.size() - 1))
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
		String salario = jtfSalario.getText();
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			Cargo1 cargo = new Cargo1(nome,salario);
			controle.salvar(cargo);//salva o novo registro
			JOptionPane.showMessageDialog(null,"Registro salvo no banco de dados com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
			Cargo1 cargo = new Cargo1(codigo,nome,salario);
			controle.atualizar(cargo);//atualiza o registro
			JOptionPane.showMessageDialog(null,"Registro alterado com sucesso.","Notificação",WIDTH);
		}
		
		salvaCancela();//habilita e desabilita botoes e Textfields
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
	
	public void apagaJtf()//seta "" em Texfields
	{
		jtfCodigo.setText("");
		jftfNome.setText("");
		jtfSalario.setText("");
		
		jtfCodigo.requestFocus();
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
		
		jtfBlock();//desabilita texfields especificos
		
		jtfProcurar.setEditable(true);
		
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
		jtfSalario.setEditable(true);
		jtfProcurar.setEditable(false);
		
		jtfCodigo.requestFocus();
	}
	
	public void jtfBlock()//desabilitada textfields especificos
	{
		jftfNome.setEditable(false);
		jtfSalario.setEditable(false);
	}
}
