<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tags:pageTemplate>

	<h1>${produto.titulo }</h1>

	<h2>${produto.descricao }</h2>

	<form action="${s:mvcUrl('carrinhoAdd').build() }">
	
		<input type="hidden" name="idProduto" value="${produto.id }">
	
		<c:forEach items="${produto.precos }" var="preco">
			<div>
				<input type="radio" name="tipoPreco" value="${preco.tipo }" /> <label>${preco.tipo }</label>
			</div>
		</c:forEach>
		
		<input type="submit" value="Adiciona no carrinho"/>

	</form>

</tags:pageTemplate>