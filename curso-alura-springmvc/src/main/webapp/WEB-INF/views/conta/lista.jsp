<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="resources/js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="resources/js/scriptConta.js"></script>
<body>
	<a href="logout">Clique aqui </a>para fazer o logout.
	<table style="height: 10px; width: 775px;" border="1">
		<thead>
			<tr>
				<th>C�digo</th>
				<th>Descri��o</th>
				<th>Valor</th>
				<th>Tipo</th>
				<th>Paga?</th>
				<th>Data de Pagamento</th>
				<th>A��o</th>
			</tr>
		</thead>
		
		<c:forEach items="${contas}" var="conta">
			<tr>
				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor }</td>
				<td>${conta.tipo }</td>
				<td>
					<c:if test="${conta.paga eq false }">N�o Paga</c:if>
					<c:if test="${conta.paga eq true }">Paga</c:if>
				</td>
				<td><fmt:formatDate value="${conta.dataPagamento.time }" pattern="dd/MM/yyyy"/></td>
				<td><a href="removeConta?id=${conta.id }">Remover</a></td>
				<td><a href="mostraConta?id=${conta.id }">Mostrar</a></td>
				<td id="conta_${conta.id }">
					<c:if test="${conta.paga eq false }"><a href="#" onclick="pagaAgoraPost(${conta.id });">Pagar</a></c:if>
					<c:if test="${conta.paga eq true }">Paga</c:if>				
				</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>