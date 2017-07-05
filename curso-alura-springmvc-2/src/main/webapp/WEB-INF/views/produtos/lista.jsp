<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<tags:pageTemplate>

	<h1>Lista de Produtos</h1>
	
	<security:authorize access="isAuthenticated()" ifAllGranted="ADMIN">
		<a href="${s:mvcUrl('produtoForm').build()}">Cadastro de Produtos</a>
	</security:authorize>
	
	<div>${mensagem }</div>

	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Páginas</td>
			<td>Data Lançamentos</td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach items="${produtos }" var="produto">
			<tr>
				<td>${produto.titulo }</td>
				<td>${produto.descricao }</td>
				<td>${produto.paginas }</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time }"/></td>
				<td><a href="${s:mvcUrl('produtoForm').build()}?id=${produto.id }">Alterar</a></td>
				<td><a href="${s:mvcUrl('produtoDetalhar').arg(0,produto.id).build()}">Detalhar <img src='<s:url value="/resources/Up.png"/>'></a></td>
			</tr>
		</c:forEach>
	</table>
</tags:pageTemplate>