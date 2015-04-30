<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Adicionar contas</h3>
	<spring:message code="conta.adicionada.com.sucesso"/>
	<form action="adicionaConta" method="post">
		<form:errors path="conta.descricao" cssClass="error"/>
		Descrição: <br/>
		<textarea name="descricao" rows="5" cols="100"></textarea>
		<br/>
		Valor: 
		<input type="text" name="valor"><br/>
		Tipo:
		<select name="tipo">
			<option value="ENTRADA">Entrada</option>
			<option value="SAIDA">Saída</option>
		</select><br/><br/>
		Tipo:
		<select name="paga">
			<option value="true">Sim</option>
			<option value="false">Não</option>
		</select><br/><br/>
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>