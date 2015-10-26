package br.com.casadocodigo.loja.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String title;
	@NotBlank
	@Lob
	private String description;
	@NotNull
	@Min(30)
	private Integer pages;

	private Calendar releaseDate;

	@ElementCollection
	private List<Price> prices = new ArrayList<Price>();

	public Product() {
	}

	public Product(String title, String description, int pages) {
		super();
		this.title = title;
		this.description = description;
		this.pages = pages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

}
