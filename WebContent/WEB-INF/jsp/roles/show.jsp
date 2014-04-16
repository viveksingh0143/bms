<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<spring:message code="role.name.label" var="nameLabel" />
<spring:message code="role.name.placeholder" var="namePlaceholder" />
<spring:message code="role.status.label" var="statusLabel" />
<spring:message code="role.status.placeholder" var="statusPlaceholder" />

<h1 class="page-header"><spring:message code="role.details" /></h1>

<div class="row">
	<div class="xs-col-12 sm-col-12">
		<h4 class="page-header">Role Information</h4>
		<dl class="dl-horizontal">
			<dt><spring:message code="role.name.label" /></dt><dd>${role.name}</dd>
			<dt><spring:message code="role.status.label" /></dt><dd>${role.status}</dd>
		</dl>
	</div>
</div>
<div class="row">
	<div class="xs-col-12 sm-col-12">
		<a href="${base}/roles/edit/${role.name}" class="btn btn-primary"><spring:message code="label.list.edit"/></a>
		<a href="${base}/roles/delete/${role.name}" class="btn btn-primary"><spring:message code="label.list.delete"/></a>
		<a href="${base}/roles/new" class="btn btn-primary"><spring:message code="role.create"/></a>
		<a href="${base}/roles" class="btn btn-primary"><spring:message code="role.list"/></a>
	</div>
</div>