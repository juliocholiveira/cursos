package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CarrinhoItem> itens = new ArrayList<CarrinhoItem>();

	public Collection<CarrinhoItem> getItens() {
		return itens;
	}

	public void add(CarrinhoItem carrinhoItem) {
		if (itens.contains(carrinhoItem)) {
			itens.get(itens.indexOf(carrinhoItem)).setQuantidade(
					itens.get(itens.indexOf(carrinhoItem)).getQuantidade() + 1);
		} else {
			carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() + 1);
			itens.add(carrinhoItem);
		}
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);

		itens.remove(itens.indexOf(new CarrinhoItem(produto, tipoPreco)));
	}

	/* Empressão lambda */
	public Integer getQuantidade() {
		// return itens.values().stream().reduce(0, (proximo, acumulador) ->
		// proximo + acumulador);
		Integer quantidade = 0;
		for (CarrinhoItem carrinhoItem : itens) {
			quantidade += carrinhoItem.getQuantidade();
		}
		return quantidade;
	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		for (CarrinhoItem carrinhoItem : itens) {
			total = total.add(carrinhoItem.getTotal());
		}
		return total;
	}

}
