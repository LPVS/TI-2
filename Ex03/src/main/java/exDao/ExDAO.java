package exDao;

import java.sql.*;
import exModel.Livro;

public class ExDAO {
	private Connection conexao;
	private int maxId = 0;
	
	public ExDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Exercicio03";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean add(Livro livro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO livro (id, nome, paginas, quantidade) "
					       + "VALUES ("+livro.getId()+ ", '" + livro.getNome() + "', " 
					+ livro.getPaginas() + ", " + livro.getQuant() + ");");
			
			st.close();
			status = true;
			maxId++;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean update(Livro livro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE livro SET nome = '" + livro.getNome() + "', paginas = '" 
					+ livro.getPaginas() + "', quantidade = '" + livro.getQuant() + "'" 
					+ " WHERE id = " + livro.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean remove(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM livro WHERE id = " + id);
			st.close();
			status = true;
			maxId--;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Livro[] getAll() {
		Livro[] Livros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM livro");		
	         if(rs.next()){
	             rs.last();
	             Livros = new Livro[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                Livros[i] = new Livro(rs.getInt("id"),rs.getString("nome"), rs.getInt("paginas"),rs.getInt("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Livros;
	}
	
	public Livro get(int id) {
		Livro[] teste = this.getAll();
		for(int i = 0; i < teste.length; i++) {
			if(teste[i].getId() == id) {
				return teste[i];
			}
		}
		return null;
	}
	
	public int getMaxId(){
		return maxId;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
}