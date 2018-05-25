package br.ufc.quixada.eda.algoritmos;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.eda.conjuntosdisjuntos.ConjuntoDisjunto;
import br.ufc.quixada.eda.grafo.*;
import br.ufc.quixada.eda.util.EDAUtil;

public class ArvoreGeradoraMinima {

	public List<Aresta> Kruskal(Grafo g){
		List<Aresta> solucao = new ArrayList<>();
		ConjuntoDisjunto conj = new ConjuntoDisjunto(g.getQtd_vertices());
		
		for(int i = 0; i < g.getQtd_vertices(); i++){
			conj.make_set(i);
		}
		
		EDAUtil.quickSort(g.getArestas(), 0, g.getQtd_arestas());
		
		for(int i = 0; i < g.getQtd_vertices(); i++){
			Aresta a = g.getArestas()[i];
			if(conj.find_set(a.getV()) != conj.find_set(a.getU())){
				solucao.add(a);
				conj.union(a.getV(), a.getU());
			}
		}
		return solucao;		
	}
}
