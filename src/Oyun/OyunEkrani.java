package Oyun;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class OyunEkrani extends JFrame{
 
	
	public OyunEkrani(String title) throws HeadlessException {
		super(title);
	}

	public static void main(String[] args) {
		OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
		ekran.setResizable(false);
		ekran.setFocusable(false);
		ekran.setSize(800,800);
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		OyunPanel oyunPanel = new OyunPanel();
		oyunPanel.requestFocus();
		oyunPanel.addKeyListener(oyunPanel);
		oyunPanel.setFocusable(true);
		oyunPanel.setFocusTraversalKeysEnabled(false);
		ekran.add(oyunPanel);
		ekran.setVisible(true);
	}
}
