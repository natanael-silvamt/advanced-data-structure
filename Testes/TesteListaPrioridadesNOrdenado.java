package br.ufc.quixada.eda.testes;

import java.io.IOException;
import java.util.List;

import br.ufc.quixada.eda.listaprioridades.LPMaximaNOrdenada;
import br.ufc.quixada.eda.util.CriarInstancia;
import br.ufc.quixada.eda.util.EDAConstants;
import br.ufc.quixada.eda.util.EDAUtil;
import br.ufc.quixada.eda.util.Operacao;

public class TesteListaPrioridadesNOrdenado {
    public static void main(String args[]){		
	try {
            for (int tamanho : CriarInstancia.tamanhoInstancias) {				
                String path = EDAConstants.caminhoPasta + "tarefa" + tamanho + ".txt";
		List<Integer> entrada = EDAUtil.getDadosIniciais(path);
				
		//PARA ARQUIVO COM MAIOR QUANTIDADE DE INSERCOES
		String arquivoOperacao = "operacoesI_" + tamanho;
		path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
		List<Operacao> operacoes = EDAUtil.getOperacoes(path);
				
		long tempoInicial = System.currentTimeMillis();	
		LPMaximaNOrdenada listaPrioridade = new LPMaximaNOrdenada(2 * entrada.size());
		listaPrioridade.contruir(entrada);							
			
		for (Operacao operacao : operacoes) {
			if(operacao.getId().equals("I")){
				listaPrioridade.inserir(operacao.getValor());
			}
			else if(operacao.getId().equals("A")){
				listaPrioridade.alterarPrioridade(operacao.getValor(), operacao.getNovoValor());
			}
			else if(operacao.getId().equals("S")){
				listaPrioridade.getMaximaPrioridade();
			}
			else if(operacao.getId().equals("R")){
				listaPrioridade.remove();
			}
                  //  System.out.println(operacao.getId() + " " + operacao.getValor() + " " + operacao.getNovoValor());
		}	
		long tempo = System.currentTimeMillis() - tempoInicial;	
		System.out.println("Tempo gasto por -> " + arquivoOperacao + ": " + tempo);
				
//		//PARA ARQUIVO COM MAIOR QUANTIDADE DE ALTERACOES
		arquivoOperacao = "operacoesA_" + tamanho;
		path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
		operacoes = EDAUtil.getOperacoes(path);
		
		tempoInicial = System.currentTimeMillis();				
		listaPrioridade = new LPMaximaNOrdenada(2*entrada.size());
		listaPrioridade.contruir(entrada);							
			
		for (Operacao operacao : operacoes) {
			if(operacao.getId().equals("A")){
				if(operacao.getId().equals("I")){
					listaPrioridade.inserir(operacao.getValor());
				}
				else if(operacao.getId().equals("A")){
					listaPrioridade.alterarPrioridade(operacao.getValor(), operacao.getNovoValor());
				}
				else if(operacao.getId().equals("S")){
					listaPrioridade.getMaximaPrioridade();
				}
				else if(operacao.getId().equals("R")){
					listaPrioridade.remove();
				}
		}
		}
		tempo = System.currentTimeMillis() - tempoInicial;			  
		System.out.println("Tempo gasto por -> " + arquivoOperacao + ": " + tempo);	
		
//		//PARA ARQUIVO COM MAIOR QUANTIDADE DE SELECAO
		arquivoOperacao = "operacoesS_" + tamanho;
		path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
		operacoes = EDAUtil.getOperacoes(path);
		
		tempoInicial = System.currentTimeMillis();
		listaPrioridade = new LPMaximaNOrdenada(2 * entrada.size());
		listaPrioridade.contruir(entrada);
		
		for(Operacao operacao : operacoes){
			if(operacao.getId().equals("S")){
				if(operacao.getId().equals("I")){
					listaPrioridade.inserir(operacao.getValor());
				}
				else if(operacao.getId().equals("A")){
					listaPrioridade.alterarPrioridade(operacao.getValor(), operacao.getNovoValor());
				}
				else if(operacao.getId().equals("S")){
					listaPrioridade.getMaximaPrioridade();
				}
				else if(operacao.getId().equals("R")){
					listaPrioridade.remove();
				}
		}
		}
		
		tempo = System.currentTimeMillis() - tempoInicial;
		System.out.println("Tempo gasto por -> " + arquivoOperacao + ": " + tempo);
//				
//		//PARA ARQUIVO COM MAIOR QUANTIDADE DE REMOCAO
		arquivoOperacao = "operacoesR_" + tamanho;
		path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
		operacoes = EDAUtil.getOperacoes(path);
		
		tempoInicial = System.currentTimeMillis();
		listaPrioridade = new LPMaximaNOrdenada(2 * entrada.size());
		listaPrioridade.contruir(entrada);
		
		for(Operacao operacao : operacoes){
			if(operacao.getId().equals("R")){
				if(operacao.getId().equals("I")){
					listaPrioridade.inserir(operacao.getValor());
				}
				else if(operacao.getId().equals("A")){
					listaPrioridade.alterarPrioridade(operacao.getValor(), operacao.getNovoValor());
				}
				else if(operacao.getId().equals("S")){
					listaPrioridade.getMaximaPrioridade();
				}
				else if(operacao.getId().equals("R")){
					listaPrioridade.remove();
				}
		}
		}
		
		tempo = System.currentTimeMillis() - tempoInicial;
		System.out.println("Tempo gasto por -> " + arquivoOperacao + ": " + tempo);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
