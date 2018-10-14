package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.ComandosJtable;
import Classes.Pergunta;
import DAO.PerguntaDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class CadastroPergunta extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCadastroPergunta;
	private JTable pergunta_tabela;
	private Pergunta pergunta = new Pergunta();
	private JButton btnCarregarCadastroPerguntas;

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
		setBounds(100, 100, 284, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldCadastroPergunta = new JTextField();
		textFieldCadastroPergunta.setColumns(10);
		textFieldCadastroPergunta.setBounds(21, 89, 227, 20);
		contentPane.add(textFieldCadastroPergunta);
		
		JButton btnCadastrarPergunta = new JButton("Cadastrar");
		btnCadastrarPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

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
		});
		btnCadastrarPergunta.setBounds(21, 135, 89, 23);
		contentPane.add(btnCadastrarPergunta);
		
		JButton btnLimparCadastroPergunta = new JButton("Limpar");
		btnLimparCadastroPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldCadastroPergunta.setText("");
				
			}
		});
		btnLimparCadastroPergunta.setBounds(159, 135, 89, 23);
		contentPane.add(btnLimparCadastroPergunta);
		
		btnCarregarCadastroPerguntas = new JButton("Carregar");
		btnCarregarCadastroPerguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				try {
					pergunta.Carregar_TabelaPergunta(pergunta_tabela);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnCarregarCadastroPerguntas.setBounds(21, 185, 89, 23);
		contentPane.add(btnCarregarCadastroPerguntas);
		
		JScrollPane scrollPanePergunta = new JScrollPane();
		scrollPanePergunta.setBounds(21, 219, 227, 92);
		contentPane.add(scrollPanePergunta);
		
		pergunta_tabela = new JTable();
		scrollPanePergunta.setViewportView(pergunta_tabela);
		
		JButton btnExcluirPergunta = new JButton("Excluir");
		btnExcluirPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

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
		});
		btnExcluirPergunta.setBounds(159, 185, 89, 23);
		contentPane.add(btnExcluirPergunta);
		
		JButton btnListarPerguntas = new JButton("Listar");
		btnListarPerguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ComandosJtable  cmdtable = new ComandosJtable();
				textFieldCadastroPergunta.setText(cmdtable.PegaRegistroString(pergunta_tabela));
				
//				textFieldCadastroPergunta.setText(String.valueOf(cmdtable.PegaRegistro(pergunta_tabela)));
				
			}
		});
		btnListarPerguntas.setBounds(21, 322, 89, 23);
		contentPane.add(btnListarPerguntas);
		
		JButton btnSalvarAlteradoPergunta = new JButton("Salvar");
		btnSalvarAlteradoPergunta.setBounds(159, 322, 89, 23);
		contentPane.add(btnSalvarAlteradoPergunta);
		
		JLabel lblPergunta = new JLabel("PERGUNTA");
		lblPergunta.setFont(new Font("Knife Princess", Font.BOLD, 30));
		lblPergunta.setBounds(44, 11, 179, 79);
		contentPane.add(lblPergunta);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(89, 363, 89, 23);
		contentPane.add(btnVoltar);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
