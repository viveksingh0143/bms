<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<spring:message code="role.name.label" var="nameLabel" />
<spring:message code="role.name.placeholder" var="namePlaceholder" />
<spring:message code="role.status.label" var="statusLabel" />
<spring:message code="role.status.placeholder" var="statusPlaceholder" />
<spring:message code="role.permissions.label" var="permissionsLabel" />

<h1 class="page-header"><spring:message code="role.manage" /></h1>

<form:form commandName="roleUpdateForm" method="post" action="${base}/roles/${roleUpdateForm.name}" class="form-horizontal" role="form">
	<t:errors/>
	<form:hidden path="id" />
	<t:input path="name" required="true" label="${nameLabel}" placeholder="${namePlaceholder}" labelcol="3" />
	<t:select path="status" required="true" items="${rolesstatus}" label="${statusLabel}" placeholder="${statusPlaceholder}" labelcol="3" />
	<t:checkboxes path="permissions_id" required="false" items="${permissions}" itemLabel="name" itemValue="id" label="${permissionsLabel}" labelcol="3" />
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<input type="submit" class="btn btn-default" value="<spring:message code="form.update"/>" />
		</div>
	</div>
</form:form>

<a href="${base}/roles" class="btn btn-primary"><spring:message code="role.list"/></a>