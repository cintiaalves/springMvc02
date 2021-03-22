<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:useBean id="data" class="java.util.Date" />

<fmt:formatDate var="dataAtual" value="${data}"
	pattern="dd 'de' MMMM 'de' yyyy" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- Folhas de estilo CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />

<link rel="stylesheet"
	href="//cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" />

<style>
label.error {
	color: #d9534f;
}

input.error {
	border: 1px solid #d9534f;
}
</style>

</head>
<body>

	<!-- MENU DO SISTEMA -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Agenda de Compromissos</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/springMvc02/home">Página inicial</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Compromissos </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="/springMvc02/cadastro-compromisso">Cadastrar
									Compromissos</a></li>
							<li><a class="dropdown-item"
								href="/springMvc02/consulta-compromisso">Consultar
									Compromissos</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item"
								href="/springMvc02/relatorio-compromisso">Relatório de
									Compromissos</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<c:if test="${not empty mensagem_sucesso}">

		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Sucesso!</strong> ${mensagem_sucesso}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>

	</c:if>

	<c:if test="${not empty mensagem_erro}">

		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>Erro!</strong> ${mensagem_erro}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>

	</c:if>

	<div class="container mt-4">

		<div class="row">
			<div class="col-md-6">
				<h5>Seja bem vindo à Agenda de Compromissos</h5>
				<p>${dataAtual}</p>
			</div>
			<div class="col-md-6 text-end">
				<strong>${usuario_autenticado.nome}</strong> <small>${usuario_autenticado.email}</small>
				<br /> <a href="/springMvc02/logout"
					onclick="return confirm('Deseja realmente sair do sistema?')">
					Sair do Sistema </a>

			</div>
		</div>

		<hr />

		<h5>Consulta de compromissos</h5>

		<form id="formConsulta" autocomplete="off"
			action="consultarCompromissos" method="post">

			<div class="row">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-6">
							<label>Data da Início:</label>
							<form:input path="consulta.dataInicio" type="date"
								name="dataInicio" id="dataInicio" class="form-control" />
						</div>
						<div class="col-md-6">
							<label>Data de Término:</label>
							<form:input path="consulta.dataFim" type="date" name="dataFim"
								id="dataFim" class="form-control" />
						</div>
					</div>
				</div>
			</div>

			<br /> <input type="submit" value="Consultar Compromissos"
				class="btn btn-success" />

		</form>

		<br />

		<table id="tabelaCompromissos" class="table table-hover table-striped">
			<thead>
				<tr>
					<th>Nome do Compromisso</th>
					<th>Date</th>
					<th>Hora</th>
					<th>Descrição</th>
					<th>Tipo</th>
					<th>Prioridade</th>
					<th width="180">Operações</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${listagem_compromissos}" var="item">
					<tr>
						<td>${item.nome}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.dataCompromisso}"/></td>
						<td>${item.horaCompromisso}</td>
						<td>${item.tipo}</td>
						<td>${item.prioridade}</td>
						<td>
						<a href="/springMvc02/edicao-compromisso?id=${item.idCompromisso}" class="btn btn-primary btn-sm">Editar</a> 
						<a href="/springMvc02/exclusao-compromisso?id=${item.idCompromisso}" onclick="return confirm('Deseja excluir o compromisso selecionado?');" class="btn btn-danger btn-sm">Excluir</a>
						</td>
					</tr>
				</c:forEach>


			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">Quantidade de compromissos:
						${listagem_compromissos.size()}</td>
				</tr>
			</tfoot>
		</table>


	</div>

	<!-- Arquivos javascript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<script src="resources/js/jquery-3.5.1.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	<script src="//cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>

	<script>
		$(document)
				.ready(
						function() {

							//aplicando a formatação do datatable
							$('#tabelaCompromissos')
									.DataTable(
											{
												language : {
													url : '//cdn.datatables.net/plug-ins/1.10.22/i18n/Portuguese-Brasil.json'
												}
											});

							//aplicando a validação do jquery-validate
							//validação do formulário
							$("#formConsulta").validate({
								rules : {
									'dataInicio' : {
										required : true
									},
									'dataFim' : {
										required : true
									}
								}
							});

						})
	</script>

</body>
</html>