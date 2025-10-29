package model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "plano_receita") //
public class PlanoReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private PlanoDeRefeicao planoDeRefeicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @OneToMany(mappedBy = "planoDeRefeicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanoReceita> itens;
    
    @Column(name = "dia_da_semana", length = 50)
    private String diaDaSemana; 

    public PlanoReceita() {
    }

   

    public long getId() {
        return id;
    }

    public PlanoDeRefeicao getPlanoDeRefeicao() {
        return planoDeRefeicao;
    }

    public void setPlanoDeRefeicao(PlanoDeRefeicao planoDeRefeicao) {
        this.planoDeRefeicao = planoDeRefeicao;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }
}