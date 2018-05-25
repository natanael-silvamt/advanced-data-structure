package br.ufc.quixada.eda.grafo;

public class Aresta {
	private int u;
	private int v;
	private int peso_aresta;

	public Aresta(int u, int v, int peso){
		this.u = u;
		this.v = v;
		this.peso_aresta = peso;
	}

	public int getU() {
		return u;
	}

	public void setU(int u) {
		this.u = u;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getPeso_aresta() {
		return peso_aresta;
	}

	public void setPeso_aresta(int peso_aresta) {
		this.peso_aresta = peso_aresta;
	}

}
