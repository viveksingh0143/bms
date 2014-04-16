<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<h1 class="page-header"><spring:message code="role.manage" /></h1>

<div class="table-responsive">
  <table class="table table-striped table-bordered table-hover">
    <thead>
    	<tr>
    		<th><spring:message code="label.serial"/></th>
    		<th><spring:message code="role.name.label"/></th>
    		<th><spring:message code="role.status.label"/></th>
    		<th></th>
    		<th></th>
    		<th></th>
    	</tr>
    </thead>
    <tbody>
		<c:if test="${not empty roles}">
			<c:forEach items="${roles}" var="role" varStatus="loop">
				<tr>
		    		<td>${loop.index + 1}</td>
		    		<td>${role.name}</td>
		    		<td>${role.status}</td>
		    		<td><a href="${base}/roles/${role.name}"><spring:message code="label.list.view"/></a></td>
		    		<td><a href="${base}/roles/edit/${role.name}"><spring:message code="label.list.edit"/></a></td>
		    		<td><a href="${base}/roles/delete/${role.name}"><spring:message code="label.list.delete"/></a></td>
    			</tr>
			</c:forEach>
		</c:if>      
    </tbody>
  </table>
</div>
<a href="${base}/roles/new" class="btn btn-primary"><spring:message code="role.create"/></a>