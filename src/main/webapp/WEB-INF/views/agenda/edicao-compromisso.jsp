<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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

<style>
	label.error { color: #d9534f; }
	input.error, select.error, textarea.error { border: 1px solid #d9534f; }
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
				<br />
				
				<a href="/springMvc02/logout" onclick="return confirm('Deseja realmente sair do sistema?')">
					Sair do Sistema
				</a>
				
			</div>
		</div>

		<hr />
		
		<h5>Formulário para edição de compromissos</h5>
		Preencha os campos abaixo para atualizar um compromisso.
		<br/>
		<br/>
		
		<form id="formEdicao" autocomplete="off">
		
			<!-- Campo oculto -->
			<form:input path="compromisso.idCompromisso" type="hidden" name="idCompromisso" id="idCompromisso"/>
		
			<div class="row">
				<div class="col-md-6">
					<label>Nome do Compromisso:</label>
					<form:input path="compromisso.nome" type="text" name="nome" id="nome" class="form-control"/>
				</div>	
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-6">
							<label>Data do Compromisso:</label>
							<form:input path="compromisso.dataCompromisso" type="date" name="dataCompromisso" id="dataCompromisso" class="form-control"/>
						</div>
						<div class="col-md-6">
							<label>Hora do Compromisso:</label>
							<form:input path="compromisso.horaCompromisso" type="text" name="horaCompromisso" id="horaCompromisso" class="form-control"/>						
						</div>
					</div>
				</div>		
			</div>
			
			<div class="row mt-3">
				<div class="col-md-3">
					<label>Tipo do Compromisso</label>
					<form:select path="compromisso.tipo" name="tipo" id="tipo" class="form-select">
						<form:options items="${tipos}"/> 
					</form:select>
				</div>
				<div class="col-md-3">
					<label>Prioridade do Compromisso</label>
					<form:select path="compromisso.prioridade" name="prioridade" id="prioridade" class="form-select">
						<form:options items="${prioridades}"/>
					</form:select>
				</div>
			</div>
			
			<div class="row mt-3">
				<div class="col-md-12">
					<label>Descrição do Compromisso</label>
					<form:textarea path="compromisso.descricao" name="descricao" id="descricao" class="form-control"></form:textarea>
				</div>
			</div>
			
			<br/>
			
			<input type="submit" value="Atualizar Compromisso" class="btn btn-primary"/>
		
		</form>
		

	</div>

	<!-- Arquivos javascript -->
	<script src="resources/js/bootstrap.min.js"></script>
	
	<script src="resources/js/jquery-3.5.1.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	<script src="resources/js/jquery.maskedinput.min.js"></script>
	
	<script>
		$(document).ready(function(){
			
			//máscara no campo hora
			$("#horaCompromisso").mask("99:99");
			
			//validação do formulário
			$("#formEdicao").validate({
				rules : {
					'nome' : {
						required : true,
						minlength : 6,
						maxlength : 150
					},
					'dataCompromisso' : {
						required : true
					},
					'horaCompromisso' : {
						required : true
					},
					'tipo' : {
						required : true
					},
					'prioridade' : {
						required : true
					},
					'descricao' : {
						required : true,
						minlength : 6,
						maxlength : 250
					}
				}
			});			
		});
	</script>

</body>
</html>


