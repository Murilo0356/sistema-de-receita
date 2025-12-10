package service;

import dao.ReceitaDao;
import model.Receita;
import model.ReceitaIngrediente;
import java.util.List;

public class ReceitaService {

	ReceitaDao receitaDao = new ReceitaDao();

	public String cadastrarReceita(Receita receita) {

		if (receita == null) {
			return "Erro: A receita não pode ser vazia.";
		}

		if (receita.getTitulo() == null || receita.getTitulo().trim().isEmpty()) {
			return "Erro: O título da receita é obrigatório.";
		}

		if (receita.getAutor() == null) {
			return "Erro: A receita precisa estar associada a um Usuário (autor).";
		}

		if (receita.getIngredientes() == null || receita.getIngredientes().isEmpty()) {
			return "Erro: A receita deve ter pelo menos um ingrediente.";
		}

		double totalCalorias = calcularCalorias(receita);
		receita.setCalorias(totalCalorias);

		try {
			receitaDao.salvar(receita);
			return "Receita '" + receita.getTitulo() + "' salva com sucesso!";
		} catch (Exception execao) {
			execao.printStackTrace();
			return "Erro técnico ao salvar no banco: " + execao.getMessage();
		}
	}

	private double calcularCalorias(Receita receita) {
		double total = 0.0;

		for (ReceitaIngrediente item : receita.getIngredientes()) {
			if (item.getIngrediente() != null && item.getIngrediente().getInformacaoNutricional() != null) {

				double caloriasPorUnidade = item.getIngrediente().getInformacaoNutricional().getQuantidade();
				double quantidadeUsada = item.getQuantidade();

				total += (caloriasPorUnidade * quantidadeUsada);
			}
		}

		return Math.round(total * 100.0) / 100.0;
	}
	public List<Receita> buscarPorTitulo(String termo) {
        return receitaDao.buscarPorTitulo(termo);
    }
	public List<Receita> listarTodas() {
		return receitaDao.listarTodas();
	}
	public Receita buscarPorId(long id) {
        return receitaDao.buscarPorId(id);
    }

    public void atualizarReceita(Receita receita) {
        receitaDao.atualizar(receita);
    }

    public void removerReceita(long id) {
        receitaDao.remover(id);
    }
}