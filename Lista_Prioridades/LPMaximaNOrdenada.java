package br.ufc.quixada.eda.listaprioridades;

import java.util.List;

/**
 * Implementa a lista de prioridade usando vetor não ordenado.
 * @author fabio
 *
 */
public class LPMaximaNOrdenada {
	private int nMaximo = 0;
	private int vetor[] = null;
	private int n = 0;
	
	public LPMaximaNOrdenada(int Nmaximo){
		nMaximo = Nmaximo;
		vetor = new int[Nmaximo];
	}
	
	public void contruir(List<Integer> entrada){
		for (int i = 0; i < entrada.size(); i++) {
			vetor[i] = entrada.get(i);
		}
		n = entrada.size();	
	}
	
	public int getMaximaPrioridade(){
		int maxima = 0;
		for(int i = 1; i < n; i++){
			if(vetor[i] > vetor[maxima]) maxima = i; 
		}
		return vetor[maxima];
	}
	
	public int remove(){
		int maxima = 0;
		for(int i = 1; i < n; i++){
			if(vetor[i] > vetor[maxima]) maxima = i; 
		}
		int valor = vetor[maxima];
		n--;
		vetor[maxima] = vetor[n];		
		return valor;
	}	
	
	public void inserir(int prioridade){
		if(n + 1 < nMaximo){			
			vetor[n] = prioridade;
			n++;
		}
	}
	
	public void alterarPrioridade(int prioridade, int novaPrioridade){
		for(int i = 0; i < n; i++){
			if(vetor[i] == prioridade){
				vetor[i] = novaPrioridade;
				break;
			}
		}		
	}
}
