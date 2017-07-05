<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="bodyClass" required="false"%>
<%@ attribute name="extraScripts" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, PHP e muito mais.</title>
</head>
<body class="${bodyClass }">

	<%@include file="/WEB-INF/views/cabecalho.jsp"%>

	<jsp:doBody />

	<%@include file="/WEB-INF/views/rodape.jsp"%>
	
	<jsp:invoke fragment="extraScripts"/>
	
	<script type="text/javascript">
	
		//Aqui entra o código javascrip para todas as páginas
	
	</script>

</body>
</html>
