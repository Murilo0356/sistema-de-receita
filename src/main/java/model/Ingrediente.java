package model;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200, unique = true)
    private String descricao;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_nutricional_id", referencedColumnName = "id")
    private InformacaoNutricional informacaoNutricional;

    @OneToMany(mappedBy = "ingrediente")
    private List<ReceitaIngrediente> receitas;
    
   

    public Ingrediente() {
    }

    public Ingrediente(String descricao, InformacaoNutricional info) {
        this.descricao = descricao;
        this.informacaoNutricional = info;
    }


    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public InformacaoNutricional getInformacaoNutricional() {
        return informacaoNutricional;
    }

    public void setInformacaoNutricional(InformacaoNutricional informacaoNutricional) {
        this.informacaoNutricional = informacaoNutricional;
    }

    public List<ReceitaIngrediente> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<ReceitaIngrediente> receitas) {
        this.receitas = receitas;
    }
}