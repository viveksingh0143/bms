<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<footer class="row bottom-most-footer">
	<div class="col-sm-12">
		<p class="copyright text-center">
			<spring:message code="footer.copyright" />
		</p>
	</div>
	<div class="col-sm-12  text-center">
		<a href="?lang=en">English</a> 
		| 
		<a href="?lang=hn">हिंदी</a>
		
		||||
									
		<a href="?theme=default">Default</a> 
		| 
		<a href="?theme=blue">Blue</a>
	</div>
</footer>