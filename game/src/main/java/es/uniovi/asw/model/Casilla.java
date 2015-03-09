package es.uniovi.asw.model;

public class Casilla {
	
	private int id;
	private Category categoria;
	
	
	public Casilla(int id, Category categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}


	public Category getCategoria() {
		return categoria;
	}


	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}
	
	

}
