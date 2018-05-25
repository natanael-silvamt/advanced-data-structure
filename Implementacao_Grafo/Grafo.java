package br.ufc.quixada.eda.grafo;

public class Grafo {
	private int qtd_vertices = 0;
	private int qtd_arestas = 0;
	private Aresta arestas [] = null;
	private ListaAdjacencia adj [] = null;
	
	
	public Grafo(int qtd_vertices, int qtd_arestas){
		this.qtd_arestas = qtd_arestas;
		this.qtd_vertices = qtd_vertices;
		this.adj = new ListaAdjacencia[qtd_arestas];
		this.setArestas(new Aresta[qtd_arestas]);
	}
	
	public Grafo(){
		
	}

	public int getQtd_vertices() {
		return qtd_vertices;
	}

	public void setQtd_vertices(int qtd_vertices) {
		this.qtd_vertices = qtd_vertices;
	}

	public int getQtd_arestas() {
		return qtd_arestas;
	}

	public void setQtd_arestas(int qtd_arestas) {
		this.qtd_arestas = qtd_arestas;
	}

	public Aresta[] getArestas() {
		return arestas;
	}

	public void setArestas(Aresta arestas[]) {
		this.arestas = arestas;
	}

	public ListaAdjacencia[] getAdj() {
		return adj;
	}

	public void setAdj(ListaAdjacencia adj[]) {
		this.adj = adj;
	}

}
