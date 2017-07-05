<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate>

	<jsp:attribute name="extraScripts">
		<script>
			alert("ol�");
		</script>
	</jsp:attribute>

	<jsp:body>
	<h1>Casa do C�digo</h1>
	<table>
		<tr>
			<td><a href="${s:mvcUrl('produtoListar').build()}">Lista de Produtos</a></td>
		</tr>
	</table>
</jsp:body>

</tags:pageTemplate>