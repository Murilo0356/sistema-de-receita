package model;

import  Enums.CategoriaReceita;
import  Enums.TipoReceita;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Lob 
    private String descricao;

    @Lob 
    private String passos;

    @Column
    private double calorias;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaReceita categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoReceita tipo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

 
    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceitaIngrediente> ingredientes;
    
    
    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanoReceita> planosOndeAparece;


    public Receita() {
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPassos() {
        return passos;
    }

    public void setPassos(String passos) {
        this.passos = passos;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public CategoriaReceita getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaReceita categoria) {
        this.categoria = categoria;
    }

    public TipoReceita getTipo() {
        return tipo;
    }

    public void setTipo(TipoReceita tipo) {
        this.tipo = tipo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public List<ReceitaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ReceitaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
    public List<PlanoReceita> getPlanosOndeAparece() {
        return planosOndeAparece;
    }

    public void setPlanosOndeAparece(List<PlanoReceita> planosOndeAparece) {
        this.planosOndeAparece = planosOndeAparece;
    }
}