package br.com.capture;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.dao.AluraPageDAO;
import br.com.dao.JPAUtil;
import br.com.model.Aula;
import br.com.model.Curso;
import br.com.model.Trilha;

public class AluraPage {

	private WebDriver driver;

	public AluraPage() {
		driver = new FirefoxDriver();
	}

	public void conectar() {
		driver.get("https://www.alura.com.br/loginForm");
		driver.findElement(By.id("login-email")).sendKeys(
				"juliocholiveira@gmail.com");
		WebElement senha = driver.findElement(By.name("password"));
		senha.sendKeys("jc0601,.");
		senha.submit();
	}

	public List<Trilha> getTrilhas() {

		List<Trilha> links = new ArrayList<Trilha>();

		for (WebElement webElement : driver.findElements(By.tagName("a"))) {
			if (webElement.getAttribute("href").contains("path")) {
				links.add(new Trilha(webElement.findElement(By.tagName("h3"))
						.getText(), webElement.getAttribute("href")));
			}
		}

		return links;

	}

	public List<Curso> getCursos(Trilha trilha) {
		driver.get(trilha.getLink());

		List<Curso> cursos = new ArrayList<Curso>();

		for (WebElement webElement : driver.findElements(By.tagName("a"))) {
			if (webElement.getAttribute("href").contains("course")) {
				cursos.add(new Curso(webElement.findElement(By.tagName("h3"))
						.getText(), webElement.getAttribute("href"), trilha));
			}
		}
		
		return cursos;
	}

	public List<Aula> getAulas(Curso curso) throws InterruptedException {
		driver.get(curso.getLink());
		Thread.sleep(2000);
		
		List<Aula> aulas = new ArrayList<Aula>();
		
		// clicar pra continuar/inscrever em curso
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for (WebElement ele : links) {
			if (ele.getAttribute("class").contains("buttonGo")) {
				ele.click();
				Thread.sleep(2000);
				break;
			}
		}
		
		// pegar o select
		List<WebElement> selects = driver.findElements(By.tagName("select"));
		
		for (WebElement select : selects) {
			if (select.getAttribute("class").contains("styledSelect")) {
				
				
				
				// percorrer options do select
				List<WebElement> options = select.findElements(By.tagName("option"));
				
				for (WebElement option : options) {
					aulas.add(new Aula(Integer.parseInt(option.getAttribute("value")), option.getText(), curso));
				}
			}
		}
		
		return aulas;
	}
	
	public void fechar() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {

		AluraPageDAO dao = new AluraPageDAO(JPAUtil.getEntityManager());

		AluraPage aluraPage = new AluraPage();

		aluraPage.conectar();

		/*List<Trilha> trilhas = aluraPage.getTrilhas();
		for (Trilha trilha : trilhas) {
			dao.salvar(trilha);
		}*/
		
		/*List<Trilha> trilhas = dao.getTrilhas();
		
		for (Trilha trilha : trilhas) {
			List<Curso> cursos = aluraPage.getCursos(trilha);
			
			for (Curso curso : cursos) {
				dao.salvar(curso);
			}
			
			trilha.setCapturado(true);
			dao.salvar(trilha);
		}*/
		
		// Detalhar os cursos e pegar os dados
		
		List<Curso> cursos = dao.getCursos();
		
		for (Curso curso : cursos) {
			List<Aula> aulas = aluraPage.getAulas(curso);
			
			for (Aula aula : aulas) {
				dao.salvar(aula);
			}
			
			curso.setCapturado(true);
			dao.salvar(curso);
		}

		aluraPage.fechar();

	}

}
