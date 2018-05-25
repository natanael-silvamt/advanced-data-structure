package br.ufc.quixada.eda.RubroNegra;

public class RubroNegra<Chave extends Comparable<Chave>, Info> {
    private static final boolean VERMELHO   = true;
    private static final boolean PRETO = false;	
    private No raiz;
    private class No {
        public Chave chave;
        public Info info;
        public No esq, dir, pai;
        public boolean cor;

        public No(Chave chave, Info info, boolean cor) {
            this.chave   = chave;
            this.info = info;
            this.cor = cor;
        }
    }
    
    /**
     * Retorna true se a cor do n� for preta.
     * @param no
     * @return
     */
    private boolean isPreto(No no) {
         if (no == null) return true;
         return (no.cor == PRETO);
    }
    
    /**
     * Retorna o dado armazenado pela chave. Caso n�o tenha nenhuma dado com a chave, devolve null.
     * @param chave
     * @return
     */
    public Info busca(Chave chave){
    	 if(chave == null) return null;
    	 return busca(raiz, chave);
    }
       
    /**
     * M�todo privado que realiza a busca propriamente dito.
     * @param raiz
     * @param chave
     * @return
     */
    private Info busca(No raiz, Chave chave){
    	 if(raiz == null) return null;
    	
    	 int cmp = chave.compareTo(raiz.chave);
    	 if(cmp < 0) return busca(raiz.esq, chave);
    	 else if(cmp > 0) return busca(raiz.dir, chave);
    	 else return raiz.info;
    }    
    
    /*
     * Esse atributo n� ir� armazenar o n� que foi inserido para realizar a reorganiza��o na �rvore. 
     */
    private No noInserido = null;
    public void inserir(Chave chave, Info info){
    	if(chave == null) throw new IllegalArgumentException("Chave deve ser diferente de null.");

    	noInserido = null;//Seta em null.
    	raiz = inserir(raiz, chave, info);
    	
    	//Caso realmente foi inserido, realizar a reorganiza��o.
    	if(noInserido != null) reorganizarInsercao(noInserido);
    	raiz.cor = PRETO;
    	raiz.pai = null;
    }
    
    /**
     * M�todo privado que realiza a inser��o. Identico a da bin�ria de busca.
     * @param raiz
     * @param chave
     * @param info
     * @return
     */
    private No inserir(No raiz, Chave chave, Info info){
    	 if(raiz == null){
    		 //Aqui seta o atributo noInserido para aponta para o novo no.
    		 noInserido = new No(chave, info, VERMELHO);
    		 return noInserido;
    	 }
    	
	   	 int cmp = chave.compareTo(raiz.chave);	   	 
	   	 if(cmp < 0){
	   		 raiz.esq = inserir(raiz.esq, chave, info);
	   		 raiz.esq.pai = raiz;
	   	 }
	   	 else if(cmp > 0){
	   		 raiz.dir = inserir(raiz.dir, chave, info);
	   		 raiz.dir.pai = raiz;
	   	 }	         
	   	 return raiz;
    }  
    
