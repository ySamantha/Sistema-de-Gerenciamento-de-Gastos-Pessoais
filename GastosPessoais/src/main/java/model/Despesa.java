package model;

public class Despesa {
    public double valor;
    public String categoria;
    public String descricao;

    public Despesa(double valor, String categoria, String descricao) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
