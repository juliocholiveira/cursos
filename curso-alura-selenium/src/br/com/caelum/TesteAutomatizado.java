package br.com.caelum;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizado {
	
	@Test
	public void deveRetornarAlgumResultado(){
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.google.com.br");
		
		WebElement findElement = driver.findElement(By.name("q"));
		findElement.sendKeys("Caelum");
		
		WebElement btnPesquisar = driver.findElement(By.name("btnK"));
		btnPesquisar.click();
		
		boolean achouCaelum = driver.getPageSource().contains("Caelum");
		driver.close();
		
		assertTrue(achouCaelum);
		
	}
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com.br");
		WebElement findElement = driver.findElement(By.name("q"));
		findElement.sendKeys("Caelum");
		findElement.submit();
		driver.close();
	}

}
