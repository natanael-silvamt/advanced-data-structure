package br.ufc.quixada.eda.hash;

public abstract class Hash<T> {
	protected Integer m = 0;
	
	public Hash(Integer tamanho){
		this.m = tamanho;
	}
	
	public Integer FuncaoHash(Integer chave){
		return (chave % m);
	}
	
	public abstract void inserir(Integer chave, T valor) throws ExececaoEnderecamentoInterno;
	
	public abstract T buscar(Integer chave);
	
	public abstract T remover(Integer chave);

}
