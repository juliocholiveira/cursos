package br.com.casadocodigo.loja.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.Product;

@Repository
public class ProductDAO extends GenericDAO<Product> {

	public ProductDAO() {
	}

	public List<Product> list() {
		return manager.createQuery(
				"select distinct(p) from Product p join fetch p.prices",
				Product.class).getResultList();
	}

	public Product buscaPorId(Long id) {
		return manager.find(Product.class, id);
	}

}