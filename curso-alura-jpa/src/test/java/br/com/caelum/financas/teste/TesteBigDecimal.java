package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TesteBigDecimal {

	public static void main(String[] args) {
		
		/*
		 * Ver explicação sobre o BigDecimal em: http://www.devmedia.com.br/java-bigdecimal-trabalhando-com-mais-precisao/30286
		 * */
		System.out.println("Divide");
		System.out.println(new BigDecimal("1.00").divide(new BigDecimal("3"), 3 , RoundingMode.UP));

	}

}
