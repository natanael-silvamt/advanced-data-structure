package br.ufc.quixada.eda.listaprioridades;

import java.util.List;

public class HeapMinimo {
	private int nMaximo = 0;
	private int vetor[] = null;
	private int indentificador[] = null;
	private int n = 0;
	
	public HeapMinimo(int Nmaximo){
		nMaximo = Nmaximo + 1;
		vetor = new int[this.nMaximo];
		indentificador = new int[this.nMaximo];
	}
	
	public void contruir(List<Integer> entrada){
        for(int i = 0; i < entrada.size(); i++){
            this.vetor[i + 1] = entrada.get(i);
            this.indentificador[i] = i + 1;
        }
        
        this.n = entrada.size();
        
        for(int i = (this.n / 2); i >= 0; i--){
            descer(i);
        }
	}
	
	public boolean isEmpty(){
		return (this.n == 0);
	}
	
	private void subir(int i){
            int p = (i / 2);
            if(p >= 1){
                if(this.vetor[p] > vetor[i]){
                    swap(i, p);
                    subir(p);
                }
            }
	}
	
	private void descer(int i){
		int j = 2 * (i + 1);
		if(j <= this.n){
			j--;
			if(j + 1 < this.n && this.vetor[j] < this.vetor[j + 1]) j++;
			if(this.vetor[i] > this.vetor[j]){
				swap(i, j);
				descer(j);				
			}
		}
	}
	
	public int getMinimaPrioridade(){
		return indentificador[1];
	}
	
	public int remove(){
            if(this.n > 0){
                int aux = this.indentificador[1];
                this.vetor[1] = this.vetor[this.n];
                this.indentificador[1] = this.indentificador[this.n];
                this.n--;
                descer(1);
                return aux;
            }
		return -1;
	}	
	
	public void inserir(int prioridade){
            if(this.n < nMaximo){
                this.vetor[this.n + 1] = prioridade;
                this.n++;
                subir(this.n);
            }
	}
	
	public void alterarPrioridade(int prioridade, int novaPrioridade){
            for(int i = 1; i <= this.n; i++){
                if(this.vetor[i] == prioridade){
                    this.vetor[i] = novaPrioridade;
                    if(novaPrioridade < prioridade){
                        subir(i);
                    } else{
                        descer(i);
                    }
                    return;
                }
            }
	}	
	
	private void swap(int i, int j){
		int temp, tempId;
		
		temp = this.vetor[i];
		tempId = this.indentificador[i];
		
		this.vetor[i] = this.vetor[j];
		this.indentificador[i] = this.indentificador[j];
		
		this.vetor[j] = temp;
		this.indentificador[j] = tempId;
	}
	
	
	
}
