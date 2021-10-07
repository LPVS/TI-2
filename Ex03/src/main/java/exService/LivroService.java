package exService;

import exDao.ExDAO;
import exModel.Livro;
import spark.Request;
import spark.Response;


public class LivroService {

	private ExDAO testeDAO;

	public LivroService() {
		testeDAO = new ExDAO();
		testeDAO.conectar();
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		int paginas = Integer.parseInt(request.queryParams("paginas"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		int id = testeDAO.getMaxId() + 1;

		Livro teste = new Livro(id, nome, paginas, quantidade);
		testeDAO.add(teste);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Livro teste = (Livro) testeDAO.get(id);
		
		if (teste != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<livro>\n" + 
            		"\t<id>" + teste.getId() + "</id>\n" +
            		"\t<nome>" + teste.getNome() + "</nome>\n" +
            		"\t<paginas>" + teste.getPaginas() + "</paginas>\n" +
            		"\t<quantidade>" + teste.getQuant() + "</quantidade>\n" +
            		"</livro>\n";
        } else {
            response.status(404); // 404 Not found
            return "Livro " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Livro teste = (Livro) testeDAO.get(id);

        if (teste != null) {
        	teste.setNome(request.queryParams("nome"));
        	teste.setPaginas(Integer.parseInt(request.queryParams("paginas")));
        	teste.setQuant(Integer.parseInt(request.queryParams("quantidade")));

        	testeDAO.update(teste);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Livro não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Livro teste = (Livro) testeDAO.get(id);

        if (teste != null) {

            testeDAO.remove(id);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Livro não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<testes type=\"array\">");
		for (Livro teste : testeDAO.getAll()) {
			returnValue.append("\n<livro>\n" + 
            		"\t<id>" + teste.getId() + "</id>\n" +
            		"\t<nome>" + teste.getNome() + "</nome>\n" +
            		"\t<paginas>" + teste.getPaginas() + "</paginas>\n" +
            		"\t<quantidade>" + teste.getQuant() + "</quantidade>\n" +
            		"</livro>\n");
		}
		returnValue.append("</testes>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}