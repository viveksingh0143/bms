<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="type" required="false" type="java.lang.String"%> <% /* success, info, warning, danger */%>
<c:if test="${empty type}">
    <c:set var="type" value="danger" />
</c:if>

<spring:bind path="*">
  <c:if test="${not empty status.errorMessages}">
    <div class="alert alert-${type} alert-dismissable">
  	  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  	  <h4 class="errors-head">List of errors</h4>
	  <c:forEach items="${status.errorMessages}" var="error" varStatus="loop">
	    <div class="errors-line">${loop.index + 1}. ${error}</div>
	  </c:forEach>
    </div>
  </c:if>      
</spring:bind>
