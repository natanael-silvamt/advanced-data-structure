package br.ufc.quixada.eda.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import br.ufc.quixada.eda.listaprioridades.HeapMaximo;

/**
 * Cria as instancias juntamente com as operacoes.
 * @author fabio
 *
 */
public class CriarInstancia {	
	public static int tamanhoInstancias[] = {10459};
	//public static int tamanhoInstancias[] = {60, 100, 200, 300, 400, 500, 600, 700, 800, 900};
	//public static int tamanhoInstancias[] = {100, 10000, 50000, 100000, 500000, 800000};
	public static void criar(){
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			Random gerador = new Random(95487145);
			System.out.println("CRIACAO DA INSTANCIA DE ENTRADA");
						
			for (int tamanho : tamanhoInstancias) {					
				//System.out.println("DIGITE O TAMANHO (NUMERO PAR) (OU 0 PARA ENCERRAR): ");
				
				//int tamanho = scanner.nextInt();
				if(tamanho == 0) break;
				while(tamanho % 2 != 0){
					System.out.println("UM NUMERO PAR!!!!!!!: ");
					tamanho = scanner.nextInt();
				}
				
				FileWriter arq = new FileWriter(EDAConstants.caminhoPasta + "tarefa" + tamanho + ".txt");
				//FileWriter arq = new FileWriter(EDAConstants.caminhoPasta + "tarefa" + tamanho + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);	
				List<Integer> prioridadesIniciais = new ArrayList<Integer>();
				for(int i = 1; i <= tamanho; i++){
					Integer novoValor = gerador.nextInt(tamanho*5) + 1;
					
					while(prioridadesIniciais.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;
						
					prioridadesIniciais.add(novoValor);
				}
				for (Integer valor : prioridadesIniciais) gravarArq.println(valor);							
				arq.close();
				
				//GERANDO AS OPERA��ES				
				int qtd = 2;//TEM DE SER UM N�MERO PAR
				int qtdOperacoes = tamanho*qtd;	

				//OPERA��ES EM MAIOR QUANTIDADE: INSER��O
				arq = new FileWriter(EDAConstants.caminhoPasta + "operacoesI_" + tamanho + ".txt");
				gravarArq = new PrintWriter(arq);					
				HeapMaximo listaPrioridade = new HeapMaximo(qtd*prioridadesIniciais.size());
				listaPrioridade.contruir(prioridadesIniciais);	
				List<Integer> numeros = new ArrayList<Integer>();
				numeros.addAll(prioridadesIniciais);
				
				while(qtdOperacoes > 0){
					for(int j = 1; j <= qtd; j++){
						Integer novoValor = gerador.nextInt(tamanho*5) + 1;						
						while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;							
						numeros.add(novoValor);						
						listaPrioridade.inserir(novoValor);	
						gravarArq.println("I " + novoValor + " 0");
					}
					
					Integer maiorprioridade = listaPrioridade.remove();
					numeros.remove(maiorprioridade);
					gravarArq.println("R " + maiorprioridade + " 0");
					
					int indice = gerador.nextInt(numeros.size());
					Integer valorAlterar = numeros.get(indice);
					Integer novoValor = gerador.nextInt(tamanho*5) + 1;						
					while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;													
					listaPrioridade.alterarPrioridade(valorAlterar, novoValor);
					numeros.set(indice, novoValor);						
					gravarArq.println("A " + valorAlterar + " " + novoValor);	
					
					maiorprioridade = listaPrioridade.getMaximaPrioridade();
					gravarArq.println("S " + maiorprioridade + " 0");
											
					qtdOperacoes -= qtd + 2;
				}
				arq.close();			
								
				//OPERA��ES EM MAIOR QUANTIDADE: REMO��O
				qtdOperacoes = tamanho*qtd;
				arq = new FileWriter(EDAConstants.caminhoPasta + "operacoesR_" + tamanho + ".txt");
				gravarArq = new PrintWriter(arq);					
				listaPrioridade = new HeapMaximo(qtd*prioridadesIniciais.size());
				listaPrioridade.contruir(prioridadesIniciais);	
				numeros = new ArrayList<Integer>();
				numeros.addAll(prioridadesIniciais);
				
				while(qtdOperacoes > 0){
					Integer novoValor = gerador.nextInt(tamanho*5) + 1;						
					while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;							
					numeros.add(novoValor);						
					listaPrioridade.inserir(novoValor);	
					gravarArq.println("I " + novoValor + " 0");						

					for(int j = 1; j <= qtd; j++){
						Integer maiorprioridade = listaPrioridade.remove();
						numeros.remove(maiorprioridade);
						gravarArq.println("R " + maiorprioridade + " 0");
					}
					
					int indice = gerador.nextInt(numeros.size());
					Integer valorAlterar = numeros.get(indice);
					novoValor = gerador.nextInt(tamanho*5) + 1;						
					while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;													
					listaPrioridade.alterarPrioridade(valorAlterar, novoValor);
					numeros.set(indice, novoValor);						
					gravarArq.println("A " + valorAlterar + " " + novoValor);	
					
					Integer maiorprioridade = listaPrioridade.getMaximaPrioridade();
					gravarArq.println("S " + maiorprioridade + " 0");
											
					qtdOperacoes -= qtd + 2;
				}		
				arq.close();
				
				//OPERA��ES EM MAIOR QUANTIDADE: ALTERA��O
				qtdOperacoes = tamanho*qtd;
				arq = new FileWriter(EDAConstants.caminhoPasta + "operacoesA_" + tamanho + ".txt");
				gravarArq = new PrintWriter(arq);					
				listaPrioridade = new HeapMaximo(qtd*prioridadesIniciais.size());
				listaPrioridade.contruir(prioridadesIniciais);	
				numeros = new ArrayList<Integer>();
				numeros.addAll(prioridadesIniciais);
				
				while(qtdOperacoes > 0){
					Integer novoValor = gerador.nextInt(tamanho*5) + 1;						
					while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;							
					numeros.add(novoValor);						
					listaPrioridade.inserir(novoValor);	
					gravarArq.println("I " + novoValor + " 0");					
					
					Integer maiorprioridade = listaPrioridade.remove();
					numeros.remove(maiorprioridade);
					gravarArq.println("R " + maiorprioridade + " 0");
					
					for(int j = 1; j <= qtd; j++){
						int indice = gerador.nextInt(numeros.size());
						Integer valorAlterar = numeros.get(indice);
						novoValor = gerador.nextInt(tamanho*5) + 1;						
						while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;													
						listaPrioridade.alterarPrioridade(valorAlterar, novoValor);
						numeros.set(indice, novoValor);						
						gravarArq.println("A " + valorAlterar + " " + novoValor);	
					}					
					
					maiorprioridade = listaPrioridade.getMaximaPrioridade();
					gravarArq.println("S " + maiorprioridade + " 0");
											
					qtdOperacoes -= qtd + 2;						
				}
				arq.close();
				
				//OPERA��ES EM MAIOR QUANTIDADE: SELE��O
				qtdOperacoes = tamanho*qtd;
				arq = new FileWriter(EDAConstants.caminhoPasta + "operacoesS_" + tamanho + ".txt");
				gravarArq = new PrintWriter(arq);					
				listaPrioridade = new HeapMaximo(qtd*prioridadesIniciais.size());
				listaPrioridade.contruir(prioridadesIniciais);	
				numeros = new ArrayList<Integer>();
				numeros.addAll(prioridadesIniciais);
				
				while(qtdOperacoes > 0){
					Integer novoValor = gerador.nextInt(tamanho*5) + 1;						
					while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;							
					numeros.add(novoValor);						
					listaPrioridade.inserir(novoValor);	
					gravarArq.println("I " + novoValor + " 0");
					
					Integer maiorprioridade = listaPrioridade.remove();
					numeros.remove(maiorprioridade);
					gravarArq.println("R " + maiorprioridade + " 0");
					
					int indice = gerador.nextInt(numeros.size());
					Integer valorAlterar = numeros.get(indice);
					novoValor = gerador.nextInt(tamanho*5) + 1;						
					while(numeros.contains(novoValor)) novoValor = gerador.nextInt(tamanho*5) + 1;													
					listaPrioridade.alterarPrioridade(valorAlterar, novoValor);
					numeros.set(indice, novoValor);						
					gravarArq.println("A " + valorAlterar + " " + novoValor);	
					
					for(int j = 1; j <= qtd; j++){
						maiorprioridade = listaPrioridade.getMaximaPrioridade();
						gravarArq.println("S " + maiorprioridade + " 0");
					}
			
					qtdOperacoes -= qtd + 2;
				}
				arq.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String args[]){
		criar();
	}
}
