package br.ufc.quixada.eda.hash;

public class EnderecamentoInterno<T> extends Hash<T> {
	private Integer p = 0;
	private NoHash<T> tabela[];
	private NoHash<T> has = new NoHash<>();
	
	
	public EnderecamentoInterno(Integer tamanho) {
		super(tamanho);
		this.tabela = new NoHash[this.m + 1];
		this.p = (int) (0.7 * this.m);
		for(int i = 0; i < this.m; i++){
			this.tabela[i] = null;
		}
	}
	
	public Integer FuncaoHash(Integer chave){
		return (chave % this.p);
	}

	@Override
	public void inserir(Integer chave, T valor) throws ExececaoEnderecamentoInterno {
		if(chave != null && valor != null){
			if(!VerificaChave(chave)) System.out.println("Chave ja existe !!!");
			NoHash<T> hash = new NoHash<>();
			if(VerificaTamanho()){
				if(this.tabela[FuncaoHash(chave)] == null){
					hash.setChave(chave);
					hash.setValor(valor);
					this.tabela[FuncaoHash(chave)] = hash;
				}
				else if(this.tabela[FuncaoHash(chave)] != null){
					for(int j = this.p; j <= (this.m - 1); j++){
						if(this.tabela[j] == null){
							this.tabela[FuncaoHash(chave)].setProximo(j);
							hash.setChave(chave);
							hash.setValor(valor);
							this.tabela[j] = hash;
							break;
						}
					}
				}
			}
			else {
				throw new ExececaoEnderecamentoInterno("Tabela cheia !!!");
			}
		}	
	}

	@Override
	public T buscar(Integer chave) {
		if(chave != null && this.tabela[FuncaoHash(chave)] != null){
			this.has = (NoHash<T>) this.tabela[FuncaoHash(chave)];
			if(this.has.getChave() == chave){
				return this.has.getValor();
			}
			else if(this.has.getChave() != chave){
				int indice = this.tabela[FuncaoHash(chave)].getProximo();
				while(true){
					if(this.tabela[indice].getChave() == chave){
						return this.tabela[indice].getValor();
					}
					else {
						indice = this.tabela[indice].getProximo();
					}
				}
			}
		}
		return null;
	}

	@Override
	public T remover(Integer chave) {
		if(chave != null && this.tabela[FuncaoHash(chave)] != null){
			this.has = (NoHash) this.tabela[FuncaoHash(chave)];
			if(this.has.getChave() == chave){
				if(this.has.getProximo() == null){
					this.tabela[FuncaoHash(chave)] = null;
					return this.has.getValor();
				}
				else if(this.has.getProximo() != null){
					this.tabela[FuncaoHash(chave)] = this.tabela[this.has.getProximo()];
					this.tabela[this.has.getProximo()] = null;
					return this.has.getValor();
				}
			}
			else if(this.has.getChave() != chave){
				int indice = this.tabela[FuncaoHash(chave)].getProximo();
				while(true){
					if(this.tabela[indice].getChave() == chave){
						this.has = (NoHash) this.tabela[indice];
						if(this.tabela[indice].getProximo() == null){
							this.tabela[indice] = null;
							return this.has.getValor();
						}
						else if(this.tabela[indice].getProximo() != null){
							this.tabela[indice] = this.tabela[this.has.getProximo()];
							this.tabela[this.has.getProximo()] = null;
							return this.has.getValor();
						}
					}
					else {
						indice = this.tabela[indice].getProximo();
					}
				}
			}
		}
		return null;
	}
	
	public void imprimir(){
		for(int i = 0; i < this.tabela.length; i++){
			if(this.tabela[i] != null){
				System.out.println(this.tabela[i].getChave());
			}
		}
	}
	
	private boolean VerificaTamanho(){
		int cont = 0;
		for(int i = 0; i < this.m; i++){
			if(this.tabela[i] == null){
				cont++;
			}
		}
		if(cont != 0){
			return true;
		}
		return false;
	}
	
	private boolean VerificaChave(Integer chave){
		for(int i = 0; i < this.tabela.length - 1; i++){
			if(this.tabela[i].getChave() == chave) return false;
		}
		return true;
	}
	
}
