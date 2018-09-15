package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.ComandosJtable;
import Classes.Pergunta;
import Classes.Suspeito;
import DAO.PerguntaDAO;
import DAO.SuspeitoDAO;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroSuspeito {

	private JFrame frame;
	private JTextField textFieldCadastroSuspeito;

	
	private  Suspeito suspeito = new Suspeito();
	private Pergunta pergunta = new Pergunta();
	private JTable suspeito_tabela;
	private JTextField textFieldCadastroPergunta;
	private JTable pergunta_tabela;
	
	
	

	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSuspeito window = new CadastroSuspeito();
					window.frame.setVisible(true);
					
					
								} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public CadastroSuspeito() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		suspeito_tabela = new JTable();
		frame = new JFrame();
		frame.setBounds(100, 100, 588, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldCadastroSuspeito = new JTextField();
		textFieldCadastroSuspeito.setBounds(26, 35, 243, 20);
		frame.getContentPane().add(textFieldCadastroSuspeito);
		textFieldCadastroSuspeito.setColumns(10);
		
		
	   	
		
	
		
		JButton btnCadastrarSuspeito = new JButton("Cadastrar");
		btnCadastrarSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Suspeito suspeitos = new Suspeito();
				suspeitos.setCaracteristica(textFieldCadastroSuspeito.getText());
				

				// fazendo a validação dos dados
				if ((textFieldCadastroSuspeito.getText().isEmpty())) {
				   JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
				}
				else {

				    // instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
				    SuspeitoDAO dao = new SuspeitoDAO();
				    dao.inserirsuspeito(suspeitos);
				    try {
						suspeito.Carregar_TabelaSuspeito(suspeito_tabela);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    JOptionPane.showMessageDialog(null, "Usuário "+textFieldCadastroSuspeito.getText()+" inserido com sucesso! ");
				}

				// apaga os dados preenchidos nos campos de texto
				textFieldCadastroSuspeito.setText("");

			}
		});
		btnCadastrarSuspeito.setBounds(31, 81, 89, 23);
		frame.getContentPane().add(btnCadastrarSuspeito);
		
		JButton btnLimparCadastroSuspeito = new JButton("Limpar");
		btnLimparCadastroSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCadastroSuspeito.setText("");
			}
		});
		btnLimparCadastroSuspeito.setBounds(180, 81, 89, 23);
		frame.getContentPane().add(btnLimparCadastroSuspeito);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
		btnSair.setBounds(229, 320, 89, 23);
		frame.getContentPane().add(btnSair);
		
		JScrollPane scrollPaneCadastro = new JScrollPane();
		
		JButton btnCarregarCadastro = new JButton("carregar");
		btnCarregarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					suspeito.Carregar_TabelaSuspeito(suspeito_tabela);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCarregarCadastro.setBounds(31, 131, 89, 23);
		frame.getContentPane().add(btnCarregarCadastro);
		
		JButton btnExcluirSuspeito = new JButton("Excluir");
		btnExcluirSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuspeitoDAO dao = new SuspeitoDAO();
				try {
					ComandosJtable  cmdtable = new ComandosJtable();
					dao.excluirSuspeito(cmdtable.PegaRegistroString(suspeito_tabela));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExcluirSuspeito.setBounds(180, 131, 89, 23);
		frame.getContentPane().add(btnExcluirSuspeito);
		
		JButton btnAlterarSuspeito = new JButton("Listar");
		btnAlterarSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ComandosJtable  cmdtable = new ComandosJtable();
				textFieldCadastroSuspeito.setText(String.valueOf(cmdtable.PegaRegistro(suspeito_tabela)));
			}
		});
		btnAlterarSuspeito.setBounds(31, 268, 89, 23);
		frame.getContentPane().add(btnAlterarSuspeito);
		
	
		scrollPaneCadastro.setBounds(36, 165, 233, 92);
		frame.getContentPane().add(scrollPaneCadastro);
		
		
		scrollPaneCadastro.setViewportView(suspeito_tabela);
		
		textFieldCadastroPergunta = new JTextField();
		textFieldCadastroPergunta.setBounds(318, 35, 227, 20);
		frame.getContentPane().add(textFieldCadastroPergunta);
		textFieldCadastroPergunta.setColumns(10);
		
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
				    JOptionPane.showMessageDialog(null, "Usuário "+textFieldCadastroPergunta.getText()+" inserido com sucesso! ");
				}

				// apaga os dados preenchidos nos campos de texto
				textFieldCadastroPergunta.setText("");
			}
		});
		btnCadastrarPergunta.setBounds(318, 81, 89, 23);
		frame.getContentPane().add(btnCadastrarPergunta);
		
		JButton btnLimparCadastroPergunta = new JButton("Limpar");
		btnLimparCadastroPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCadastroPergunta.setText("");
			}
		});
		btnLimparCadastroPergunta.setBounds(456, 81, 89, 23);
		frame.getContentPane().add(btnLimparCadastroPergunta);
		
		JButton btnCarregarCadastroPerguntas = new JButton("Carregar");
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
		btnCarregarCadastroPerguntas.setBounds(318, 131, 89, 23);
		frame.getContentPane().add(btnCarregarCadastroPerguntas);
		
		JScrollPane scrollPanePergunta = new JScrollPane();
		scrollPanePergunta.setBounds(318, 165, 227, 92);
		frame.getContentPane().add(scrollPanePergunta);
		
		pergunta_tabela = new JTable();
		scrollPanePergunta.setViewportView(pergunta_tabela);
		
		JButton btnExcluirPergunta = new JButton("Excluir");
		btnExcluirPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerguntaDAO dao = new PerguntaDAO();
				try {
					ComandosJtable  cmdtable = new ComandosJtable();
					dao.excluirPergunta(cmdtable.PegaRegistroString(pergunta_tabela));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExcluirPergunta.setBounds(456, 131, 89, 23);
		frame.getContentPane().add(btnExcluirPergunta);
		
		JButton btnSalvarAlteradoSuspeito = new JButton("Salvar");
		btnSalvarAlteradoSuspeito.setBounds(180, 268, 89, 23);
		frame.getContentPane().add(btnSalvarAlteradoSuspeito);
		
		JButton btnListarPerguntas = new JButton("Listar");
		btnListarPerguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnListarPerguntas.setBounds(318, 268, 89, 23);
		frame.getContentPane().add(btnListarPerguntas);
		
		JButton btnSalvarAlteradoPergunta = new JButton("Salvar");
		btnSalvarAlteradoPergunta.setBounds(456, 268, 89, 23);
		frame.getContentPane().add(btnSalvarAlteradoPergunta);
		
	}
}