    /**
     * M�todo privado que realiza a reorganiza��o ap�s a inser��o do n�.
     * Pessoal, n�o conseguir implementa-lo de forma recursivo, implementei de forma iterativa.
     * @param z
     */
    private void reorganizarInsercao(No z){
    	/*
    	 * Caso o pai do n� que foi inserido seja preto, n�o faz nada pois a �rvore ainda 
    	 * � rubro negra, mesmo ap�s a inser��o.
    	 * Caso o pai seja vermelho, realiza a reorganiza��o.
    	 */
    	while(!isPreto(z.pai)){
    		if(z.pai == z.pai.pai.esq){//Caso onde o z descendente a esquerda de seu av�.
    			No y = z.pai.pai.dir;//Tio do z. Posivelmente null.
    			if(!isPreto(y)){
    				/*
    				 * Caso 1: O tio do seja vermelho. Como o pai do z � vermelho, 
    				 * apenas pinta o pai e o tio z de preto, e  pinta o av� de z de vermenho.
    				 * Faz o av� de z sendo o novo z.
    				 * Caso o pai do novo z seja preto, a �rvore voltou a ser rubro negra,
    				 * caso contr�rio, continua a recolora��o.
    				 */
    				z.pai.cor = PRETO;
    				y.cor = PRETO;
    				z.pai.pai.cor = VERMELHO;
    				z = z.pai.pai;
    			}else{
    				if(z == z.pai.dir){//Caso 2
    					/*
    					 * Transforma para aplicar o caso 3.  
    					 */
    					z = z.pai;    					
    	   				No pai = z.pai;
        				if(pai.esq == z) pai.esq = rotacaoEsquerda(z);
        				else pai.dir = rotacaoEsquerda(z);  
    				}
    				//Caso 3
    				/*
    				 * O z e seu pai � vermelho e o tio do z � preto.
    				 * Um rota��o a direita no av� do z e altera��o de cores de alguns n�.
    				 * Ap�s esse caso, ele voltou a ser rubro negra e sai da itera��o.
    				 */
    				z.pai.cor = PRETO;
    				z.pai.pai.cor = VERMELHO;
    				
	   				No pai = z.pai.pai.pai;
	   				if(pai == null) raiz = rotacaoDireita(z.pai.pai);
	   				else if(pai.esq == z.pai.pai) pai.esq = rotacaoDireita(z.pai.pai);
    				else pai.dir = rotacaoDireita(z.pai.pai);          				    				
    			}    			
    		}else{//Caso onde o z descendente a direita de seu av�.
    			No y = z.pai.pai.esq;
    			if(!isPreto(y)){//Caso 1
    				z.pai.cor = PRETO;
    				y.cor = PRETO;
    				z.pai.pai.cor = VERMELHO;
    				z = z.pai.pai;
    			}else{//Caso 2
    				if(z == z.pai.esq){
    					z = z.pai;    					
    	   				No pai = z.pai;
        				if(pai.esq == z) pai.esq = rotacaoDireita(z);
        				else pai.dir = rotacaoDireita(z);  
    				}	
    				//Caso 3
    				z.pai.cor = PRETO;
    				z.pai.pai.cor = VERMELHO;
    				
	   				No pai = z.pai.pai.pai;
	   				if(pai == null) raiz = rotacaoEsquerda(z.pai.pai);
	   				else if(pai.esq == z.pai.pai) pai.esq = rotacaoEsquerda(z.pai.pai);
    				else pai.dir = rotacaoEsquerda(z.pai.pai);          				
    			}      			
    		}
    	}
    }    
    
    /*
     * Esse atributo n� ir� armazenar o n� que foi exclu�do 
     * no caso onde ele n�o tem filhos e � preto. Foi necess�rio pois n�o conseguir 
     * implementar a remo��o desse caso espec�fico de forma recursiva.
     * Nos demais casos, utilizei a recurs�o e n�o preciso utilizar esse atributo.
     */    
    private No noExcluido = null;
    public void remover(Chave chave){
    	if(chave == null) throw new IllegalArgumentException("Chave deve ser diferente de null.");
    	    	
    	noExcluido = null;
    	raiz = remover(raiz, chave, null);
    	
    	if(noExcluido != null) reorganizarRemocao(noExcluido);
    	
    	if(raiz != null) raiz.cor = PRETO;
    }
    
