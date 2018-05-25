package br.ufc.quixada.eda.testes;

import java.io.IOException;
import java.util.List;

import br.ufc.quixada.eda.grafo.Aresta;
import br.ufc.quixada.eda.grafo.Grafo;
import br.ufc.quixada.eda.listaprioridades.HeapMaximo;
import br.ufc.quixada.eda.util.CriarInstancia;
import br.ufc.quixada.eda.util.EDAConstants;
import br.ufc.quixada.eda.util.EDAUtil;
import br.ufc.quixada.eda.util.Operacao;
import br.ufc.quixada.eda.util.OperacaoGrafo;

public class TesteArvoreGeradoraMinima {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			for (int tamanho : CriarInstancia.tamanhoInstancias) {	
				for(int i = 0; i < 4; i++){
					String arquivoOperacao = "tb8ch" + tamanho + "_" + i;
					String path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
					List<OperacaoGrafo> operacoes = (List<OperacaoGrafo>) EDAUtil.lerGrafo(path);
					
					long tempoInicial = System.currentTimeMillis();
					Grafo g = new Grafo();
					Aresta [] arestas = null;
 					
					for(OperacaoGrafo operacao : operacoes){
						arestas[i] = new Aresta(operacao.getAresta(), operacao.getVertice(), operacao.getPeso());
						g.setArestas(arestas);
					}
					long tempo = System.currentTimeMillis() - tempoInicial;			  
					System.out.println("Tempo gasto por -> " + arquivoOperacao + ": " + tempo);
				}
			} 
		
	}catch (IOException e) {
		e.printStackTrace();
	}
	}

}
