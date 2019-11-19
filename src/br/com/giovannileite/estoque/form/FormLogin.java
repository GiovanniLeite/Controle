package br.com.giovannileite.estoque.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//classe criada para interface de login do programa
public class FormLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtfUsuario;
	
	
	private JPasswordField psfSenha;
	private List<Usuario> listaUsuarios;
	private ControleCrud controle = new ControleCrud();
	private JLabel jblAviso;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormLogin() {
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel jblTitulo = new JLabel("Tela de Login");
		jblTitulo.setBounds(68, 16, 160, 29);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		jblTitulo.setForeground(Color.BLACK);
		
		JLabel jblUsuario = new JLabel("Usu\u00E1rio:");
		jblUsuario.setBounds(35, 66, 40, 14);
		jblUsuario.setToolTipText("Insira o usu\u00E1rio.");
		
		JLabel jblSenha = new JLabel("Senha:");
		jblSenha.setBounds(41, 94, 34, 14);
		jblSenha.setToolTipText("Insira a senha.");
		
		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(85, 63, 124, 20);
		jtfUsuario.setToolTipText("Insira o usu\u00E1rio");
		jtfUsuario.setColumns(10);
		
		psfSenha = new JPasswordField();
		psfSenha.setToolTipText("Insira a senha");
		psfSenha.setBounds(85, 91, 124, 20);
		psfSenha.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				entrar();//entra no programa
			}
		});
		
		jblAviso = new JLabel("");
		jblAviso.setBounds(77, 112, 149, 15);
		jblAviso.setForeground(Color.RED);
		
		JButton jbtEntrar = new JButton("Entrar");
		jbtEntrar.setBounds(78, 133, 63, 23);
		jbtEntrar.setToolTipText("Entrar");
		jbtEntrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				entrar();//entra no programa
			}
		});
		
		JButton jbtSair = new JButton("Sair");
		jbtSair.setToolTipText("Sair");
		jbtSair.setBounds(151, 133, 73, 23);
		jbtSair.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);//fecha o programa
			}
		});
		
		setResizable(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 46, 208, 2);
		
		
		contentPane.setLayout(null);
		contentPane.add(jblTitulo);
		contentPane.add(jblSenha);
		contentPane.add(separator);
		contentPane.add(jblUsuario);
		contentPane.add(jblAviso);
		contentPane.add(jtfUsuario);
		contentPane.add(jbtEntrar);
		contentPane.add(jbtSair);
		contentPane.add(psfSenha);
		
		
		lookAndFeel();//Altera a aparência da tela
		this.setLocationRelativeTo(null);
	}
	
	
	//Altera a aparência da tela
	public void lookAndFeel()
	{
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null,"Erro ao mudar aparência","Atenção",WIDTH);
		}
	}
	
	public void entrar()
	{
		//verifica se campos nao estao vazios
		if(jtfUsuario.getText().trim().equals("") | psfSenha.getText().trim().equals(""))
		{
			if(jtfUsuario.getText().trim().equals("") && !psfSenha.getText().trim().equals(""))//Usuario vazio
			{
				JOptionPane.showMessageDialog(null,"O campo usuário não deve ficar vazio.","Atenção",WIDTH);
			}
			else if(!jtfUsuario.getText().trim().equals("") && psfSenha.getText().trim().equals(""))//Senha vazia
			{
				JOptionPane.showMessageDialog(null,"O campo senha não deve ficar vazio.","Atenção",WIDTH);
			}
			else if(jtfUsuario.getText().trim().equals("") && psfSenha.getText().trim().equals(""))//Ambos vazios
			{
				jblAviso.setText("");
				JOptionPane.showMessageDialog(null,"Os campos não devem ficar vazios.","Atenção",WIDTH);
			}
		}
		else
		{
			if((jtfUsuario.getText().trim().length() > 21) | (psfSenha.getText().trim().length() > 12))//verifica se usuario e senha nao estao muito grandes
			{
				jblAviso.setText("");
				JOptionPane.showMessageDialog(null,"Usuário e/ou Senha execedem o limite de caracteres.","Atenção",WIDTH);
			}
			else
			{
				String usuarioDigitado = jtfUsuario.getText().trim();//pega o usuario que foi digitado
				String senhaDigitada = psfSenha.getText().trim();//pega a senha que foi digitada
				
				listaUsuarios = controle.consultarTodosUsuario("codigo");//consulta os registros de usuarios no banco
				
				for(int i = 0; i < listaUsuarios.size(); i++)//percorre a lista de usuarios
				{
					String usuarioBanco = listaUsuarios.get(i).getUsuario().trim();//pega o usuario do atual registro do list
					String senhaBanco = listaUsuarios.get(i).getSenha().trim();//pega a senha do atual registro do list
					
					if(usuarioDigitado.equals(usuarioBanco) && senhaDigitada.equals(senhaBanco))//verifica se a senha e o usuario coincidem com o registro atual do list
					{
						new FormPrincipal(listaUsuarios.get(i)).setVisible(true);//entrou
						controle.sair();//desconecta
						dispose();//fecha a janela de login
						break;
					}
					else if(i == (listaUsuarios.size() - 1))//se nao encontrou nenhum usuario
					{
						jblAviso.setText("Usuário e/ou Senha inválidos.");
					}
				}
			}
		}
	}
}
