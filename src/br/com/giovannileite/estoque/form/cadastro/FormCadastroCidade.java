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
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCidade;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.modelo.Cidade;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface de cadastro
public class FormCadastroCidade extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ControleCrud controle = new ControleCrud();//classe que faz operacoes no banco
	
	
	private String semRegistro = "";//orienta botoes que devem estar ativos ou n
	
	private boolean iniciaComboEstado = false;//variavel q habilita ou n o actionPerformed de jcbPais e jcbUf
	
	private int linha = 0;//indica qual linha da tabela esta selecionada
	private int registroAtual = 0;//idicador de qual registro do list estamos
	private int atualizar = 3;//variavel que indica se vai atualizar ou criar um novo registro
	
	private List<Cidade> cidadesLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Estado> estadosLista;//Lista contendo as informacoes que serão mostradas na tela
	private List<Pai> paisesLista;//Lista contendo as informacoes que serão mostradas na tela
	
	private FormRelatorioCidade formRelCidade;//form relatorio
	
	//jtf
	private JPanel contentPane;
	private JTextField jtfCodigo;
	private JTextField jftfNome;
	private JTable JTable;
	private JTextField jtfProcurar;
	private JTextField jftfNomeEstado;
	
	//jcb
	private JComboBox jcbPais = new JComboBox();
	private JComboBox jcbUf = new JComboBox();
	
	//Botoes
	private JButton jbtNovoRegistro = new JButton("");
	private JButton jbtImprimir = new JButton("");
	private JButton jbtCancelar = new JButton("");
	private JButton jbtExcluir = new JButton("");
	private JButton jbtAtualizar = new JButton("");
	private JButton jbtSalvar = new JButton("");
	private JButton jbtUltimo = new JButton("");
	private JButton jbtProximo = new JButton("");
	private JButton jbtAnterior = new JButton("");
	private JButton jbtPrimeiro = new JButton("");
	private JButton jbtProcurar = new JButton("");	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastroCidade frame = new FormCadastroCidade();
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
	
	public FormCadastroCidade() {
		
	}
	
	public FormCadastroCidade(Usuario usuario) {
		
		
		setResizable(false);
		setTitle("Manuten\u00E7\u00E3o do cadastro de cidades");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);

		JLabel jblTitulo = new JLabel("Manuten\u00E7\u00E3o do cadastro de cidades");
		jblTitulo.setBounds(90, 11, 376, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(90, 47, 376, 2);
		
		JLabel jblCodigo = new JLabel("C\u00F3digo:");
		jblCodigo.setBounds(10, 106, 41, 14);
		jblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel jblNome = new JLabel("*Nome:");
		jblNome.setBounds(69, 106, 49, 14);
		jblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jtfCodigo = new JTextField();
		jtfCodigo.setToolTipText("C\u00F3digo");
		jtfCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jtfCodigo.setEditable(false);
		jtfCodigo.setBounds(10, 124, 49, 20);
		jtfCodigo.setColumns(10);
		
		jftfNome = new JFormattedTextField(controle.setMascara("********************************"));//limita numero de caracteres
		jftfNome.setBounds(69, 124, 279, 20);
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
		
		
		JLabel jblUf = new JLabel("*UF:");
		jblUf.setBounds(358, 106, 51, 14);
		jblUf.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 204, 535, 215);
		
		JLabel jblNomeEstado = new JLabel("Estado:");
		jblNomeEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblNomeEstado.setBounds(420, 106, 46, 14);
		
		
		jbtPrimeiro.setEnabled(false);
		jbtPrimeiro.setBounds(146, 164, 37, 29);
		jbtPrimeiro.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/primeiro.png")));
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
		jbtAnterior.setBounds(186, 164, 37, 29);
		jbtAnterior.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/anterior.png")));
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
		jbtProximo.setBounds(226, 164, 37, 29);
		jbtProximo.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/proximo.png")));
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
		jbtUltimo.setBounds(266, 164, 37, 29);
		jbtUltimo.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/ultimo.png")));
		jbtUltimo.setToolTipText("\u00DAltimo");
		jbtUltimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				registroAtual = cidadesLista.size() -1;//variavel indica o indice do ultimo registro na lista
				pegarRegistro();//coloca as informacoes do registro na tela
				linha = JTable.getRowCount() - 1;//variavel indica a ultima linha da tabela de registros do form
				selecionaLinha();//seleciona linha indicada
			}
		});
		
		
		jbtProcurar.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/if_search_172546.png")));
		jbtProcurar.setToolTipText("Procurar");
		jbtProcurar.setBounds(220, 73, 24, 24);
		jbtProcurar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				procurar();//metodo que procura o registro informado por codigo ou nome
			}
		});
		
		jbtNovoRegistro.setBounds(304, 164, 37, 29);
		jbtNovoRegistro.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-adicionar-30 (1).png")));
		jbtNovoRegistro.setToolTipText("Novo");
		jbtNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//desabilita botoes,textfields e muda uma variavel 
				
				apagaJtf();//seta " " em textfields e comboBox especificos

				jcbPais.setEnabled(true);
				
				atualizar = 0;//indica que ira criar um novo registro
			}
		});
		
		jbtSalvar.setEnabled(false);
		jbtSalvar.setBounds(344, 164, 37, 29);
		jbtSalvar.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-salvar-como-26.png")));
		jbtSalvar.setToolTipText("Salvar");
		jbtSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{	//verifica se informacoes do formulario nao estao em branco
					if(jftfNome.getText().trim().equals("") 
							|| (jcbUf.getItemCount() == 0) 
							|| jftfNomeEstado.getText().trim().equals("")
							|| (jcbPais.getItemCount() == 0)
							|| (String.valueOf(jcbUf.getSelectedItem()).trim().equals(""))
							|| (String.valueOf(jcbPais.getSelectedItem()).trim().equals(""))
					  )
					{
						JOptionPane.showMessageDialog(null,"Campos obrigatórios estão vazios.","Atenção",WIDTH);
					}
					else
					{
						//pega informacoes do formulario
						String nome = jftfNome.getText();
						String uf = String.valueOf(jcbUf.getSelectedItem());
						String pais = String.valueOf(jcbPais.getSelectedItem());
						String nomeEstado = jftfNomeEstado.getText();
						
						if(atualizar == 0)//caso esteja criando um novo registro
						{
							Cidade cidade = new Cidade(nome,pais,uf,nomeEstado);
							controle.salvar(cidade);//salva o novo registro
							JOptionPane.showMessageDialog(null,"Registro salvo no banco de dados com sucesso.","Notificação",WIDTH);
						}
						if(atualizar == 1)//caso esteja atualizando um registro ja existente
						{
							int codigo = Integer.parseInt(jtfCodigo.getText());//pega o codigo
							Cidade cidade = new Cidade(codigo,nome,pais,uf,nomeEstado);
							controle.atualizar(cidade);//atualiza o registro
							JOptionPane.showMessageDialog(null,"Registro alterado com sucesso.","Notificação",WIDTH);
						}
						
						salvaCancela();//habilita e desabilita botoes e Textfields e muda uma variavel 
						atualizaCb();//atualiza os comboBox
						pegarRegistro();//mostra as informacoes do registro na tela
						pegarRegistroTabela();//mostra informacoes dos registros na tabela de registros do form
						selecionaLinha();//seleciona linha correspondente ao registro na tabela de registros do form
						
						semRegistro = "2";//indica que existem registros cadastrados
					}
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao salvar/atualizar o registro.","Atenção",WIDTH);
				}
			}
		});
		
		
		
		jbtAtualizar.setBounds(384, 164, 37, 29);
		jbtAtualizar.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-actualizar-filled-26.png")));
		jbtAtualizar.setToolTipText("Atualizar");
		jbtAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				atualizaNovo();//habilita e desabilita botoes e Textfields e muda uma variavel 

				jtfCodigo.requestFocus();
				jcbPais.setEnabled(true);
				
				atualizar = 1;//indica que e uma atualizacao de registro
			}
		});
		
		
		jbtExcluir.setBounds(424, 164, 37, 29);
		jbtExcluir.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-res\u00EDduos-26.png")));
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
						controle.excluir(2,codigo);//exclui registro 
						JOptionPane.showMessageDialog(null,"Registro excluído do banco de dados com sucesso.","Notificação",WIDTH);
						
						if(cidadesLista.size() == 1)//se era o unico registro da lista
						{
							pegarRegistro();//desabilita botoes e textfields
						}
						else
						{
							for (int a = 0; a < cidadesLista.size(); a++) 
							{
								if(a == (cidadesLista.size() - 1) & codigo == cidadesLista.get(a).getCodigo())//se o excluido era o ultimo registro
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
		
		
		jbtCancelar.setBounds(464, 164, 37, 29);
		jbtCancelar.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-excluir-filled-26 (1).png")));
		jbtCancelar.setToolTipText("Cancelar");
		jbtCancelar.setEnabled(false);
		jbtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(semRegistro.equals("1"))//se nao existem registros
				{	
					iniciaComboEstado = false;//indica que o actionPerformed de "jcbPais e jcbUf" n deve funcionar

					atualizaCb();//atualiza os comboBox
					
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
					iniciaComboEstado = false;//indica que o actionPerformed de "jcbPais e jcbUf" n deve funcionar
					
					atualizaCb();//atualiza os comboBox
					
					salvaCancela();//desabilita e habilita botoes e texfiels e muda variaveis

					pegarRegistro();//mostra informacoes do registro na tela
					
					verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
				}
			}
		});
		
		jbtImprimir.setBounds(506, 164, 37, 29);
		jbtImprimir.setIcon(new ImageIcon(FormCadastroCidade.class.getResource("/br/com/giovannileite/estoque/form/imagem/icons8-impress\u00E3o-24.png")));
		jbtImprimir.setToolTipText("Relat\u00F3rio/Imprimir");
		jbtImprimir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelCidade == null)
				{
					formRelCidade = new FormRelatorioCidade();//cria o frame
				}
				formRelCidade.setVisible(true);//mostra o frame
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
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Estado", "Pa\u00EDs"
			}
		));
		JTable.getColumnModel().getColumn(1).setPreferredWidth(185);
		scrollPane.setViewportView(JTable);
		
		jcbUf.setEnabled(false);
		jcbUf.setToolTipText("Campo obrigat\u00F3rio");
		jcbUf.setBounds(358, 124, 51, 20);
		jcbUf.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaComboEstado == true)//se esta permitido iniciar
				{
					//seta o nome do estado no textfield utilizando o comboBox
					jftfNomeEstado.setText("");
					for(int i = 0;i < estadosLista.size();i++)
					{
						if(String.valueOf(estadosLista.get(i).getUf().trim()).equals(String.valueOf(jcbUf.getSelectedItem()).trim()))
						{
							jftfNomeEstado.setText(estadosLista.get(i).getNome().trim());
							break;
						}
					}
				}
			}
		});
		
		JLabel jblPais = new JLabel("*Pa\u00EDs:");
		jblPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblPais.setBounds(10, 155, 35, 14);
		
		jcbPais.setEnabled(false);
		jcbPais.setToolTipText("Campo obrigat\u00F3rio");
		jcbPais.setBounds(10, 173, 126, 20);
		jcbPais.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(iniciaComboEstado == true)
				{
					//preenche o jcbUf com as UFs dos estados que existem no pais selecionado no jcbPais
					jcbUf.removeAllItems();
					jftfNomeEstado.setText("");
					for(int i = 0;i < estadosLista.size();i++)
					{
						if(String.valueOf(estadosLista.get(i).getPais().trim()).equals(String.valueOf(jcbPais.getSelectedItem()).trim()))
						{
							jcbUf.addItem(estadosLista.get(i).getUf().trim());
						}
					}
					jcbUf.setEnabled(true);
				}
			}
		});
		
		JLabel jblProcurar = new JLabel("Procurar:");
		jblProcurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblProcurar.setBounds(10, 59, 58, 14);
		
		jtfProcurar = new JTextField();
		jtfProcurar.setToolTipText("Procurar por c\u00F3digo ou nome");
		jtfProcurar.setColumns(10);
		jtfProcurar.setBounds(10, 75, 207, 20);
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
		
		jftfNomeEstado = new JTextField();
		jftfNomeEstado.setToolTipText("Nome do estado");
		jftfNomeEstado.setEditable(false);
		jftfNomeEstado.setColumns(10);
		jftfNomeEstado.setBounds(419, 124, 126, 20);
		
		contentPane.setLayout(null);
		contentPane.add(jblTitulo);
		contentPane.add(separator);
		contentPane.add(jblCodigo);
		contentPane.add(jblNome);
		contentPane.add(jtfCodigo);
		contentPane.add(jftfNome);
		contentPane.add(jblUf);
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
		contentPane.add(jcbUf);
		contentPane.add(jblPais);
		contentPane.add(jcbPais);
		contentPane.add(jblProcurar);
		contentPane.add(jtfProcurar);
		contentPane.add(jblNomeEstado);
		contentPane.add(jbtProcurar);
		contentPane.add(jftfNomeEstado);
		
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
			
			cidadesLista = controle.consultarTodosCidade("codigo");//preenche a lista com todos os registros
			
			//mostra as informacoes do registro indicado
			jtfCodigo.setText(String.valueOf(cidadesLista.get(registroAtual).getCodigo()));
			jftfNome.setText(String.valueOf(cidadesLista.get(registroAtual).getNome()));
			jftfNomeEstado.setText(String.valueOf(cidadesLista.get(registroAtual).getNomeEstado()));
			jcbUf.setSelectedItem(String.valueOf(cidadesLista.get(registroAtual).getUf()).trim());
			jcbPais.setSelectedItem(String.valueOf(cidadesLista.get(registroAtual).getPais()).trim());
			
			verificarBotao();//verifica quais botoes "Primeiro,Anterior,Proximo,Ultimo" devem estar habilitados ou desabilitados
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Não existem registros.","Atenção",WIDTH);
			
			//jtf
			apagaJtf();//seta " " em textfields e comboBox especificos
			
			jtfProcurar.setEditable(false);
			
			jcbPais.setEnabled(false);
			jcbUf.setEnabled(false);
			
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
		if(registroAtual == cidadesLista.size() - 1)//se estiver no ultimo registro do list
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
		
		for (int i = 0; i < cidadesLista.size(); i++) //Adiciona as linhas na tabela
		{
			model.addRow(new Object [] {cidadesLista.get(i).getCodigo(),
										cidadesLista.get(i).getNome(),
										cidadesLista.get(i).getUf(),
										cidadesLista.get(i).getPais()});
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
			jcbUf.removeAllItems();
			jcbUf.addItem("");
			estadosLista = controle.consultarTodosEstado("codigo");//consulta e preenche a lista com todos os registros de estados
			for(int i = 0;i < estadosLista.size();i++)
			{
				jcbUf.addItem(estadosLista.get(i).getUf().trim());//preenche o cb de UFs
			}
			
			jcbPais.removeAllItems();
			jcbPais.addItem("");
			paisesLista = controle.consultarTodosPai("codigo");//consulta e preenche a lista com todos os registros de paises
			for(int i = 0;i < paisesLista.size();i++)
			{
				jcbPais.addItem(paisesLista.get(i).getNome().trim());//preenche o cb de paises
			}
			
		}
		catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null,"Erro ao atualizar Pais e Estado","Atenção",WIDTH);
		}
	}
	
	public void procurar()
	{
		String digitado = jtfProcurar.getText().trim();//pega o codigo ou nome que foi digitado na procura
		
		try 
		{
			Long.parseLong (digitado);//Se a pessoa digitar um numero a pesquisa continua por codigo
				
			for(int i = 0;i < cidadesLista.size();i++)//percorre toda lista da cidades
			{
				if(digitado.equals(String.valueOf(cidadesLista.get(i).getCodigo()).trim()))//se o codigo digitado e encontrado
				{
					registroAtual = i;//indica o registro encontrado
					linha = i;//indica a linha correspondente ao registro encontrado
					pegarRegistro();//mostra na tela as informacoes do registro
					selecionaLinha();//seleciona linha correspondente ao registro
					break;
				}
				else if(i == (cidadesLista.size() - 1))
				{
					JOptionPane.showMessageDialog(null,"Código não encontrado.","Notificação",WIDTH);
				}
			}
		} 
		catch (NumberFormatException ex)//Se a pessoa digitou um nome a pesquisa continua por nome 
		{
			int tamanhoDigitado = jtfProcurar.getText().length();//pega o numero de caracteres digitados

			for (int i = 0; i < cidadesLista.size();i++)//percorre toda lista de cidades
			{
				int tamanhoNome = String.valueOf(cidadesLista.get(i).getNome()).length();//pega o tamanho do nome encontrado
				
				if(tamanhoNome >= tamanhoDigitado)//se o tamanho do nome encontrado e maior ou igual ao tamanho que foi digitado
				{
					if(digitado.toLowerCase().trim().equals(String.valueOf(cidadesLista.get(i).getNome()).toLowerCase().trim()))//se o nome digitado e encontrado
					{
						registroAtual = i;//indica o registro encontrado
						linha = i;//indica a linha correspondente ao registro encontrado
						pegarRegistro();//mostra na tela as informacoes do registro
						selecionaLinha();//seleciona linha correspondente ao registro
						break;
					}
					else
					{
						String nomeCortado = String.valueOf(cidadesLista.get(i).getNome()).substring(0,tamanhoDigitado);//deixa o nome encontrado no mesmo tamanho do que foi digitado
						
						if(digitado.toLowerCase().trim().equals(nomeCortado.toLowerCase().trim()))//se o nome digitado e encontrado
						{
							registroAtual = i;//indica o registro encontrado
							linha = i;//indica a linha correspondente ao registro encontrado
							pegarRegistro();//mostra na tela as informacoes do registro
							selecionaLinha();//seleciona linha correspondente ao registro
							break;
						}
						else if(i == (cidadesLista.size() - 1))
						{
							JOptionPane.showMessageDialog(null,"Nome não encontrado.","Notificação",WIDTH);
						}
					}
				}
				else if(i == (cidadesLista.size() - 1))
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
	
	public void apagaJtf()//seta " " em Texfields e ComboBox especificos
	{
		jtfCodigo.setText("");
		jftfNome.setText("");
		jftfNomeEstado.setText("");
		
		jcbPais.setSelectedItem("");
		jcbUf.setSelectedItem("");
		
		jtfCodigo.requestFocus();
	}
	
	public void salvaCancela()//funcao compartilhada por salvar e cancelar
	{
		iniciaComboEstado = false;//indica que o actionPerformed de "jcbPais e jcbUf" n deve funcionar
		
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
		jtfProcurar.setEditable(false);
		
		iniciaComboEstado = true;//indica que o actionPerformed de "jcbPais e jcbUf" deve funcionar
	}
	
	public void jtfBlock()//desabilitada textfields e comboBox especificos
	{
		jftfNome.setEditable(false);
		jftfNomeEstado.setEditable(false);
		
		jcbUf.setEnabled(false);
		jcbPais.setEnabled(false);
	}
}
