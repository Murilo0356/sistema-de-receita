package model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

   
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Receita> receitasEnviadas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlanoDeRefeicao> planosDeRefeicao;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Receita> getReceitasEnviadas() {
        return receitasEnviadas;
    }

    public void setReceitasEnviadas(List<Receita> receitasEnviadas) {
        this.receitasEnviadas = receitasEnviadas;
    }

    public List<PlanoDeRefeicao> getPlanosDeRefeicao() {
        return planosDeRefeicao;
    }

    public void setPlanosDeRefeicao(List<PlanoDeRefeicao> planosDeRefeicao) {
        this.planosDeRefeicao = planosDeRefeicao;
    }
}