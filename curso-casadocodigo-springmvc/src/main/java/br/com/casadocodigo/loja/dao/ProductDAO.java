package br.com.casadocodigo.loja.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.Product;

@Repository
public class ProductDAO extends GenericDAO<Product> {

	public List<Product> lista() {
		return manager.createQuery("select p from Product p").getResultList();
	}

	public Product buscaPorId(Long id) {
		return manager.find(Product.class, id);
	}

}