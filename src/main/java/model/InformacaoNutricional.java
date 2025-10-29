package model;

import javax.persistence.*;


@Entity
@Table(name = "informacao_nutricional")
public class InformacaoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Descrição (ex: "Porção de 100g")
    @Column(length = 255)
    private String descricao; 

    // Quantidade (ex: Calorias, Proteínas, etc. 
    // Seu modelo original estava simples, mantive assim)
    @Column
    private double quantidade;

    // Lado inverso do relacionamento com Ingrediente
    @OneToOne(mappedBy = "informacaoNutricional")
    private Ingrediente ingrediente;
    
    public InformacaoNutricional() {
    }

    // --- Getters e Setters ---

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}