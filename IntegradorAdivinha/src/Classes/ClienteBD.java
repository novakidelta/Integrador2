package Conecao_Banco_Posto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Classes.Cliente;
import Classes.Endereco;
import Classes.Pessoa;
import Classes.Veiculo;


public class ClienteBD {
	private Connection con;//obj conecao
	private PreparedStatement stm;
	private ResultSet rs;
	private String Sql;
	
	public void cadastrar(Cliente cliente) throws MyClassException, SQLException{
		MinhaConexao conexao= new MinhaConexao();
		con=conexao.getConexao();
		
		Sql="insert into endereco(codigo_endereco,rua,bairro,numero,cidade,cep,estado)values(?,?,?,?,?,?,?)";
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1, cliente.getPessoa().getEndereco().getId_endereco());
		stm.setString(2, cliente.getPessoa().getEndereco().getRua());
		stm.setString(3, cliente.getPessoa().getEndereco().getBairro());
		stm.setString(4, cliente.getPessoa().getEndereco().getNumero());
		stm.setString(5, cliente.getPessoa().getEndereco().getCidade());
		stm.setString(6, cliente.getPessoa().getEndereco().getCep());
		stm.setString(7, cliente.getPessoa().getEndereco().getEstado());
		
		
		stm.execute();
		stm.close();
		
