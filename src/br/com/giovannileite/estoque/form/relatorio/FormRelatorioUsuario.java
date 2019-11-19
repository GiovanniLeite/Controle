package br.com.giovannileite.estoque.form.relatorio;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.giovannileite.estoque.controle.ControleRelatorio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Version information (1.0)
 * 
 * Author (Giovanni Moraes de Oliveira Leite)
 */

//Classe criada para interface que gera relatorios
public class FormRelatorioUsuario extends JFrame {

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
					FormRelatorioUsuario frame = new FormRelatorioUsuario();
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
	public FormRelatorioUsuario() {
		
		
		setResizable(false);
		setTitle("Usu\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel jblTitulo = new JLabel("Relat\u00F3rios");
		jblTitulo.setBounds(75, 16, 105, 25);
		jblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 52, 197, 2);
		contentPane.setLayout(null);
		contentPane.add(jblTitulo);
		contentPane.add(separator);
		
		JLabel jblOrganizar = new JLabel("Organizar por:");
		jblOrganizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jblOrganizar.setBounds(75, 62, 80, 14);
		contentPane.add(jblOrganizar);
		
		JComboBox jcbOrganizar = new JComboBox();
		jcbOrganizar.setToolTipText("Selecione a organiza\u00E7\u00E3o");
		jcbOrganizar.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Usu\u00E1rio", "Funcion\u00E1rio"}));
		jcbOrganizar.setBounds(75, 81, 105, 20);
		contentPane.add(jcbOrganizar);
		
		JButton jbtGerar = new JButton("Gerar");
		jbtGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String organizar = String.valueOf(jcbOrganizar.getSelectedItem());
				
				try
				{
					if(organizar.equals("Código"))
					{
						new ControleRelatorio(11,"codigo");//gera relatorio por codigo
					}
					else if(organizar.equals("Usuário"))
					{
						new ControleRelatorio(11,"usuario");//gera relatorio por usuario
					}
					else if(organizar.equals("Funcionário"))
					{
						new ControleRelatorio(11,"funcionario");//gera relatorio por funcionario
					}
				}
				catch(Exception erro)
				{
					JOptionPane.showMessageDialog(null,"Erro ao gerar relatório","Atenção",WIDTH);
				}
			}
		});
		jbtGerar.setToolTipText("Gerar");
		jbtGerar.setIcon(null);
		jbtGerar.setBounds(85, 112, 75, 23);
		contentPane.add(jbtGerar);
		
		JButton jbtSair = new JButton("Cancelar");
		jbtSair.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		jbtSair.setToolTipText("Sair");
		jbtSair.setIcon(null);
		jbtSair.setBounds(85, 136, 75, 23);
		contentPane.add(jbtSair);
		this.setLocationRelativeTo(null);
	}
}
