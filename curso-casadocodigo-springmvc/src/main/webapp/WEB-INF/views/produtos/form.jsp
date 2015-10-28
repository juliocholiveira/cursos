<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cadastro de Produtos</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>

	<!-- 
	<spring:hasBindErrors name="product">
		<ul>
			<c:forEach items="${errors.allErrors}" var="error">
				<li><spring:message code="${error.code}"
						text="${error.defaultMessage}"></spring:message></li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
 -->

	<!-- A vantagem é que, caso você queira mudar a URL do seu controller, 
		 você não precisará mudar os links da sua página. -->
	<form:form 
		action='${spring:mvcUrl("saveProduct").build()}'
		method="post" 
		commandName="product"
		enctype="multipart/form-data">

		<div>
			<label for="title">Titulo</label>
			<form:input path="title" />
			<form:errors path="title" cssClass="error" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea rows="10" cols="20" path="description" />
			<form:errors path="description" cssClass="error" />
		</div>
		<div>
			<label for="pages">Número de paginas</label>
			<form:input path="pages" />
			<form:errors path="pages" cssClass="error" />
		</div>
		<div>
			<label for="releaseDate">Data de lançamento</label> 
			<form:input path="releaseDate" type="date" />
			<form:errors path="releaseDate" cssClass="error" />
		</div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<form:input path="prices[${status.index}].value" />
				<form:errors path="prices[${status.index}].value" cssClass="error" />
				<input type="hidden" name="prices[${status.index}].bookType"
					value="${bookType}" />
			</div>
		</c:forEach>
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>
</body>
</html>