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
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioUsuario;
import br.com.giovannileite.estoque.modelo.Cliente;
import br.com.giovannileite.estoque.modelo.Funcionario;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco
	
	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	
	private List<Usuario> usuariosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Funcionario> funcionariosLista;//Lista contendo as informacoes que serão mostradas na tela
	
	private FormRelatorioUsuario formRelUsuario;//form relatorio
	
	private JPanel contentPane;
	
	private JTable JTable;
	
	//JComboBox
	private JComboBox jcbNivel = new JComboBox();
	private JComboBox jcbFunc = new JComboBox();
			
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
	private JButton jbtProcurar = new JButton("");
	
	//Jformated
	private JFormattedTextField jftfUsuario;
	private JTextField jtfCodigo;
	private JTextField jtfProcurar;
	
	//field
	private JPasswordField jtfSenha;
	private JPasswordField jtfSenhaConfirmar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroUsuario frame = new FormCadastroUsuario();
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
	public FormCadastroUsuario() {
		
		
		setResizable(false);
		setTitle("Manuten\u00E7\u00E3o do cadastro de usu\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de usu\u00E1rios");
		jblTitulo.setBounds(33, 16, 376, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 52, 376, 2);
		
		JLabel jblCodigo = new JLabel("C\u00F3digo:");
		jblCodigo.setBounds(10, 100, 41, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblUsuario = new JLabel("*Usu\u00E1rio:");
		jblUsuario.setBounds(69, 100, 58, 14);
		jblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setToolTipText("C\u00F3digo");
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setBounds(10, 118, 49, 20);
		jtfCodigo.setEditable(false);
		jtfCodigo.setColumns(10);
		
		
		jftfUsuario = new JFormattedTextField(controle.setMascara("*********************"));//limita o numero de caracteres
		jftfUsuario.setBounds(69, 118, 156, 20);
		jftfUsuario.setToolTipText("Campo obrigat\u00F3rio");
		jftfUsuario.setEditable(false);
		jftfUsuario.setColumns(10);
		jftfUsuario.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				jftfUsuario.setCaretPosition(0);//inicia na primeira posicao
			}
			public void focusGained(FocusEvent arg0) {
				jftfUsuario.setCaretPosition(0);//inicia na primeira posicao
			}
		});
		
		JLabel jblSenha = new JLabel("*Senha:");
		jblSenha.setBounds(237, 100, 49, 14);
		jblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Tabela de registros");
		scrollPane.setBounds(10, 229, 420, 133);

		
		JLabel jblConfirmar = new JLabel("*Confirmar:");
		jblConfirmar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblConfirmar.setBounds(339, 100, 75, 14);
		
		
		jtfSenha = new JPasswordField();
		jtfSenha.setToolTipText("Campo obrigat\u00F3rio,limite de 12 carateres");
		jtfSenha.setEditable(false);
		jtfSenha.setBounds(237, 118, 91, 20);
		
		jtfSenhaConfirmar = new JPasswordField();
		jtfSenhaConfirmar.setToolTipText("Campo obrigat\u00F3rio,limite de 12 carateres");
		jtfSenhaConfirmar.setEditable(false);
		jtfSenhaConfirmar.setBounds(339, 118, 91, 20);
		
		
		jbtPrimeiro.setEnabled(false);
		jbtPrimeiro.setBounds(9, 189, 37, 29);
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
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
		jbtAnterior.setBounds(49, 189, 37, 29);
		jbtAnterior.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
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
		jbtProximo.setBounds(89, 189, 37, 29);
		jbtProximo.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
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
		jbtUltimo.setBounds(129, 189, 37, 29);
		jbtUltimo.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = usuariosLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		jbtProcurar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurar.setToolTipText("Procurar");
		jbtProcurar.setBounds(220, 76, 24, 24);
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtNovoRegistro.setBounds(179, 189, 37, 29);
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30 (1).png")));
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				apagaJtf();//seta "" em textfields e "" ou "1" comboBox especificos

				atualizaNovo();//desabilita botoes e textfields 
				
				atualizar = 0;//indica que ira criar um novo registro
			}
		});
		
		
		jbtSalvar.setBounds(219, 189, 37, 29);
		jbtSalvar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.setEnabled(false);
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					//verifica se informacoes do formulario nao estao em branco
					if(jftfUsuario.getText().trim().equals("") 
							|| jtfSenha.getText().trim().equals("") 
							|| jtfSenhaConfirmar.getText().trim().equals("") 
							|| (jcbFunc.getItemCount() == 0) 
							|| (jcbNivel.getItemCount() == 0) 
							|| (String.valueOf(jcbFunc.getSelectedItem()).trim().equals(""))
							|| (String.valueOf(jcbNivel.getSelectedItem()).trim().equals(""))
						 )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios.","Atenção",WIDTH);
					}
					else
					{	//se senha e confirmacao estao iguais e nao ultrapassam o numero de caracteres
						if(String.valueOf(jtfSenha.getPassword()).equals(String.valueOf(jtfSenhaConfirmar.getPassword()))
								&& String.valueOf(jtfSenha.getPassword()).length() <= 12
								&& String.valueOf(jtfSenhaConfirmar.getPassword()).length() <= 12)
						{
							if(usuariosLista.size() == 0)//se nao existem registros
							{
								salvar();//salva o registro
							}
							else
							{
								int contAtualiza = 0;//contador do atualizar
								String codigoAtualiza = "";//codigo repetido
								
								if(atualizar == 1)//se esta atualizando um registro
								{
									for (int p = 0; p < usuariosLista.size(); p++) //percorre a lista de usuarios
									{
										//verifica se usuario ou funcionario ja existem em algum registro
										if (jftfUsuario.getText().trim().equals(usuariosLista.get(p).getUsuario().trim())
												   | String.valueOf(jcbFunc.getSelectedItem()).trim().equals(usuariosLista.get(p).getFuncionario().trim()))
										{
												contAtualiza ++;
												codigoAtualiza = String.valueOf(usuariosLista.get(p).getCodigo()).trim();
										}
										if(p == (usuariosLista.size() - 1))//se chegou ao ultimo registro da lista
										{
											//se n encotrou  usuario ou funcionario repetidos ou se encontrou 1 repetido e este tem o msm codigo do que estamos atualizando
											if( (contAtualiza == 0) 
													|| (contAtualiza == 1 && codigoAtualiza.equals(jtfCodigo.getText().trim())) )
											{
												salvar();//salva o registro
												break;
											}
											JOptionPane.showMessageDialog(null,"Usuário e/ou Funcionário já existem em outro cadastro","Atenção",WIDTH);
											break;
										}
									}
								}
								else//se esta criando um novo registro
								{
									for (int p = 0; p < usuariosLista.size(); p++) //percorre a lista de clientes
									{	
										//verifica se usuario ou funcionario ja existem em algum registro
										if (jftfUsuario.getText().trim().equals(usuariosLista.get(p).getUsuario().trim())
												   | String.valueOf(jcbFunc.getSelectedItem()).trim().equals(usuariosLista.get(p).getFuncionario().trim()))
										{
											JOptionPane.showMessageDialog(null,"Usuário e/ou Funcionário já existem em outro cadastro","Atenção",WIDTH);
											break;
										}
										else if(p == (usuariosLista.size() - 1))//se n encontrou nenhum usuario ou funcionario igual
										{
											salvar();//salva o registro
											break;
										}
									}
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Senha e confirmação não coincidem ou excedem o número de caracteres","Atenção",WIDTH);
						}
					}
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao salvar o registro.","Atenção",WIDTH);
				}
			}
		});
		
		
		jbtAtualizar.setBounds(259, 189, 37, 29);
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//desabilita botoes e textfields 
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		
		jbtExcluir.setBounds(299, 189, 37, 29);
		jbtExcluir.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
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
						List<Usuario> user = controle.consultarTodosUsuario("codigo");//consulta todos os usuarios
						int cont = 0;//contador
						
						for(int i = 0; i < user.size(); i++)//percorre a lista de usuarios
						{
							if(user.get(i).getNivel().equals("3"))//verifica se e um adm
							{
								cont++;
							}
						}
						
						if(cont <= 1)//caso nao reste mais nenhum usuario adm registrado
						{
							JOptionPane.showMessageDialog(null,"Impossível excluir o único usuário administrador","Notificação",WIDTH);
						}
						else
						{
							int codigo = Integer.parseInt(jtfCodigo.getText());
							controle.excluir(11,codigo);//exclui registro 
							JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
							
							if(usuariosLista.size() == 1)//se era o unico registro da lista
							{
								pegarRegistro();//desabilita botoes e textfields
							}
							else
							{
								for (int a = 0; a < usuariosLista.size(); a++) 
								{
									if(a == (usuariosLista.size() - 1) & codigo == usuariosLista.get(a).getCodigo())//se o excluido era o ultimo registro
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
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Não foi possivel excluir o registro do banco de dados.","Atenção",WIDTH);
				}
			}
		});
		
		
		jbtCancelar.setBounds(339, 189, 37, 29);
		jbtCancelar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
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
					salvaCancela();//desabilita e habilita botoes e texfiels e muda variaveis
					
					pegarRegistro();//mostra informacoes do registro na tela
					
					verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
				}
			}
		});
		
		
		jbtImprimir.setBounds(393, 189, 37, 29);
		jbtImprimir.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelUsuario == null)
				{
					formRelUsuario = new FormRelatorioUsuario();//cria o frame
				}
				formRelUsuario.setVisible(true);//mostra o frame
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
				"C\u00F3digo", "Usu\u00E1rio", "Funcion\u00E1rio", "N\u00EDvel"
			}
		));
		JTable.getColumnModel().getColumn(0).setPreferredWidth(44);
		JTable.getColumnModel().getColumn(2).setPreferredWidth(66);
		JTable.getColumnModel().getColumn(3).setPreferredWidth(38);
		scrollPane.setViewportView(JTable);
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblProcurar.setBounds(10, 62, 58, 14);
		
		jtfProcurar = new JTextField();
		jtfProcurar.setToolTipText("Insira o c\u00F3digo ou nome do funcion\u00E1rio");
		jtfProcurar.setBounds(10, 78, 207, 20);
		jtfProcurar.setColumns(10);
		jtfProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		
		
		JLabel jblCodFuncionario = new JLabel("*Funcion\u00E1rio:");
		jblCodFuncionario.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblCodFuncionario.setBounds(10, 142, 76, 14);
		
		JLabel jblNivel = new JLabel("*N\u00EDvel:");
		jblNivel.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblNivel.setBounds(235, 142, 41, 14);
		
		
		jcbNivel.setEnabled(false);
		jcbNivel.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		jcbNivel.setBounds(235, 158, 130, 20);
		jcbNivel.setToolTipText("Campo obrigat\u00F3rio");
		
		jcbFunc.setEnabled(false);
		jcbFunc.setBounds(10, 158, 215, 20);
		jcbFunc.setToolTipText("Campo obrigat\u00F3rio");
		
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
		contentPane.add(jblUsuario);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfUsuario);
		contentPane.add(jblSenha);
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
		contentPane.add(jblCodFuncionario);
		contentPane.add(jtfProcurar);
		contentPane.add(jblNivel);
		contentPane.add(jcbNivel);
		contentPane.add(jcbFunc);
		contentPane.add(jblConfirmar);
		contentPane.add(jtfSenhaConfirmar);
		contentPane.add(jtfSenha);
		contentPane.add(jbtProcurar);
		
		//colocando dados na tela
		atualizaCb();//atualiza o comboBox
		pegarRegistro();//mostra o primeiro registro na tela
		pegarRegistroTabela();//preenche a tabela de registros
		selecionaLinha();//seleciona a primeira linha
		jtfCodigo.requestFocus();
	}
	
	
	
	public void pegarRegistro()//coloca as informações na tela
	{
		try
		{
			apagaJtf();//seta "" em textfields e comboBox especificos
			
			usuariosLista = controle.consultarTodosUsuario("codigo");//preenche a lista com todos os registros
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(usuariosLista.get(registroAtual).getCodigo()));
			jftfUsuario.setText(String.valueOf(usuariosLista.get(registroAtual).getUsuario()));
			jtfSenha.setText(String.valueOf(usuariosLista.get(registroAtual).getSenha()));
			jtfSenhaConfirmar.setText(String.valueOf(usuariosLista.get(registroAtual).getSenha()));
			jcbFunc.setSelectedItem(String.valueOf(usuariosLista.get(registroAtual).getFuncionario()).trim());
			jcbNivel.setSelectedItem(String.valueOf(usuariosLista.get(registroAtual).getNivel()));
			
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
		if(registroAtual == usuariosLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < usuariosLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {usuariosLista.get(i).getCodigo(),
										usuariosLista.get(i).getUsuario(),
										usuariosLista.get(i).getFuncionario(),
										usuariosLista.get(i).getNivel()});
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
			funcionariosLista = controle.consultarTodosFuncionario("codigo");//consulta e preenche a lista com todos os registros de funcionarios
			jcbFunc.removeAllItems();
			jcbFunc.addItem("");
			for(int i = 0;i < funcionariosLista.size();i++)
			{
				jcbFunc.addItem(funcionariosLista.get(i).getNome().trim());//preenche o cb de funcionarios
			}
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar lista de funcionários","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < usuariosLista.size();i++)//percorre toda lista
			{
				if(digitado.equals(String.valueOf(usuariosLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (usuariosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < usuariosLista.size();i++)//percorre toda lista
			{
				int tamanhoNome = String.valueOf(usuariosLista.get(i).getUsuario()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(usuariosLista.get(i).getUsuario()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(usuariosLista.get(i).getUsuario()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (usuariosLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (usuariosLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
				}
			}
		}
	}
	
	public void salvar()
	{
		String lookPadrao = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		
		//pega informacoes do formulario
		String usuario = jftfUsuario.getText();
		String senha = String.valueOf(jtfSenha.getPassword());
		String funcionario = String.valueOf(jcbFunc.getSelectedItem());
		String nivel = String.valueOf(jcbNivel.getSelectedItem()).trim();
		
		if(atualizar == 0)//caso esteja criando um novo registro
		{
			Usuario user = new Usuario(funcionario,senha,usuario,nivel,lookPadrao);
			controle.salvar(user);
			JOptionPane.showMessageDialog(null,"Registro salvo com sucesso.","Notificação",WIDTH);
		}
		if(atualizar == 1)//caso esteja atualizando um registro ja existente
		{
			int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
			String look = usuariosLista.get(registroAtual).getLook().trim();
			Usuario user = new Usuario(codigo,funcionario,senha,usuario,nivel,look);
			controle.atualizar(user);//atualiza o registro
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
		jftfUsuario.setText("");
		jtfSenha.setText("");
		jtfSenhaConfirmar.setText("");
		
		jcbNivel.setSelectedItem("1");
		jcbFunc.setSelectedItem("");
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

		jcbBlock();//desabilita comboBox especificos
		
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

		jftfUsuario.setEditable(true);
		jtfSenha.setEditable(true);
		jtfSenhaConfirmar.setEditable(true);
		jtfProcurar.setEditable(false);

		jcbFunc.setEnabled(true);
		jcbNivel.setEnabled(true);
	}
	
	public void jcbBlock()//desabilita comboBox especificos
	{
		jcbFunc.setEnabled(false);
		jcbNivel.setEnabled(false);
	}
	
	public void jtfBlock()//desabilita texfields especificos
	{
		jtfCodigo.setEditable(false);
		jftfUsuario.setEditable(false);
		jtfSenha.setEditable(false);
		jtfSenhaConfirmar.setEditable(false);
	}
}
