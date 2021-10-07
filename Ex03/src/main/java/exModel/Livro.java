package exModel;

import java.io.Serializable;

public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String nome_PADRAO = "Novo Livro";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String nome;
	private int paginas;
	private int quantidade;
	
	public Livro() {
		id = -1;
		nome = nome_PADRAO;
		paginas = 1;
		quantidade = 0;
	}

	public Livro(int id, String nome, int paginas, int quantidade) {
		setId(id);
		setNome(nome);
		setPaginas(paginas);
		setQuant(quantidade);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() >= 3)
			this.nome = nome;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		if (paginas > 0)
			this.paginas = paginas;
	}

	public int getQuant() {
		return quantidade;
	}
	
	public void setQuant(int quantidade) {
		if (quantidade >= 0 && quantidade <= MAX_ESTOQUE)
			this.quantidade = quantidade;
	}
	

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Categoria: " + nome + "   P�ginas: " + paginas + "   Quant.: " + quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Livro) obj).getId());
	}	
}