		Sql="insert into pessoa(codigo_pessoa,codigo_endereco,nome,telefone,dataNascimento,cpf,email,ativo)values(?,?,?,?,?,?,?,?)";
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1, cliente.getPessoa().getId_Pessoa());
		stm.setInt(2, cliente.getPessoa().getEndereco().getId_endereco());
		stm.setString(3, cliente.getPessoa().getNome());
		stm.setString(4, cliente.getPessoa().getTelefone());
		stm.setString(5, cliente.getPessoa().getData_Nascimento());
		stm.setString(6, cliente.getPessoa().getCpf());
		stm.setString(7, cliente.getPessoa().getE_mail());
		stm.setBoolean(8, cliente.getPessoa().getAtivo());//criar esse ativo no banco de dados e referenciar no volues 
					
		stm.execute();
		stm.close();

		Sql="insert into veiculo(codigo_veiculo,tipo,placa,combustivel)values(?,?,?,?)";
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1, cliente.getVeiculo().getId_Veiculo());
		stm.setString(2, cliente.getVeiculo().getTipo());
		stm.setString(3, cliente.getVeiculo().getPlaca());
		stm.setString(4, cliente.getVeiculo().getCombustivel());
		
		stm.execute();
		stm.close();
		
		Sql="insert into cliente values(?,?,?,?)";
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1, cliente.getId_Cliente());
		stm.setInt(2, cliente.getPessoa().getId_Pessoa());
		stm.setInt(3, cliente.getVeiculo().getId_Veiculo());
		//stm.setInt(4, cliente.getPessoa().getEndereco().getId_endereco());
		stm.setInt(4, cliente.getPontos_Fidelidade());
					
		stm.execute();
		stm.close();
		
		con.close();
	}
	public  List <Cliente>  listar() throws MyClassException, SQLException{
		List<Cliente> lista_Cliente= new ArrayList<>();
		MinhaConexao conexao= new MinhaConexao();
		con = conexao.getConexao();
		
		
		Sql="select * from cliente inner join pessoa on (cliente.codigo_pessoa=pessoa.codigo_pessoa) "
				+ "inner join  veiculo on (cliente.codigo_veiculo= veiculo.codigo_veiculo)"
				+ "inner join endereco on(pessoa.codigo_endereco= endereco.codigo_endereco)";//pega somente os clientes ativos onde ativo é true quando é excluido o ativo vira false e a tabela não busca
		
		stm= con.prepareStatement(Sql);
		rs=stm.executeQuery();
		while(rs.next()){			
			Cliente cliente =new Cliente();
			Endereco endereco = new Endereco();
			Pessoa pessoa= new Pessoa();
			Veiculo veiculo= new Veiculo();
			
			veiculo.setCombustivel(rs.getString(("combustivel")));
			veiculo.setId_Veiculo(rs.getInt(("codigo_veiculo")));
			veiculo.setPlaca(rs.getString(("placa")));
			veiculo.setTipo(rs.getString(("tipo")));
			
			endereco.setBairro(rs.getString(("bairro")));					
			endereco.setRua(rs.getString(("rua")));
			endereco.setNumero(rs.getString(("numero")));
			endereco.setCidade(rs.getString(("cidade")));
			endereco.setCep(rs.getString(("cep")));
			endereco.setEstado(rs.getString(("estado")));
			
			cliente.setId_Cliente(rs.getInt("codigo_pessoa"));
			endereco.setId_endereco(rs.getInt("codigo_endereco"));
			
			pessoa.setNome(rs.getString("nome"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setData_Nascimento(rs.getString(("dataNascimento")));
			pessoa.setCpf(rs.getString("cpf"));	
			pessoa.setE_mail((rs.getString("email")));
			pessoa.setAtivo(rs.getBoolean("ativo"));
			
			cliente.setPontos_Fidelidade(rs.getInt("pontosFidelidade"));
			
			cliente.setEndereco(endereco);
			cliente.setPessoa(pessoa);
			cliente.setVeiculo(veiculo);
			
			lista_Cliente.add(cliente);
			
			
			Cliente.getCliente().getLista_Cliente().add(cliente);//vai carreegar no array Estaticos todos os clientes do banco
		}
		con.close();
		stm.close();
		rs.close();
		
		return lista_Cliente;
	}

	public void excluir(int id_Cliente) throws MyClassException, SQLException{
		MinhaConexao conexao= new MinhaConexao();
		con= conexao.getConexao();
		Sql="update pessoa set ativo=0 where codigo_pessoa=?;";
		stm=con.prepareStatement(Sql);
		stm.setInt(1, id_Cliente);
		
		stm.execute();
		stm.close();
		con.close();	
	}
	public void alterar(int id_Alterar, Cliente c1) throws MyClassException, SQLException{
		MinhaConexao conexao= new MinhaConexao();
		con=conexao.getConexao();
		
		Sql="UPDATE endereco SET codigo_endereco=?,rua=?,bairro=?,numero=?,cidade=?,cep=?,estado=? WHERE codigo_endereco=?";
		stm= con.prepareStatement(Sql);
		stm.setInt(1, c1.getPessoa().getEndereco().getId_endereco());
		
		stm.setString(2, c1.getPessoa().getEndereco().getRua());
		stm.setString(3, c1.getPessoa().getEndereco().getBairro());
		stm.setString(4, c1.getPessoa().getEndereco().getNumero());
		stm.setString(5, c1.getPessoa().getEndereco().getCidade());
		stm.setString(6, c1.getPessoa().getEndereco().getCep());
		stm.setString(7, c1.getPessoa().getEndereco().getEstado());
		stm.setInt(8, id_Alterar);
		System.out.println(id_Alterar);
		
		stm.execute();
		stm.close();
		con.close();
		Sql="UPDATE pessoa SET codigo_pessoa=?,codigo_endereco=?,nome=?,telefone=?,dataNascimento=?,cpf=?,email=?,ativo=? WHERE codigo_pessoa=?";
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1, c1.getPessoa().getId_Pessoa());
		stm.setInt(2, c1.getPessoa().getEndereco().getId_endereco());
		stm.setString(3, c1.getPessoa().getNome());
		stm.setString(4, c1.getPessoa().getTelefone());
		stm.setString(5, c1.getPessoa().getData_Nascimento());
		stm.setString(6, c1.getPessoa().getCpf());
		stm.setString(7, c1.getPessoa().getE_mail());
		stm.setBoolean(8, c1.getPessoa().getAtivo());//criar esse ativo no banco de dados e referenciar no volues 
		stm.setInt(9, id_Alterar);			
		
		stm.execute();
		stm.close();

		Sql="UPDATE veiculo SET codigo_veiculo=?,tipo=?,placa=?,combustivel=? WHERE codigo_veiculo=?";
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1,c1.getVeiculo().getId_Veiculo());
		stm.setString(2, c1.getVeiculo().getTipo());
		stm.setString(3, c1.getVeiculo().getPlaca());
		stm.setString(4, c1.getVeiculo().getCombustivel());
		stm.setInt(5, id_Alterar);
		
		stm.execute();
		stm.close();
		
		Sql=" UPDATE cliente SET codigo_cliente=?,codigo_pessoa=?,codigo_veiculo=?,pontosFidelidade=? WHERE codigo_cliente=?";
		
		
		stm= con.prepareStatement(Sql);
		stm.setInt(1, c1.getId_Cliente());
		stm.setInt(2, c1.getPessoa().getId_Pessoa());
		stm.setInt(3, c1.getVeiculo().getId_Veiculo());
		//stm.setInt(4, cliente.getPessoa().getEndereco().getId_endereco());
		stm.setInt(4, c1.getPontos_Fidelidade());
		stm.setInt(5, id_Alterar);
		
		
	
		stm.execute();
		stm.close();
		
		con.close();
	}
}
