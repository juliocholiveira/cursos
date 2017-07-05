package br.com.caelum;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AluraPage {
	
	private WebDriver driver;
	
	public AluraPage() {
		driver = new FirefoxDriver();
	}
	
	public void conectar(){
		driver.get("https://www.alura.com.br/loginForm");
		driver.findElement(By.id("login-email")).sendKeys("juliocholiveira@gmail.com");
		WebElement senha = driver.findElement(By.name("password"));
		senha.sendKeys("jc0601,.");
		senha.submit();
	}
	
	public void imprimeTrilhas(){
		
		List<String> links = new ArrayList<String>();
		
		for (WebElement webElement : driver.findElements(By.tagName("a"))) {
			if (webElement.getAttribute("href").contains("path")) {
				links.add(webElement.getAttribute("href"));
			}
		}
		
		for (String string : links) {
			imprimeCursos(string);
		}
	}
	
	public void imprimeCursos(String trilha){
		driver.get(trilha);
		List<WebElement> cursos = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : cursos) {
			if (webElement.getAttribute("href").contains("course")) {
				System.out.println(webElement.getAttribute("href"));
			}
		}
		
	}
	
	public void fechar(){
		driver.close(); 
	}
	
	public static void main(String[] args) {
		
		AluraPage aluraPage = new AluraPage();
		
		aluraPage.conectar();
		aluraPage.imprimeTrilhas();
		aluraPage.fechar();
		
		
	}

}
