package br.com.giovannileite.estoque.form;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//classe que modifica o fundo da tela do programa
public class Panel extends JPanel
{
	private static final long serialVersionUID = 1L;
	ImageIcon fundo = new ImageIcon(getClass().getResource("imagem/fundo.png"));
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Image img = fundo.getImage();
		g.drawImage(img,0,0,this);
	}
}