package Classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Conecao_Banco_Posto.ClienteBD;


public class Cliente  {
	private int Id_Cliente;
	private int Pontos_Fidelidade;
	private static  Cliente cliente = new Cliente();
	
	
	
	private Veiculo veiculo;
	private Endereco Endereco;
	private Pessoa pessoa;
	private static ArrayList<Cliente>lista_Cliente= new ArrayList<>();
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return Endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.Endereco = endereco;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public int getId_Cliente() {
		return Id_Cliente;
	}

	public void setId_Cliente(int id_Cliente) {
		Id_Cliente = id_Cliente;
	}

	public int getPontos_Fidelidade() {
		return Pontos_Fidelidade;
	}

	public void setPontos_Fidelidade(int pontos_Fidelidade) {
		Pontos_Fidelidade = pontos_Fidelidade;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		Cliente.cliente = cliente;
	}

	public static ArrayList<Cliente> getLista_Cliente() {
		return lista_Cliente;
	}

	public static void setLista_Cliente(ArrayList<Cliente> lista_Cliente) {
		Cliente.lista_Cliente = lista_Cliente;
	}
	public void Carregar_Tabela_cliente(JTable tabela_cliente) throws Conecao_Banco_Posto.MyClassException, SQLException{
		DefaultTableModel defaultTableModel_cliente= new DefaultTableModel();
		
		defaultTableModel_cliente.addColumn("ID_Cliente");
		defaultTableModel_cliente.addColumn("Nome");
		
		ClienteBD clienteBD= new ClienteBD();
			List<Cliente> lista_clientes= clienteBD.listar();
			
			for(Cliente cliente : lista_clientes){///Mudei aqui para não dar problemas com o idduplicado
				if(cliente.getPessoa().getAtivo()==true){
				defaultTableModel_cliente.addRow(new Object[]{
						cliente.getId_Cliente(),
						cliente.getPessoa().getNome()
				});
			}
		}
			tabela_cliente.setModel(defaultTableModel_cliente);
		}
	public void Carregar_Tabela_Aniversario(JTable tabela_cliente) throws Conecao_Banco_Posto.MyClassException, SQLException{
		DefaultTableModel defaultTableModel_cliente= new DefaultTableModel();
		
		
		defaultTableModel_cliente.addColumn("Nome");
		defaultTableModel_cliente.addColumn("Data Aniversario (Mês/Dia)");
	
		
		ClienteBD clienteBD= new ClienteBD();
		
			List<Cliente> lista_clientes= clienteBD.listar();
			
			for(Cliente cliente : lista_clientes){///Mudei aqui para não dar problemas com o idduplicado
				if(cliente.getPessoa().getAtivo()==true){
				
				defaultTableModel_cliente.addRow(new Object[]{
						cliente.getPessoa().getNome().toUpperCase(),
						cliente.getPessoa().getData_Nascimento().substring(5).toUpperCase(),
				});
			}
		}
			tabela_cliente.setModel(defaultTableModel_cliente);
		}
	
	public boolean validaCPF(String cpf){//mechii aquiiii
		for(int i=0; i<Cliente.getCliente().getLista_Cliente().size(); i++){
			if(Cliente.getLista_Cliente().get(i).getPessoa().getCpf().equals(cpf)){
				return true;
			}
		}
		return false;
	}
	}
	