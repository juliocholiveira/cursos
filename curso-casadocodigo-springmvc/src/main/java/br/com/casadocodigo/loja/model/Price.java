package br.com.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
public class Price {

	@Min(1)
	private BigDecimal value;
	private BookType bookType;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

}
