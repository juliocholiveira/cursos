<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tags:pageTemplate>

	<h1>Lista de Produtos do Carrinho</h1>

	<div>${sucesso }</div>


	<table>
		<thead>
			<tr>
				<td>Título</td>
				<td>Descrição</td>
				<td>Preço</td>
				<td>Qtde</td>
				<td>Total</td>
				<td></td>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${carrinhoCompras.itens }" var="item">
				<tr>
					<td>${item.produto.titulo }</td>
					<td>${item.produto.descricao }</td>
					<td>${item.preco }</td>
					<td><input type="number" min="1" name="quantidade"
						value="${item.quantidade }"></td>
					<td>${item.total }</td>
					<td>
						<form
							action="${s:mvcUrl('CC#remover').arg(0,item.produto.id).arg(1,item.tipoPreco).build() }"
							method="post">
							<input type="submit" value="Excluir" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>

		<tfoot>
			<tr>
				<td colspan="3">
					<form action="${s:mvcUrl('PC#finalizar').build() }" method="post">
						<input type="submit" value="Finalizar Compra" />
					</form>
				</td>
				<td>${carrinhoCompras.quantidade }</td>
				<td>${carrinhoCompras.total }</td>
			</tr>
		</tfoot>

	</table>

</tags:pageTemplate>