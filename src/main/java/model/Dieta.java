package model;

import Enums.TipoDieta;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "dieta")
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TipoDieta tipo;

    @Column(nullable = false, unique = true)
    private String descricao;

    @OneToMany(mappedBy = "dieta", fetch = FetchType.LAZY)
    private List<Receita> receitas;

    public Dieta() {
    }



    public long getId() {
        return id;
    }

    public TipoDieta getTipo() {
        return tipo;
    }

    public void setTipo(TipoDieta tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
}