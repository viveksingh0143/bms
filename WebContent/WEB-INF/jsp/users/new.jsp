<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
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
<spring:message code="user.roles.label" var="rolesLabel" />

<h1 class="page-header"><spring:message code="user.manage" /></h1>

<form:form commandName="userCreateForm" method="post" action="${base}/users/" class="form-horizontal" role="form">
	<t:errors/>
	<t:input path="username" required="true" label="${usernameLabel}" placeholder="${usernamePlaceholder}" labelcol="3" />
	<t:input path="email" required="true" label="${emailLabel}" placeholder="${emailPlaceholder}" labelcol="3" />
	<t:input path="name" required="true" label="${nameLabel}" placeholder="${namePlaceholder}" labelcol="3" />
	<t:password path="password" required="true" label="${passwordLabel}" placeholder="${passwordPlaceholder}" labelcol="3" />
	<t:password path="confirmPassword" required="true" label="${confirmPasswordLabel}" placeholder="${confirmPasswordPlaceholder}" labelcol="3" />
	<t:select path="status" required="true" items="${usersstatus}" label="${statusLabel}" placeholder="${statusPlaceholder}" labelcol="3" />
	<t:select path="admin" required="true" items="${userstype}" label="${adminLabel}" placeholder="${adminPlaceholder}" labelcol="3" />
	<t:checkboxes path="roles_id" required="false" items="${roles}" itemLabel="name" itemValue="id" label="${rolesLabel}" labelcol="3" />
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<input type="submit" class="btn btn-default" value="<spring:message code="form.create"/>" />
		</div>
	</div>
</form:form>

<a href="${base}/users" class="btn btn-primary"><spring:message code="user.list"/></a>