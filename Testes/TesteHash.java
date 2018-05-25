package br.ufc.quixada.eda.testes;

import br.ufc.quixada.eda.hash.EnderecamentoExterno;
import br.ufc.quixada.eda.hash.EnderecamentoInterno;
import br.ufc.quixada.eda.hash.ExececaoEnderecamentoInterno;

public class TesteHash {

	public static void main(String[] args) {
		
		EnderecamentoInterno<String> in = new EnderecamentoInterno<String>(5);
		
		try{
			in.inserir(5, "hehe");
			in.inserir(10, "kkkk");
			in.inserir(9, "bb");
			in.inserir(11, "mel");
			in.inserir(6, "te");
			in.imprimir();
			
			System.out.println("\n\n" + in.remover(6));
			in.imprimir();
			
		}catch(ExececaoEnderecamentoInterno e){
			System.out.println(e.getMessage());
		}
		
		

	}

}
