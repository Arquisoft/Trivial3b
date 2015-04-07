package es.uniovi.asw.controller.game;

import es.uniovi.asw.modelo.model.Category;

public class Casilla {

    private int id;
    private Category categoria;
    private TipoCasilla tipo;

    public Casilla(int id, TipoCasilla tipo) {
        this(id, null, tipo);
    }

    public Casilla(int id, Category categoria) {
        this(id, categoria, TipoCasilla.NORMAL);
    }

    public Casilla(int id, Category categoria, TipoCasilla tipo) {
        this.id = id;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public Category getCategoria() {
        return categoria;
    }

    public TipoCasilla getTipoCasilla() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Casilla [id=" + id + ", categoria=" + categoria + ", tipo="
                + tipo + "]";
    }
}
