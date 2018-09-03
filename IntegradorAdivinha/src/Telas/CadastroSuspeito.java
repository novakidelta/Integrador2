package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Suspeito;
import DAO.SuspeitoDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroSuspeito {

	private JFrame frame;
	private JTextField textFieldCadastro;
	private static JTable tabela_suspeito;
	
	private static Suspeito suspeito;
	
	
	

	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSuspeito window = new CadastroSuspeito();
					window.frame.setVisible(true);
					
					suspeito.Carregar_TabelaSuspeito(tabela_suspeito);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldCadastro = new JTextField();
		textFieldCadastro.setBounds(43, 32, 243, 20);
		frame.getContentPane().add(textFieldCadastro);
		textFieldCadastro.setColumns(10);
		
		
	   	
		
	
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Suspeito suspeitos = new Suspeito();
				suspeitos.setCaracteristica(textFieldCadastro.getText());
				

				// fazendo a validação dos dados
				if ((textFieldCadastro.getText().isEmpty())) {
				   JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
				}
				else {

				    // instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
				    SuspeitoDAO dao = new SuspeitoDAO();
				    dao.inserir(suspeitos);
				    JOptionPane.showMessageDialog(null, "Usuário "+textFieldCadastro.getText()+" inserido com sucesso! ");
				}

				// apaga os dados preenchidos nos campos de texto
				textFieldCadastro.setText("");

			}
		});
		btnNewButton.setBounds(48, 78, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCadastro.setText("");
			}
		});
		btnNewButton_1.setBounds(197, 78, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
		btnSair.setBounds(118, 125, 89, 23);
		frame.getContentPane().add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 145, 134, 105);
		frame.getContentPane().add(scrollPane);
		
	
		
		
		scrollPane.setViewportView(tabela_suspeito);
	}
}
