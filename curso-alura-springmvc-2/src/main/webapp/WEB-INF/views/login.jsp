<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tags:pageTemplate>

	<h1>Minha p�gina de Login</h1>
	
	<c:if test="${param.error != null}">
		Usu�rio ou senha inv�lidos.
	</c:if>
	
	<c:if test="${param.logout != null}">
		Sess�o finalizada.
	</c:if>

	<c:if test="${param.expired != null}">
		Sess�o expirou.
	</c:if>
	
	<form:form servletRelativeAction="/login">
		<div>
			<label> Usu�rio <input type='text' name='username' value=''>
			</label>
		</div>
		<div>
			<label>Senha <input type='password' name='password' />
			</label>
		</div>
		<div>
			<input name="submit" type="submit" value="Entrar" />
		</div>
	</form:form>

</tags:pageTemplate>