package br.ufc.quixada.eda.algoritmos;

import java.util.*;

import br.ufc.quixada.eda.grafo.*;
import br.ufc.quixada.eda.listaprioridades.HeapMinimo;

public class Dijkstra {
	private int antecessor[] = null;
	private double custo[] = null;
	
	public void executar(Grafo grafo, int raiz){

		List<Integer> vert = new ArrayList<>();

		this.antecessor = new int[grafo.getQtd_vertices() + 1];
		this.custo = new double[grafo.getQtd_vertices() + 1];
		
		for(int i = 1; i <= grafo.getQtd_vertices(); i++){
			this.antecessor[i] = -1;
			this.custo[i] = Double.MAX_VALUE;
			vert.add(i);
		}	
		
		this.custo[raiz] = 0;
		
		HeapMinimo heap = new HeapMinimo(grafo.getQtd_vertices());
		heap.contruir(vert);
		
		while(!heap.isEmpty()){
			int u = heap.remove();
			ListaAdjacencia adj = grafo.getAdj()[u];
			
			adj.InicializaIterator();
			while(adj.hasNext()){
				Aresta aresta = adj.next();
				int v = (aresta.getU() == u ? aresta.getV() : aresta.getU());
				if(this.custo[v] > (this.custo[u] + aresta.getPeso_aresta())){
					this.antecessor[v] = u;
					this.custo[v] = aresta.getPeso_aresta() + this.custo[u];
				}
			}
		}
	}
	
	public void imprimeCaminho(int origem, int v){
		if(origem == v) 
			System.out.println(origem);
		else if(this.antecessor[v] == -1) 
			System.out.println("Nao existe caminho de " + origem + "ate" +v);
		else {
			imprimeCaminho(origem, this.antecessor[v]);
			System.out.println(v);
		}
	}
	
}
