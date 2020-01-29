package br.com.giovannileite.estoque.form;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroCargo;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroCidade;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroCliente;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroEstado;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroFornecedor;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroFuncionario;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroNotaEntradaControle;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroNotaSaidaControle;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroPais;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroProduto;
import br.com.giovannileite.estoque.form.cadastro.FormCadastroUsuario;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCargo;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCidade;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioCliente;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioEstado;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioFornecedor;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioFuncionario;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaEntrada;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioNotaSaida;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioPais;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioProduto;
import br.com.giovannileite.estoque.form.relatorio.FormRelatorioUsuario;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Font;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//classe criada para interface do programa
public class FormPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//cadastros
	private FormCadastroCargo formCargo;
	private FormCadastroCidade formCidade;
	private FormCadastroCliente formCliente;
	private FormCadastroEstado formEstado;
	private FormCadastroFornecedor formFornecedor;
	private FormCadastroFuncionario formFuncionario;
	private FormCadastroNotaEntradaControle formNotaEntrada;
	private FormCadastroNotaSaidaControle formNotaSaida;
	private FormCadastroPais formPais;
	private FormCadastroProduto formProduto;
	private FormCadastroUsuario formUsuario;
	
	//relatorios
	private FormRelatorioCargo formRelCargo;
	private FormRelatorioCidade formRelCidade;
	private FormRelatorioCliente formRelCliente;
	private FormRelatorioEstado formRelEstado;
	private FormRelatorioFornecedor formRelFornecedor;
	private FormRelatorioFuncionario formRelFuncionario;
	private FormRelatorioNotaEntrada formRelNotaEntrada;
	private FormRelatorioNotaSaida formRelNotaSaida;
	private FormRelatorioPais formRelPais;
	private FormRelatorioProduto formRelProduto;
	
	//string com a aparência que quero para a tela
	private static String lookPadrao = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	
	private JLabel jblHora;
	private JLabel jblUsuario;
	private JLabel jblData;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormPrincipal() 
	{
		
	}
	
	public FormPrincipal(Usuario usuario) {
		setTitle("Sistema");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1934, 1056);
		setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu jmnCadastro = new JMenu("Cadastros");
		menuBar.add(jmnCadastro);
		
		JMenuItem jmniCadProduto = new JMenuItem("Produtos");
		jmniCadProduto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formProduto == null)
				{
					formProduto = new FormCadastroProduto(usuario);//cria o frame
					formProduto.setVisible(true);//mostra
				}
				else
				{
					formProduto.dispose();//desfaz a janela
					formProduto = new FormCadastroProduto(usuario);//recria o frame
					formProduto.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadastro.add(jmniCadProduto);
		
		JMenuItem jmniCadFornecedor = new JMenuItem("Fornecedores");
		jmniCadFornecedor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formFornecedor == null)
				{
					formFornecedor = new FormCadastroFornecedor(usuario);//cria o frame
					formFornecedor.setVisible(true);//mostra
				}
				else
				{
					formFornecedor.dispose();//desfaz a janela
					formFornecedor = new FormCadastroFornecedor(usuario);//recria o frame
					formFornecedor.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadastro.add(jmniCadFornecedor);
		
		JMenuItem jmniCadFuncionario = new JMenuItem("Funcion\u00E1rios");
		jmniCadFuncionario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formFuncionario == null)
				{
					formFuncionario = new FormCadastroFuncionario(usuario);//cria o frame
					formFuncionario.setVisible(true);//mostra
				}
				else
				{
					formFuncionario.dispose();//desfaz a janela
					formFuncionario = new FormCadastroFuncionario(usuario);//recria o frame
					formFuncionario.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadastro.add(jmniCadFuncionario);
		
		JMenuItem jmniCadCargo = new JMenuItem("Cargos");
		jmniCadCargo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formCargo == null)
				{
					formCargo = new FormCadastroCargo(usuario);//cria o frame
					formCargo.setVisible(true);//mostra
				}
				else
				{
					formCargo.dispose();//desfaz a janela
					formCargo = new FormCadastroCargo(usuario);//recria o frame
					formCargo.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadastro.add(jmniCadCargo);
		
		JMenu jmnCadLocal = new JMenu("Locais");
		jmnCadastro.add(jmnCadLocal);
		
		JMenuItem jmniCadPais = new JMenuItem("Pa\u00EDses");
		jmniCadPais.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formPais == null)
				{
					formPais = new FormCadastroPais(usuario);//cria o frame
					formPais.setVisible(true);//mostra
				}
				else
				{
					formPais.dispose();//desfaz a janela
					formPais = new FormCadastroPais(usuario);//recria o frame
					formPais.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadLocal.add(jmniCadPais);
		
		JMenuItem jmniCadEstado = new JMenuItem("Estados");
		jmniCadEstado.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formEstado == null)
				{
					formEstado = new FormCadastroEstado(usuario);//cria o frame
					formEstado.setVisible(true);//mostra
				}
				else
				{
					formEstado.dispose();//desfaz a janela
					formEstado = new FormCadastroEstado(usuario);//recria o frame
					formEstado.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadLocal.add(jmniCadEstado);
		
		JMenuItem jmniCadCidade = new JMenuItem("Cidades");
		jmniCadCidade.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formCidade == null)
				{
					formCidade = new FormCadastroCidade(usuario);//cria o frame
					formCidade.setVisible(true);//mostra
				}
				else
				{
					formCidade.dispose();//desfaz a janela
					formCidade = new FormCadastroCidade(usuario);//recria o frame
					formCidade.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadLocal.add(jmniCadCidade);
		
		JMenu jmnCadNotaFiscal = new JMenu("Nota Fiscal");
		jmnCadastro.add(jmnCadNotaFiscal);
		
		JMenuItem jmniCadEntrada = new JMenuItem("Entrada");
		jmniCadEntrada.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formNotaEntrada == null)
				{
					formNotaEntrada = new FormCadastroNotaEntradaControle(usuario);//cria o frame
					formNotaEntrada.setVisible(true);//mostra
				}
				else
				{
					formNotaEntrada.dispose();//desfaz a janela
					formNotaEntrada = new FormCadastroNotaEntradaControle(usuario);//recria o frame
					formNotaEntrada.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadNotaFiscal.add(jmniCadEntrada);
		
		JMenuItem jmniCadSaida = new JMenuItem("Sa\u00EDda");
		jmniCadSaida.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formNotaSaida == null)
				{
					formNotaSaida = new FormCadastroNotaSaidaControle(usuario);//cria o frame
					formNotaSaida.setVisible(true);//mostra
				}
				else
				{
					formNotaSaida.dispose();//desfaz a janela
					formNotaSaida = new FormCadastroNotaSaidaControle(usuario);//recria o frame
					formNotaSaida.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadNotaFiscal.add(jmniCadSaida);
		
		JMenuItem jmniCadCliente = new JMenuItem("Clientes");
		jmniCadCliente.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
				if(formCliente == null)
				{
					formCliente = new FormCadastroCliente(usuario);//cria o frame
					formCliente.setVisible(true);//mostra
				}
				else
				{
					formCliente.dispose();//desfaz a janela
					formCliente = new FormCadastroCliente(usuario);//recria o frame
					formCliente.setVisible(true);//mostra o frame
				}
			}
		});
		jmnCadastro.add(jmniCadCliente);
		
		JMenuItem jmniSair = new JMenuItem("Sair");
		jmniSair.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);//sai do programa
			}
		});
		
		JSeparator separator = new JSeparator();
		jmnCadastro.add(separator);
		jmnCadastro.add(jmniSair);
		
		JMenu jmnRelatorio = new JMenu("Relat\u00F3rios");
		menuBar.add(jmnRelatorio);
		
		JMenuItem jmniRelProduto = new JMenuItem("Produtos");
		jmniRelProduto.addActionListener(new ActionListener() 
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
		jmnRelatorio.add(jmniRelProduto);
		
		JMenuItem jmniRelFornecedor = new JMenuItem("Fornecedores");
		jmniRelFornecedor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta 
				if(formRelFornecedor == null)
				{
					formRelFornecedor = new FormRelatorioFornecedor();//cria o frame
				}
				formRelFornecedor.setVisible(true);//mostra
			}
		});
		jmnRelatorio.add(jmniRelFornecedor);
		
		JMenuItem jmniRelFuncionario = new JMenuItem("Funcion\u00E1rios");
		jmniRelFuncionario.addActionListener(new ActionListener() 
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
		jmnRelatorio.add(jmniRelFuncionario);
		
		JMenuItem jmniRelCargo = new JMenuItem("Cargos");
		jmniRelCargo.addActionListener(new ActionListener() 
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
		jmnRelatorio.add(jmniRelCargo);
		
		JMenu jmnRelLocais = new JMenu("Locais");
		jmnRelatorio.add(jmnRelLocais);
		
		JMenuItem jmniRelPais = new JMenuItem("Pa\u00EDs");
		jmniRelPais.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelPais == null)
				{
					formRelPais = new FormRelatorioPais();//cria o frame
				}
				formRelPais.setVisible(true);//mostra o frame
			}
		});
		jmnRelLocais.add(jmniRelPais);
		
		JMenuItem jmniRelEstado = new JMenuItem("Estados");
		jmniRelEstado.addActionListener(new ActionListener() 
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
		jmnRelLocais.add(jmniRelEstado);
		
		JMenuItem jmniRelCidade = new JMenuItem("Cidades");
		jmniRelCidade.addActionListener(new ActionListener() 
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
		jmnRelLocais.add(jmniRelCidade);
		
		JMenu jmnRelNotaFiscal = new JMenu("Nota Fiscal");
		jmnRelatorio.add(jmnRelNotaFiscal);
		
		JMenuItem jmniRelEntrada = new JMenuItem("Entrada");
		jmniRelEntrada.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//meio que encontrei de deixar apenas uma janela aberta
				if(formRelNotaEntrada == null)
				{
					formRelNotaEntrada = new FormRelatorioNotaEntrada();//cria o frame
				}
				formRelNotaEntrada.setVisible(true);//mostra o frame
			}
		});
		jmnRelNotaFiscal.add(jmniRelEntrada);
		
		JMenuItem jmniRelSaida = new JMenuItem("Sa\u00EDda");
		jmniRelSaida.addActionListener(new ActionListener() 
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
		jmnRelNotaFiscal.add(jmniRelSaida);
		
		JMenuItem jmniRelCliente = new JMenuItem("Clientes");
		jmniRelCliente.addActionListener(new ActionListener() 
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
		jmnRelatorio.add(jmniRelCliente);
		
		JMenu jmnConfiguracao = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(jmnConfiguracao);
		
		JMenuItem jmniUsuario = new JMenuItem("Usu\u00E1rios");
		jmniUsuario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(usuario.getNivel().equals("3"))
				{
					//meio que encontrei de deixar apenas uma janela aberta sem bugar a classe controleCrud de cada form
					if(formUsuario == null)
					{
						formUsuario = new FormCadastroUsuario(usuario);//cria o frame
						formUsuario.setVisible(true);//mostra
					}
					else
					{
						formUsuario.dispose();//desfaz a janela
						formUsuario = new FormCadastroUsuario(usuario);//recria o frame
						formUsuario.setVisible(true);//mostra o frame
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Área restrita a administradores","Atenção",WIDTH);
				}
			}
		});
		jmnConfiguracao.add(jmniUsuario);
		
		JMenuItem jmniAparencia = new JMenuItem("Apar\u00EAncia");
		jmniAparencia.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new FormAparencia(usuario).setVisible(true);//abre a tela que modifica aparencia
			}
		});
		jmnConfiguracao.add(jmniAparencia);
		
		contentPane = new Panel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel jlblAutor = new JLabel("Sistema Desenvolvido por Giovanni M. de O. Leite");
		jlblAutor.setToolTipText("Desenvolvedor");
		jlblAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jblData = new JLabel("Data:19/01/2019");
		jblData.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jblHora = new JLabel("");
		jblHora.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		jblUsuario = new JLabel("Usu\u00E1rio:Giovanni Leite");
		jblUsuario.setToolTipText("Usu\u00E1rio atual");
		jblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(jlblAutor)
					.addPreferredGap(ComponentPlacement.RELATED, 1160, Short.MAX_VALUE)
					.addComponent(jblData)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jblHora, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jblUsuario, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(972, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jblData)
								.addComponent(jlblAutor))
							.addComponent(jblHora, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(jblUsuario, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
		
		//Usuario
		jblUsuario.setText("Usuário: " + usuario.getFuncionario());
		
		//Data
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		jblData.setText("Data: " + formato.format(data));
		
		//hora
		Timer timer = new Timer(1000, new hora());
		timer.start();

		lookAndFeel(usuario.getLook());
	}
	

	//Altera a aparência da tela
	public void lookAndFeel(String look)
	{
		try 
		{
			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(this);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null,"Erro ao mudar aparência","Atenção",WIDTH);
		}
	}
	
	class hora implements ActionListener//coloca o horario na tela
	{	@Override
		public void actionPerformed(ActionEvent e)
		{
			Calendar now = Calendar.getInstance();
			jblHora.setText("Hora: " + String.format("%1$tH:%1tM:%1$tS", now));
		}
	}

	
}
