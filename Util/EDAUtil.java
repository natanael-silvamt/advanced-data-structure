package br.ufc.quixada.eda.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import br.ufc.quixada.eda.grafo.Aresta;
import br.ufc.quixada.eda.grafo.Grafo;
import br.ufc.quixada.eda.grafo.ListaAdjacencia;

public class EDAUtil {
	private static Aresta [] arestas;

	/**
	 * Ler o arquivo que contem as prioridades iniciais da lista de prioridades.
	 * @param path
	 * @return
	 * @throws IOException
	 */
    public static List<Integer> getDadosIniciais(String path) throws IOException {
        List<Integer> entrada = new ArrayList<Integer>();
        Scanner scanner = new Scanner(new FileReader(path)).useDelimiter("\r\n");
		while (scanner.hasNext())
			entrada.add(scanner.nextInt());
		
		scanner.close();
        return entrada;
    }
    
    /**
     * Ler as operacoes que serao realizadas na lista de prioridades apos a sua criacao.
     * @param path
     * @return
     * @throws IOException
     */
    public static List<Operacao> getOperacoes(String path) throws IOException {
        List<Operacao> operacoes = new ArrayList<Operacao>();
        Scanner scanner = new Scanner(new FileReader(path)).useDelimiter(" |\r\n");	
		while (scanner.hasNext())
			operacoes.add(new Operacao(scanner.next(), scanner.nextInt(), scanner.nextInt()));
			
		scanner.close();
        return operacoes;
    }   
    
    public static List<Pessoa> getDados(String path) throws IOException {
    	List<String> dados = new ArrayList<>();
		List<Pessoa> lista = new ArrayList<Pessoa>();
    	String linha = "";
    //	String cam = EDAConstants.caminhoPasta + "pessoasC" + ".txt";
    	FileReader fr = new FileReader(path);
    	BufferedReader br = new BufferedReader(fr);
    	while((linha=br.readLine()) != null){
    		dados.add(linha);
   		}
    	
    	for(int i = 0; i < dados.size(); i++) {
    		Scanner scanner = new Scanner(dados.get(i)).useDelimiter(", ");
			while(scanner.hasNext())
				lista.add(new Pessoa(scanner.next().substring(5), scanner.next().substring(4), scanner.next().substring(3),
				scanner.next().substring(15), scanner.next().substring(6), scanner.next().substring(11), scanner.next().substring(4),
				scanner.next().substring(4), scanner.next().substring(7), scanner.next().substring(7), scanner.next().substring(7),
				scanner.next().substring(7), scanner.next().substring(9), scanner.next().substring(8)));
				scanner.close();
    	}
    	
//    	for(Pessoa p : lista) {
//			System.out.println(p.getCpf());
//		}
    	
//    	System.out.println(lista.size());
    	br.close();
    	fr.close();
    	return lista;
    }
    
    public static Grafo lerGrafo(String path) throws IOException{
    	Grafo g = null;

    	Scanner scanner = new Scanner(new FileReader(path)).useDelimiter(" |\r\n");
    	
    	if(scanner.hasNext()){
	  //  	g.setQtd_vertices(scanner.nextInt());
	    //	g.setQtd_arestas(scanner.nextInt());
    //	}
    		g = new Grafo(scanner.nextInt(), scanner.nextInt());
    		//Aresta aresta[] = new Aresta[g.getQtd_arestas()];
    	   ListaAdjacencia adj[] = new ListaAdjacencia[g.getQtd_vertices() + 1];
    	   for(int i = 0; i < adj.length; i++){
    		   adj[i] = new ListaAdjacencia();
    	   }
	    	int i = 0;
	
	    	while(scanner.hasNext()){
	    		int u = scanner.nextInt();
	    		int v = scanner.nextInt();
	    		Aresta a = new Aresta(u, v, scanner.nextInt());
	    		adj[u].add(a);
	    		adj[v].add(a);
	    		
	    		
	    		//aresta[i] = new Aresta(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	    		//i++;
	    		//listaAresta.add(new Aresta(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
	    	}
	    	scanner.close();
	    	g.setAdj(adj);
	    	return g;
    	}
    	return null;
    }
    
    //Ordenação usando o metodo do java
    public static void ordenarListaAresta(List<Aresta> arestas){
    	Collections.sort(arestas, new Comparator<Aresta>(){
			@Override
			public int compare(Aresta arg0, Aresta arg1) {
				if(arg0.getPeso_aresta() < arg1.getPeso_aresta()) return -1;
				else if(arg0.getPeso_aresta() > arg1.getPeso_aresta()) return 1;
				else return 0;
			}
    	});
    }
    
    //Implementação do quickShort 
    public static void quickSort(Aresta[] arestas, int i, int j){
    	if(i < j){
    		int posicaoPivo = particiona(arestas, i, j);
    		quickSort(arestas, i, posicaoPivo - 1);
    		quickSort(arestas, posicaoPivo + 1, j);
    	}
    }
    
    private static int particiona(Aresta[] arestas, int i, int j){
    	int pivo = j;
    	int q = i - 1;
    	
    	for(int k = i; k < j; k++){
    		if(arestas[k].getPeso_aresta() < arestas[pivo].getPeso_aresta()){
    			q++;
    			Aresta aux = arestas[k];
    			arestas[k] = arestas[q];
    			arestas[q] = aux;
    		}
    	} 
    	
    	Aresta aux = arestas[q + 1];
    	arestas[q + 1] = arestas[j];
    	arestas[j] = aux;
    	
    	return q + 1;
    }   	
}
