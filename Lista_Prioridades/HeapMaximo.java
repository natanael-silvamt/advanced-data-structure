package br.ufc.quixada.eda.listaprioridades;

import java.util.List;

public class HeapMaximo {
	private int nMaximo = 0;
	private int vetor[] = null;
	private int n = 0;
	
	public HeapMaximo(int Nmaximo){
		nMaximo = Nmaximo;
		vetor = new int[Nmaximo];
	}
	
	public void contruir(List<Integer> entrada){
        for(int i = 0; i < entrada.size(); i++){
            this.vetor[i] = entrada.get(i);
        }
        this.n = entrada.size();
        
        for(int i = (this.n / 2); i >= 0; i--){
            descer(i);
        }
	}
	
	private void subir(int i){
            int p = (i / 2);
            if(p >= 1){
                if(this.vetor[p] < vetor[i]){
                    int aux = vetor[i];
                    vetor[i] = vetor[p];
                    vetor[p] = aux;
                    subir(p);
                }
            }
	}
	
	private void descer(int i){
		int j = 2 * (i + 1);
		if(j <= this.n){
			j--;
			if(j + 1 < this.n && this.vetor[j] < this.vetor[j + 1]) j++;
			if(this.vetor[i] < this.vetor[j]){
				int aux = this.vetor[i];
				this.vetor[i] = this.vetor[j];
				this.vetor[j] = aux;
				descer(j);
			}
		}
//            int f = (i * 2);
//            if(f <= n && f + 1 <= n){
//                    if(this.vetor[f + 1]  > this.vetor[f]) f++;
//            }
//            if(this.vetor[f] > this.vetor[i]){
//                int aux = this.vetor[f];
//                this.vetor[f] = this.vetor[i];
//                this.vetor[i] = aux;
//                descer(f);
//            }
	}
	
	public int getMaximaPrioridade(){
		return this.vetor[0];
	}
	
	public int remove(){
            if(this.n > 0){
                int aux = this.vetor[1];
                this.vetor[1] = this.vetor[this.n];
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
                    if(novaPrioridade > prioridade){
                        subir(i);
                    } else{
                        descer(i);
                    }
                    return;
                }
            }
	}	
}
