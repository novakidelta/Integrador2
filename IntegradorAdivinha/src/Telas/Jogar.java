package Telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Comparar;
import Classes.ListarSqlFK;
import Classes.Pergunta;
import Classes.RecebeRespostas;
import Classes.Suspeito;
import DAO.JogarDAO;
import DAO.PerguntaDAO;
import DAO.PerguntaSuspeitoDAO;
import DAO.SuspeitoDAO;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Jogar extends JFrame {

	private JPanel contentPane;
	PerguntaDAO  perguntaDAO = new PerguntaDAO();
	String pergunta;
	Classes.Jogar jogar2 = new Classes.Jogar();
	JogarDAO dao = new JogarDAO();
	Integer contador = 0;
	JLabel btnSim = new JLabel("");
	JLabel ImgSair = new JLabel("");
	JLabel btnNoSei = new JLabel("");
	JLabel btnNo = new JLabel("");
	JLabel ImgFundo = new JLabel("");
	JLabel ImgFrente = new JLabel("");
	JLabel lblPerguntas = new JLabel("BEM VINDO");
	List<RecebeRespostas> recebe_respostas = new ArrayList<RecebeRespostas>();
	List<Classes.Jogar> lista_recebida_bd = new ArrayList<Classes.Jogar>();
	PerguntaSuspeitoDAO perguntaSuspeitoDAO = new PerguntaSuspeitoDAO();
	private final JLabel lblSuspeito = new JLabel("??????");
	

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
		
		JLabel btnV = new JLabel("");
		btnV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnV.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Accept btn press.png")));
				TelaInicial inicial = new TelaInicial();
				inicial.setVisible(true);
				dispose();
				}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnV.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Accept btn press.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnV.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Accept btn.png")));
			}
		});
		btnV.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Accept btn.png")));
		btnV.setBounds(377, 335, 69, 71);
		contentPane.add(btnV);
		btnV.setVisible(false);
		lblSuspeito.setBackground(Color.DARK_GRAY);
		lblSuspeito.setForeground(Color.BLACK);
		lblSuspeito.setFont(new Font("Knife Princess", Font.PLAIN, 35));
		lblSuspeito.setBounds(68, 101, 223, 71);
		lblSuspeito.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuspeito.setVisible(false);
		contentPane.add(lblSuspeito);
		
		
		lblPerguntas.setForeground(Color.WHITE);
		lblPerguntas.setFont(new Font("Knife Princess", Font.PLAIN, 35));
		lblPerguntas.setBounds(10, 22, 679, 33);
		lblPerguntas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerguntas.setVisible(false);
		contentPane.add(lblPerguntas);
		
		JLabel btnX = new JLabel("");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnX.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/X btn press.png")));
				btnX.setVisible(false);
				btnV.setVisible(false);
				lblPerguntas.setVisible(false);
				ImgSair.setVisible(false);
				lblPerguntas.setVisible(true);
				lblSuspeito.setVisible(true);
				btnNo.setVisible(true);
				btnNoSei.setVisible(true);
				btnSim.setVisible(true);
				ImgFundo.setVisible(true);
				ImgFrente.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnX.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/X btn press.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnX.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/X btn.png")));
			}
		});
		btnX.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/X btn.png")));
		btnX.setBounds(254, 335, 69, 71);
		contentPane.add(btnX);
		btnX.setVisible(false);
		
		
		ImgSair.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/sair.png")));
		ImgSair.setBounds(137, 113, 424, 270);
		contentPane.add(ImgSair);
		ImgSair.setVisible(false);
		
		
		btnSim.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Easy.png")));
		btnSim.setVisible(false);
		btnSim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSim.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/EasyPress.png")));
				if(contador>jogar2.lista_perguntas.size()-1) {
									
									Comparar comparar = new Comparar();
									PerguntaSuspeitoDAO perguntaSuspeitoDAO = new PerguntaSuspeitoDAO();
									SuspeitoDAO suspeitoDao= new SuspeitoDAO();
									try {
										List<Suspeito> lista_suspeito = suspeitoDao.listarSuspeitos();
										for(int i=0; i <lista_suspeito.size();i++) {
											if(lista_suspeito.get(i).getIDSuspeito()==comparar.Comparar(recebe_respostas)) {
												lblPerguntas.setText("");;
												lblSuspeito.setText(lista_suspeito.get(i).getCaracteristica());
											}
											
										}
										
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				}
				else{
				if(contador<=(jogar2.lista_perguntas.size()-1)) {
				RecebeRespostas recebeRespostas = new RecebeRespostas();
				recebeRespostas.setIDPergunta(jogar2.lista_perguntas.get(contador).getIDPergunta());
				recebe_respostas.add(recebeRespostas);
				contador++;
				if(contador<=jogar2.lista_perguntas.size()-1)
				lblPerguntas.setText(jogar2.lista_perguntas.get(contador).getPergunta());
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSim.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/EasyPress.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSim.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Easy.png")));
			}
		});
		btnSim.setBounds(393, 113, 153, 49);
		contentPane.add(btnSim);
		
		
		btnNoSei.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Medium btn.png")));
		btnNoSei.setVisible(false);
		btnNoSei.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNoSei.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Medium btn Press.png")));
				if(contador>=jogar2.lista_perguntas.size()) {
					lblPerguntas.setText("Voce Venceu");
				}
				else{
				contador++;
				if(contador<=jogar2.lista_perguntas.size()-1)
				lblPerguntas.setText(jogar2.lista_perguntas.get(contador).getPergunta());
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNoSei.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Medium btn Press.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNoSei.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Medium btn.png")));
			}
		});
		btnNoSei.setBounds(393, 213, 153, 49);
		contentPane.add(btnNoSei);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				label.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/BtnVoltarPress.png")));
				btnNo.setVisible(false);
				btnNoSei.setVisible(false);
				btnSim.setVisible(false);
				lblPerguntas.setVisible(false);
				lblSuspeito.setVisible(false);
				ImgFundo.setVisible(false);
				ImgFrente.setVisible(false);
				ImgSair.setVisible(true);
				btnV.setVisible(true);
				btnX.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/BtnVoltarPress.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/BtnVoltar.png")));
			}
		});
		
		JLabel btnIniciar = new JLabel("");
		btnIniciar.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/btnIniciar.png")));
		btnIniciar.addMouseListener(new MouseAdapter() {
			@Override
			//
			public void mouseClicked(MouseEvent e) {
				btnIniciar.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/btnIniciarpress.png")));
				try {
					lista_recebida_bd=perguntaSuspeitoDAO.listarttodossuspeitoseperguntas();
					jogar2.lista_perguntas=dao.PreencherArray();
					btnIniciar.setEnabled(false);
					btnIniciar.setVisible(false);
					lblPerguntas.setVisible(true);
					lblSuspeito.setVisible(true);
					btnNo.setVisible(true);
					btnNoSei.setVisible(true);
					btnSim.setVisible(true);
					ImgFundo.setVisible(true);
					ImgFrente.setVisible(true);
					lblPerguntas.setText(jogar2.lista_perguntas.get(contador).getPergunta());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnIniciar.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/btnIniciarpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnIniciar.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/btnIniciar.png")));;
			}
		});
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNo.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Hard btn Press.png")));
				if(contador>=jogar2.lista_perguntas.size()) {
					lblPerguntas.setText("Voce Venceu");
				}
				else{
				contador++;
				if(contador<=jogar2.lista_perguntas.size()-1)
				lblPerguntas.setText(jogar2.lista_perguntas.get(contador).getPergunta());
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNo.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Hard btn Press.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNo.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Hard btn.png")));
			}
		});
		
		
		btnNo.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Hard btn.png")));
		btnNo.setBounds(393, 334, 153, 49);
		contentPane.add(btnNo);
		btnIniciar.setBounds(262, 313, 171, 49);
		btnNo.setVisible(false);
		contentPane.add(btnIniciar);
		
		
		ImgFrente.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/frenteImagem.png")));
		ImgFrente.setBounds(95, 183, 176, 198);
		contentPane.add(ImgFrente);
		ImgFrente.setVisible(false);
		
		
		ImgFundo.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/fundoImagem.png")));
		ImgFundo.setBounds(34, 87, 289, 324);
		contentPane.add(ImgFundo);
		label.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/BtnVoltar.png")));
		label.setBounds(10, 430, 54, 58);
		contentPane.add(label);
		ImgFundo.setVisible(false);
		
		
		JLabel ImgBorda = new JLabel("");
		ImgBorda.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/Borda.png")));
		ImgBorda.setBounds(0, 0, 699, 499);
		contentPane.add(ImgBorda);
		
		JLabel ImgBackground = new JLabel("");
		ImgBackground.setIcon(new ImageIcon(Jogar.class.getResource("/Imagens/fundo.png")));
		ImgBackground.setBounds(0, 0, 699, 499);
		contentPane.add(ImgBackground);
		setUndecorated(true);
		setLocationRelativeTo(null);


	}
}