    /**
     * M�todo privado que realiza a remo��o. Quase identico a da bin�ria de busca.
     * @param raiz
     * @param chave
     * @param pai
     * @return
     */
    private No remover(No raiz, Chave chave, No pai){
   	 	 if(raiz == null) return null;
 	
	   	 int cmp = chave.compareTo(raiz.chave);
	   	 
	   	 if(cmp < 0) raiz.esq = remover(raiz.esq, chave, raiz);
	   	 else if(cmp > 0) raiz.dir = remover(raiz.dir, chave, raiz);
	   	 else{
	   		 if(raiz.esq == null && raiz.dir == null){
	   			 if(!isPreto(raiz)) raiz = null;//N�o tem nenhum filho e � vermelho, apenas remove a refer�ncia do no.
	   			 else{//N�o tem nenhum filho e � preto, verifica os 4 casos "explicado" em sala :).
	   				noExcluido = raiz;
	   			 }
	   		 }
	   		 else if(raiz.esq == null || raiz.dir == null){//Apenas um filho, logo ele � preto, atualiza a nova raiz, em outra palavras, atualiza o pai indicando seu novo filho.	   			 
	   			 raiz = (raiz.esq == null ? raiz.dir : raiz.esq);
	   			 raiz.cor = PRETO;
	   		 }else{//Dois filhos, busca o sucessor e copia seu valor para esse n� e exclui o sucessor.
	   			 /*
	   			  * Busca pelo sucessor e copia os dados do sucessor para o n� a ser exclu�do.
	   			  */
	   			 No suces = sucessor(raiz.dir);
	   			 raiz.info = suces.info;
	   			 raiz.chave = suces.chave;
	   			 
	   			 /*
	   			  * Exclui na verdade, o sucessor.
	   			  */
	   			 if(suces.dir != null){//Caso em que o sucessor tem o filho direito
	   				 suces.pai.esq = suces.dir;
	   				 suces.dir.pai = suces.pai;
	   				 suces.dir.cor = PRETO;	
	   			 }else if(!isPreto(suces)) suces.pai.esq = null;//Caso em que o sucessor n�o tem filho e � vermelho.
	   			 else{
	   				noExcluido = suces;//N�o tem nenhum filho e � preto, verifica os 4 casos "explicado" em sala :).
	   			 }
	   		 }
	   	 }
	   	 return raiz;    	
    }  
    private void reorganizarRemocao(No x){
    	if(x.pai == null){
    		raiz = null;
    		return;
    	}
    	
    	/*
    	 * Primeiro fa�o toda a reorganiza��o primeiro e s� depois excluo o n�.
    	 * Armazeno o n� a ser removido nessa vari�vel abaixo.
    	 */
    	No noRemover = x;
    	while(isPreto(x) && x != raiz){
    		if(x == x.pai.esq){//Caso onde x � filho a esquerdo de seu pai.
    			No w = x.pai.dir;//Irm�o de x. Possilvemente null.
    			if(!isPreto(w)){//Caso 1
    				/*
    				 * W � vermelho. 
    				 * Altera a �rvore apenas para aplicar um dos demais casos, ou seja, 
    				 * n�o torna a �rvore rubro negra.
    				 */
    				w.cor = PRETO;
    				x.pai.cor = VERMELHO;
    				
    				No avo = x.pai.pai;
    				if(avo.esq == x.pai) avo.esq = rotacaoEsquerda(x.pai);
    				else avo.dir = rotacaoEsquerda(x.pai);    				
    				w = x.pai.dir;
    			}
    			
    			if(w != null && isPreto(w.esq) && isPreto(w.dir)){//Caso 2
    				/*
    				 * W e seus filhos pretos.
    				 * Torna w vermelho e atualiza o x.
    				 */
    				w.cor = VERMELHO;
    				x = x.pai;
    			}else{
    				if(w != null && isPreto(w.dir)){//Caso 3
    					/*
    					 * W e seu filho direito preto e esquerdo vermelho.
    					 * Altera para aplicar o caso 4, ou seja, n�o torna a �rvore rubro negra.
    					 */
    					w.esq.cor = PRETO;
    					w.cor = VERMELHO;
    					x.pai.dir = rotacaoDireita(w);
    					w = x.pai.dir;
    				}
    				//Caso 4
    				/*
    				 * W preto e seu filho direito vermelho.
    				 * Ap�s esse caso, ele voltou a ser rubro negra e sai da itera��o.
    				 */
					if(w != null) w.cor = w.pai.cor;
					if(w != null) w.pai.cor = PRETO;
					if(w != null && w.dir != null) w.dir.cor = PRETO;
					
    				No avo = x.pai.pai;
    				if(avo != null){
        				if(avo.esq == x.pai) avo.esq = rotacaoEsquerda(x.pai);
        				else avo.dir = rotacaoEsquerda(x.pai);       					
    				}
  					    				
    				x = raiz;
    			}
    		}else{//Caso onde x � filho a direito de seu pai.
    			No w = x.pai.esq;
    			if(!isPreto(w)){//Caso 1
    				w.cor = PRETO;
    				x.pai.cor = VERMELHO;
    				No avo = x.pai.pai;
    				if(avo.esq == x.pai) avo.esq = rotacaoDireita(x.pai);
    				else avo.dir = rotacaoDireita(x.pai);    				
    				w = x.pai.esq;
    			}
    			
    			if(w != null && isPreto(w.esq) && isPreto(w.dir)){//Caso 2
    				w.cor = VERMELHO;
    				x = x.pai;
    			}else{
    				if(w != null && isPreto(w.esq)){//Caso 3
    					w.esq.cor = PRETO;
    					w.cor = VERMELHO;
    					x.pai.dir = rotacaoEsquerda(w);
    					w = x.pai.esq;
    				}
    				
    				//Caso 4
					if(w != null) w.cor = w.pai.cor;
					if(w != null) w.pai.cor = PRETO;
					if(w != null) w.esq.cor = PRETO;
					
    				No avo = x.pai.pai;
    				if(avo != null){
        				if(avo.esq == x.pai) avo.esq = rotacaoDireita(x.pai);
        				else avo.dir = rotacaoDireita(x.pai);      					
    				}
   					    				
    				x = raiz;
    			}    			
    		}
    	}
    	/*
    	 * Ap�s a reorganiza��o, remove efetivamente o n�, atualizando o ponteiro do pai.
    	 */
    	if(noRemover == noRemover.pai.esq) noRemover.pai.esq = null;
    	else noRemover.pai.dir = null;    	
    }
    
    
    /**
     * Funciona apenas para buscar o sucessor de n� com dois filhos.
     * Para esse c�digo funcionar, deve-se na primeira chamada, n�o passar o n� que de deseja pegar o sucessor mas seu filho a direita.
     *  
     * @param raiz
     * @return
     */
    private No sucessor(No raiz){   	
    	if(raiz.esq != null) return sucessor(raiz.esq);
    	else return raiz; 
    }    
    
