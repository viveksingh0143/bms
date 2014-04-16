<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<spring:message code="user.username.label" var="usernameLabel" />
<spring:message code="user.username.placeholder" var="usernamePlaceholder" />
<spring:message code="user.email.label" var="emailLabel" />
<spring:message code="user.email.placeholder" var="emailPlaceholder" />
<spring:message code="user.name.label" var="nameLabel" />
<spring:message code="user.name.placeholder" var="namePlaceholder" />
<spring:message code="user.password.label" var="passwordLabel" />
<spring:message code="user.password.placeholder" var="passwordPlaceholder" />
<spring:message code="user.password_confirm.label" var="confirmPasswordLabel" />
<spring:message code="user.password_confirm.placeholder" var="confirmPasswordPlaceholder" />
<spring:message code="user.status.label" var="statusLabel" />
<spring:message code="user.status.placeholder" var="statusPlaceholder" />
<spring:message code="user.admin.label" var="adminLabel" />
<spring:message code="user.admin.placeholder" var="adminPlaceholder" />

<h1 class="page-header"><spring:message code="user.details" /></h1>

<div class="row">
	<div class="xs-col-12 sm-col-12">
		<h4 class="page-header">Profile Information</h4>
		<dl class="dl-horizontal">
			<dt><spring:message code="user.username.label" /></dt><dd>${user.username}</dd>
			<dt><spring:message code="user.email.label" /></dt><dd>${user.email}</dd>
			<dt><spring:message code="user.name.label" /></dt><dd>${user.name}</dd>
			<dt><spring:message code="user.status.label" /></dt><dd>${user.status}</dd>
			<dt><spring:message code="user.admin.label" /></dt><dd>${user.admin}</dd>
		</dl>
	</div>
</div>
<div class="row">
	<div class="xs-col-12 sm-col-12">
		<a href="${base}/users/edit/${user.username}" class="btn btn-primary"><spring:message code="label.list.edit"/></a>
		<a href="${base}/users/delete/${user.username}" class="btn btn-primary"><spring:message code="label.list.delete"/></a>
		<a href="${base}/users/new" class="btn btn-primary"><spring:message code="user.create"/></a>
		<a href="${base}/users" class="btn btn-primary"><spring:message code="user.list"/></a>
	</div>
</div>