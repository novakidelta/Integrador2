package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import DAO.JogarDAO;
import DAO.PerguntaDAO;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Jogar extends JFrame {

	private JPanel contentPane;
	PerguntaDAO  perguntaDAO = new PerguntaDAO();
	String pergunta;
	Classes.Jogar jogar2 = new Classes.Jogar();
	JogarDAO dao = new JogarDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogar frame = new Jogar();
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
	public Jogar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPerguntas = new JLabel("BEM VINDO");
		lblPerguntas.setForeground(Color.WHITE);
		lblPerguntas.setFont(new Font("Knife Princess", Font.PLAIN, 35));
		lblPerguntas.setBounds(10, 77, 679, 33);
		lblPerguntas.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPerguntas);
		
		JButton btnSim = new JButton("SIM");
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPerguntas.setText(jogar2.lista_perguntas.get(1).getPergunta());
			}
		});
		btnSim.setBounds(70, 232, 89, 23);
		contentPane.add(btnSim);
		btnSim.setVisible(false);
		
		JButton btnNoSei = new JButton("N\u00C3O SEI");
		btnNoSei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPerguntas.setText(jogar2.lista_perguntas.get(2).getPergunta());
			}
		});
		btnNoSei.setBounds(270, 232, 89, 23);
		contentPane.add(btnNoSei);
		btnNoSei.setVisible(false);
		
		JButton btnNo = new JButton("N\u00C3O");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblPerguntas.setText(jogar2.lista_perguntas.get(3).getPergunta());
			}
		});
		btnNo.setBounds(463, 232, 89, 23);
		contentPane.add(btnNo);
		btnNo.setVisible(false);
		
		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jogar2.lista_perguntas=dao.PreencherArray();
					btnIniciar.setEnabled(false);
					btnIniciar.setVisible(false);	
					btnNo.setVisible(true);
					btnNoSei.setVisible(true);
					btnSim.setVisible(true);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(jogar2.lista_perguntas.size());
				lblPerguntas.setText(jogar2.lista_perguntas.get(0).getPergunta());
			}
		});
		btnIniciar.setBounds(270, 313, 89, 23);
		contentPane.add(btnIniciar);
		
		
		JLabel ImgBorda = new JLabel("");
		ImgBorda.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Borda.png")));
		ImgBorda.setBounds(0, 0, 699, 499);
		contentPane.add(ImgBorda);
		
		JLabel ImgBackground = new JLabel("");
		ImgBackground.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/fundo madeira.png")));
		ImgBackground.setBounds(0, 0, 699, 499);
		contentPane.add(ImgBackground);
		setUndecorated(true);
		setLocationRelativeTo(null);


	}
}