    private No rotacaoDireita(No no) {
        No x = no.esq;
        if(x != null) no.esq = x.dir;
        if(x != null) x.dir = no;
   	 
        if(x != null) x.pai = no.pai;
        no.pai = x;
        if(no.esq != null) no.esq.pai = no;        
        return x;
    }

    private No rotacaoEsquerda(No no) {
        No x = no.dir;
        if(x != null) no.dir = x.esq;
        if(x != null) x.esq = no;
   	 	
        if(x != null) x.pai = no.pai;
        no.pai = x;
        if(no.dir != null) no.dir.pai = no;
        return x;
    }    
    
    
    /**
     * Imprimir em em-ordem para verificar se est� correto.
     */
    public void imprimirEmOrdem(){
    	imprimirEmOrdem(raiz);
    }
    private void imprimirEmOrdem(No raiz){
    	if(raiz != null){
    		System.out.print(" (" + raiz.chave + ", " + raiz.cor + ")");
    		imprimirEmOrdem(raiz.esq);
    		imprimirEmOrdem(raiz.dir);
    	}
    }      
    
    public static void main(String[] args) {
    	RubroNegra<Integer, Integer> rn = new RubroNegra<Integer, Integer>();
    	rn.inserir(5, 5);
        rn.inserir(9, 9);
        rn.inserir(13, 13);
        rn.inserir(11, 11);
        rn.inserir(8, 8);
        rn.inserir(4, 4);
        rn.inserir(3, 3);
        rn.inserir(2, 2);
        rn.inserir(15, 15);
        rn.inserir(19, 19); 
        rn.inserir(25, 25);
        rn.remover(11);
        rn.remover(8);
        rn.imprimirEmOrdem();
    }    
}
