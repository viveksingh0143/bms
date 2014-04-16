<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><spring:message
					code="nav.sitename" /></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><spring:message code="nav.dashboard" /></a></li>
				<li><a href="#"><spring:message code="nav.settings" /></a></li>
				<li><a href="#"><spring:message code="nav.profile" /></a></li>
				<li><a href="#"><spring:message code="nav.help" /></a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control"
					placeholder="<spring:message code="form.search"/>">
			</form>
		</div>
	</div>
</div>