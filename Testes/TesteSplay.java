package br.ufc.quixada.eda.testes;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import br.ufc.quixada.eda.Splay.Splay;
import br.ufc.quixada.eda.util.CriarInstancia;
import br.ufc.quixada.eda.util.EDAConstants;
import br.ufc.quixada.eda.util.EDAUtil;
import br.ufc.quixada.eda.util.Pessoa;

public class TesteSplay {

	public static void main(String[] args) {
		Splay arvore = new Splay();
		try {
			for (int tamanho : CriarInstancia.tamanhoInstancias) {	
					String arquivoOperacao = "pessoasC";
					String path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
					List<Pessoa> dados = (List<Pessoa>) EDAUtil.getDados(path);
					
					Set<Integer> numerosI = new TreeSet<Integer>();
					Random randI = new Random();
					while (numerosI.size() < 5230) {
					   numerosI.add(randI.nextInt(10460));
					}
					
					long tempoI = System.currentTimeMillis(); 	
					for(Integer num : numerosI){
						arvore.inserir(dados.get(num).getCpf(), dados);
					}
					long tempI = System.currentTimeMillis() - tempoI;	
					System.out.println("Tempo gasto por -> " + "Inserção" + ": " + tempI);
					
					Set<Integer> numerosB = new TreeSet<Integer>();
					Random randB = new Random();
					while (numerosB.size() < 1569) {
					   numerosB.add(randB.nextInt(5228));
					}
					long tempoB = System.currentTimeMillis(); 					
					for(Integer num : numerosB){
						arvore.buscar(dados.get(num).getCpf());
					}
					long tempB = System.currentTimeMillis() - tempoB;
					System.out.println("Tempo gasto por -> " + "Busca" + ": " + tempB);
					
					Set<Integer> numerosR = new TreeSet<Integer>();
					Random randR = new Random();
					while (numerosR.size() < 1046) {
					   numerosR.add(randR.nextInt(5225));
					}
					long tempoR = System.currentTimeMillis(); 					
					for(Integer num : numerosR){
						arvore.remover(dados.get(num).getCpf());
					}
					long tempR = System.currentTimeMillis() - tempoR;
					System.out.println("Tempo gasto por -> " + "Remoção" + ": " + tempR);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
