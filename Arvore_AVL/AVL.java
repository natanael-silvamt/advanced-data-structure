package br.ufc.quixada.eda.AVL;

public class AVL<Chave extends Comparable<Chave>, Info> {
	private class No{
		public Chave chave;
		public Info info;
		public No esq, dir;
		public int altura;
		
		public No(Chave chave, Info info){
			this.chave = chave;
			this.info = info;
			this.altura = 1;
			this.dir = null;
			this.esq = null;
		}
	}
	
	private No raiz;
	
	private int altura(No raiz){
		return (raiz != null ? raiz.altura : 0);
	}
	
	private static int max(int first, int segond){
		return (first > segond ? first : segond);
	}
	
	private No rotacaoEsquerda(No nodo){
		No nodo_aux = nodo.esq;
		nodo.esq = nodo_aux.dir;
		nodo_aux.dir = nodo;
		
		nodo.altura = max(altura(nodo.esq), altura(nodo.dir)) + 1;
		nodo_aux.altura = max(altura(nodo_aux.esq), nodo.altura) + 1;
		return nodo_aux;
	}
	
	private No rotacaoDireita(No nodo){
		No nodo_aux = nodo.dir;
		nodo.dir = nodo_aux.esq;
		nodo_aux.esq = nodo;
		
		nodo.altura = max(altura(nodo.esq), altura(nodo.dir)) + 1;
		nodo_aux.altura = max(altura(nodo_aux.dir), nodo.altura) + 1;
		return nodo_aux;
	}
	
//	private No rotacaoDuplaDireita(No nodo){
//		nodo.dir = rotacaoEsquerda(nodo.dir);
//		return rotacaoDireita(nodo);
//	}
//	
//	private No rotacaoDuplaEsquerda(No nodo){
//		nodo.esq = rotacaoDireita(nodo.esq);
//		return rotacaoEsquerda(nodo);
//	}
	public void inserir(Chave chave, Info info){
		this.raiz = insertNode(this.raiz, chave, info);
	}
	private No insertNode(No node, Chave chave, Info info) {
	    if (node == null) {
	        return new No(chave, info);
	    }
	    if (chave.compareTo(node.chave) < 0) {
	        node.esq = insertNode(node.esq, chave, info);
	    } else {
	        node.dir = insertNode(node.dir, chave, info);
	    }
	   // currentNode = balanceTree(currentNode, chave, info);
	    node.altura = altura(node);
	    return node;
	}
	
	public Info buscar(Chave chave) {
        return buscar(this.raiz, chave);
    }
    
    private Info buscar(No raiz, Chave chave) {
        while(raiz != null) {
        	int temp = chave.compareTo(raiz.chave);
        	if(temp < 0) raiz = raiz.esq;
        	else if(temp > 0) raiz = raiz.dir;
        	else return  raiz.info;
        }
        return null;
    }	
    public void remove(Chave chave){
    	this.raiz = remove(this.raiz, chave);
    }
    
    private No remove(No node, Chave chave) {
        if (node == null) {
            return null;
        }
     
        No filho_esq = node.esq;
        No filho_dir = node.dir;
     
        if (chave.compareTo(node.chave) == 0) {     
            if (filho_esq == null && filho_dir == null) {
                return null;
            } else if (filho_esq == null) {
                node = null;
                return filho_dir;
            } else if (filho_dir== null) {
                node = null;
                return filho_esq;
            } else {
                No maior_esq = findMax(filho_esq);
                node.chave = maior_esq.chave;
                node.esq = remove(node.esq, maior_esq.chave);
            }
        } else if (chave.compareTo(node.chave) < 0) {
            node.esq = remove(filho_esq, chave);
        } else {
            node.dir = remove(filho_dir, chave);
        }
        node.altura = altura(node);
        return balancear_remocao(node);
    }
    
    private No balancear_remocao(No node) {
        int balanceValue = getBalance(node);
        if (balanceValue > 1) {
            if (getBalance(node.esq) < 0) {
                node.esq = rotacaoEsquerda(node.esq);
            }
            return rotacaoDireita(node);
        }
        if (balanceValue < -1) {
            if (getBalance(node.dir) > 0) {
                node.dir = rotacaoDireita(node.dir);
            }
            return rotacaoEsquerda(node);
        }
        return node;
    }
    
	public No findMax(No node){        
        No atual = node;
        while (atual.dir != null)
           atual = atual.dir;
        return atual;
    }
	
    int getBalance(No node){
        if (node == null) return 0;
        return altura(node.esq) - altura(node.dir);
    }
	
	private void imp(No n, String s) {
		if(n != null && (n.esq != null || n.dir != null))
			imp(n.dir, s + "r");
		int tam = s.length();
		for(int i = 0; i < tam - 1; i++) {
			if(s.charAt(i) != s.charAt(i+1)) {
				System.out.print("| "+"  ");
			}else {
				System.out.print("  "+"  ");
			}
		}
		if(s != "") {
			if(s.endsWith("r") == true)
				System.out.print("┌───");
			else
				System.out.print("└───");
		}
		if(n == null) {
			System.out.println("#");
			return;
		}
		System.out.println(n.chave +"(" + n.altura+ ")");
		if(n != null && (n.esq != null || n.dir != null)) {
			imp(n.esq, s + "l");
		}
	}
	
	public void show() {
		imp(this.raiz, "");
	}
}
