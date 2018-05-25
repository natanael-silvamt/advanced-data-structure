package br.ufc.quixada.eda.grafo;

import java.util.*;

public class ListaAdjacencia implements Iterator<Aresta>{
	private List<Aresta> lista;
	private int posicao;
	
	public ListaAdjacencia(){
		this.posicao = 0;
		this.lista = new ArrayList<Aresta>();
	}
	
	public int length(){
		return this.lista.size();
	}
	
	public boolean isEmpty(){
		return lista.size() == 0;
	}

	public void add(Aresta a){
		this.lista.add(a);
	}
	
	public void InicializaIterator(){
		this.posicao = 0;
	}

	@Override
	public boolean hasNext() {
		return (this.posicao < this.lista.size());
	}

	@Override
	public Aresta next() {
		Aresta aresta = this.lista.get(posicao);
		this.posicao++;
		return aresta;
	}
}
