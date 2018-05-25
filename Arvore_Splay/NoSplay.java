package br.ufc.quixada.eda.Splay;

public class NoSplay<T> {
	private int chave;
	private NoSplay<?> esq, dir;
	
	public NoSplay(int chave){
		this.chave = chave;
		this.esq = null;
		this.dir = null;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public NoSplay<?> getEsq() {
		return esq;
	}

	public void setEsq(NoSplay<?> esq) {
		this.esq = esq;
	}

	public NoSplay<?> getDir() {
		return dir;
	}

	public void setDir(NoSplay<?> dir) {
		this.dir = dir;
	}
}
