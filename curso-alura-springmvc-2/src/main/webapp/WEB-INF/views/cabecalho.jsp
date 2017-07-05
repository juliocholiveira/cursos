<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<header>
	<h1>
		<a href="<s:url value="/"/>">Cabeçalho</a>
	</h1>
	<div>
		<a href="${s:mvcUrl('carrinhoItens').build()}">Seu carrinho</a>
		(${carrinhoCompras.quantidade })

		<security:authorize access="isAuthenticated()">
			<span>Usuário: <security:authentication property="principal"
					var="usuario" />${usuario.nome }</span>
			<span><security:authentication property="principal.username"/></span>
			<div><a href="<c:url value="/logout"/>">Logout</a></div>
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<div><a href="<c:url value="/login"/>">Entrar</a></div>
		</security:authorize>

	</div>
</header>