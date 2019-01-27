package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.JogarDAO;
import DAO.PerguntaSuspeitoDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 500);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel BtnJogar = new JLabel("");
		BtnJogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnJogar.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/JogarSpres.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnJogar.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/JogarNpres.png")));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Jogar jogar;
				try {
					jogar = new Jogar();
					jogar.setVisible(true);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		BtnJogar.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/JogarNpres.png")));
		BtnJogar.setBounds(515, 259, 98, 33);
		contentPane.add(BtnJogar);
		
		JLabel Btnsair = new JLabel("");
		Btnsair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Btnsair.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/SairSpres.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Btnsair.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/SairNpres.png")));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		Btnsair.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/SairNpres.png")));
		Btnsair.setBounds(333, 259, 98, 33);
		contentPane.add(Btnsair);
		
		JLabel BeVindo = new JLabel("");
		BeVindo.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/Bem vindos.png")));
		BeVindo.setBounds(331, 100, 202, 75);
		contentPane.add(BeVindo);
		
		textFieldNome = new JTextField();
		textFieldNome.setForeground(Color.BLACK);
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldNome.setColumns(10);
		textFieldNome.setBackground(Color.LIGHT_GRAY);
		textFieldNome.setBounds(416, 202, 197, 20);
		contentPane.add(textFieldNome);
		
		JLabel ImgNOme = new JLabel("");
		ImgNOme.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/Nome.png")));
		ImgNOme.setBounds(326, 191, 296, 42);
		contentPane.add(ImgNOme);
		
		JLabel ImgQuadrado = new JLabel("");
		ImgQuadrado.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/quadrado.png")));
		ImgQuadrado.setBounds(258, 42, 456, 378);
		contentPane.add(ImgQuadrado);
		
		JLabel BtnGerenciarPergunta = new JLabel("");
		BtnGerenciarPergunta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnGerenciarPergunta.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/GerenciarPerguntaMouseCima.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnGerenciarPergunta.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/GerenciarPergunta.png")));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CadastroPergunta cadastroPergunta = new CadastroPergunta();
				cadastroPergunta.setVisible(true);
				dispose();
				
			}
		});
		BtnGerenciarPergunta.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/GerenciarPergunta.png")));
		BtnGerenciarPergunta.setBounds(10, 59, 42, 42);
		contentPane.add(BtnGerenciarPergunta);
		
		JLabel BtnGerenciarSuspeito = new JLabel("");
		BtnGerenciarSuspeito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnGerenciarSuspeito.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/GerenciarSuspeitoMouseCima.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnGerenciarSuspeito.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/GerenciarSuspeito.png")));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CadastrSuspeito cadastrSuspeito = new CadastrSuspeito();
				cadastrSuspeito.setVisible(true);
				dispose();
			}
		});
		BtnGerenciarSuspeito.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/GerenciarSuspeito.png")));
		BtnGerenciarSuspeito.setBounds(10, 112, 42, 42);
		contentPane.add(BtnGerenciarSuspeito);
		
		JLabel ImgFindTheSuspect = new JLabel("");
		ImgFindTheSuspect.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/Find the Suspect.png")));
		ImgFindTheSuspect.setBounds(20, 286, 263, 191);
		contentPane.add(ImgFindTheSuspect);
		
		JLabel ImgBorda = new JLabel("");
		ImgBorda.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/Borda.png")));
		ImgBorda.setBounds(0, 0, 699, 500);
		contentPane.add(ImgBorda);
		
		JLabel ImgBackground = new JLabel("");
		ImgBackground.setBounds(0, 0, 699, 500);
		ImgBackground.setIcon(new ImageIcon(TelaInicial.class.getResource("/Imagens/fundo madeira.png")));
		contentPane.add(ImgBackground);
	}
}
