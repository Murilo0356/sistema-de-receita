package model;

import javax.persistence.*;

@Entity
@Table(name = "receita_ingrediente")
public class ReceitaIngrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "receita_id")
	private Receita receita;

	@ManyToOne
	@JoinColumn(name = "ingrediente_id")
	private Ingrediente ingrediente;

	@Column(nullable = false, length = 100)
	private String unidadeMedida;

	@Column(nullable = false)
	private double quantidade;

	public double getQuantidade() {
		return quantidade;
	}

	public long getId() {
		return id;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public ReceitaIngrediente() {
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
}