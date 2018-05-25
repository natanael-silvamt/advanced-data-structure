package br.ufc.quixada.eda.hash;

import java.util.LinkedList;
import java.util.List;

public class EnderecamentoExterno<T> extends Hash<T>{
	private List<T> tabela[];
	private NoHash<T> has = new NoHash<>();
	

	public EnderecamentoExterno(Integer tamanho) {
		super(tamanho);
		tabela = new LinkedList[this.m + 1];
	}

	@Override
	public void inserir(Integer chave, T valor) {
		NoHash<T> hash = new NoHash<>();
		if(chave != null && valor != null){
			if(this.tabela[FuncaoHash(chave)] == null){
				this.tabela[FuncaoHash(chave)] = new LinkedList<>();
				hash.setChave(chave);
				hash.setValor(valor);
				this.tabela[FuncaoHash(chave)].add((T) hash);
			}
			else {
				hash.setChave(chave);
				hash.setValor(valor);
				this.tabela[FuncaoHash(chave)].add((T) hash);
			}	
		}
	}

	@Override
	public T buscar(Integer chave) {
		if(chave != null && this.tabela[FuncaoHash(chave)] != null){
			int tam = this.tabela[FuncaoHash(chave)].size();
			for(int i = (tam - 1); i >= 0; i--){
				this.has = (NoHash) this.tabela[FuncaoHash(chave)].get(i);
				if(this.has.getChave() == chave){
					return (T) this.has.getValor();
				}
			}
		}
		return null;
	}

	@Override
	public T remover(Integer chave) {
		if(chave != null && this.tabela[FuncaoHash(chave)] != null){
			T valor = null;
			int aux = this.tabela[FuncaoHash(chave)].size();
			if(aux == 1){
				has = (NoHash) this.tabela[FuncaoHash(chave)].get(0);
				valor = (T) has.getValor();
				this.tabela[FuncaoHash(chave)] = null;
				return valor;
			}
			for(int i = (aux - 1); i >= 0; i--){
				has = (NoHash) this.tabela[FuncaoHash(chave)].get(i);
				if(has.getChave() == chave){
					valor = (T) has.getValor();
					this.tabela[FuncaoHash(chave)].remove(i);
					return valor;					
				}
			}
		}
		return null;
	}
	
	public void imprimir(){
		for(int i = 0; i < this.tabela.length; i++){
			System.out.println(this.tabela[i]);			
		}
	}

}
