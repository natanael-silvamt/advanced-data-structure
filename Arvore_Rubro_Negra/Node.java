package br.ufc.quixada.eda.RubroNegra;

public class Node {
	private Node esq, dir;
	private int chave;
	private boolean cor;
	public static final boolean PRETO = true;
	public static final boolean VERMELHO = false;
	
	public Node(int chave){
		this.chave = chave;
		this.esq = null;
		this.dir = null;
		this.cor = Node.PRETO;
	}
	
	public Node getEsq() {
		return esq;
	}
	public void setEsq(Node esq) {
		this.esq = esq;
	}
	public Node getDir() {
		return dir;
	}
	public void setDir(Node dir) {
		this.dir = dir;
	}
	public int getChave() {
		return chave;
	}
	public void setChave(int chave) {
		this.chave = chave;
	}
	public boolean isCor() {
		return cor;
	}
	public void setCor(boolean cor) {
		this.cor = cor;
	}
	public static boolean isPreto() {
		return PRETO;
	}
	public static boolean isVermelho() {
		return VERMELHO;
	}
}
