<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<!-- Folhas de estilo CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />

</head>
<body class="bg-secondary">

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-4 offset-md-4 text-center">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Criar conta de usuário</h5>
						<h6 class="card-subtitle mb-2 text-muted">Preencha os campos
							abaixo</h6>

						<hr />

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

						<form id="formRegister" action="registerUser" method="post">

							<label>Nome do usuário:</label>
							<form:input path="register.nome" type="text" id="nome"
								name="nome" class="form-control" placeholder="Ex: João da Silva"
								autocomplete="off" />
							<br /> <label>Email de acesso:</label>
							<form:input path="register.email" type="text" id="email"
								name="email" class="form-control"
								placeholder="Ex: joaosilva@gmail.com" autocomplete="off" />
							<br /> <label>Senha de acesso:</label>
							<form:input path="register.senha" type="password" id="senha"
								name="senha" class="form-control" placeholder="Digite aqui"
								autocomplete="off" />
							<br /> <label>Confirme sua senha:</label>
							<form:input path="register.senhaConfirmacao" type="password"
								id="senhaConfirmacao" name="senhaConfirmacao"
								class="form-control" placeholder="Digite aqui"
								autocomplete="off" />
							<br />

							<div class="d-grid">
								<input type="submit" value="Cadastrar Usuário"
									class="btn btn-success" />
							</div>

						</form>

						<hr />

						<a href="/springMvc02/">Acesso ao Sistema</a>

					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Arquivos java -->
	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>