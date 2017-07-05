package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.List;

public class CarrinhoItem {

	private Produto produto;
	private TipoPreco tipoPreco;
	private Integer quantidade;

	public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
		this.produto = produto;
		this.tipoPreco = tipoPreco;
		
		/* Este código é necessário para que os preços sejam consultados no bd no momento 
		 * de adicionar o item no carrinho. Evitando o erro de lazyloading quando ia calcular
		 * o total, uma vez que a conexão com o bd já havia sido fechado pelo request. */
		if (produto.getPrecos() != null) this.produto.getPrecos().size();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	public Integer getQuantidade() {
		return quantidade == null ? 0 : quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		List<Precos> precos = produto.getPrecos();

		for (Precos preco : precos) {
			if (preco.getTipo() == tipoPreco) {
				return preco.getValor();
			}
		}
		return new BigDecimal(0);
	}

	public BigDecimal getTotal() {
		BigDecimal preco = getPreco();
		BigDecimal total = preco.multiply(new BigDecimal(getQuantidade()));
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result
				+ ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (tipoPreco != other.tipoPreco)
			return false;
		return true;
	}

}
