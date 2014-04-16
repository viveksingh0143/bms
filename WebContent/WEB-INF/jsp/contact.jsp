<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<h1 class="page-header">Dashboard</h1>
<div class="row placeholders">
	<div class="col-xs-6 col-sm-3 placeholder">
		<img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
		<h4>Label</h4>
		<span class="text-muted">Something else</span>
	</div>
	<div class="col-xs-6 col-sm-3 placeholder">
		<img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
		<h4>Label</h4>
		<span class="text-muted">Something else</span>
	</div>
	<div class="col-xs-6 col-sm-3 placeholder">
		<img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
		<h4>Label</h4>
		<span class="text-muted">Something else</span>
	</div>
	<div class="col-xs-6 col-sm-3 placeholder">
		<img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
		<h4>Label</h4>
		<span class="text-muted">Something else</span>
	</div>
</div>

<h2 class="sub-header">Section title</h2>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Header</th>
				<th>Header</th>
				<th>Header</th>
				<th>Header</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</div>




<form:form method="post" action="contacts/addContact.html">
	<table>
		<tr>
			<td><form:label path="firstname">
					<spring:message code="label.firstname" />
				</form:label></td>
			<td><form:input path="firstname" /></td>
		</tr>
		<tr>
			<td><form:label path="lastname">
					<spring:message code="label.lastname" />
				</form:label></td>
			<td><form:input path="lastname" /></td>
		</tr>
		<tr>
			<td><form:label path="lastname">
					<spring:message code="label.email" />
				</form:label></td>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<td><form:label path="lastname">
					<spring:message code="label.telephone" />
				</form:label></td>
			<td><form:input path="telephone" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.addcontact"/>" /></td>
		</tr>
	</table>
</form:form>