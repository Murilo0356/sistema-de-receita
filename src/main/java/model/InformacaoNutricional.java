package model;

import javax.persistence.*;


@Entity
@Table(name = "informacao_nutricional")
public class InformacaoNutricional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

<<<<<<< HEAD
   
=======
    
>>>>>>> 10356997b969cc1b6329196c1ea71726e5b4acd7
    @Column(length = 255)
    private String descricao; 

  
    @Column
    private double quantidade;

<<<<<<< HEAD
   
=======
>>>>>>> 10356997b969cc1b6329196c1ea71726e5b4acd7
    @OneToOne(mappedBy = "informacaoNutricional")
    private Ingrediente ingrediente;
    
    public InformacaoNutricional() {
    }
<<<<<<< HEAD


=======
>>>>>>> 10356997b969cc1b6329196c1ea71726e5b4acd7
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
