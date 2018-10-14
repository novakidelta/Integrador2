package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnJogar = new JButton("JOGAR");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnJogar.setBounds(277, 115, 89, 23);
		contentPane.add(btnJogar);
		
		JButton btnSuspeito = new JButton("SUSPEITO");
		btnSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrSuspeito cadastrSuspeito = new CadastrSuspeito();
				cadastrSuspeito.setVisible(true);
				dispose();
				
			}
		});
		btnSuspeito.setBounds(57, 57, 89, 23);
		contentPane.add(btnSuspeito);
		
		JButton btnPergunta = new JButton("PERGUNTA");
		btnPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroPergunta cadastroPergunta = new CadastroPergunta();
				cadastroPergunta.setVisible(true);
				dispose();
			}
		});
		btnPergunta.setBounds(57, 145, 89, 23);
		contentPane.add(btnPergunta);
		
		JLabel lblJogar = new JLabel("JOGAR");
		lblJogar.setBounds(292, 47, 46, 14);
		contentPane.add(lblJogar);
	}

}
