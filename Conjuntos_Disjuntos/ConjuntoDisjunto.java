package br.ufc.quixada.eda.conjuntosdisjuntos;

public class ConjuntoDisjunto {
	private int pai [] = null;
	private int ordenado [] = null;
	private int n = 0;
	
	public ConjuntoDisjunto(int n){
		this.n = n;
		this.pai = new int[n];
		this.ordenado = new int[n];		
	}
	
	public void make_set(int v){
		this.pai[v] = v;
		this.ordenado[v] = 0;
	}
	
	public int find_set(int v){
		if(v == this.pai[v]) return v;
		this.pai[v] = find_set(this.pai[v]);
		return this.pai[v];
	}
	
	public void link(int v, int u){
		if(this.ordenado[v] < this.ordenado[u]){
			this.pai[v] = u;
		}else{
			if(this.ordenado[v] == this.ordenado[u]){
				this.ordenado[v]++;
			}
			this.pai[u] = v;
		}
	}
	
	public void union(int v, int u){
		link(find_set(v), find_set(u));
	}

}
