 package br.ufc.quixada.eda.hash;

public class HashLinear<T> extends Hash<T> {
	private NoHash<T> tabela[];

	public HashLinear(Integer tamanho) {
		super(tamanho);
		this.tabela = new NoHash[this.m];
	}

	private Integer fHLinear(int chave, int k) {
		return ((this.FuncaoHash(chave) + k) % this.m);
	}
	
	@Override
	public void inserir(Integer chave, T valor) {
		for(int k = 0; k < this.m; k++){
			if(this.tabela[fHLinear(chave, k)] == null) {
				this.tabela[fHLinear(chave, k)] = new NoHash<>(chave, valor);
				return;
			}
		}
	}

	@Override
	public T buscar(Integer chave) {
		for(int k = 0; k < this.m; k++) {
			if(this.tabela[fHLinear(chave, k)].getChave() == chave) {
				return this.tabela[fHLinear(chave, k)].getValor();
			}
		}
		return null;
	}

	@Override
	public T remover(Integer chave) {
		for(int k = 0; k < this.m; k++) {
			if(this.tabela[fHLinear(chave, k)].getChave() == chave) {
				T aux = this.tabela[fHLinear(chave, k)].getValor();
				this.tabela[fHLinear(chave, k)].setChave(null);
				this.tabela[fHLinear(chave, k)].setValor(null);
				return aux;
			}
		}
		return null;
	}
	
	public void imprimir() {
		for(int i = 0; i < this.m; i++) {
			if(this.tabela[i] != null) {
				System.out.println(this.tabela[i].getChave());
			}else {
				System.out.println("vazio");
			}
		}
	}
}

