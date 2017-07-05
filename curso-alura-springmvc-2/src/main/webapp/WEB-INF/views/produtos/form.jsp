<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tags:pageTemplate>

	
	<form:form action="${s:mvcUrl('produtoGravar').build() }" 
		method="post"
		commandName="produto">
		<div>
			<form:hidden path="id"/>
			<label>Título</label> 
			<form:input  path="titulo"/>
			<form:errors path="titulo"/>
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao"></form:textarea>
			<form:errors path="descricao"/>
		</div>
		<div>
			<label>Páginas</label> 
			<form:input path="paginas"/>
			<form:errors path="paginas"/>
		</div>

		<c:forEach var="tipoPreco" items="${tipos }" varStatus="status">
			<div>
				<label>${tipoPreco }</label> 
				<form:input path="precos[${status.index}].valor"/>
				<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco }"/>
				<form:errors path="precos[${status.index}].valor"/>
			</div>
		</c:forEach>
		
		<div>
			<label for="dataLancamento">Data de Lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento"/>
		</div>

		<button type="submit">Cadastrar</button>
	</form:form>

</tags:pageTemplate>