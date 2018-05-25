package br.ufc.quixada.eda.util;

/**
 * Classe responsável por armazenar uma operação que será realizada na lista de prioridade.
 * O atributo id contém o identificador da operação sendo as seguintes possibilidades: Inserção: I; Remoção: R; Alteração: A; S: Seleção.
 * O atributo valor terá o valor a ser inserido, o valor que foi removido, o valor que será alterado e o valor com maior prioridade, respectivamente para as operações de inserção, remoção, alteração e seleção.
 * O atributo novoValor terá o novo valor da prioridade para a operação de alteração. Para as demais operações ele terá valor 0(zero).  
 * @author fabio
 *
 */
public class Operacao {
	private String id;
	private Integer valor;
	private Integer novoValor;
	
	public Operacao(String id, Integer valor, Integer novoValor) {
		this.id = id;
		this.valor = valor;
		this.novoValor = novoValor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public Integer getNovoValor() {
		return novoValor;
	}
	public void setNovoValor(Integer novoValor) {
		this.novoValor = novoValor;
	}
}
