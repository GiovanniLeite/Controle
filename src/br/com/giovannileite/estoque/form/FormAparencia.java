package br.com.giovannileite.estoque.form;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.giovannileite.estoque.controle.ControleCrud;
import br.com.giovannileite.estoque.modelo.Usuario;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//classe criada para modificar a interface do programa
public class FormAparencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAparencia frame = new FormAparencia();
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

	public FormAparencia() 
	{
		
	}
	
	public FormAparencia(Usuario usuario) {
		setResizable(false);
		setTitle("Apar\u00EAncia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 234, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel jblTitulo = new JLabel("Apar\u00EAncia");
		jblTitulo.setBounds(60, 11, 102, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		contentPane.add(jblTitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 37, 170, 2);
		contentPane.add(separator);
		
		JRadioButton rbtnWindows = new JRadioButton("Windows");
		rbtnWindows.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtnWindows.setBounds(70, 43, 109, 23);
		rbtnWindows.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String look = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";//aparencia escolhida pelo usuario
					ControleCrud controle = new ControleCrud();//instancia da classe que realiza operacoes no banco
					usuario.setLook(look);//seta aparencia escolhida pelo usuario
					controle.atualizar(usuario);//atualiza o registro do usuario
					lookAndFeel(look);//altera aparencia da interface
					controle.sair();//desconecta
					dispose();//fecha a janela
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao mudar aparência","Atenção",WIDTH);
				}
			}
		});
		contentPane.add(rbtnWindows);
		
		JRadioButton rbtnClassic = new JRadioButton("Classic");
		rbtnClassic.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtnClassic.setBounds(70, 69, 109, 23);
		rbtnClassic.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String look = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";//aparencia escolhida pelo usuario
					ControleCrud controle = new ControleCrud();//instancia da classe que realiza operacoes no banco
					usuario.setLook(look);//seta aparencia escolhida pelo usuario
					controle.atualizar(usuario);//atualiza o registro do usuario
					lookAndFeel(look);//altera aparencia da interface
					controle.sair();//desconecta
					dispose();//fecha a janela
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao mudar aparência","Atenção",WIDTH);
				}
			}
		});
		contentPane.add(rbtnClassic);
		
		ButtonGroup btngAparencia = new ButtonGroup();
		btngAparencia.add(rbtnWindows);
		btngAparencia.add(rbtnClassic);
		
		this.setLocationRelativeTo(null);
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
}
