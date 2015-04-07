<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
    <h3>Alterar Conta</h3>
    <form action="alteraContaComRedirect" method="post">
        Descrição: <br/>
        <textarea name="descricao" rows="5" cols="100">${conta.descricao}</textarea>
        <br/>
        Valor: <br/>
        <input type="text" name="valor" value="${conta.valor}" />
        Tipo: <br/>
        <select name="tipo">
            <option value="ENTRADA" ${conta.tipo=='ENTRADA' ? 'selected':''}>Entrada</option>
            <option value="SAIDA" ${conta.tipo=='SAIDA' ? 'selected':''}>Saída</option>
        </select>
        <br/>
        Pago? <input type="checkbox" name="paga"     ${conta.paga?'checked':''} />
        <br/>
        Data de Pagamento: <input type="text" name="dataPagamento" value="<fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy" />" />
        <br/>
        <input type="hidden" name="id" value="${conta.id}"/>
        <input type="submit" value="Alterar"/>
    </form>
</body>
</html>