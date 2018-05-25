package br.ufc.quixada.eda.hash;

public class NoHash<T> {
	private Integer chave;
	private T valor;
	private Integer proximo;
	
	public NoHash(){	
	}
	
	public NoHash(Integer chave, T valor){
		this.chave = chave;
		this.valor = valor;
	}
	
	public Integer getChave() {
		return chave;
	}
	public void setChave(Integer chave) {
		this.chave = chave;
	}
	public T getValor() {
		return valor;
	}
	public void setValor(T valor) {
		this.valor = valor;
	}
	public Integer getProximo() {
		return proximo;
	}
	public void setProximo(Integer proximo) {
		this.proximo = proximo;
	}
	
}
