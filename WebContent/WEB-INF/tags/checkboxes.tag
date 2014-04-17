<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="items" required="true" type="java.util.List"%>
<%@attribute name="itemLabel" required="false" type="java.lang.String"%>
<%@attribute name="itemValue" required="false" type="java.lang.String"%>
<%@attribute name="cssClass" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="placeholder" required="false" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@attribute name="labelcol" required="false" type="java.lang.Integer"%>

<c:if test="${empty label}">
    <c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:toLowerCase(fn:substring(path, 1,fn:length(path)))}" />
</c:if>
<c:if test="${empty labelcol}">
    <c:set var="labelcol" value="2" />
</c:if>


<spring:bind path="${path}">
	<div class="form-group${status.error ? ' error' : '' }">
		<form:label path="${path}" class="col-sm-${labelcol} control-label">${label}<c:if test="${required}"><span class="required">*</span></c:if></form:label>
		<div class="col-sm-4">
			<form:checkboxes element="div" items="${items}" itemLabel="${itemLabel}" itemValue="${itemValue}" path="${path}" cssClass="${empty cssClass ? 'inline-checkbox' : cssClass}" />
	    </div>
	    <div class="col-sm-${8-labelcol}">
	    	<c:if test="${status.error}">
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
	    </div>
	</div>
</spring:bind>