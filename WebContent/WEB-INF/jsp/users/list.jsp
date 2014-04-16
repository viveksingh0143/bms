<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<h1 class="page-header"><spring:message code="user.manage" /></h1>

<div class="table-responsive">
  <table class="table table-striped table-bordered table-hover">
    <thead>
    	<tr>
    		<th><spring:message code="label.serial"/></th>
    		<th><spring:message code="user.username.label"/></th>
    		<th><spring:message code="user.email.label"/></th>
    		<th><spring:message code="user.name.label"/></th>
    		<th><spring:message code="user.admin.label"/></th>
    		<th><spring:message code="user.status.label"/></th>
    		<th></th>
    		<th></th>
    		<th></th>
    	</tr>
    </thead>
    <tbody>
		<c:if test="${not empty users}">
			<c:forEach items="${users}" var="user" varStatus="loop">
				<tr>
		    		<td>${loop.index + 1}</td>
		    		<td>${user.username}</td>
		    		<td>${user.email}</td>
		    		<td>${user.name}</td>
		    		<td>${user.admin}</td>
		    		<td>${user.status}</td>
		    		<td><a href="${base}/users/${user.username}"><spring:message code="label.list.view"/></a></td>
		    		<td><a href="${base}/users/edit/${user.username}"><spring:message code="label.list.edit"/></a></td>
		    		<td><a href="${base}/users/delete/${user.username}"><spring:message code="label.list.delete"/></a></td>
    			</tr>
			</c:forEach>
		</c:if>      
    </tbody>
  </table>
</div>
<a href="${base}/users/new" class="btn btn-primary"><spring:message code="user.create"/></a>