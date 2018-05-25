package br.ufc.quixada.eda.AVL;

public class NoAVL<T> {
	private NoAVL<?> esq, dir;
	private int altura, chave;
	
	public NoAVL(int chave){
		this.chave = chave;
		this.esq = null;
		this.dir = null;
		this.altura = 1;
	}

	public NoAVL<?> getEsq() {
		return esq;
	}

	public void setEsq(NoAVL<?> esq) {
		this.esq = esq;
	}

	public NoAVL<?> getDir() {
		return dir;
	}

	public void setDir(NoAVL<?> dir) {
		this.dir = dir;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}
}
