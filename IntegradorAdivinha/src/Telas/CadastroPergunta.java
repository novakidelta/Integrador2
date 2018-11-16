package Telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Classes.ComandosJtable;
import Classes.Pergunta;
import DAO.PerguntaDAO;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroPergunta extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCadastroPergunta;
	private JTable pergunta_tabela;
	private Pergunta pergunta = new Pergunta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPergunta frame = new CadastroPergunta();
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
	public CadastroPergunta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		textFieldCadastroPergunta = new JTextField();
		textFieldCadastroPergunta.setBackground(Color.GRAY);
		textFieldCadastroPergunta.setColumns(10);
		textFieldCadastroPergunta.setBounds(133, 162, 188, 20);
		contentPane.add(textFieldCadastroPergunta);
		
		JScrollPane scrollPanePergunta = new JScrollPane();
		scrollPanePergunta.setBounds(374, 50, 287, 410);
		contentPane.add(scrollPanePergunta);
		
		pergunta_tabela = new JTable();
		pergunta_tabela.setBorder(null);
		pergunta_tabela.setForeground(Color.WHITE);
		pergunta_tabela.setBackground(new Color(0, 128, 0));
		scrollPanePergunta.setViewportView(pergunta_tabela);
		
		JLabel ImgPergunta = new JLabel("");
		ImgPergunta.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/Pergunta.png")));
		ImgPergunta.setBounds(38, 151, 296, 42);
		contentPane.add(ImgPergunta);
		
		JLabel BtnCadastrar = new JLabel("");
		BtnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Pergunta pergunta  = new Pergunta();
				pergunta.setPergunta(textFieldCadastroPergunta.getText());
				

				// fazendo a validação dos dados
				if ((textFieldCadastroPergunta.getText().isEmpty())) {
				   JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
				}
				else {

				    // instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
				    PerguntaDAO dao = new PerguntaDAO();
				    dao.inserirpergunta(pergunta);
				    try {
						pergunta.Carregar_TabelaPergunta(pergunta_tabela);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    //JOptionPane.showMessageDialog(null, "Usuário "+textFieldCadastroPergunta.getText()+" inserido com sucesso! ");
				}

				// apaga os dados preenchidos nos campos de texto
				textFieldCadastroPergunta.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnCadastrar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnCadastrarSpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnCadastrar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnCadastrarNpress.png")));
			}
		});
		BtnCadastrar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnCadastrarNpress.png")));
		BtnCadastrar.setBounds(38, 216, 98, 33);
		contentPane.add(BtnCadastrar);
		
		JLabel BtnAlterar = new JLabel("");
		BtnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnAlterar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnAlterarSpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnAlterar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnAlterarNpress.png")));
			}
		});
		BtnAlterar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnAlterarNpress.png")));
		BtnAlterar.setBounds(38, 280, 98, 33);
		contentPane.add(BtnAlterar);
		
		JLabel BtnExcluir = new JLabel("");
		BtnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PerguntaDAO dao = new PerguntaDAO();
				try {
					ComandosJtable  cmdtable = new ComandosJtable();
					dao.ExcluirPergunta(cmdtable.PegaRegistro(pergunta_tabela));
					pergunta.Carregar_TabelaPergunta(pergunta_tabela);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnExcluir.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnExcluirSpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnExcluir.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnExcluirNpress.png")));
			}
		});
		BtnExcluir.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnExcluirNpress.png")));
		BtnExcluir.setBounds(236, 280, 98, 33);
		contentPane.add(BtnExcluir);
		
		JLabel BtnVoltar = new JLabel("");
		BtnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnVoltar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnVoltarPress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnVoltar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnVoltar.png")));
			}
		});
		BtnVoltar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnVoltar.png")));
		BtnVoltar.setBounds(28, 411, 54, 58);
		contentPane.add(BtnVoltar);
		
		JLabel BtnCarregar = new JLabel("");
		BtnCarregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					pergunta.Carregar_TabelaPergunta(pergunta_tabela);
	            	pergunta_tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
	            	pergunta_tabela.getColumnModel().getColumn(1).setPreferredWidth(180);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnCarregar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnCarregarPress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnCarregar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnCarregarNPress.png")));
			}
		});
		BtnCarregar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/BtnCarregarNPress.png")));
		BtnCarregar.setBounds(236, 216, 98, 33);
		contentPane.add(BtnCarregar);
		
		JLabel ImgGerenciar = new JLabel("");
		ImgGerenciar.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/gerenciar.png")));
		ImgGerenciar.setBounds(28, 37, 306, 113);
		contentPane.add(ImgGerenciar);
		
		JLabel FindTheSuspect = new JLabel("");
		FindTheSuspect.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/Find the Suspect menor.png")));
		FindTheSuspect.setBounds(118, 341, 203, 119);
		contentPane.add(FindTheSuspect);
		
		JLabel ImgBase = new JLabel("");
		ImgBase.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/base.png")));
		ImgBase.setBounds(0, 23, 374, 453);
		contentPane.add(ImgBase);
		
		JLabel ImgBorda = new JLabel("");
		ImgBorda.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/Borda.png")));
		ImgBorda.setBounds(0, 0, 700, 500);
		contentPane.add(ImgBorda);
		
		JLabel ImgTelaverde = new JLabel("");
		ImgTelaverde.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/telaverde.png")));
		ImgTelaverde.setBounds(360, 23, 316, 453);
		contentPane.add(ImgTelaverde);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon(CadastroPergunta.class.getResource("/Imagens/fundo madeira.png")));
		Background.setBounds(0, 0, 700, 500);
		contentPane.add(Background);
	}
}
