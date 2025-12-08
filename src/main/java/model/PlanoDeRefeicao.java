package model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "plano_de_refeicao")
public class PlanoDeRefeicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 200)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "planoDeRefeicao", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlanoReceita> itensPlano;

	public PlanoDeRefeicao() {
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PlanoReceita> getItensPlano() {
		return itensPlano;
	}

	public void setItensPlano(List<PlanoReceita> itensPlano) {
		this.itensPlano = itensPlano;
	}
}