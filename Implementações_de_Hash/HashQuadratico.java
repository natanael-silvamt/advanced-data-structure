package br.ufc.quixada.eda.hash;

public class HashQuadratico<T> extends Hash<T> {
	private NoHash<T> tabela[];

	public HashQuadratico(Integer tamanho) {
		super(tamanho);
		this.tabela = new NoHash[this.m];
	}
	
	private Integer fhashaux(int chave, int k) {
		return (this.FuncaoHash(chave) + k) % this.m;
	}
	
	private Integer fhashquadratico(int chave, int k) {
		return (fhashaux(chave, (k - 1)) + k) % this.m;
	}

	@Override
	public void inserir(Integer chave, T valor) {
		if(this.tabela[this.FuncaoHash(chave)] == null) {
			this.tabela[this.FuncaoHash(chave)] = new NoHash<>(chave, valor);
		}else {
			for(int k = 1; k < this.m; k++){
				if(this.tabela[fhashquadratico(chave, k)] == null) {
					this.tabela[fhashquadratico(chave, k)] = new NoHash<>(chave, valor);
					return;
				}	
			}
		}
	}

	@Override
	public T buscar(Integer chave) {
		if(this.tabela[this.FuncaoHash(chave)].getChave() == chave) {
			return this.tabela[this.FuncaoHash(chave)].getValor();
		}else {
			for(int k = 1; k < this.m; k++){
				if(this.tabela[fhashquadratico(chave, k)].getChave() == chave) {
					return this.tabela[fhashquadratico(chave, k)].getValor();			
				}
			}
		}
		return null;
	}

	@Override
	public T remover(Integer chave) {
		if(this.tabela[this.FuncaoHash(chave)].getChave() == chave) {
			T aux = this.tabela[this.FuncaoHash(chave)].getValor();
			this.tabela[this.FuncaoHash(chave)].setChave(null);
			this.tabela[this.FuncaoHash(chave)].setValor(null);
			return aux;
		}else {
			for(int k = 1; k < this.m; k++){
				if(this.tabela[fhashquadratico(chave, k)].getChave() == chave) {
					T aux = this.tabela[fhashquadratico(chave, k)].getValor();
					this.tabela[fhashquadratico(chave, k)].setChave(null);
					this.tabela[fhashquadratico(chave, k)].setValor(null);
					return aux;
				}
				
			}
		}
		return null;
	}
	
	public void imprimir() {
		for(int i = 0; i < this.m; i++) {
			if(this.tabela[i] != null) {
				System.out.println(this.tabela[i].getChave() + " -> " + this.tabela[i].getValor());
			}
		}
	}
}
