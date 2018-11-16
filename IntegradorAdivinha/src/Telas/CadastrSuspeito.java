package Telas;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Classes.ComandosJtable;
import Classes.Pergunta;
import Classes.PerguntaSuspeito;
import Classes.Suspeito;
import DAO.PerguntaDAO;
import DAO.PerguntaSuspeitoDAO;
import DAO.SuspeitoDAO;
import Listagem.PerguntaID;
import modelos.ButtonColumn;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class CadastrSuspeito extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCadastroSuspeito;
	private JTable suspeito_tabela;
	private  Suspeito suspeito = new Suspeito();
	private Pergunta  pergunta = new Pergunta();
	public  JTable pergunta_tabela;

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
		setBounds(100, 100, 699, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JLabel BtnVoltar = new JLabel("");
		BtnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
		});
		setUndecorated(true);
		BtnVoltar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnVoltar.png")));
		BtnVoltar.setBounds(10, 430, 54, 58);
		contentPane.add(BtnVoltar);
		
		textFieldCadastroSuspeito = new JTextField();
		textFieldCadastroSuspeito.setBackground(Color.GRAY);
		textFieldCadastroSuspeito.setColumns(10);
		textFieldCadastroSuspeito.setBounds(132, 51, 192, 20);
		contentPane.add(textFieldCadastroSuspeito);
		
		JScrollPane scrollPaneCadastro = new JScrollPane();
		scrollPaneCadastro.setBounds(37, 228, 290, 215);
		contentPane.add(scrollPaneCadastro);
		
		suspeito_tabela = new JTable();
		suspeito_tabela.setForeground(new Color(255, 255, 255));
		suspeito_tabela.setBackground(new Color(0, 128, 0));
		suspeito_tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int Indicelinha = suspeito_tabela.getSelectedRow();
				textFieldCadastroSuspeito.setText(suspeito_tabela.getValueAt(Indicelinha, 0).toString());
			}
		});
		scrollPaneCadastro.setViewportView(suspeito_tabela);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 28, 296, 430);
		contentPane.add(scrollPane);
		
		pergunta_tabela = new JTable();
		pergunta_tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		pergunta_tabela.setBackground(new Color(0, 128, 0));
		pergunta_tabela.setForeground(new Color(255, 255, 255));
		scrollPane.setViewportView(pergunta_tabela);
		
		JLabel BtnCadastrar = new JLabel("");
		BtnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				

				
				Suspeito suspeitos = new Suspeito();
				PerguntaSuspeito perguntaSuspeito = new PerguntaSuspeito();
				suspeitos.setNome(textFieldCadastroSuspeito.getText());
				
				

				
				if ((textFieldCadastroSuspeito.getText().isEmpty())) {
				   JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
				}
				else {
				    SuspeitoDAO dao = new SuspeitoDAO();
				    PerguntaSuspeitoDAO dao2 = new PerguntaSuspeitoDAO();
				    
				   // dao2.inserirPerguntaeSuspeito(perguntaSuspeito);
				    dao.inserirsuspeito(suspeitos);			    
				    
				    try {
						suspeito.Carregar_TabelaSuspeito(suspeito_tabela);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   // JOptionPane.showMessageDialog(null, "Usuário "+textFieldCadastroSuspeito.getText()+" inserido com sucesso! ");
				}

				textFieldCadastroSuspeito.setText("");
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnCadastrar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnCadastrarSpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnCadastrar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnCadastrarNpress.png")));
			}
		});
		BtnCadastrar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnCadastrarNpress.png")));
		BtnCadastrar.setBounds(31, 108, 98, 33);
		contentPane.add(BtnCadastrar);
		
		JLabel BtnAlterar = new JLabel("");
		BtnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnAlterar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnAlterarSpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnAlterar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnAlterarNpress.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				PerguntaSuspeito perguntaSuspeito = new PerguntaSuspeito();
				Pergunta pergunta =  new Pergunta();
				
				PerguntaDAO perguntaDao = new PerguntaDAO();
				PerguntaSuspeitoDAO perguntaSuspeitoDAO = new PerguntaSuspeitoDAO();
				
				int linha = suspeito_tabela.getSelectedRow();
	            int id = (int) suspeito_tabela.getValueAt(linha, 0);
	            try {
	            	
	            	
	            	List<PerguntaID> listaParaAtualizar = new ArrayList();
	            	List<PerguntaID> listaComNovosDados = new ArrayList();
	            	
	            	listaParaAtualizar.clear();
	            	listaComNovosDados.clear();
	            	
	            	listaParaAtualizar.addAll(perguntaDao.listarIDperguntas());
	            	listaComNovosDados.addAll(perguntaSuspeitoDAO.listartFKIDPerguntas(id));
	            	
	            	//listaComNovosDados.retainAll(listaParaAtualizar);
	            	listaParaAtualizar.removeAll(listaComNovosDados);
	            	listaParaAtualizar.addAll(listaComNovosDados);
	            	
	            	int atualizar = listaParaAtualizar.size();
	            	int listanova = listaComNovosDados.size();
	            	System.out.println(atualizar);
	            	
	            	
	            /*	for(int j=0;j<atualizar;j++){
	            		for(int i=0;i<listanova;i++) {
	            			if(listaParaAtualizar.get(j).getIDPergunta()==listaComNovosDados.get(i).getIDPergunta()) {
	            				listaParaAtualizar.remove(listaParaAtualizar.get(j));
	            				listaParaAtualizar.addAll(listaComNovosDados);
	            			}
	            			
	            		}
	            	}*/
	            	
	            	

	            	perguntaSuspeito.Carregar_TabelaPerguntaID(pergunta_tabela, listaParaAtualizar);
	            	pergunta_tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
	            	pergunta_tabela.getColumnModel().getColumn(1).setPreferredWidth(110);
	            	pergunta_tabela.getColumnModel().getColumn(2).setPreferredWidth(70);
					ButtonColumn buttonColumn = new ButtonColumn(pergunta_tabela, suspeito_tabela, 2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		BtnAlterar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnAlterarNpress.png")));
		BtnAlterar.setBounds(31, 172, 98, 33);
		contentPane.add(BtnAlterar);
		
		JLabel BtnExcluir = new JLabel("");
		BtnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnExcluir.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnExcluirSpress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnExcluir.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnExcluirNpress.png")));
			}
		});
		BtnExcluir.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnExcluirNpress.png")));
		BtnExcluir.setBounds(229, 172, 98, 33);
		contentPane.add(BtnExcluir);
		
		JLabel BtnCarregar = new JLabel("");
		BtnCarregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					suspeito.Carregar_TabelaSuspeito(suspeito_tabela);					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BtnCarregar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnCarregarPress.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BtnCarregar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnCarregarNPress.png")));
			}
		});
		BtnCarregar.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/BtnCarregarNPress.png")));
		BtnCarregar.setBounds(229, 108, 98, 33);
		contentPane.add(BtnCarregar);
		
		JLabel Nome = new JLabel("");
		Nome.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/Nome.png")));
		Nome.setBounds(37, 39, 296, 42);
		contentPane.add(Nome);
		
		JLabel ImgTelaverde = new JLabel("");
		ImgTelaverde.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/telaverdemedia.png")));
		ImgTelaverde.setBounds(29, 216, 307, 237);
		contentPane.add(ImgTelaverde);
		
		JLabel ImgBase = new JLabel("");
		ImgBase.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/base.png")));
		ImgBase.setBounds(-3, 17, 374, 453);
		contentPane.add(ImgBase);
		
		JLabel ImgTela = new JLabel("");
		ImgTela.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/telaverde.png")));
		ImgTela.setBounds(362, 17, 316, 453);
		contentPane.add(ImgTela);
		
		JLabel ImgMoldura = new JLabel("");
		ImgMoldura.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/Borda.png")));
		ImgMoldura.setBounds(0, 0, 700, 500);
		contentPane.add(ImgMoldura);
		
		JLabel ImgFundo = new JLabel("");
		ImgFundo.setIcon(new ImageIcon(CadastrSuspeito.class.getResource("/Imagens/fundo madeira.png")));
		ImgFundo.setBounds(0, 0, 700, 500);
		contentPane.add(ImgFundo);
		
		
		
	}

	
}
