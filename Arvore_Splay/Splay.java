package br.ufc.quixada.eda.Splay;

public class Splay<Chave extends Comparable<Chave>, Info> {	
	private class No{
		public Chave chave;
		public Info info;
		public No esq, dir;
		
		public No(Chave chave, Info info){
			this.chave = chave;
			this.info = info;
		}
	}
	
	private No raiz;
	
	private No splay(No raiz, Chave chave){
		if(raiz == null) return null;
		
		int temp = chave.compareTo(raiz.chave);
		if(temp < 0){
			if(raiz.esq != null){
				raiz.esq = splay(raiz.esq, chave);
				raiz = rotacaoDireita(raiz);
			}
		}
		else if(temp > 0){
			if(raiz.dir != null){
				raiz.dir = splay(raiz.dir, chave);
				raiz = rotacaoEsquerda(raiz);
			}
		}
	   return raiz;
	}
	
	public Info buscar(Chave chave){
		this.raiz = splay(this.raiz, chave);
		int temp = chave.compareTo(this.raiz.chave);
		if(temp < 0 || temp > 0) return null;
		else return this.raiz.info;
	}
	
	public void inserir(Chave chave, Info info){
		if(this.raiz == null){
			this.raiz = new No(chave, info);
			return;
		}
		
	//	if(!buscar(chave).equals(info)){
			this.raiz = splay(this.raiz, chave);
			int temp = chave.compareTo(this.raiz.chave);
			if(temp < 0){
				No node = new No(chave, info);
				node.esq = this.raiz.esq;
				node.dir = this.raiz;
				this.raiz.esq = null;
				this.raiz = node;
			}
			else if(temp > 0){
				No node = new No(chave, info);
				node.dir = this.raiz.dir;
				node.esq = this.raiz;
				this.raiz.dir = null;
				this.raiz = node;
			}
	//	} else {
		//	System.out.println("Chave ja existe !!!");
		//}
	}
	
	public Info remover(Chave chave){
		if(this.raiz == null) throw new IllegalArgumentException("Chave deve ser diferente de null.");
		
		if(buscar(chave) != null){
			this.raiz = splay(this.raiz, chave);
			No aux;
			if(this.raiz.esq == null){
				aux = this.raiz;
				this.raiz = this.raiz.dir;
				return aux.info;
			} else {
				No no = this.raiz.dir;
				aux = this.raiz;
				this.raiz = this.raiz.esq;
				this.raiz = splay(this.raiz, chave);
				this.raiz.dir = no;
				return aux.info;
			}
		}
		return null;
	}
	
	private No rotacaoDireita(No nodo) {
        No no = nodo.esq;
        nodo.esq = no.dir;
        no.dir = nodo;
        return no;
    }

    private No rotacaoEsquerda(No nodo) {
        No no = nodo.dir;
        nodo.dir = no.esq;
        no.esq = nodo;
        return no;
    }
    
    public void printTree(){
        if(this.raiz == null)
            System.out.println("Empty tree");
        else
            printTree(this.raiz);
    }
	
	  private void printTree(No raiz) {
         if(raiz != null ){
             printTree(raiz.esq);
             System.out.println(raiz.chave);
             printTree(raiz.dir);
         }
     }
}
