package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.ComandosJtable;
import Classes.Pergunta;
import Classes.Suspeito;
import DAO.PerguntaSuspeitoDAO;
import DAO.SuspeitoDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastrSuspeito extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCadastroSuspeito;
	private JTable suspeito_tabela;
	private  Suspeito suspeito = new Suspeito();
	private Pergunta  pergunta = new Pergunta();
	private JTable pergunta_tabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrSuspeito frame = new CadastrSuspeito();
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
	public CadastrSuspeito() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldCadastroSuspeito = new JTextField();
		textFieldCadastroSuspeito.setColumns(10);
		textFieldCadastroSuspeito.setBounds(21, 89, 243, 20);
		contentPane.add(textFieldCadastroSuspeito);
		
		JButton btnCadastrarSuspeito = new JButton("Cadastrar");
		btnCadastrarSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Suspeito suspeitos = new Suspeito();
				suspeitos.setNome(textFieldCadastroSuspeito.getText());
				

				
				if ((textFieldCadastroSuspeito.getText().isEmpty())) {
				   JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
				}
				else {
					
					int FK_pergunta = 0;
					int FK_suspeito = 0;
					String FK_perguntaS;
					
					
					
				    
				    SuspeitoDAO dao = new SuspeitoDAO();
				    PerguntaSuspeitoDAO daoPS = new PerguntaSuspeitoDAO();
				    dao.inserirsuspeito(suspeitos);
				    
				    ComandosJtable  cmdtable = new ComandosJtable();
				   // FK_perguntaS=textFieldCadastroSuspeito.setText(cmdtable.PegaRegistro(pergunta_tabela));
				    
				    FK_suspeito=suspeitos.getIDSuspeito();
				    
				    daoPS.inserirFK(FK_pergunta, FK_suspeito);
				    
				    try {
						suspeito.Carregar_TabelaSuspeito(suspeito_tabela);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   // JOptionPane.showMessageDialog(null, "Usuário "+textFieldCadastroSuspeito.getText()+" inserido com sucesso! ");
				}

				// apaga os dados preenchidos nos campos de texto
				textFieldCadastroSuspeito.setText("");
				
			}
		});
		btnCadastrarSuspeito.setBounds(24, 135, 89, 23);
		contentPane.add(btnCadastrarSuspeito);
		
		JButton btnLimparCadastroSuspeito = new JButton("Limpar");
		btnLimparCadastroSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCadastroSuspeito.setText("");
			}
		});
		btnLimparCadastroSuspeito.setBounds(173, 135, 89, 23);
		contentPane.add(btnLimparCadastroSuspeito);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(98, 356, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnCarregarCadastro = new JButton("carregar");
		btnCarregarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					suspeito.Carregar_TabelaSuspeito(suspeito_tabela);
					pergunta.Carregar_TabelaPergunta(pergunta_tabela);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCarregarCadastro.setBounds(24, 185, 89, 23);
		contentPane.add(btnCarregarCadastro);
		
		JButton btnExcluirSuspeito = new JButton("Excluir");
		btnExcluirSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuspeitoDAO dao = new SuspeitoDAO();
				try {
					ComandosJtable  cmdtable = new ComandosJtable();
					dao.excluirSuspeito(cmdtable.PegaRegistro(suspeito_tabela));
					suspeito.Carregar_TabelaSuspeito(suspeito_tabela);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExcluirSuspeito.setBounds(173, 185, 89, 23);
		contentPane.add(btnExcluirSuspeito);
		
		JButton btnAlterarSuspeito = new JButton("Listar");
		btnAlterarSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ComandosJtable  cmdtable = new ComandosJtable();
				textFieldCadastroSuspeito.setText(cmdtable.PegaRegistroString(suspeito_tabela));
			}
		});
		btnAlterarSuspeito.setBounds(24, 322, 89, 23);
		contentPane.add(btnAlterarSuspeito);
		
		JScrollPane scrollPaneCadastro = new JScrollPane();
		scrollPaneCadastro.setBounds(26, 219, 233, 92);
		contentPane.add(scrollPaneCadastro);
		
		suspeito_tabela = new JTable();
		suspeito_tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int Indicelinha = suspeito_tabela.getSelectedRow();
				textFieldCadastroSuspeito.setText(suspeito_tabela.getValueAt(Indicelinha, 0).toString());
			}
		});
		scrollPaneCadastro.setViewportView(suspeito_tabela);
		
		JButton btnSalvarAlteradoSuspeito = new JButton("Salvar");
		btnSalvarAlteradoSuspeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSalvarAlteradoSuspeito.setBounds(173, 322, 89, 23);
		contentPane.add(btnSalvarAlteradoSuspeito);
		
		JLabel lblSuspeito = new JLabel("SUSPEITO");
		lblSuspeito.setFont(new Font("Knife Princess", Font.BOLD, 30));
		lblSuspeito.setBounds(60, 9, 171, 79);
		contentPane.add(lblSuspeito);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 89, 192, 259);
		contentPane.add(scrollPane);
		
		pergunta_tabela = new JTable();
		scrollPane.setViewportView(pergunta_tabela);
		
	}
}
