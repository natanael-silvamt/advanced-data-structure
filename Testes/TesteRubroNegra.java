package br.ufc.quixada.eda.testes;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import br.ufc.quixada.eda.RubroNegra.RubroNegra;
import br.ufc.quixada.eda.util.CriarInstancia;
import br.ufc.quixada.eda.util.EDAConstants;
import br.ufc.quixada.eda.util.EDAUtil;
import br.ufc.quixada.eda.util.Pessoa;

public class TesteRubroNegra {

	public static void main(String[] args) {
		RubroNegra arvore = new RubroNegra();
		try {
			for (int tamanho : CriarInstancia.tamanhoInstancias) {	
					String arquivoOperacao = "pessoasC";
					String path = EDAConstants.caminhoPasta + arquivoOperacao + ".txt";
					List<Pessoa> dados = (List<Pessoa>) EDAUtil.getDados(path);
					
					long tempoI = System.currentTimeMillis(); 					
					for(Pessoa dados_p : dados){
						arvore.inserir(dados_p.getCpf(), dados_p);
					}
					long tempI = System.currentTimeMillis() - tempoI;			  
				
					Set<Integer> numeros = new TreeSet<Integer>();
					Random rand = new Random();
					while (numeros.size() < 3000) {
					   numeros.add(rand.nextInt(4570));
					}
					
					long tempoB = System.currentTimeMillis(); 					
					for(Integer num : numeros){
						System.out.println(arvore.busca(dados.get(num).getCpf()));
					}
					long tempB = System.currentTimeMillis() - tempoB;	
					
					System.out.println("Tempo gasto por -> " + "Inserção" + ": " + tempI);
					System.out.println("Tempo gasto por -> " + "Busca" + ": " + tempB);

			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
